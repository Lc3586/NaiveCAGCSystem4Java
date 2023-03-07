package top.lctr.cagc.business.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.extension.mybatis.edge.core.provider.standard.INaiveSql;
import project.extension.mybatis.edge.dbContext.repository.IBaseRepository_Key;
import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.mybatis.edge.extention.datasearch.DataSearchExtension;
import project.extension.mybatis.edge.model.FilterCompare;
import project.extension.mybatis.edge.model.NullResultException;
import project.extension.standard.api.response.select.SelectOptionDTO;
import project.extension.standard.entity.IEntityExtension;
import project.extension.standard.exception.BusinessException;
import top.lctr.cagc.business.service.Interface.IConstEnumService;
import top.lctr.cagc.dto.constEnumDTO.*;
import top.lctr.cagc.entity.CagcConstEnum;
import top.lctr.cagc.entityFields.CCE_Fields;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 常量/枚举服务实现类
 *
 * @author LCTR
 * @date 2022-06-15
 */
@Service
@Scope("prototype")
public class ConstEnumServiceImpl
        implements IConstEnumService {
    public ConstEnumServiceImpl(INaiveSql orm,
                                IEntityExtension entityExtension) {
        this.orm = orm;
        this.repository_Key = orm.getRepository_Key(CagcConstEnum.class,
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

    private final IBaseRepository_Key<CagcConstEnum, String> repository_Key;

    private final Map<String, String> tableKeyAliasMap;

    private final String defaultTableKey = "main";

    private final IEntityExtension entityExtension;

    /**
     * 获取包含子查询的sql语句
     *
     * @param dataCount 查询数据总数
     * @return sql语句
     */
    private String getWithSql(boolean dataCount) {
        return String.format("select * %s from \"CAGC_CONST_ENUM\" as a",
                             dataCount
                             ? "\r\n, (select count(1) from \"CAGC_CONST_ENUM_DATA\" as c where c.\"C_E_ID\" = a.\"ID\") as \"DATA_COUNT\" "
                             : "");
    }

    @Override
    public List<CE_List> list(DataSearchDTO dataSearch) {
        try {
            return repository_Key.select()
                                 .as(tableKeyAliasMap.get(defaultTableKey))
                                 .withSql(getWithSql(true))
                                 .where(x -> x.and(DataSearchExtension.toDynamicFilter(dataSearch.getFilters(),
                                                                                       tableKeyAliasMap)))
                                 .orderBy(x -> dataSearch.getOrder() == null
                                               ? x.orderBy(
                                         CCE_Fields.createTime)
                                               : x.orderBy(DataSearchExtension.toDynamicOrder(dataSearch.getOrder(),
                                                                                              tableKeyAliasMap)))
                                 .pagination(dataSearch.getPagination())
                                 .toList(CE_List.class);
        } catch (Throwable ex) {
            throw new BusinessException("查询数据失败",
                                        ex);
        }
    }

    @Override
    public CE_Detail detail(String id)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .withSql(getWithSql(true))
                                 .where(x -> x.and(CCE_Fields.id,
                                                   FilterCompare.Eq,
                                                   id))
                                 .mainTagLevel(1)
                                 .first(CE_Detail.class);
        } catch (NullResultException ex) {
            throw new BusinessException(ex.getMessage());
        } catch (Throwable ex) {
            throw new BusinessException("获取详情数据失败",
                                        ex);
        }
    }

    @Override
    public void create(CE_Create data)
            throws
            BusinessException {
        try {
            repository_Key.insert(entityExtension.initialization(data),
                                  CE_Create.class,
                                  1);
        } catch (Exception ex) {
            throw new BusinessException("新增数据失败",
                                        ex);
        }
    }

    @Override
    public CE_Edit edit(String id)
            throws
            BusinessException {
        try {
            return repository_Key.getByIdAndCheckNull(id,
                                                      CE_Edit.class,
                                                      1);
        } catch (Exception ex) {
            throw new BusinessException("获取编辑数据失败",
                                        ex);
        }
    }

    @Override
    public void edit(CE_Edit data)
            throws
            BusinessException {
        try {
            repository_Key.update(entityExtension.modify(data),
                                  CE_Edit.class,
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
    public List<CE_Detail> detailList(Collection<String> ids)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .withSql(getWithSql(true))
                                 .where(x -> x.and(CCE_Fields.id,
                                                   FilterCompare.InSet,
                                                   ids))
                                 .orderBy(x -> x.orderBy(CCE_Fields.name))
                                 .toList(CE_Detail.class);
        } catch (Exception ex) {
            throw new BusinessException("获取详情集合失败",
                                        ex);
        }
    }

    @Override
    public List<SelectOptionDTO<String>> selectOptionList()
            throws
            BusinessException {
        try {
            List<Map<String, Object>> mapList
                    = repository_Key.select()
                                    .as(tableKeyAliasMap.get(defaultTableKey))
                                    .orderBy(CCE_Fields.name)
                                    .columns(CCE_Fields.id,
                                             CCE_Fields.name,
                                             CCE_Fields.type,
                                             CCE_Fields.moduleName)
                                    .toMapList();

            return mapList.stream()
                          .map(x -> new SelectOptionDTO<String>(String.format("%s（%s）（%s）",
                                                                              orm.getMapValueByFieldName(x,
                                                                                                         CCE_Fields.name),
                                                                              orm.getMapValueByFieldName(x,
                                                                                                         CCE_Fields.moduleName),
                                                                              orm.getMapValueByFieldName(x,
                                                                                                         CCE_Fields.type)),
                                                                orm.getMapValueByFieldName(x,
                                                                                           CCE_Fields.id)))
                          .collect(Collectors.toList());
        } catch (Throwable ex) {
            throw new BusinessException("查询数据失败",
                                        ex);
        }
    }
}
