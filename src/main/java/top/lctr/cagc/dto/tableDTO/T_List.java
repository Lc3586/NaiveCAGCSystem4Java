package top.lctr.cagc.dto.tableDTO;

import project.extension.mybatis.edge.annotations.EntityMapping;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import project.extension.openapi.annotations.OpenApiMainTag;
import project.extension.openapi.annotations.OpenApiMainTags;
import top.lctr.cagc.entity.CagcTable;

/**
 * 数据库表业务模型
 * <p>列表数据</p>
 *
 * @author LCTR
 * @date 2022-06-15
 */
@OpenApiMainTags({@OpenApiMainTag("List"),
                  @OpenApiMainTag(names = {"List" ,
                                           "_List"},
                                  level = 1)})
@EntityMapping
public class T_List
        extends CagcTable {
    /**
     * 列数量
     */
    @EntityMappingSetting(self = true)
    private int columnCount;

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
