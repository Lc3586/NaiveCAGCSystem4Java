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
 * 数据库表
 *
 * @author LCTR
 * @date 2022-06-15
 */
@TableSetting
@Alias("CagcTable")
public class CagcTable {
    /**
     * 主键
     * <p>值为表名的md5值</p>
     */
    @OpenApiDescription("主键")
    @ColumnSetting(isPrimaryKey = true,
                   length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Edit"})
    private String id;

    /**
     * 表名
     */
    @OpenApiDescription("表名")
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
     * 所属分组
     */
    @OpenApiDescription("所属分组")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String group;

    /**
     * 署名
     */
    @OpenApiDescription("署名")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String signature;

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
     * 业务名称
     */
    @OpenApiDescription("业务名称")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String businessName;

    /**
     * 所属模块
     */
    @OpenApiDescription("所属模块")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String moduleName;

    /**
     * 其它选项（[,]号拼接）
     */
    @OpenApiDescription("其它选项（[,]号拼接）")
    @ColumnSetting(length = -4)
    @OpenApiSubTag({"_Detail",
                    "_Create",
                    "_Edit"})
    private String options;


    /**
     * 备注
     */
    @OpenApiDescription("备注")
    @ColumnSetting(length = -4)
    @OpenApiSubTag({"Detail",
                    "Create",
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
    @OpenApiSubTag({"List",
                    "Detail"})
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
     * <p>值为表名的md5值</p>
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
     * 所属分组
     */
    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * 署名
     */
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
     * 业务名称
     */
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * 所属模块
     */
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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
