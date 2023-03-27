package top.lctr.naive.cagc.system.business.service.Interface;

import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import top.lctr.naive.cagc.system.dto.constEnumDataDTO.*;

import java.util.Collection;

/**
 * 常量/枚举数据服务接口类
 *
 * @author LCTR
 * @date 2022-06-15
 */
public interface IConstEnumDataService {
    /**
     * 列表数据
     *
     * @param dataSearch 数据搜索参数
     * @return 列表数据集合
     */
    java.util.List<CED_List> list(DataSearchDTO dataSearch);

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    CED_Detail detail(String id);

    /**
     * 新增
     *
     * @param data 数据
     */
    void create(CED_Create data);

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    CED_Edit edit(String id);

    /**
     * 编辑
     *
     * @param data 数据
     */
    void edit(CED_Edit data);

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    void delete(Collection<String> ids);

    /**
     * 获取常量/枚举的数据集合
     *
     * @param constEnumId 常量/枚举Id
     * @return 常量/枚举数据集合
     */
    java.util.List<FunUse_Value> valueList(String constEnumId);
}
