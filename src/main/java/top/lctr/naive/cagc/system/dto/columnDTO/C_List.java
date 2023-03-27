package top.lctr.naive.cagc.system.dto.columnDTO;

import org.springframework.util.StringUtils;
import project.extension.mybatis.edge.annotations.EntityMapping;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import project.extension.openapi.annotations.OpenApiDescription;
import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.naive.cagc.system.entity.CagcColumn;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 数据库列业务模型
 * <p>列表数据</p>
 *
 * @author LCTR
 * @date 2022-06-15
 */
@OpenApiMainTags({@OpenApiMainTag("List"),
                  @OpenApiMainTag(names = {"List",
                                           "_List"},
                                  level = 1)})
@EntityMapping
public class C_List
        extends CagcColumn {
    /**
     * 所属表
     */
    @EntityMappingSetting(self = true)
    private String tableName;

    /**
     * 关联常量/枚举
     */
    @EntityMappingSetting(self = true)
    private String cEName;

    /**
     * 其他标签集合
     */
    @EntityMappingSetting(ignore = true)
    @OpenApiDescription("其他标签集合")
    private java.util.List<String> tagList;

    /**
     * 所属表
     */
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * 关联常量/枚举
     */
    public String getCEName() {
        return cEName;
    }

    public void setCEName(String cEName) {
        this.cEName = cEName;
    }

    /**
     * 其他标签
     */
    public java.util.List<String> getTagList() {
        if (tagList == null)
            setTags(super.getTags());
        return tagList;
    }

    @Override
    public void setTags(String tags) {
        super.setTags(tags);
        this.tagList = StringUtils.hasText(tags)
                       ? Arrays.asList(tags.split(","))
                       : new ArrayList<>();
    }
}
