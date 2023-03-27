package top.lctr.naive.cagc.system.dto;

import java.util.List;

/**
 * 字段信息
 *
 * @author LCTR
 * @date 2022-06-07
 */
public class CagcField {
    public CagcField() {

    }

    public CagcField(String name,
                     String name4Method,
                     String javaType,
                     String tsType,
                     String title,
                     String description,
                     boolean hasSplit,
                     String split,
                     boolean enable,
                     boolean lock,
                     boolean query,
                     String queryCompare,
                     boolean sort,
                     boolean required,
                     int maxLength,
                     boolean longText) {
        this.name = name;
        this.name4Method = name4Method;
        this.javaType = javaType;
        this.setTsType(tsType);
        this.setTitle(title);
        this.description = description;
        this.hasSplit = hasSplit;
        this.split = split;
        this.setEnable(enable);
        this.setLock(lock);
        this.setQuery(query);
        this.setQueryCompare(queryCompare);
        this.setSort(sort);
        this.setRequired(required);
        this.setMaxLength(maxLength);
        this.longText = longText;
    }

    /**
     * 名称
     */
    private String name;

    /**
     * 方法所使用的名称
     */
    private String name4Method;

    /**
     * java类型
     */
    private String javaType;

    /**
     * typescript类型
     */
    private String tsType;

    /**
     * 标题/名称
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 是否使用了数据分隔符号
     */
    private boolean hasSplit;

    /**
     * 数据分隔符号
     */
    private String split;

    /**
     * 注解
     */
    private List<String> annotations;

    /**
     * 常量/枚举快速导航连接
     */
    private String contEnumLink;

    /**
     * 启用状态
     */
    private boolean enable;

    /**
     * 锁定状态
     */
    private boolean lock;

    /**
     * 查询字段
     */
    private boolean query;

    /**
     * 查询方法
     */
    private String queryCompare;

    /**
     * 排序字段
     */
    private boolean sort;

    /**
     * 必填字段
     */
    private boolean required;

    /**
     * 最大长度
     */
    private int maxLength;

    /**
     * 长文本
     */
    private boolean longText;

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
     * 方法所使用的名称
     */
    public String getName4Method() {
        return name4Method;
    }

    public void setName4Method(String name4Method) {
        this.name4Method = name4Method;
    }

    /**
     * java类型
     */
    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    /**
     * typescript类型
     */
    public String getTsType() {
        return tsType;
    }

    public void setTsType(String tsType) {
        this.tsType = tsType;
    }

    /**
     * 标题/名称
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 注释
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 是否使用了数据分隔符号
     */
    public boolean isHasSplit() {
        return hasSplit;
    }

    public void setHasSplit(boolean hasSplit) {
        this.hasSplit = hasSplit;
    }

    /**
     * 数据分隔符号
     */
    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    /**
     * 注解
     */
    public List<String> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<String> annotations) {
        this.annotations = annotations;
    }

    /**
     * 常量/枚举快速导航连接
     */
    public String getContEnumLink() {
        return contEnumLink;
    }

    public void setContEnumLink(String contEnumLink) {
        this.contEnumLink = contEnumLink;
    }

    /**
     * 启用状态
     */
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 锁定状态
     */
    public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    /**
     * 查询字段
     */
    public boolean isQuery() {
        return query;
    }

    public void setQuery(boolean query) {
        this.query = query;
    }

    /**
     * 查询方法
     */
    public String getQueryCompare() {
        return queryCompare;
    }

    public void setQueryCompare(String queryCompare) {
        this.queryCompare = queryCompare;
    }

    /**
     * 排序字段
     */
    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    /**
     * 必填字段
     */
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * 最大长度
     */
    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * 长文本
     */
    public boolean isLongText() {
        return longText;
    }

    public void setLongText(boolean longText) {
        this.longText = longText;
    }
}
