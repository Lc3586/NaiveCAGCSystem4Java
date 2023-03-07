package top.lctr.cagc.dto.constEnumDTO;

import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.cagc.entity.CagcConstEnum;

/**
 * 常量/枚举业务模型
 * <p>新增</p>
 *
 * @author LCTR
 * @date 2022-06-15
 */
@OpenApiMainTags({
        @OpenApiMainTag("Create"),
        @OpenApiMainTag(names = "*",
                        level = 1)
})
public class CE_Create
        extends CagcConstEnum {

}
