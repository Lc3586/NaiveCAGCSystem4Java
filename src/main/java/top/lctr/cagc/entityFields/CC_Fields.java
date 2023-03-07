package top.lctr.cagc.entityFields;

/**
 * 数据库列实体字段
 *
 * @author LCTR
 * @date 2022-06-15
 * @see top.lctr.cagc.entity.CagcColumn
 */
public final class CC_Fields {
    /**
     * 主键
     * <p>值为表名+列名的md5值</p>
     */
    public static final String id = "id";

    /**
     * 所属表
     */
    public static final String tableId = "tableId";

    /**
     * 关联常量/枚举
     */
    public static final String cEId = "cEId";

    /**
     * 列名
     */
    public static final String name = "name";

    /**
     * 标题/名称
     */
    public static final String title = "title";

    /**
     * 描述
     */
    public static final String description = "description";

    /**
     * 主键
     */
    public static final String pk = "pk";

    /**
     * 外键
     */
    public static final String fk = "fk";

    /**
     * 外键名称
     */
    public static final String fkName = "fkName";

    /**
     * 外键关联表
     */
    public static final String fkTableId = "fkTableId";

    /**
     * 外键关联列
     */
    public static final String fkColumnId = "fkColumnId";

    /**
     * 索引
     */
    public static final String index = "index";

    /**
     * 索引名称
     */
    public static final String indexName = "indexName";

    /**
     * 索引降序
     */
    public static final String indexDesc = "indexDesc";

    /**
     * 唯一键
     */
    public static final String unique = "unique";

    /**
     * 唯一键名称
     */
    public static final String uniqueName = "uniqueName";

    /**
     * 唯一键降序
     */
    public static final String uniqueDesc = "uniqueDesc";

    /**
     * 列排序值
     */
    public static final String columnSort = "columnSort";

    /**
     * 数据库数据类型
     */
    public static final String dbType = "dbType";

    /**
     * 最大长度
     */
    public static final String maxLength = "maxLength";

    /**
     * 精度
     */
    public static final String precision = "precision";

    /**
     * 标度
     */
    public static final String scale = "scale";

    /**
     * java字段名
     */
    public static final String javaField = "javaField";

    /**
     * java基本类型
     */
    public static final String javaType = "javaType";

    /**
     * java包装类型
     */
    public static final String javaPackageType = "javaPackageType";

    /**
     * java基本类型强制转换语句
     */
    public static final String javaTypeConvert = "javaTypeConvert";

    /**
     * java包装类型强制转换语句
     */
    public static final String javaPackageTypeConvert = "javaPackageTypeConvert";

    /**
     * java反序列化语句
     */
    public static final String javaParse = "javaParse";

    /**
     * java序列化语句
     */
    public static final String javaStringify = "javaStringify";

    /**
     * typescript类型
     */
    public static final String tsType = "tsType";

    /**
     * 必填字段
     */
    public static final String required = "required";

    /**
     * 搜索字段
     */
    public static final String query = "query";

    /**
     * 搜索字段比较类型
     */
    public static final String queryCompare = "queryCompare";

    /**
     * 数据分隔
     */
    public static final String split = "split";

    /**
     * 数据分隔符号
     */
    public static final String splitChar = "splitChar";

    /**
     * 列表功能
     */
    public static final String list = "list";

    /**
     * 详情功能
     */
    public static final String detail = "detail";

    /**
     * 新增功能
     */
    public static final String create = "create";

    /**
     * 编辑功能
     */
    public static final String edit = "edit";

    /**
     * 启用/禁用功能
     */
    public static final String enable = "enable";

    /**
     * 锁定/解锁功能
     */
    public static final String lock = "lock";

    /**
     * 排序功能
     */
    public static final String sort = "sort";

    /**
     * 树状结构级别列
     */
    public static final String treeLevel = "treeLevel";

    /**
     * 树状结构父id列
     */
    public static final String treeParentId = "treeParentId";

    /**
     * 树状结构根id列
     */
    public static final String treeRootId = "treeRootId";

    /**
     * 导入功能
     */
    public static final String import_ = "import_";

    /**
     * 导出功能
     */
    public static final String export = "export";

    /**
     * 其它标签（[,]号拼接）
     */
    public static final String tags = "tags";

    /**
     * 其它选项（[,]号拼接）
     */
    public static final String options = "options";

    /**
     * 备注
     */
    public static final String remark = "remark";

    /**
     * 创建者
     */
    public static final String createBy = "createBy";

    /**
     * 创建时间
     */
    public static final String createTime = "createTime";

    /**
     * 更新者
     */
    public static final String updateBy = "updateBy";

    /**
     * 更新时间
     */
    public static final String updateTime = "updateTime";
}
