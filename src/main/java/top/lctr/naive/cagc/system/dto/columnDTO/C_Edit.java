package top.lctr.naive.cagc.system.dto.columnDTO;

import org.springframework.util.StringUtils;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import project.extension.openapi.annotations.OpenApiDescription;
import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.naive.cagc.system.entity.CagcColumn;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 数据库列业务模型
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
public class C_Edit
        extends CagcColumn {
    /**
     * 其他标签集合
     */
    @EntityMappingSetting(ignore = true)
    @OpenApiDescription("其他标签")
    private java.util.List<String> tagList;

    /**
     * 其它选项集合
     */
    @EntityMappingSetting(ignore = true)
    @OpenApiDescription("其它选项")
    private java.util.List<String> optionList;

    /**
     * 其他标签
     */
    public java.util.List<String> getTagList() {
        return tagList;
    }

    public void setTagList(java.util.List<String> tagList) {
        this.tagList = tagList;
        setTags(tagList != null
                ? String.join(",",
                              tagList)
                : "");
    }

    @Override
    public void setTags(String tags) {
        super.setTags(tags);
        this.tagList = StringUtils.hasText(tags)
                       ? Arrays.asList(tags.split(","))
                       : new ArrayList<>();
    }

    /**
     * 其它选项
     */
    public java.util.List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(java.util.List<String> optionList) {
        this.optionList = optionList;
        setOptions(optionList != null
                   ? String.join(",",
                                 optionList)
                   : "");
    }

    @Override
    public void setOptions(String options) {
        super.setOptions(options);
        this.optionList = StringUtils.hasText(options)
                          ? Arrays.asList(options.split(","))
                          : new ArrayList<>();
    }
}
