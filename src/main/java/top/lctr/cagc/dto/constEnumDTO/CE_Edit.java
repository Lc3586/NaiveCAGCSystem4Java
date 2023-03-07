package top.lctr.cagc.dto.constEnumDTO;

import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.cagc.entity.CagcConstEnum;

/**
 * 常量/枚举业务模型
 * <p>编辑</p>
 *
 * @author LCTR
 * @date 2022-06-15
 */
@OpenApiMainTags({
        @OpenApiMainTag(names = {"Edit"}),
        @OpenApiMainTag(names = {"Edit",
                                 "_Edit"},
                        level = 1)
})
public class CE_Edit
        extends CagcConstEnum {

}
