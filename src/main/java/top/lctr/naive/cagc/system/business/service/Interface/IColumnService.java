package top.lctr.naive.cagc.system.business.service.Interface;

import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.mybatis.edge.model.DbTableInfo;
import project.extension.standard.api.request.datasort.DataSortDTO;
import project.extension.standard.api.request.datasort.DragSortDTO;
import top.lctr.naive.cagc.system.dto.columnDTO.*;

import java.util.Collection;

/**
 * 数据库列服务接口类
 *
 * @author LCTR
 * @date 2022-06-15
 */
public interface IColumnService {
    /**
     * 列表数据
     *
     * @param dataSearch 数据搜索参数
     * @return 列表数据集合
     */
    java.util.List<C_List> list(DataSearchDTO dataSearch);

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    C_Detail detail(String id);

    /**
     * 同步
     *
     * @param tableInfo         表信息
     * @param withTransactional 所有操作是否在由springframework管理的事务下运行
     */
    void sync(DbTableInfo tableInfo,
              boolean withTransactional);

    /**
     * 新增
     *
     * @param data 数据
     */
    void create(C_Create data);

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    C_Edit edit(String id);

    /**
     * 编辑
     *
     * @param data 数据
     */
    void edit(C_Edit data);

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    void delete(Collection<String> ids);

    /**
     * 删除数据表相关的列
     *
     * @param tableIds          数据表主键集合
     * @param withTransactional 所有操作是否在由springframework管理的事务下运行
     */
    void delete(Collection<String> tableIds,
                boolean withTransactional);

    /**
     * 获取列信息集合
     *
     * @param tableId 表信息Id
     * @return 列信息数据集合
     */
    java.util.List<C_Detail> columnList(String tableId);

    /**
     * 启用/禁用
     *
     * @param id     主键
     * @param enable true：启用，false：禁用
     */
    void enable(String id,
                Boolean enable);

    /**
     * 锁定/解锁
     *
     * @param id     主键
     * @param locked true：锁定，false：解锁
     */
    void lock(String id,
              Boolean locked);

    /**
     * 排序
     *
     * @param data 数据
     */
    void sort(DataSortDTO<String> data);

    /**
     * 拖动排序
     *
     * @param data 数据
     */
    void dragSort(DragSortDTO<String> data);
}
