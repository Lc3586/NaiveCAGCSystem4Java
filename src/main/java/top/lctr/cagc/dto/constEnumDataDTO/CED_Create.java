package top.lctr.cagc.dto.constEnumDataDTO;

import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.cagc.entity.CagcConstEnumData;

/**
 * 常量/枚举数据业务模型
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
public class CED_Create
        extends CagcConstEnumData {

}
