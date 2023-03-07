package top.lctr.cagc.dto.constEnumDTO;

/**
 * 常量/枚举信息
 *
 * @author LCTR
 * @date 2022-06-17
 */
public class ConstEnumInfo {
    public ConstEnumInfo() {

    }

    public ConstEnumInfo(String type,
                         String name,
                         String explain) {
        this.setType(type);
        this.name = name;
        this.setExplain(explain);
    }

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 说明
     */
    private String explain;

    /**
     * 类型
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 说明
     */
    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
