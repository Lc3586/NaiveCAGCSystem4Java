package top.lctr.naive.cagc.system.business.service.Interface;

import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.standard.api.response.select.SelectOptionDTO;
import top.lctr.naive.cagc.system.dto.tableDTO.*;

import java.util.Collection;

/**
 * 数据库表服务接口类
 *
 * @author LCTR
 * @date 2022-06-15
 */
public interface ITableService {
    /**
     * 列表数据
     *
     * @param dataSearch 数据搜索参数
     * @return 列表数据集合
     */
    java.util.List<T_List> list(DataSearchDTO dataSearch);

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    T_Detail detail(String id);

    /**
     * 同步
     *
     * @param names 表名集合
     */
    void sync(Collection<String> names);

    /**
     * 新增
     *
     * @param data 数据
     */
    void create(T_Create data);

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    T_Edit edit(String id);

    /**
     * 编辑
     *
     * @param data 数据
     */
    void edit(T_Edit data);

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    void delete(Collection<String> ids);

    /**
     * 获取所有数据库表
     *
     * @return <表名, 注释>
     */
    java.util.Map<String, String> tableMap();

    /**
     * 获取已同步的数据库表下拉选择框选项集合
     *
     * @return 选项集合
     */
    java.util.List<SelectOptionDTO<String>> synchronizedTableSelectOptionList();
}
