package top.lctr.naive.cagc.system.business.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.extension.cryptography.MD5Utils;
import project.extension.mybatis.edge.core.provider.standard.INaiveSql;
import project.extension.mybatis.edge.dbContext.repository.IBaseRepository_Key;
import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.mybatis.edge.extention.datasearch.DataSearchExtension;
import project.extension.mybatis.edge.model.DbTableInfo;
import project.extension.mybatis.edge.model.FilterCompare;
import project.extension.mybatis.edge.model.NullResultException;
import project.extension.standard.api.response.select.SelectOptionDTO;
import project.extension.standard.authentication.IAuthenticationService;
import project.extension.standard.entity.IEntityExtension;
import project.extension.standard.exception.BusinessException;
import project.extension.string.StringExtension;
import top.lctr.naive.cagc.system.business.service.Interface.IColumnService;
import top.lctr.naive.cagc.system.business.service.Interface.ITableService;
import top.lctr.naive.cagc.system.dto.tableDTO.*;
import top.lctr.naive.cagc.system.entity.CagcTable;
import top.lctr.naive.cagc.system.entityFields.CT_Fields;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据库表服务实现类
 *
 * @author LCTR
 * @date 2022-06-15
 */
@Service
@Scope("prototype")
public class TableServiceImpl
        implements ITableService {
    public TableServiceImpl(INaiveSql orm,
                            IColumnService columnService,
                            IEntityExtension entityExtension,
                            IAuthenticationService authenticationService) {
        this.orm = orm;
        this.repository_Key = orm.getRepository_Key(CagcTable.class,
                                                    String.class);
        this.columnService = columnService;
        this.entityExtension = entityExtension;
        this.authenticationService = authenticationService;
        this.tableKeyAliasMap = new HashMap<>();
        this.tableKeyAliasMap.put(defaultTableKey,
                                  "a");
    }

    /**
     * 日志组件
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final INaiveSql orm;

    private final IBaseRepository_Key<CagcTable, String> repository_Key;

    private final IColumnService columnService;

    private final Map<String, String> tableKeyAliasMap;

    private final String defaultTableKey = "main";

    private final IEntityExtension entityExtension;

    private final IAuthenticationService authenticationService;

    /**
     * 获取包含子查询的sql语句
     *
     * @param columnCount 查询数据库列总数
     * @return sql语句
     */
    private String getWithSql(boolean columnCount) {
        return String.format("select * %s from \"CAGC_TABLE\" as a",
                             columnCount
                             ? "\r\n, (select count(1) from \"CAGC_COLUMN\" as c where c.\"TABLE_ID\" = a.\"ID\") as \"COLUMN_COUNT\" "
                             : "");
    }

    @Override
    public List<T_List> list(DataSearchDTO dataSearch)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .as(tableKeyAliasMap.get(defaultTableKey))
                                 .withSql(getWithSql(true))
                                 .where(x -> x.and(DataSearchExtension.toDynamicFilter(dataSearch.getFilters(),
                                                                                       tableKeyAliasMap)))
                                 .orderBy(x -> dataSearch.getOrder() == null
                                               ? x.orderBy(CT_Fields.createTime)
                                               : x.orderBy(DataSearchExtension.toDynamicOrder(dataSearch.getOrder(),
                                                                                              tableKeyAliasMap)))
                                 .pagination(dataSearch.getPagination())
                                 .mainTagLevel(1)
                                 .toList(T_List.class);
        } catch (Throwable ex) {
            throw new BusinessException("查询数据失败",
                                        ex);
        }
    }

    @Override
    public T_Detail detail(String id)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .withSql(getWithSql(true))
                                 .where(x -> x.and(CT_Fields.id,
                                                   FilterCompare.Eq,
                                                   id))
                                 .mainTagLevel(1)
                                 .first(T_Detail.class);
        } catch (NullResultException ex) {
            throw new BusinessException(ex.getMessage());
        } catch (Throwable ex) {
            throw new BusinessException("获取详情数据失败",
                                        ex);
        }
    }

    @Override
    @Transactional
    public void sync(Collection<String> names)
            throws
            BusinessException {
        try {
            //使用事务
//            Tuple2<Boolean, Exception> result = RepositoryExtension.runTransaction(() -> {
            List<T_Create> createList = new ArrayList<>();

            for (String name : names) {
                DbTableInfo tableInfo = orm.getDbFirst()
                                           .getTableByName(name,
                                                           true);

                String tableId = MD5Utils.hash(tableInfo.getName())
                                         .toUpperCase(Locale.ROOT);

                if (repository_Key.select()
                                  .where(x -> x.and(CT_Fields.id,
                                                    FilterCompare.Eq,
                                                    tableId))
                                  .any()) throw new BusinessException(String.format("%s表已存在，请先删除后再同步",
                                                                                    tableInfo.getName()));

                T_Create data = new T_Create();
                data.setName(tableInfo.getName());
                data.setTitle(tableInfo.getComment());
                data.setSignature(authenticationService.getOperator()
                                                       .getUsername());
//                data.setDescription(tableInfo.getComment());
                String[] modelWithName = tableInfo.getName()
                                                  .split("_",
                                                         2);
                data.setModuleName(StringExtension.firstChar2UpperCase(StringExtension.underscoreToPascalCase(modelWithName[0])));
                data.setBusinessName(StringExtension.firstChar2UpperCase(StringExtension.underscoreToPascalCase(modelWithName[1])));
                data.setGroup(data.getModuleName());
                entityExtension.initialization(data)
                               .setId(tableId);

                //同步列
                columnService.sync(tableInfo,
                                   true);

                createList.add(data);
            }

            if (createList.size() > 0) repository_Key.batchInsert(createList,
                                                                  T_Create.class,
                                                                  1);
//            });
//
//            if (!result.a)
//                throw result.b;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException("同步数据失败",
                                        ex);
        }
    }

    @Override
    public void create(T_Create data)
            throws
            BusinessException {
        try {
            repository_Key.insert(entityExtension.initialization(data),
                                  T_Create.class,
                                  1);
        } catch (Exception ex) {
            throw new BusinessException("新增数据失败",
                                        ex);
        }
    }

    @Override
    public T_Edit edit(String id)
            throws
            BusinessException {
        try {
            return repository_Key.getByIdAndCheckNull(id,
                                                      T_Edit.class,
                                                      1);
        } catch (Exception ex) {
            throw new BusinessException("获取编辑数据失败",
                                        ex);
        }
    }

    @Override
    public void edit(T_Edit data)
            throws
            BusinessException {
        try {
            repository_Key.update(entityExtension.modify(data),
                                  T_Edit.class,
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
            columnService.delete(ids,
                                 true);

            repository_Key.deleteByIds(ids);
        } catch (Exception ex) {
            throw new BusinessException("删除数据失败",
                                        ex);
        }
    }

    @Override
    public Map<String, String> tableMap() {
        try {
            return orm.getDbFirst()
                      .getTablesByDatabase()
                      .stream()
                      .collect(Collectors.toMap(DbTableInfo::getName,
                                                DbTableInfo::getComment));
        } catch (Exception ex) {
            throw new BusinessException("获取数据库表失败",
                                        ex);
        }
    }

    @Override
    public List<SelectOptionDTO<String>> synchronizedTableSelectOptionList() {
        try {
            List<Map<String, Object>> mapList
                    = repository_Key.select()
                                    .as(tableKeyAliasMap.get(defaultTableKey))
                                    .orderBy(CT_Fields.name)
                                    .columns(CT_Fields.id,
                                             CT_Fields.name,
                                             CT_Fields.title)
                                    .toMapList();

            return mapList.stream()
                          .map(x -> new SelectOptionDTO<String>(String.format("%s（%s）",
                                                                              orm.getMapValueByFieldName(x,
                                                                                                         CT_Fields.name),
                                                                              orm.getMapValueByFieldName(x,
                                                                                                         CT_Fields.title)),
                                                                orm.getMapValueByFieldName(x,
                                                                                           CT_Fields.id)))
                          .collect(Collectors.toList());
        } catch (Throwable ex) {
            throw new BusinessException("查询数据失败",
                                        ex);
        }
    }
}
