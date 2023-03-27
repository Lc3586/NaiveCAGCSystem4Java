package top.lctr.naive.cagc.system.dto.tableDTO;

import org.springframework.util.StringUtils;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import project.extension.openapi.annotations.OpenApiDescription;
import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.naive.cagc.system.entity.CagcTable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 数据库表业务模型
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
public class T_Create
        extends CagcTable {
    /**
     * 其它选项集合
     */
    @EntityMappingSetting(ignore = true)
    @OpenApiDescription("其它选项")
    private java.util.List<String> optionList;

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
