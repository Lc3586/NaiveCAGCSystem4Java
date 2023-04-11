package top.lctr.naive.cagc.system.business.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.extension.cryptography.MD5Utils;
import project.extension.mybatis.edge.core.provider.standard.IDbFirst;
import project.extension.mybatis.edge.core.provider.standard.INaiveSql;
import project.extension.mybatis.edge.core.provider.standard.curd.ISelect;
import project.extension.mybatis.edge.dbContext.repository.IBaseRepository_Key;
import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.mybatis.edge.extention.datasearch.DataSearchExtension;
import project.extension.mybatis.edge.model.DbColumnInfo;
import project.extension.mybatis.edge.model.DbTableInfo;
import project.extension.mybatis.edge.model.FilterCompare;
import project.extension.mybatis.edge.model.NullResultException;
import project.extension.standard.api.request.datasort.DataSortDTO;
import project.extension.standard.api.request.datasort.DragSortDTO;
import project.extension.standard.api.request.datasort.SortMethod;
import project.extension.standard.entity.IEntityExtension;
import project.extension.standard.exception.BusinessException;
import project.extension.string.StringExtension;
import project.extension.tuple.Tuple2;
import project.extension.tuple.Tuple3;
import top.lctr.naive.cagc.system.business.service.Interface.IColumnService;
import top.lctr.naive.cagc.system.dto.columnDTO.*;
import top.lctr.naive.cagc.system.entity.CagcColumn;
import top.lctr.naive.cagc.system.entityFields.CC_Fields;

import java.util.*;

/**
 * 代码自动生成-数据库列服务实现类
 *
 * @author LCTR
 * @date 2022-06-15
 */
