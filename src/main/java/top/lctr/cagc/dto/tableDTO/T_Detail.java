package top.lctr.cagc.dto.tableDTO;

import org.springframework.util.StringUtils;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import project.extension.openapi.annotations.OpenApiDescription;
import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.cagc.entity.CagcTable;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 数据库表业务模型
 * <p>详情</p>
 *
 * @author LCTR
 * @date 2022-06-15
 */
@OpenApiMainTags({@OpenApiMainTag("Detail"),
                  @OpenApiMainTag(names = {"Detail",
                                           "_Detail"},
                                  level = 1)})
public class T_Detail
        extends CagcTable {
    /**
     * 其它选项集合
     */
    @EntityMappingSetting(ignore = true)
    @OpenApiDescription("其它选项")
    private java.util.List<String> optionList;

    /**
     * 列数量
     */
    @EntityMappingSetting(self = true)
    private int columnCount;

    /**
     * 其它选项
     */
    public java.util.List<String> getOptionList() {
        if (optionList == null)
            setOptions(super.getOptions());
        return optionList;
    }

    @Override
    public void setOptions(String options) {
        super.setOptions(options);
        this.optionList = StringUtils.hasText(options)
                          ? Arrays.asList(options.split(","))
                          : new ArrayList<>();
    }

    /**
     * 列数量
     */
    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
