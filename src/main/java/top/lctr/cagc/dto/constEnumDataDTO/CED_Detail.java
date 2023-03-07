package top.lctr.cagc.dto.constEnumDataDTO;

import project.extension.mybatis.edge.annotations.EntityMapping;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.cagc.entity.CagcConstEnumData;

/**
 * 常量/枚举数据业务模型
 * <p>详情</p>
 *
 * @author LCTR
 * @date 2022-06-15
 */
@OpenApiMainTags({
        @OpenApiMainTag("Detail")
})
@EntityMapping
public class CED_Detail
        extends CagcConstEnumData {
    /**
     * 所属常量/枚举
     */
    @EntityMappingSetting(self = true)
    private String cEName;

    /**
     * 所属常量/枚举
     */
    public String getCEName() {
        return cEName;
    }

    public void setCEName(String cEName) {
        this.cEName = cEName;
    }
}
