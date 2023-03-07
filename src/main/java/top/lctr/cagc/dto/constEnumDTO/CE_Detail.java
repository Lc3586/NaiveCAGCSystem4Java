package top.lctr.cagc.dto.constEnumDTO;

import project.extension.mybatis.edge.annotations.EntityMapping;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.cagc.entity.CagcConstEnum;

/**
 * 常量/枚举业务模型
 * <p>详情</p>
 *
 * @author LCTR
 * @date 2022-06-15
 */
@OpenApiMainTags({
        @OpenApiMainTag("Detail")
})
@EntityMapping
public class CE_Detail
        extends CagcConstEnum {
    /**
     * 数据数量
     */
    @EntityMappingSetting(self = true)
    private int dataCount;

    /**
     * 数据数量
     */
    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
}
