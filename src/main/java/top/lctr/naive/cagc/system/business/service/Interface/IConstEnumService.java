package top.lctr.naive.cagc.system.business.service.Interface;

import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.standard.api.response.select.SelectOptionDTO;
import top.lctr.naive.cagc.system.dto.constEnumDTO.*;

import java.util.Collection;

/**
 * 常量/枚举服务接口类
 *
 * @author LCTR
 * @date 2022-06-15
 */
public interface IConstEnumService {
    /**
     * 列表数据
     *
     * @param dataSearch 数据搜索参数
     * @return 列表数据集合
     */
    java.util.List<CE_List> list(DataSearchDTO dataSearch);

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    CE_Detail detail(String id);

    /**
     * 新增
     *
     * @param data 数据
     */
    void create(CE_Create data);

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    CE_Edit edit(String id);

    /**
     * 编辑
     *
     * @param data 数据
     */
    void edit(CE_Edit data);

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    void delete(Collection<String> ids);

    /**
     * 详情数据集合
     *
     * @param ids 主键集合
     * @return 详情数据集合
     */
    java.util.List<CE_Detail> detailList(Collection<String> ids);

    /**
     * 获取下拉选择框选项集合
     *
     * @return 选项集合
     */
    java.util.List<SelectOptionDTO<String>> selectOptionList();
}