@Service
@Scope("prototype")
public class ColumnServiceImpl
        implements IColumnService {
    public ColumnServiceImpl(INaiveSql orm,
                             IEntityExtension entityExtension) {
        this.orm = orm;
        this.repository_Key = orm.getRepository_Key(CagcColumn.class,
                                                    String.class);
        this.entityExtension = entityExtension;
        this.tableKeyAliasMap = new HashMap<>();
        this.tableKeyAliasMap.put(defaultTableKey,
                                  "a");
    }

    /**
     * 日志组件
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final INaiveSql orm;

    private final IBaseRepository_Key<CagcColumn, String> repository_Key;

    private final Map<String, String> tableKeyAliasMap;

    private final String defaultTableKey = "main";

    private final IEntityExtension entityExtension;

    /**
     * 获取包含子查询的sql语句
     *
     * @param withTableName 查询表名
     * @param withCEName    查询常量/枚举名
     * @return sql语句
     */
    private String getWithSql(boolean withTableName,
                              boolean withCEName) {
        return String.format("select * %s %s from \"CAGC_COLUMN\" as a",
                             withTableName
                             ? "\r\n, (select b.\"NAME\" from \"CAGC_TABLE\" as b where b.\"ID\" = a.\"TABLE_ID\") as \"TABLE_NAME\" "
                             : "",
                             withCEName
                             ? "\r\n, (select c.\"NAME\" from \"CAGC_CONST_ENUM\" as c where c.\"ID\" = a.\"C_E_ID\") as \"C_E_NAME\" "
                             : "");
    }

    @Override
    public List<C_List> list(DataSearchDTO dataSearch)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .as(tableKeyAliasMap.get(defaultTableKey))
                                 .withSql(getWithSql(true,
                                                     true))
                                 .where(x -> x.and(DataSearchExtension.toDynamicFilter(dataSearch.getFilters(),
                                                                                       tableKeyAliasMap)))
                                 .orderBy(x -> dataSearch.getOrder() == null
                                               ? x.orderBy(CC_Fields.createTime)
                                               : x.orderBy(DataSearchExtension.toDynamicOrder(dataSearch.getOrder(),
                                                                                              tableKeyAliasMap)))
                                 .pagination(dataSearch.getPagination())
                                 .mainTagLevel(1)
                                 .toList(C_List.class);
        } catch (Throwable ex) {
            throw new BusinessException("查询数据失败",
                                        ex);
        }
    }

    @Override
    public C_Detail detail(String id)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .withSql(getWithSql(true,
                                                     true))
                                 .where(x -> x.and(CC_Fields.id,
                                                   FilterCompare.Eq,
                                                   id))
                                 .mainTagLevel(1)
                                 .first(C_Detail.class);
        } catch (NullResultException ex) {
            throw new BusinessException(ex.getMessage());
        } catch (Throwable ex) {
            throw new BusinessException("获取详情数据失败",
                                        ex);
        }
    }

    @Override
    public void sync(DbTableInfo tableInfo,
                     boolean withTransactional)
            throws
            BusinessException {
        try {
            List<C_Create> createList = new ArrayList<>();

            //外键 <列名, (外键名, 关联表名, 关联列名)>
            Map<String, Tuple3<String, String, String>> foreigns = new HashMap<>();
            tableInfo.getForeignsDict()
                     .forEach((k, v) -> foreigns.put(v.getColumns()
                                                      .get(0)
                                                      .getName(),
                                                     new Tuple3<>(k,
                                                                  v.getTable()
                                                                   .getName(),
                                                                  v.getReferencedColumns()
                                                                   .get(0)
                                                                   .getName())));

            //索引 <列名, (索引名, 降序)>
            Map<String, Tuple2<String, Boolean>> indexes = new HashMap<>();
            tableInfo.getIndexesDict()
                     .forEach((k, v) -> v.getColumns()
                                         .forEach(c -> indexes.put(c.getColumn()
                                                                    .getName(),
                                                                   new Tuple2<>(k,
                                                                                c.isDesc()))));

            //唯一键 <列名, (唯一键名, 降序)>
            Map<String, Tuple2<String, Boolean>> uniques = new HashMap<>();
            tableInfo.getUniquesDict()
                     .forEach((k, v) -> v.getColumns()
                                         .forEach(c -> uniques.put(c.getColumn()
                                                                    .getName(),
                                                                   new Tuple2<>(k,
                                                                                c.isDesc()))));

            //不属于列表功能的字段
            List<String> listDisableFields = Arrays.asList("remark",
                                                           "createBy",
                                                           "createTime",
                                                           "updateBy",
                                                           "updateTime");
            //不属于新增功能的字段
            List<String> createDisableFields = Arrays.asList("id",
                                                             "rootId",
                                                             "parentId",
                                                             "sort",
                                                             "level",
                                                             "createBy",
                                                             "createTime",
                                                             "updateBy",
                                                             "updateTime");
            //不属于编辑功能的字段
            List<String> editDisableFields = Arrays.asList("rootId",
                                                           "parentId",
                                                           "sort",
                                                           "level",
                                                           "createBy",
                                                           "createTime",
                                                           "updateBy",
                                                           "updateTime");
            //编辑功能的特殊字段，需要额外添加_Edit标签
            List<String> editSpecialFields = Arrays.asList("updateBy",
                                                           "updateTime");

            for (DbColumnInfo columnInfo : tableInfo.getColumns()) {
                C_Create data = new C_Create();
                data.setTableId(MD5Utils.hash(tableInfo.getName())
                                        .toUpperCase(Locale.ROOT));
                data.setName(columnInfo.getName());
                data.setTitle(columnInfo.getComment());
//                data.setDescription(columnInfo.getComment());
                data.setPk(columnInfo.isPrimary());

                //外键
                if (foreigns.containsKey(columnInfo.getName())) {
                    data.setFk(true);
                    Tuple3<String, String, String> fk_info = foreigns.get(columnInfo.getName());
                    data.setFkName(fk_info.a);
                    data.setFkTableId(MD5Utils.hash(fk_info.b)
                                              .toUpperCase(Locale.ROOT));
                    data.setFkColumnId(MD5Utils.hash(String.format("%s%s",
                                                                   fk_info.b,
                                                                   fk_info.c))
                                               .toUpperCase(Locale.ROOT));
                } else data.setFk(false);

                //索引
                if (indexes.containsKey(columnInfo.getName())) {
                    data.setIndex(true);
                    Tuple2<String, Boolean> index_info = indexes.get(columnInfo.getName());
                    data.setIndexName(index_info.a);
                    data.setIndexDesc(index_info.b);
                } else data.setIndex(false);

                //唯一键
                if (uniques.containsKey(columnInfo.getName())) {
                    data.setUnique(true);
                    Tuple2<String, Boolean> unique_info = uniques.get(columnInfo.getName());
                    data.setUniqueName(unique_info.a);
                    data.setUniqueDesc(unique_info.b);
                } else data.setUnique(false);

                data.setColumnSort(columnInfo.getPosition());
                data.setDbType(columnInfo.getDbTypeTextFull());
                data.setMaxLength(columnInfo.getMaxLength());
                data.setPrecision(columnInfo.getPrecision());
                data.setScale(columnInfo.getScale());

                IDbFirst dbFirst = orm.getDbFirst();
                data.setJavaField(StringExtension.firstChar2LowerCase(StringExtension.underscoreToPascalCase(columnInfo.getName())));
                data.setJavaType(dbFirst.getJavaType(columnInfo));
                data.setJavaPackageType(dbFirst.getJavaPackageType(columnInfo));
                data.setJavaTypeConvert(dbFirst.getJavaTypeConvert(columnInfo));
                data.setJavaPackageTypeConvert(dbFirst.getJavaTypeConvert(columnInfo));
                data.setJavaParse(dbFirst.getJavaParse(columnInfo));
                data.setJavaStringify(dbFirst.getJavaStringify(columnInfo));

                switch (data.getJavaType()) {
                    case "boolean":
                        data.setTsType("boolean");
                        break;
                    case "byte":
                    case "short":
                    case "int":
                    case "float":
                    case "double":
                    case "BigDecimal":
                        data.setTsType("number");
                        break;
                    case "byte[]":
                        data.setTsType("number[]");
                        break;
                    case "java.sql.Date":
                    case "java.sql.Time":
                    case "Date":
                        data.setTsType("Date");
                        break;
                    default:
                    case "long":
                    case "String":
                        data.setTsType("string");
                        break;
                }

                data.setRequired(!columnInfo.isNullable());
                data.setQuery(false);
                data.setSplit(false);
                data.setList(listDisableFields.stream()
                                              .noneMatch(x -> x.equalsIgnoreCase(data.getJavaField())));
                data.setDetail(true);
                data.setCreate(createDisableFields.stream()
                                                  .noneMatch(x -> x.equalsIgnoreCase(data.getJavaField())));
                data.setEdit(editDisableFields.stream()
                                              .noneMatch(x -> x.equalsIgnoreCase(data.getJavaField())));
                if (editSpecialFields.stream()
                                     .anyMatch(x -> x.equalsIgnoreCase(data.getJavaField()))) data.setTags("_Edit");

                data.setEnable(data.getJavaField()
                                   .equalsIgnoreCase("enable"));
                data.setLock(data.getJavaField()
                                 .equalsIgnoreCase("lock"));
                data.setSort(data.getJavaField()
                                 .equalsIgnoreCase("sort"));
                data.setTreeLevel(data.getJavaField()
                                      .equalsIgnoreCase("level"));
                data.setTreeParentId(data.getJavaField()
                                         .equalsIgnoreCase("parentId"));
                data.setTreeRootId(data.getJavaField()
                                       .equalsIgnoreCase("rootId"));
                data.setImport_(false);
                data.setExport(false);

                entityExtension.initialization(data)
                               .setId(MD5Utils.hash(String.format("%s%s",
                                                                  tableInfo.getName(),
                                                                  columnInfo.getName()))
                                              .toUpperCase(Locale.ROOT));
                createList.add(data);
            }

            if (createList.size() > 0) repository_Key.batchInsert(createList,
                                                                  C_Create.class,
                                                                  1);
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException("同步数据失败",
                                        ex);
        }
    }

    @Override
    public void create(C_Create data)
            throws
            BusinessException {
        try {
            repository_Key.insert(entityExtension.initialization(data),
                                  C_Create.class,
                                  1);
        } catch (Exception ex) {
            throw new BusinessException("新增数据失败",
                                        ex);
        }
    }

    @Override
    public C_Edit edit(String id)
            throws
            BusinessException {
        try {
            return repository_Key.getByIdAndCheckNull(id,
                                                      C_Edit.class,
                                                      1);
        } catch (Exception ex) {
            throw new BusinessException("获取编辑数据失败",
                                        ex);
        }
    }

    @Override
    public void edit(C_Edit data)
            throws
            BusinessException {
        try {
            repository_Key.update(entityExtension.modify(data),
                                  C_Edit.class,
                                  1);
        } catch (Exception ex) {
            throw new BusinessException("编辑数据失败",
                                        ex);
        }
    }

    @Override
    @Transactional
    public void delete(Collection<String> ids)
            throws
            BusinessException {
        try {
            repository_Key.deleteByIds(ids);
        } catch (Exception ex) {
            throw new BusinessException("删除数据失败",
                                        ex);
        }
    }

    @Override
    public void delete(Collection<String> tableIds,
                       boolean withTransactional)
            throws
            BusinessException {
        try {
            repository_Key.deleteDiy()
                          .where(x -> x.and(CC_Fields.tableId,
                                            FilterCompare.InSet,
                                            tableIds))
                          .executeAffrows();
        } catch (Exception ex) {
            throw new BusinessException("删除数据失败",
                                        ex);
        }
    }

    @Override
    public List<C_Detail> columnList(String tableId)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .withSql(getWithSql(true,
                                                     true))
                                 .where(x -> x.and(CC_Fields.tableId,
                                                   FilterCompare.Eq,
                                                   tableId))
                                 .orderBy(x -> x.orderBy(CC_Fields.columnSort))
                                 .toList(C_Detail.class);
        } catch (Exception ex) {
            throw new BusinessException("获取列信息集合失败",
                                        ex);
        }
    }

    @Override
    public void enable(String id,
                       Boolean enable)
            throws
            BusinessException {
        try {
            if (repository_Key.updateDiy()
                              .set(CC_Fields.enable,
                                   enable)
                              .where(x -> x.and(CC_Fields.id,
                                                FilterCompare.Eq,
                                                id))
                              .executeAffrows() < 0) throw new Exception("数据库受影响行数有误");
        } catch (Exception ex) {
            throw new BusinessException(String.format("%s失败",
                                                      enable
                                                      ? "启用"
                                                      : "禁用"),
                                        ex);
        }
    }

    @Override
    public void lock(String id,
                     Boolean locked)
            throws
            BusinessException {
        try {
            if (repository_Key.updateDiy()
                              .set(CC_Fields.lock,
                                   locked)
                              .where(x -> x.and(CC_Fields.id,
                                                FilterCompare.Eq,
                                                id))
                              .executeAffrows() < 0) throw new Exception("数据库受影响行数有误");
        } catch (Exception ex) {
            throw new BusinessException(String.format("%s失败",
                                                      locked
                                                      ? "锁定"
                                                      : "解锁"),
                                        ex);
        }
    }

    @Override
    @Transactional
    public void sort(DataSortDTO<String> data)
            throws
            BusinessException {
        try {
            //跨度为0并且也不是置顶和置底操作，判定为无意义的操作
            if (data.getSpan() == 0 && (data.getMethod() != SortMethod.TOP || data.getMethod() != SortMethod.LOW))
                return;

            Map<String, Object> current = repository_Key.select()
                                                        .columns(CC_Fields.id,
                                                                 CC_Fields.tableId,
                                                                 CC_Fields.columnSort)
                                                        .where(x -> x.and(CC_Fields.id,
                                                                          FilterCompare.Eq,
                                                                          data.getId()))
                                                        .firstMap();

            if (current == null || current.size() == 0) throw new BusinessException("数据不存在或已被移除");

            ISelect<CagcColumn> targetSelect = repository_Key.select()
                                                             .columns(CC_Fields.id,
                                                                      CC_Fields.columnSort)
                                                             .where(x -> x.and(CC_Fields.tableId,
                                                                               FilterCompare.Eq,
                                                                               orm.getMapValueByFieldName(current,
                                                                                                          CC_Fields.tableId)));
            Map<String, Object> target;

            switch (data.getMethod()) {
                case TOP:
                    target = targetSelect.orderBy(x -> x.orderBy(CC_Fields.columnSort))
                                         .firstMap();
                    break;
                case UP:
                    target = targetSelect.where(x -> x.and(CC_Fields.columnSort,
                                                           FilterCompare.Lt,
                                                           orm.getMapValueByFieldName(current,
                                                                                      CC_Fields.columnSort)))
                                         .orderBy(x -> x.orderByDescending(CC_Fields.columnSort))
                                         .firstMap();
                    break;
                case DOWN:
                    target = targetSelect.where(x -> x.and(CC_Fields.columnSort,
                                                           FilterCompare.Gt,
                                                           orm.getMapValueByFieldName(current,
                                                                                      CC_Fields.columnSort)))
                                         .orderBy(x -> x.orderBy(CC_Fields.columnSort))
                                         .firstMap();
                    break;
                case LOW:
                    target = targetSelect.orderBy(x -> x.orderByDescending(CC_Fields.columnSort))
                                         .firstMap();
                    break;
                default:
                    throw new BusinessException(String.format("不支持此排序方法%s",
                                                              data.getMethod()));
            }

            //目标为空，判定为无意义的操作
            if (target == null || target.size() == 0) return;

            if (repository_Key.updateDiy()
                              .set(CC_Fields.columnSort,
                                   orm.getMapValueByFieldName(current,
                                                              CC_Fields.columnSort))
                              .where(x -> x.and(CC_Fields.id,
                                                FilterCompare.Eq,
                                                orm.getMapValueByFieldName(target,
                                                                           CC_Fields.id)))
                              .executeAffrows() < 0 || repository_Key.updateDiy()
                                                                     .set(CC_Fields.columnSort,
                                                                          orm.getMapValueByFieldName(target,
                                                                                                     CC_Fields.columnSort))
                                                                     .where(x -> x.and(CC_Fields.id,
                                                                                       FilterCompare.Eq,
                                                                                       orm.getMapValueByFieldName(current,
                                                                                                                  CC_Fields.id)))
                                                                     .executeAffrows() < 0)
                throw new BusinessException("排序失败");
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException("排序失败",
                                        ex);
        }
    }

    @Override
    @Transactional
    public void dragSort(DragSortDTO<String> data)
            throws
            BusinessException {
        try {
            //操作对象和目标对象相同，判定为无意义的操作
            if (data.getId()
                    .equals(data.getTargetId())) return;

            Map<String, Object> current = repository_Key.select()
                                                        .columns(CC_Fields.id,
                                                                 CC_Fields.tableId,
                                                                 CC_Fields.columnSort)
                                                        .where(x -> x.and(CC_Fields.id,
                                                                          FilterCompare.Eq,
                                                                          data.getId()))
                                                        .firstMap();

            if (current == null || current.size() == 0) throw new BusinessException("数据不存在或已被移除");

            Map<String, Object> target = repository_Key.select()
                                                       .columns(CC_Fields.id,
                                                                CC_Fields.tableId,
                                                                CC_Fields.columnSort)
                                                       .where(x -> x.and(CC_Fields.id,
                                                                         FilterCompare.Eq,
                                                                         data.getTargetId()))
                                                       .firstMap();

            if (target == null || target.size() == 0) throw new BusinessException("目标数据不存在");

            //目标列和当前列不属于同一个数据表，无法排序
            if (!orm.getMapValueByFieldName(current,
                                            CC_Fields.tableId)
                    .equals(orm.getMapValueByFieldName(target,
                                                       CC_Fields.tableId)))
                return;

            ISelect<CagcColumn> targetNewSelect = repository_Key.select()
                                                                .columns(CC_Fields.id,
                                                                         CC_Fields.columnSort)
                                                                .where(x -> x.and(CC_Fields.tableId,
                                                                                  FilterCompare.Eq,
                                                                                  orm.getMapValueByFieldName(target,
                                                                                                             CC_Fields.tableId)));
            //同层级排序
            Map<String, Object> targetNew;

            if (data.getAppend()) {
                targetNew = targetNewSelect.where(x -> x.and(CC_Fields.columnSort,
                                                             FilterCompare.Eq,
                                                             (int) orm.getMapValueByFieldName(target,
                                                                                              CC_Fields.columnSort)
                                                                     + 1))
                                           .firstMap();
            } else {
                targetNew = targetNewSelect.where(x -> x.and(CC_Fields.columnSort,
                                                             FilterCompare.Eq,
                                                             (int) orm.getMapValueByFieldName(target,
                                                                                              CC_Fields.columnSort)
                                                                     - 1))
                                           .firstMap();
            }

            if (repository_Key.updateDiy()
                              .set(CC_Fields.columnSort,
                                   orm.getMapValueByFieldName(current,
                                                              CC_Fields.columnSort))
                              .where(x -> x.and(CC_Fields.id,
                                                FilterCompare.Eq,
                                                orm.getMapValueByFieldName(targetNew == null
                                                                           ? target
                                                                           : targetNew,
                                                                           CC_Fields.id)))
                              .executeAffrows() < 0 || repository_Key.updateDiy()
                                                                     .set(CC_Fields.columnSort,
                                                                          orm.getMapValueByFieldName(
                                                                                  targetNew == null
                                                                                  ? target
                                                                                  : targetNew,
                                                                                  CC_Fields.columnSort))
                                                                     .where(x -> x.and(CC_Fields.id,
                                                                                       FilterCompare.Eq,
                                                                                       orm.getMapValueByFieldName(current,
                                                                                                                  CC_Fields.id)))
                                                                     .executeAffrows() < 0)
                throw new BusinessException("排序失败");
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException("排序失败",
                                        ex);
        }
    }
}
