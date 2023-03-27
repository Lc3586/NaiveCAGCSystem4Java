package top.lctr.naive.cagc.system.dto.constEnumDataDTO;

import project.extension.mybatis.edge.annotations.EntityMapping;
import project.extension.mybatis.edge.annotations.EntityMappingSetting;
import top.lctr.naive.cagc.system.entity.CagcConstEnumData;

/**
 * 枚举值
 *
 * @author LCTR
 * @date 2022-06-07
 */
@EntityMapping(CagcConstEnumData.class)
public class FunUse_Value {
    /**
     * 键
     */
    @EntityMappingSetting(self = true)
    private String key;

    /**
     * 值
     */
    @EntityMappingSetting(self = true)
    private String value;

    /**
     * 描述
     */
    @EntityMappingSetting(self = true)
    private String description;

    /**
     * 是否为最后一个
     */
    @EntityMappingSetting(ignore = true)
    private boolean last;

    /**
     * 键
     */
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 值
     */
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 是否为最后一个
     */
    public boolean getLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
