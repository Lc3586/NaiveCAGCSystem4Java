package top.lctr.naive.cagc.system.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.Alias;
import project.extension.mybatis.edge.annotations.ColumnSetting;
import project.extension.mybatis.edge.annotations.TableSetting;
import project.extension.openapi.annotations.OpenApiDescription;
import project.extension.openapi.annotations.OpenApiSubTag;

import java.util.Date;

/**
 * 数据库列
 *
 * @author LCTR
 * @date 2022-06-15
 */
@TableSetting
@Alias("CagcColumn")
public class CagcColumn {
    /**
     * 主键
     * <p>值为表名+列名的md5值</p>
     */
    @OpenApiDescription("主键（值为表名+列名的md5值）")
    @ColumnSetting(isPrimaryKey = true,
                   length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Edit"})
    private String id;

    /**
     * 所属表
     */
    @OpenApiDescription("所属表")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String tableId;

    /**
     * 关联常量/枚举
     */
    @OpenApiDescription("关联常量/枚举")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String cEId;

    /**
     * 列名
     */
    @OpenApiDescription("列名")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String name;

    /**
     * 标题/名称
     */
    @OpenApiDescription("标题/名称")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String title;

    /**
     * 描述
     */
    @OpenApiDescription("描述")
    @ColumnSetting(length = 800)
    @OpenApiSubTag({"Detail",
                    "Create",
                    "Edit"})
    private String description;


    /**
     * 标识列
     */
    @OpenApiDescription("标识列")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean pk;

    /**
     * 外键
     */
    @OpenApiDescription("外键")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean fk;

    /**
     * 外键名称
     */
    @OpenApiDescription("外键名称")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String fkName;

    /**
     * 外键关联表
     */
    @OpenApiDescription("外键关联表")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String fkTableId;

    /**
     * 外键关联列
     */
    @OpenApiDescription("外键关联列")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String fkColumnId;

    /**
     * 索引
     */
    @OpenApiDescription("索引")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean index;

    /**
     * 索引名称
     */
    @OpenApiDescription("索引名称")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String indexName;

    /**
     * 索引降序
     */
    @OpenApiDescription("索引降序")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean indexDesc;

    /**
     * 唯一键
     */
    @OpenApiDescription("唯一键")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean unique;

    /**
     * 唯一键名称
     */
    @OpenApiDescription("唯一键名称")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String uniqueName;

    /**
     * 唯一键降序
     */
    @OpenApiDescription("唯一键降序")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean uniqueDesc;

    /**
     * 列排序值
     */
    @OpenApiDescription("列排序值")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private int columnSort;

    /**
     * 数据库数据类型
     */
    @OpenApiDescription("数据库数据类型")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String dbType;

    /**
     * 最大长度
     */
    @OpenApiDescription("最大长度")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Integer maxLength;

    /**
     * 精度
     */
    @OpenApiDescription("精度")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Integer precision;

    /**
     * 标度
     */
    @OpenApiDescription("标度")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Integer scale;


    /**
     * java字段名
     */
    @OpenApiDescription("java字段名")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String javaField;

    /**
     * java基本类型
     */
    @OpenApiDescription("java基本类型")
    @ColumnSetting(length = 100)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String javaType;

    /**
     * java包装类型
     */
    @OpenApiDescription("java包装类型")
    @ColumnSetting(length = 100)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String javaPackageType;

    /**
     * java基本类型强制转换语句
     */
    @OpenApiDescription("java基本类型强制转换语句")
    @ColumnSetting(length = 300)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String javaTypeConvert;

    /**
     * java包装类型强制转换语句
     */
    @OpenApiDescription("java包装类型强制转换语句")
    @ColumnSetting(length = 300)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String javaPackageTypeConvert;

    /**
     * java反序列化语句
     */
    @OpenApiDescription("java反序列化语句")
    @ColumnSetting(length = 300)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String javaParse;

    /**
     * java序列化语句
     */
    @OpenApiDescription("java序列化语句")
    @ColumnSetting(length = 300)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String javaStringify;

    /**
     * typescript类型
     */
    @OpenApiDescription("typescript类型")
    @ColumnSetting(length = 100)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String tsType;

    /**
     * 必填字段
     */
    @OpenApiDescription("必填字段")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean required;

    /**
     * 搜索字段
     */
    @OpenApiDescription("搜索字段")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean query;

    /**
     * 搜索字段比较类型
     */
    @OpenApiDescription("搜索字段比较类型")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String queryCompare;

    /**
     * 数据分隔
     */
    @OpenApiDescription("数据分隔")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean split;

    /**
     * 数据分隔符号
     */
    @OpenApiDescription("数据分隔符号")
    @ColumnSetting(length = 10)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String splitChar;

    /**
     * 列表功能
     */
    @OpenApiDescription("列表功能")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean list;

    /**
     * 详情功能
     */
    @OpenApiDescription("详情功能")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean detail;

    /**
     * 新增功能
     */
    @OpenApiDescription("新增功能")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean create;

    /**
     * 编辑功能
     */
    @OpenApiDescription("编辑功能")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean edit;

    /**
     * 启用/禁用功能
     */
    @OpenApiDescription("启用/禁用功能")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean enable;

    /**
     * 锁定/解锁功能
     */
    @OpenApiDescription("锁定/解锁功能")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean lock;

    /**
     * 排序功能
     */
    @OpenApiDescription("排序功能")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean sort;

    /**
     * 树状结构级别列
     */
    @OpenApiDescription("树状结构级别列")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean treeLevel;

    /**
     * 树状结构父id列
     */
    @OpenApiDescription("树状结构父id列")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean treeParentId;

    /**
     * 树状结构根id列
     */
    @OpenApiDescription("树状结构根id列")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean treeRootId;

    /**
     * 导入功能
     */
    @OpenApiDescription("导入功能")
    @ColumnSetting(alias = "import")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean import_;

    /**
     * 导出功能
     */
    @OpenApiDescription("导出功能")
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private Boolean export;

    /**
     * 其它标签（[,]号拼接）
     */
    @OpenApiDescription("其它标签（[,]号拼接）")
    @ColumnSetting(length = 1024)
    @OpenApiSubTag({"_List",
                    "_Detail",
                    "_Edit"})
    private String tags;


    /**
     * 其它选项（[,]号拼接）
     */
    @OpenApiDescription("其它选项（[,]号拼接）")
    @ColumnSetting(length = -4)
    @OpenApiSubTag({"_Detail",
                    "_Edit"})
    private String options;


    /**
     * 备注
     */
    @OpenApiDescription("备注")
    @ColumnSetting(length = -4)
    @OpenApiSubTag({"Detail",
                    "Edit"})
    private String remark;

    /**
     * 创建者
     */
    @OpenApiDescription("创建者")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"Detail"})
    private String createBy;

    /**
     * 创建时间
     */
    @OpenApiDescription("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @OpenApiSubTag({"Detail"})
    private Date createTime;

    /**
     * 更新者
     */
    @OpenApiDescription("更新者")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"Detail",
                    "_Edit"})
    private String updateBy;

    /**
     * 更新时间
     */
    @OpenApiDescription("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @OpenApiSubTag({"Detail",
                    "_Edit"})
    private Date updateTime;

    /**
     * 主键
     * <p>值为表名+列名的md5值</p>
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 所属表
     */
    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * 关联常量/枚举
     */
    public String getcEId() {
        return cEId;
    }

    public void setcEId(String cEId) {
        this.cEId = cEId;
    }

    /**
     * 表名
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
     * 描述
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 标识列
     */
    public Boolean getPk() {
        return pk;
    }

    public void setPk(Boolean pk) {
        this.pk = pk;
    }

    /**
     * 外键
     */
    public Boolean getFk() {
        return fk;
    }

    public void setFk(Boolean fk) {
        this.fk = fk;
    }

    /**
     * 外键名称
     */
    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }

    /**
     * 外键关联表
     */
    public String getFkTableId() {
        return fkTableId;
    }

    public void setFkTableId(String fkTableId) {
        this.fkTableId = fkTableId;
    }

    /**
     * 外键关联列
     */
    public String getFkColumnId() {
        return fkColumnId;
    }

    public void setFkColumnId(String fkColumnId) {
        this.fkColumnId = fkColumnId;
    }

    /**
     * 索引
     */
    public Boolean getIndex() {
        return index;
    }

    public void setIndex(Boolean index) {
        this.index = index;
    }

    /**
     * 索引名称
     */
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    /**
     * 索引降序
     */
    public Boolean getIndexDesc() {
        return indexDesc;
    }

    public void setIndexDesc(Boolean indexDesc) {
        this.indexDesc = indexDesc;
    }

    /**
     * 唯一键
     */
    public Boolean getUnique() {
        return unique;
    }

    public void setUnique(Boolean unique) {
        this.unique = unique;
    }

    /**
     * 唯一键名称
     */
    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    /**
     * 唯一键降序
     */
    public Boolean getUniqueDesc() {
        return uniqueDesc;
    }

    public void setUniqueDesc(Boolean uniqueDesc) {
        this.uniqueDesc = uniqueDesc;
    }

    /**
     * 列排序值
     */
    public int getColumnSort() {
        return columnSort;
    }

    public void setColumnSort(int columnSort) {
        this.columnSort = columnSort;
    }

    /**
     * 数据库数据类型
     */
    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * 最大长度
     */
    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * 精度
     */
    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    /**
     * 标度
     */
    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    /**
     * java字段名
     */
    public String getJavaField() {
        return javaField;
    }

    public void setJavaField(String javaField) {
        this.javaField = javaField;
    }

    /**
     * java基本类型
     */
    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    /**
     * java包装类型
     */
    public String getJavaPackageType() {
        return javaPackageType;
    }

    public void setJavaPackageType(String javaPackageType) {
        this.javaPackageType = javaPackageType;
    }

    /**
     * java基本类型强制转换语句
     */
    public String getJavaTypeConvert() {
        return javaTypeConvert;
    }

    public void setJavaTypeConvert(String javaTypeConvert) {
        this.javaTypeConvert = javaTypeConvert;
    }

    /**
     * java包装类型强制转换语句
     */
    public String getJavaPackageTypeConvert() {
        return javaPackageTypeConvert;
    }

    public void setJavaPackageTypeConvert(String javaPackageTypeConvert) {
        this.javaPackageTypeConvert = javaPackageTypeConvert;
    }

    /**
     * java反序列化语句
     */
    public String getJavaParse() {
        return javaParse;
    }

    public void setJavaParse(String javaParse) {
        this.javaParse = javaParse;
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
     * java序列化语句
     */
    public String getJavaStringify() {
        return javaStringify;
    }

    public void setJavaStringify(String javaStringify) {
        this.javaStringify = javaStringify;
    }

    /**
     * 必填字段
     */
    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    /**
     * 搜索字段
     */
    public Boolean getQuery() {
        return query;
    }

    public void setQuery(Boolean query) {
        this.query = query;
    }

    /**
     * 搜索字段比较类型
     */
    public String getQueryCompare() {
        return queryCompare;
    }

    public void setQueryCompare(String queryCompare) {
        this.queryCompare = queryCompare;
    }

    /**
     * 数据分隔
     */
    public Boolean getSplit() {
        return split;
    }

    public void setSplit(Boolean split) {
        this.split = split;
    }

    /**
     * 数据分隔符号
     */
    public String getSplitChar() {
        return splitChar;
    }

    public void setSplitChar(String splitChar) {
        this.splitChar = splitChar;
    }

    /**
     * 列表功能
     */
    public Boolean getList() {
        return list;
    }

    public void setList(Boolean list) {
        this.list = list;
    }

    /**
     * 详情功能
     */
    public Boolean getDetail() {
        return detail;
    }

    public void setDetail(Boolean detail) {
        this.detail = detail;
    }

    /**
     * 新增功能
     */
    public Boolean getCreate() {
        return create;
    }

    public void setCreate(Boolean create) {
        this.create = create;
    }

    /**
     * 编辑功能
     */
    public Boolean getEdit() {
        return edit;
    }

    public void setEdit(Boolean edit) {
        this.edit = edit;
    }

    /**
     * 启用/禁用功能
     */
    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * 锁定/解锁功能
     */
    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    /**
     * 排序功能
     */
    public Boolean getSort() {
        return sort;
    }

    public void setSort(Boolean sort) {
        this.sort = sort;
    }

    /**
     * 树状结构级别列
     */
    public Boolean getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(Boolean treeLevel) {
        this.treeLevel = treeLevel;
    }

    /**
     * 树状结构父id列
     */
    public Boolean getTreeParentId() {
        return treeParentId;
    }

    public void setTreeParentId(Boolean treeParentId) {
        this.treeParentId = treeParentId;
    }

    /**
     * 树状结构根id列
     */
    public Boolean getTreeRootId() {
        return treeRootId;
    }

    public void setTreeRootId(Boolean treeRootId) {
        this.treeRootId = treeRootId;
    }

    /**
     * 导入功能
     */
    public Boolean getImport_() {
        return import_;
    }

    public void setImport_(Boolean import_) {
        this.import_ = import_;
    }

    /**
     * 导出功能
     */
    public Boolean getExport() {
        return export;
    }

    public void setExport(Boolean export) {
        this.export = export;
    }

    /**
     * 其它标签（[,]号拼接）
     */
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 其它选项（[,]号拼接）
     */
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 创建者
     */
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新者
     */
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
