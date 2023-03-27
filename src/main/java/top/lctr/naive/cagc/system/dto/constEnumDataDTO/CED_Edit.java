package top.lctr.naive.cagc.system.dto.constEnumDataDTO;

import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.naive.cagc.system.entity.CagcConstEnumData;

/**
 * 常量/枚举数据业务模型
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
public class CED_Edit
        extends CagcConstEnumData {

}
