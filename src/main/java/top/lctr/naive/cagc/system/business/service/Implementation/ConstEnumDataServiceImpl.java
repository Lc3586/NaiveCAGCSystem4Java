package top.lctr.naive.cagc.system.business.service.Implementation;

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
import project.extension.standard.entity.IEntityExtension;
import project.extension.standard.exception.BusinessException;
import top.lctr.naive.cagc.system.business.service.Interface.IConstEnumDataService;
import top.lctr.naive.cagc.system.dto.constEnumDataDTO.*;
import top.lctr.naive.cagc.system.entity.CagcConstEnumData;
import top.lctr.naive.cagc.system.entityFields.CCED_Fields;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 常量/枚举服务实现类
 *
 * @author LCTR
 * @date 2022-06-15
 */
@Service
@Scope("prototype")
public class ConstEnumDataServiceImpl
        implements IConstEnumDataService {
    public ConstEnumDataServiceImpl(INaiveSql orm,
                                    IEntityExtension entityExtension) {
        this.repository_Key = orm.getRepository_Key(CagcConstEnumData.class,
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

    private final IBaseRepository_Key<CagcConstEnumData, String> repository_Key;

    private final Map<String, String> tableKeyAliasMap;

    private final String defaultTableKey = "main";

    private final IEntityExtension entityExtension;

    /**
     * 获取包含子查询的sql语句
     *
     * @param withCEName 查询常量/枚举名
     * @return sql语句
     */
    private String getWithSql(boolean withCEName) {
        return String.format("select * %s from \"CAGC_CONST_ENUM_DATA\" as a",
                             withCEName
                             ? "\r\n, (select c.\"NAME\" from \"CAGC_CONST_ENUM\" as c where c.\"ID\" = a.\"C_E_ID\") as \"C_E_NAME\" "
                             : "");
    }

    @Override
    public List<CED_List> list(DataSearchDTO dataSearch) {
        try {
            return repository_Key.select()
                                 .as(tableKeyAliasMap.get(defaultTableKey))
                                 .withSql(getWithSql(true))
                                 .where(x -> x.and(DataSearchExtension.toDynamicFilter(dataSearch.getFilters(),
                                                                                       tableKeyAliasMap)))
                                 .orderBy(x -> dataSearch.getOrder() == null
                                               ? x.orderBy(
                                         CCED_Fields.createTime)
                                               : x.orderBy(DataSearchExtension.toDynamicOrder(dataSearch.getOrder(),
                                                                                              tableKeyAliasMap)))
                                 .pagination(dataSearch.getPagination())
                                 .toList(CED_List.class);
        } catch (Throwable ex) {
            throw new BusinessException("查询数据失败",
                                        ex);
        }
    }

    @Override
    public CED_Detail detail(String id)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .withSql(getWithSql(true))
                                 .where(x -> x.and(CCED_Fields.id,
                                                   FilterCompare.Eq,
                                                   id))
                                 .mainTagLevel(1)
                                 .first(CED_Detail.class);
        } catch (NullResultException ex) {
            throw new BusinessException(ex.getMessage());
        } catch (Throwable ex) {
            throw new BusinessException("获取详情数据失败",
                                        ex);
        }
    }

    @Override
    public void create(CED_Create data)
            throws
            BusinessException {
        try {
            repository_Key.insert(entityExtension.initialization(data),
                                  CED_Create.class,
                                  1);
        } catch (Exception ex) {
            throw new BusinessException("新增数据失败",
                                        ex);
        }
    }

    @Override
    public CED_Edit edit(String id)
            throws
            BusinessException {
        try {
            return repository_Key.getByIdAndCheckNull(id,
                                                      CED_Edit.class,
                                                      1);
        } catch (Exception ex) {
            throw new BusinessException("获取编辑数据失败",
                                        ex);
        }
    }

    @Override
    public void edit(CED_Edit data)
            throws
            BusinessException {
        try {
            repository_Key.update(entityExtension.modify(data),
                                  CED_Edit.class,
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
    public List<FunUse_Value> valueList(String constEnumId)
            throws
            BusinessException {
        try {
            return repository_Key.select()
                                 .where(x -> x.and(CCED_Fields.cEId,
                                                   FilterCompare.Eq,
                                                   constEnumId))
                                 .orderBy(x -> x.orderBy(CCED_Fields.key))
                                 .toList(FunUse_Value.class);
        } catch (Exception ex) {
            throw new BusinessException("获取数据集合失败",
                                        ex);
        }
    }
}
