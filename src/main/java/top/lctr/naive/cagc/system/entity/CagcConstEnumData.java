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
 * 常量/枚举数据
 *
 * @author LCTR
 * @date 2022-06-07
 */
@TableSetting
@Alias("CagcConstEnumData")
public class CagcConstEnumData {
    /**
     * 主键
     */
    @OpenApiDescription("主键")
    @ColumnSetting(isPrimaryKey = true,
                   length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Edit"})
    private String id;

    /**
     * 所属常量/枚举
     */
    @OpenApiDescription("所属常量/枚举")
    @ColumnSetting(length = 36)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String cEId;

    /**
     * 键
     */
    @OpenApiDescription("键")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String key;

    /**
     * 值
     */
    @OpenApiDescription("值")
    @ColumnSetting(length = 50)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String value;

    /**
     * 描述
     */
    @OpenApiDescription("描述")
    @ColumnSetting(length = 800)
    @OpenApiSubTag({"List",
                    "Detail",
                    "Create",
                    "Edit"})
    private String description;


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
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 常量/枚举Id
     */
    public String getcEId() {
        return cEId;
    }

    public void setcEId(String cEId) {
        this.cEId = cEId;
    }

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
