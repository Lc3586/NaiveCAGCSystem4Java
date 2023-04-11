package top.lctr.naive.cagc.system.business.service.Implementation;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import project.extension.Identity.SnowFlake;
import project.extension.collections.CollectionsExtension;
import project.extension.func.IFunc2;
import project.extension.standard.authentication.IAuthenticationService;
import project.extension.standard.exception.BusinessException;
import project.extension.string.StringExtension;
import top.lctr.naive.cagc.system.business.service.Interface.*;
import top.lctr.naive.cagc.system.dto.CagcField;
import top.lctr.naive.cagc.system.dto.GenerateType;
import top.lctr.naive.cagc.system.dto.columnDTO.C_Detail;
import top.lctr.naive.cagc.system.dto.constEnumDTO.CE_Detail;
import top.lctr.naive.cagc.system.dto.constEnumDTO.ConstEnumInfo;
import top.lctr.naive.cagc.system.dto.constEnumDataDTO.FunUse_Value;
import top.lctr.naive.cagc.system.dto.tableDTO.*;
import top.lctr.naive.cagc.system.entity.CagcColumn;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码自动生成服务实现类
 *
 * @author LCTR
 * @date 2022-06-09
 */
@Service
@Scope("prototype")
public class CagcServiceImpl
        implements ICagcService {
    public CagcServiceImpl(ITableService tableService,
                           IColumnService columnService,
                           IConstEnumService constEnumService,
                           IConstEnumDataService constEnumDataService,
                           IAuthenticationService authenticationService) {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (servletRequestAttributes != null) {
            this.response = servletRequestAttributes.getResponse();
        } else {
//            throw new Exception("获取ServletRequestAttributes对象失败");
            this.response = null;
        }
        this.tableService = tableService;
        this.columnService = columnService;
        this.constEnumService = constEnumService;
        this.constEnumDataService = constEnumDataService;
        this.authenticationService = authenticationService;
        initialization();
    }

    /**
     * 日志组件
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 响应对象
     */
    private final HttpServletResponse response;

    private final ITableService tableService;

    private final IColumnService columnService;

    private final IConstEnumService constEnumService;

    private final IConstEnumDataService constEnumDataService;

    private final IAuthenticationService authenticationService;

    /**
     * 初始化
     */
    private void initialization() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class",
                          "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING,
                          StandardCharsets.UTF_8.name());
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception ex) {
            throw new BusinessException("初始化失败",
                                        ex);
        }
    }

    /**
     * 设置公共信息
     *
     * @param velocityContext VM模板数据上下文
     * @param table           表信息
     * @param packageName     包名
     * @param currentOpName   当前操作人
     * @return VM模板数据上下文
     */
    private VelocityContext setPublicInfo(VelocityContext velocityContext,
                                          T_Detail table,
                                          String packageName,
                                          String currentOpName) {
        //计算操作人名称长度
        int currentOpNameLength = 18;
        for (byte _byte : currentOpName.getBytes(StandardCharsets.UTF_8)) {
            if (_byte < 0) currentOpNameLength--;
        }
        //当前操作人
        velocityContext.put("currentOpName",
                            Strings.padEnd(currentOpName,
                                           currentOpNameLength,
                                           ' '));

        table.setModuleName(table.getModuleName()
                                 .toLowerCase(Locale.ROOT));

        velocityContext.put("businessName_lower",
                            StringExtension.firstChar2LowerCase(table.getBusinessName()));

        velocityContext.put("initialsName",
                            StringExtension.justFirstChar(table.getBusinessName()));

        //表信息
        velocityContext.put("table",
                            table);

        //实体类名
        velocityContext.put("entityName",
                            StringExtension.underscoreToPascalCase(table.getName()));

        //包名
        velocityContext.put("packageName",
                            packageName);
        //创建日期
        velocityContext.put("date",
                            new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //创建时间
        velocityContext.put("dateTime",
                            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return velocityContext;
    }

    /**
     * 设置方法信息
     *
     * @param velocityContext VM模板数据上下文
     * @param columns         列信息
     * @return VM模板数据上下文
     */
    private VelocityContext setFunctionInfo(VelocityContext velocityContext,
                                            List<C_Detail> columns) {
        velocityContext.put("tree",
                            false);
        velocityContext.put("sort",
                            false);
        velocityContext.put("enable",
                            false);
        velocityContext.put("import",
                            false);
        velocityContext.put("export",
                            false);

        for (CagcColumn column : columns) {
            if (column.getTreeParentId()) {
                //树状列表
                velocityContext.put("tree",
                                    true);
                velocityContext.put("treeParentIdCode",
                                    column.getJavaField());
                velocityContext.put("treeParentIdCode4Method",
                                    StringExtension.firstChar2UpperCase(column.getJavaField()));
            } else if (column.getTreeRootId()) {
                //树状列表
                velocityContext.put("treeRootIdCode",
                                    column.getJavaField());
                velocityContext.put("treeRootIdCode4Method",
                                    StringExtension.firstChar2UpperCase(column.getJavaField()));
            } else if (column.getTreeLevel()) {
                //树状列表
                velocityContext.put("treeLevelCode",
                                    column.getJavaField());
                velocityContext.put("treeLevelCode4Method",
                                    StringExtension.firstChar2UpperCase(column.getJavaField()));
            } else if (column.getSort()) {
                //排序功能
                velocityContext.put("sort",
                                    true);
                velocityContext.put("sortCode",
                                    column.getJavaField());
                velocityContext.put("sortCode4Method",
                                    StringExtension.firstChar2UpperCase(column.getJavaField()));
            } else if (column.getEnable()) {
                //启用/禁用功能
                velocityContext.put("enable",
                                    true);
                velocityContext.put("enableCode",
                                    column.getJavaField());
                velocityContext.put("enableCode4Method",
                                    StringExtension.firstChar2UpperCase(column.getJavaField()));
            } else if (column.getLock()) {
                //锁定/解锁功能
                velocityContext.put("lock",
                                    true);
                velocityContext.put("lockCode",
                                    column.getJavaField());
                velocityContext.put("lockCode4Method",
                                    StringExtension.firstChar2UpperCase(column.getJavaField()));
            } else if (column.getImport_()) {
                //导入功能
                velocityContext.put("import",
                                    true);
            } else if (column.getExport()) {
                //导出功能
                velocityContext.put("export",
                                    true);
            }
        }

        return velocityContext;
    }

    /**
     * 设置路由地址
     *
     * @param velocityContext VM模板数据上下文
     * @param table           表信息
     * @return VM模板数据上下文
     */
    private VelocityContext setRoutePath(VelocityContext velocityContext,
                                         T_Detail table) {
        velocityContext.put("routePath",
                            String.format("/%s/%s",
                                          StringExtension.pascalCaseToUnderScore(table.getModuleName())
                                                         .toLowerCase(Locale.ROOT)
                                                         .replaceAll("_",
                                                                     "-"),
                                          StringExtension.pascalCaseToUnderScore(table.getBusinessName())
                                                         .toLowerCase(Locale.ROOT)
                                                         .replaceAll("_",
                                                                     "-")));
        return velocityContext;
    }

    /**
     * 设置权限值信息
     *
     * @param velocityContext VM模板数据上下文
     * @param table           表信息
     * @return VM模板数据上下文
     */
    private VelocityContext setAuthValue(VelocityContext velocityContext,
                                         T_Detail table) {
        velocityContext.put("authValue",
                            String.format("%s:%s",
                                          StringExtension.pascalCaseToUnderScore(table.getModuleName())
                                                         .toLowerCase(Locale.ROOT)
                                                         .replaceAll("_",
                                                                     "-"),
                                          StringExtension.pascalCaseToUnderScore(table.getBusinessName())
                                                         .toLowerCase(Locale.ROOT)
                                                         .replaceAll("_",
                                                                     "-")));
        return velocityContext;
    }

    /**
     * 设置常量/枚举信息
     *
     * @param velocityContext VM模板数据上下文
     * @param columns         列信息
     * @return VM模板数据上下文
     */
    private VelocityContext setConstEnumInfo(VelocityContext velocityContext,
                                             List<C_Detail> columns) {
        Map<String, ConstEnumInfo> constEnumInfoMap = new HashMap<>();
        columns.forEach(column -> {
            if (!StringUtils.hasText(column.getcEId()) || constEnumInfoMap.containsKey(column.getcEId())) return;

            CE_Detail constEnum = constEnumService.detail(column.getcEId());

            constEnumInfoMap.put(constEnum.getId(),
                                 new ConstEnumInfo(constEnum.getType(),
                                                   constEnum.getName(),
                                                   constEnum.getDescription()));
        });

        velocityContext.put("constEnumList",
                            new ArrayList<>(constEnumInfoMap.values()));

        return velocityContext;
    }

    /**
     * 设置字段信息
     *
     * @param velocityContext VM模板数据上下文
     * @param columns         列信息
     * @param action          处理字段信息，返回false则不会将该字段加至集合中
     * @return VM模板数据上下文
     */
    private VelocityContext setFields(VelocityContext velocityContext,
                                      List<C_Detail> columns,
                                      IFunc2<C_Detail, CagcField, Boolean> action) {

        List<CagcField> fields = new ArrayList<>();
        columns.forEach(column -> {
            CagcField field = new CagcField(column.getJavaField(),
                                            StringExtension.firstChar2UpperCase(column.getJavaField()),
                                            column.getJavaPackageType(),
                                            column.getTsType(),
                                            column.getTitle(),
                                            column.getDescription(),
                                            column.getSplit(),
                                            column.getSplitChar(),
                                            column.getEnable(),
                                            column.getLock(),
                                            column.getQuery(),
                                            column.getQueryCompare(),
                                            column.getSort(),
                                            column.getRequired(),
                                            column.getMaxLength(),
                                            column.getMaxLength() > 100 || column.getMaxLength() < 0);
            try {
                if (action != null && !action.invoke(column,
                                                     field)) return;
                fields.add(field);
            } catch (Exception ex) {
                logger.warn("设置字段信息时发生错误",
                            ex);
            }
        });
        velocityContext.put("fields",
                            fields);
        return velocityContext;
    }

    /**
     * 设置业务模型字段信息
     *
     * @param velocityContext VM模板数据上下文
     * @param table           表信息
     * @param columns         列信息
     * @param dtoTags         业务模型标签
     * @param ignoreNames     需要忽略的列名
     * @return VM模板数据上下文
     */
    private VelocityContext setDTOFields(VelocityContext velocityContext,
                                         T_Detail table,
                                         List<C_Detail> columns,
                                         List<String> dtoTags,
                                         List<String> ignoreNames) {
        return setFields(velocityContext,
                         columns,
                         (column, field) -> {
                             if (ignoreNames != null && ignoreNames.stream()
                                                                   .anyMatch(x -> StringExtension.ignoreCaseEquals(x,
                                                                                                                   column.getName())))
                                 return false;

                             //只添加符合当前标签的字段
                             for (String dtoTag : dtoTags) {
                                 if (dtoTag.equals("*")) return true;

                                 boolean flag;
                                 switch (dtoTag) {
                                     case "List":
                                         flag = column.getList();
                                         break;
                                     case "Detail":
                                         flag = column.getDetail();
                                         break;
                                     case "Create":
                                         flag = column.getCreate();
                                         break;
                                     case "Edit":
                                         flag = column.getEdit();
                                         break;
                                     case "Import":
                                         flag = column.getImport_();
                                         break;
                                     case "Export":
                                         flag = column.getExport();
                                         break;
                                     default:
                                         flag = column.getTagList()
                                                      .contains(dtoTag);
                                 }

                                 if (flag) return true;
                             }
                             return false;
                         });
    }

    /**
     * 获取实体VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getEntityContext(T_Detail table,
                                             String packageName,
                                             String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        //常量/枚举导航连接
        Map<String, String> constEnumLinkMap = new HashMap<>();

        return setFields(velocityContext,
                         columnList,
                         (column, field) -> {
                             List<String> annotations = new ArrayList<>();
                             annotations.add(String.format("@OpenApiDescription(\"%s\")",
                                                           column.getTitle()));

                             List<String> tags = new ArrayList<>();
                             if (column.getList()) tags.add("List");
                             if (column.getDetail()) tags.add("Detail");
                             if (column.getCreate()) tags.add("Create");
                             if (column.getEdit()) tags.add("Edit");
                             if (column.getImport_()) tags.add("Import");
                             if (column.getExport()) tags.add("Export");

                             if (StringUtils.hasText(column.getTags())) {
                                 tags.addAll(Arrays.asList(column.getTags()
                                                                 .split(",")));
                             }

                             //附属标签注解
                             annotations.add(String.format("@OpenApiSubTag({\"%s\"})",
                                                           String.join("\", \"",
                                                                       tags)));

                             List<String> columnSetting = new ArrayList<>();
                             //主键
                             if (column.getPk()) columnSetting.add("primaryKey = true");

                             //java数据类型
                             String javaType = column.getJavaPackageType();
                             //数据库数据类型
                             String dbType = column.getDbType()
                                                   .trim();

                             if (StringExtension.ignoreCaseEquals(javaType,
                                                                  "String")) {
                                 if (StringExtension.ignoreCaseEquals(dbType,
                                                                      "longtext")
                                         || StringExtension.ignoreCaseEquals(dbType,
                                                                             "text")
                                         || StringExtension.ignoreCaseEquals(dbType,
                                                                             "clob"))
                                     //长文本
                                     columnSetting.add("length = -3");
                                 else {
                                     //指定长度的文本
                                     columnSetting.add(String.format("length = %s",
                                                                     column.getMaxLength()));
                                 }
                             } else if (StringExtension.ignoreCaseEquals(javaType,
                                                                         "BigDecimal")
                                     || StringExtension.ignoreCaseEquals(javaType,
                                                                         "Double")
                                     || StringExtension.ignoreCaseEquals(javaType,
                                                                         "Float")) {
                                 if (StringExtension.ignoreCaseEquals(javaType,
                                                                      "BigDecimal"))
                                     velocityContext.put("hasBigDecimal",
                                                         true);
                                 //指定精度和标度的数值
                                 columnSetting.add(String.format("precision = %s, scale = %s",
                                                                 column.getPrecision(),
                                                                 column.getScale()));
                             }

                             //列设置注解
                             if (columnSetting.size() == 0) annotations.add("@ColumnSetting");
                             else annotations.add(String.format("@ColumnSetting(%s)",
                                                                String.join(", ",
                                                                            columnSetting)));

                             if (StringExtension.ignoreCaseEquals(javaType,
                                                                  "Date")) {
                                 //日期时间数值
                                 annotations.add("@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\")");
                                 annotations.add("@JSONField(format = \"yyyy-MM-dd HH:mm:ss\")");
                             } else if (StringExtension.ignoreCaseEquals(javaType,
                                                                         "java.sql.Date")) {
                                 //日期数值
                                 annotations.add("@JsonFormat(pattern = \"yyyy-MM-dd\")");
                                 annotations.add("@JSONField(format = \"yyyy-MM-dd\")");
                             } else if (StringExtension.ignoreCaseEquals(javaType,
                                                                         "java.sql.Time")) {
                                 //时间数值
                                 annotations.add("@JsonFormat(pattern = \"HH:mm:ss\")");
                                 annotations.add("@JSONField(format = \"HH:mm:ss\")");
                             }

                             field.setAnnotations(annotations);

                             //常量/枚举信息
                             if (StringUtils.hasText(column.getcEId())) {
                                 String constEnumLink;
                                 if (constEnumLinkMap.containsKey(column.getcEId()))
                                     constEnumLink = constEnumLinkMap.get(column.getcEId());
                                 else {
                                     CE_Detail constEnum = constEnumService.detail(column.getcEId());
                                     constEnumLink = String.format("@see %s.dto.%s.%s %s",
                                                                   packageName,
                                                                   constEnum.getModuleName()
                                                                            .toLowerCase(Locale.ROOT),
                                                                   constEnum.getName(),
                                                                   constEnum.getDescription());
                                     constEnumLinkMap.put(column.getcEId(),
                                                          constEnumLink);
                                 }
                                 field.setContEnumLink(constEnumLink);
                             }

                             return true;
                         });
    }

    /**
     * 获取实体字段集合VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getEntityFieldsContext(T_Detail table,
                                                   String packageName,
                                                   String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());
        return setFields(velocityContext,
                         columnList,
                         null);
    }

    /**
     * 获取业务模型VM模板数据上下文
     *
     * @param dtoTag        业务模型标签
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getDTOContext(String dtoTag,
                                          T_Detail table,
                                          String packageName,
                                          String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        velocityContext.put("dtoTag",
                            dtoTag);

        List<String> dtoTags = new ArrayList<>();
        dtoTags.add(dtoTag);

        List<String> mainTags = new ArrayList<>();
        mainTags.add(String.format("@OpenApiMainTag({\"%s\"})",
                                   dtoTag));
        if (dtoTag.equalsIgnoreCase("Create")) {
            mainTags.add(",@OpenApiMainTag(names = {\"*\"}, level = 1)");
            dtoTags.add("*");
        } else if (dtoTag.equalsIgnoreCase("Edit")) {
            mainTags.add(",@OpenApiMainTag(names = {\"Edit\", \"_Edit\"}, level = 1)");
            dtoTags.add("_Edit");
        }
        velocityContext.put("mainTags",
                            mainTags);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());
        if (StringExtension.ignoreCaseEquals(dtoTag,
                                             "List")) setFunctionInfo(velocityContext,
                                                                      columnList);

        return setDTOFields(velocityContext,
                            table,
                            columnList,
                            dtoTags,
                            null);
    }

    /**
     * 获取常量/枚举VM模板数据上下文
     *
     * @param table         表信息
     * @param constEnum     关联常量/枚举信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getConstEnumContext(T_Detail table,
                                                CE_Detail constEnum,
                                                String packageName,
                                                String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        velocityContext.put("name",
                            constEnum.getName());
        velocityContext.put("description",
                            constEnum.getDescription());

        //常量/枚举数据集合
        List<FunUse_Value> valueList = constEnumDataService.valueList(constEnum.getId());
        for (int i = 0; i < valueList.size(); i++)
            valueList.get(i)
                     .setLast(i == valueList.size() - 1);

        velocityContext.put("values",
                            valueList);

        return velocityContext;
    }

    /**
     * 获取服务接口类VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getServiceContext(T_Detail table,
                                              String packageName,
                                              String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        setConstEnumInfo(velocityContext,
                         columnList);

        return setFunctionInfo(velocityContext,
                               columnList);
    }

    /**
     * 获取控制器VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getControllerContext(T_Detail table,
                                                 String packageName,
                                                 String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        setRoutePath(velocityContext,
                     table);

        setAuthValue(velocityContext,
                     table);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        setConstEnumInfo(velocityContext,
                         columnList);

        return setFunctionInfo(velocityContext,
                               columnList);
    }

    /**
     * 获取命名空间TS代码VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getTSModelFieldsContext(T_Detail table,
                                                    String packageName,
                                                    String currentOpName) {
        return getEntityFieldsContext(table,
                                      packageName,
                                      currentOpName);
    }

    /**
     * 获取业务模型TS代码VM模板数据上下文
     *
     * @param dtoTag        业务模型标签
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getTSDTOContext(String dtoTag,
                                            T_Detail table,
                                            String packageName,
                                            String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        velocityContext.put("dtoTag",
                            dtoTag);

        List<String> dtoTags = new ArrayList<>();
        dtoTags.add(dtoTag);

        if (dtoTag.equalsIgnoreCase("List")) dtoTags.add("_List");
        else if (dtoTag.equalsIgnoreCase("Detail")) dtoTags.add("_Detail");
        else if (dtoTag.equalsIgnoreCase("Create")) dtoTags.add("_Create");
        else if (dtoTag.equalsIgnoreCase("Edit")) dtoTags.add("_Edit");

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());
        if (StringExtension.ignoreCaseEquals(dtoTag,
                                             "List")) setFunctionInfo(velocityContext,
                                                                      columnList);

        return setDTOFields(velocityContext,
                            table,
                            columnList,
                            dtoTags,
                            null);
    }

    /**
     * 获取常量/枚举TS代码VM模板数据上下文
     *
     * @param table         表信息
     * @param constEnum     关联常量/枚举信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getTSConstEnumContext(T_Detail table,
                                                  CE_Detail constEnum,
                                                  String packageName,
                                                  String currentOpName) {
        return getConstEnumContext(table,
                                   constEnum,
                                   packageName,
                                   currentOpName);
    }

    /**
     * 获取接口服务TS代码VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getTSServiceContext(T_Detail table,
                                                String packageName,
                                                String currentOpName) {
        VelocityContext velocityContext = getServiceContext(table,
                                                            packageName,
                                                            currentOpName);
        return setRoutePath(velocityContext,
                            table);
    }

    /**
     * 获取Vue管理页VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getVueIndexContext(T_Detail table,
                                               String packageName,
                                               String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        velocityContext.put("businessName_componentName",
                            StringExtension.pascalCaseToUnderScore(table.getBusinessName())
                                           .replaceAll("_",
                                                       "-")
                                           .toLowerCase(Locale.ROOT));

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        setFunctionInfo(velocityContext,
                        columnList);

        return setDTOFields(velocityContext,
                            table,
                            columnList,
                            Arrays.asList("List",
                                          "_List"),
                            Collections.singletonList("Id"));
    }

    /**
     * 获取Vue详情页VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getVueDetailsContext(T_Detail table,
                                                 String packageName,
                                                 String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        setFunctionInfo(velocityContext,
                        columnList);

        return setDTOFields(velocityContext,
                            table,
                            columnList,
                            Arrays.asList("Detail",
                                          "_Detail"),
                            Collections.singletonList("Id"));
    }

    /**
     * 获取Vue新增页VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getVueAddContext(T_Detail table,
                                             String packageName,
                                             String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        setFunctionInfo(velocityContext,
                        columnList);

        return setDTOFields(velocityContext,
                            table,
                            columnList,
                            Arrays.asList("Create",
                                          "_Create"),
                            null);
    }

    /**
     * 获取Vue编辑页VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getVueEditContext(T_Detail table,
                                              String packageName,
                                              String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        setFunctionInfo(velocityContext,
                        columnList);

        return setDTOFields(velocityContext,
                            table,
                            columnList,
                            Arrays.asList("Edit",
                                          "_Edit"),
                            Collections.singletonList("Id"));
    }

    /**
     * 获取添加菜单的Sql语句VM模板数据上下文
     *
     * @param table         表信息
     * @param packageName   包名
     * @param currentOpName 当前操作人名称
     * @return 模板数据上下文
     */
    private VelocityContext getSqlMenuContext(T_Detail table,
                                              String packageName,
                                              String currentOpName) {
        VelocityContext velocityContext = new VelocityContext();
        setPublicInfo(velocityContext,
                      table,
                      packageName,
                      currentOpName);

        velocityContext.put("trim_currentOpName",
                            currentOpName);

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        setFunctionInfo(velocityContext,
                        columnList);

        List<CagcMenuFun> menuFuns = new ArrayList<>();

        SnowFlake snowFlake = new SnowFlake(1,
                                            1);
        setRoutePath(velocityContext,
                     table);
        setAuthValue(velocityContext,
                     table);

        velocityContext.put("moduleMenuId",
                            snowFlake.nextId());
        velocityContext.put("businessMenuId",
                            snowFlake.nextId());

        if (velocityContext.containsKey("tree") && (boolean) velocityContext.get("tree")) {
            menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                         "树状列表数据",
                                         1,
                                         ":tree-list",
                                         "TreeList",
                                         "/tree-list"));
        } else {
            menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                         "列表数据",
                                         1,
                                         ":list",
                                         "List",
                                         "/list"));

        }

        menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                     "详情数据",
                                     menuFuns.size() + 1,
                                     ":detail",
                                     "Detail",
                                     "/detail/{id}"));
        menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                     "新增",
                                     menuFuns.size() + 1,
                                     ":create",
                                     "Create",
                                     "/create"));
        menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                     "获取编辑数据",
                                     menuFuns.size() + 1,
                                     ":edit",
                                     "Edit",
                                     "/edit/{id}"));
        menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                     "编辑",
                                     menuFuns.size() + 1,
                                     ":edit",
                                     "Edit",
                                     "/edit"));
        menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                     "删除",
                                     menuFuns.size() + 1,
                                     ":delete",
                                     "Delete",
                                     "/delete"));

        if (velocityContext.containsKey("sort") && (boolean) velocityContext.get("sort")) {
            menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                         "排序",
                                         menuFuns.size() + 1,
                                         ":sort",
                                         "Sort",
                                         "/sort"));

            menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                         "拖动排序",
                                         menuFuns.size() + 1,
                                         ":drag-sort",
                                         "DragSort",
                                         "/drag-sort"));
        }

        if (velocityContext.containsKey("enable") && (boolean) velocityContext.get("enable"))
            menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                         "启用/禁用",
                                         menuFuns.size() + 1,
                                         ":sort",
                                         "Sort",
                                         "/enable/{id}/{enable}"));

        if (velocityContext.containsKey("lock") && (boolean) velocityContext.get("lock"))
            menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                         "锁定/解锁",
                                         menuFuns.size() + 1,
                                         ":lock",
                                         "Lock",
                                         "/lock/{id}/{locked}"));

        if (velocityContext.containsKey("import") && (boolean) velocityContext.get("import"))
            menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                         "导入数据",
                                         menuFuns.size() + 1,
                                         ":import",
                                         "Import",
                                         "/import/{personalFileId}"));

        if (velocityContext.containsKey("export") && (boolean) velocityContext.get("export"))
            menuFuns.add(new CagcMenuFun(snowFlake.nextId(),
                                         "导出数据",
                                         menuFuns.size() + 1,
                                         ":export",
                                         "Export",
                                         "/export/{version}"));

        velocityContext.put("menuFuns",
                            menuFuns);

        return velocityContext;
    }

    /**
     * 生成代码
     *
     * @param table        表信息
     * @param constEnum    关联常量/枚举信息
     * @param dtoTag       业务模型标签
     * @param generateType 生成类型
     * @param packageName  包名
     * @return 代码内容
     */
    private String generate(T_Detail table,
                            CE_Detail constEnum,
                            String dtoTag,
                            GenerateType generateType,
                            String packageName)
            throws
            Exception {
        VelocityContext context;
        String template;
        switch (generateType) {
            case 实体类:
                context = getEntityContext(table,
                                           packageName,
                                           authenticationService.getOperator()
                                                                .getNickname());
                template = "vm/java/entity.java.vm";
                break;
            case 实体字段集合:
                context = getEntityFieldsContext(table,
                                                 packageName,
                                                 authenticationService.getOperator()
                                                                      .getNickname());
                template = "vm/java/entityFields.java.vm";
                break;
            case 业务模型:
                context = getDTOContext(dtoTag,
                                        table,
                                        packageName,
                                        authenticationService.getOperator()
                                                             .getNickname());
                template = "vm/java/dto.java.vm";
                break;
            case 常量:
                context = getConstEnumContext(table,
                                              constEnum,
                                              packageName,
                                              authenticationService.getOperator()
                                                                   .getNickname());
                template = "vm/java/const.java.vm";
                break;
            case 枚举:
                context = getConstEnumContext(table,
                                              constEnum,
                                              packageName,
                                              authenticationService.getOperator()
                                                                   .getNickname());
                template = "vm/java/enum.java.vm";
                break;
            case 服务接口类:
                context = getServiceContext(table,
                                            packageName,
                                            authenticationService.getOperator()
                                                                 .getNickname());
                template = "vm/java/service.java.vm";
                break;
            case 服务实现类:
                context = getServiceContext(table,
                                            packageName,
                                            authenticationService.getOperator()
                                                                 .getNickname());
                template = "vm/java/serviceImpl.java.vm";
                break;
            case 控制器:
                context = getControllerContext(table,
                                               packageName,
                                               authenticationService.getOperator()
                                                                    .getNickname());
                template = "vm/java/controller.java.vm";
                break;
            case TS模型字段集合:
                context = getTSModelFieldsContext(table,
                                                  packageName,
                                                  authenticationService.getOperator()
                                                                       .getNickname());
                template = "vm/ts/model_fields.ts.vm";
                break;
            case TS业务模型:
                context = getTSDTOContext(dtoTag,
                                          table,
                                          packageName,
                                          authenticationService.getOperator()
                                                               .getNickname());
                template = "vm/ts/dto.ts.vm";
                break;
            case TS常量_枚举:
                context = getTSConstEnumContext(table,
                                                constEnum,
                                                packageName,
                                                authenticationService.getOperator()
                                                                     .getNickname());
                template = "vm/ts/const_enum.ts.vm";
                break;
            case TS接口服务:
                context = getTSServiceContext(table,
                                              packageName,
                                              authenticationService.getOperator()
                                                                   .getNickname());
                template = "vm/ts/service.ts.vm";
                break;
            case Vue管理页:
                context = getVueIndexContext(table,
                                             packageName,
                                             authenticationService.getOperator()
                                                                  .getNickname());
                template = "vm/vue/index.vue.vm";
                break;
            case Vue详情页:
                context = getVueDetailsContext(table,
                                               packageName,
                                               authenticationService.getOperator()
                                                                    .getNickname());
                template = "vm/vue/details.vue.vm";
                break;
            case Vue新增页:
                context = getVueAddContext(table,
                                           packageName,
                                           authenticationService.getOperator()
                                                                .getNickname());
                template = "vm/vue/add.vue.vm";
                break;
            case Vue编辑页:
                context = getVueEditContext(table,
                                            packageName,
                                            authenticationService.getOperator()
                                                                 .getNickname());
                template = "vm/vue/edit.vue.vm";
                break;
            case 添加菜单的Sql语句:
                context = getSqlMenuContext(table,
                                            packageName,
                                            authenticationService.getOperator()
                                                                 .getNickname());
                template = "vm/sql/menu.sql.vm";
                break;
            default:
                return String.format("//暂不支持%s",
                                     generateType);
        }

        //渲染模板
        StringWriter sw = new StringWriter();
        Template tpl = Velocity.getTemplate(template,
                                            StandardCharsets.UTF_8.name());
        tpl.merge(context,
                  sw);
        String code = sw.toString();
        sw.close();
        return code;
    }

    /**
     * 生成实体代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateEntity(T_Detail table,
                                               String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s.java",
                                 Paths.get("entity",
                                           "domain",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           String.format("%s%s",
                                                         table.getModuleName(),
                                                         table.getBusinessName()))),
                   generate(table,
                            null,

                            null,
                            GenerateType.实体类,
                            packageName));
        return result;
    }

    /**
     * 生成实体字段集合代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateEntityFields(T_Detail table,
                                                     String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s_Fields.java",
                                 Paths.get("entity",
                                           "entityFields",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           StringExtension.justFirstChar(table.getBusinessName()))),
                   generate(table,
                            null,
                            null,
                            GenerateType.实体字段集合,
                            packageName));
        return result;
    }

    /**
     * 生成业务模型代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @param ts          是否为TS代码
     * @return <条目, 内容>
     */
    private Map<String, String> generateDTO(T_Detail table,
                                            String packageName,
                                            boolean ts)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();

        List<String> dtoTags = new ArrayList<>();

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        for (C_Detail column : columnList) {
            List<String> columnDtoTags = new ArrayList<>();
            if (CollectionsExtension.anyPlus(column.getTagList())) {
                column.getTagList()
                      .forEach(x -> {
                          if (!x.equalsIgnoreCase("_List") && !x.equalsIgnoreCase("_Detail")
                                  && !x.equalsIgnoreCase("_Create") && !x.equalsIgnoreCase("_Edit")
                                  && !x.equalsIgnoreCase("_Import") && !x.equalsIgnoreCase("_Export"))
                              columnDtoTags.add(x);
                      });
            }

            if (column.getList()) columnDtoTags.add("List");
            if (column.getDetail()) columnDtoTags.add("Detail");
            if (column.getCreate()) columnDtoTags.add("Create");
            if (column.getEdit()) columnDtoTags.add("Edit");
            if (column.getImport_()) columnDtoTags.add("Import");
            if (column.getExport()) columnDtoTags.add("Export");

            for (String dtoTag : columnDtoTags) {
                if (dtoTags.contains(dtoTag)) continue;

                dtoTags.add(dtoTag);

                String entry;
                if (ts) entry = String.format("%s.ts",
                                              Paths.get("api",
                                                        "dto",
                                                        table.getModuleName()
                                                             .toLowerCase(Locale.ROOT),
                                                        StringExtension.firstChar2LowerCase(table.getBusinessName()),
                                                        dtoTag));
                else entry = String.format("%s.java",
                                           Paths.get("dto",
                                                     table.getModuleName()
                                                          .toLowerCase(Locale.ROOT),
                                                     String.format("%sDTO",
                                                                   StringExtension.firstChar2LowerCase(table.getBusinessName())),
                                                     dtoTag));

                result.put(entry,
                           generate(table,
                                    null,
                                    dtoTag,
                                    ts
                                    ? GenerateType.TS业务模型
                                    : GenerateType.业务模型,
                                    packageName));
            }
        }
        return result;
    }

    /**
     * 生成常量/枚举代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @param ts          是否为TS代码
     * @return <条目, 内容>
     */
    private Map<String, String> generateConstEnum(T_Detail table,
                                                  String packageName,
                                                  boolean ts)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();

        //列信息集合
        List<C_Detail> columnList = columnService.columnList(table.getId());

        List<String> ceIdList = new ArrayList<>();
        for (C_Detail column : columnList)
            if (StringUtils.hasText(column.getcEId()) && !ceIdList.contains(column.getcEId()))
                ceIdList.add(column.getcEId());

        List<CE_Detail> constEnumList = constEnumService.detailList(ceIdList);

        for (CE_Detail constEnum : constEnumList) {
            if (ts) {
                result.put(String.format("%s.ts",
                                         Paths.get("api",
                                                   "dto",
                                                   table.getModuleName()
                                                        .toLowerCase(Locale.ROOT),
                                                   constEnum.getName())),
                           generate(table,
                                    constEnum,
                                    null,
                                    GenerateType.TS常量_枚举,
                                    packageName));
            } else {
                if (StringExtension.ignoreCaseEquals(constEnum.getType(),
                                                     "枚举")) result.put(String.format("%s.java",
                                                                                     Paths.get("dto",
                                                                                               table.getModuleName()
                                                                                                    .toLowerCase(Locale.ROOT),
                                                                                               constEnum.getName())),
                                                                       generate(table,
                                                                                constEnum,
                                                                                null,
                                                                                GenerateType.枚举,
                                                                                packageName));
                else result.put(String.format("%s.java",
                                              Paths.get("dto",
                                                        table.getModuleName()
                                                             .toLowerCase(Locale.ROOT),
                                                        constEnum.getName())),
                                generate(table,
                                         constEnum,
                                         null,
                                         GenerateType.常量,
                                         packageName));
            }
        }
        return result;
    }

    /**
     * 生成服务接口类代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @param ts          是否为TS代码
     * @return <条目, 内容>
     */
    private Map<String, String> generateService(T_Detail table,
                                                String packageName,
                                                boolean ts)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        if (ts) result.put(String.format("%s.ts",
                                         Paths.get("api",
                                                   "service",
                                                   table.getModuleName()
                                                        .toLowerCase(Locale.ROOT),
                                                   String.format("%sService",
                                                                 table.getBusinessName()))),
                           generate(table,
                                    null,
                                    null,
                                    GenerateType.TS接口服务,
                                    packageName));
        else result.put(String.format("%s.java",
                                      Paths.get("service",
                                                table.getModuleName()
                                                     .toLowerCase(Locale.ROOT),
                                                String.format("I%sService",
                                                              table.getBusinessName()))),
                        generate(table,
                                 null,
                                 null,
                                 GenerateType.服务接口类,
                                 packageName));
        return result;
    }

    /**
     * 生成服务实现类代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateServiceImpl(T_Detail table,
                                                    String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s.java",
                                 Paths.get("service",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           "impl",
                                           String.format("%sServiceImpl",
                                                         table.getBusinessName()))),
                   generate(table,
                            null,
                            null,
                            GenerateType.服务实现类,
                            packageName));
        return result;
    }

    /**
     * 生成控制器代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateController(T_Detail table,
                                                   String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s.java",
                                 Paths.get("web",
                                           "controller",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           String.format("%sController",
                                                         table.getBusinessName()))),
                   generate(table,
                            null,
                            null,
                            GenerateType.控制器,
                            packageName));
        return result;
    }

    /**
     * 生成TS模型字段集合代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateModelFields(T_Detail table,
                                                    String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s_Fields.ts",
                                 Paths.get("api",
                                           "dto",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           StringExtension.firstChar2LowerCase(table.getBusinessName()),
                                           StringExtension.justFirstChar(table.getBusinessName()))),
                   generate(table,
                            null,
                            null,
                            GenerateType.TS模型字段集合,
                            packageName));
        return result;
    }

    /**
     * 生成Vue管理页代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateVueIndex(T_Detail table,
                                                 String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s.vue",
                                 Paths.get("views",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           StringExtension.firstChar2LowerCase(table.getBusinessName()),
                                           "index")),
                   generate(table,
                            null,
                            null,
                            GenerateType.Vue管理页,
                            packageName));
        return result;
    }

    /**
     * 生成Vue详情页代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateVueDetails(T_Detail table,
                                                   String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s.vue",
                                 Paths.get("views",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           StringExtension.firstChar2LowerCase(table.getBusinessName()),
                                           "details")),
                   generate(table,
                            null,
                            null,
                            GenerateType.Vue详情页,
                            packageName));
        return result;
    }

    /**
     * 生成Vue新增页代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateVueAdd(T_Detail table,
                                               String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s.vue",
                                 Paths.get("views",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           StringExtension.firstChar2LowerCase(table.getBusinessName()),
                                           "add")),
                   generate(table,
                            null,
                            null,
                            GenerateType.Vue新增页,
                            packageName));
        return result;
    }

    /**
     * 生成Vue编辑页代码
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateVueEdit(T_Detail table,
                                                String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s.vue",
                                 Paths.get("views",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           StringExtension.firstChar2LowerCase(table.getBusinessName()),
                                           "edit")),
                   generate(table,
                            null,
                            null,
                            GenerateType.Vue编辑页,
                            packageName));
        return result;
    }

    /**
     * 生成添加菜单的Sql语句
     *
     * @param table       数据库表
     * @param packageName 包名
     * @return <条目, 内容>
     */
    private Map<String, String> generateSql4Menu(T_Detail table,
                                                 String packageName)
            throws
            Exception {
        Map<String, String> result = new HashMap<>();
        result.put(String.format("%s.sql",
                                 Paths.get("menu",
                                           table.getModuleName()
                                                .toLowerCase(Locale.ROOT),
                                           table.getBusinessName())),
                   generate(table,
                            null,
                            null,
                            GenerateType.添加菜单的Sql语句,
                            packageName));
        return result;
    }

    /**
     * 将内容写入输出流
     *
     * @param os      输出流
     * @param content 内容
     * @return 写入字节数
     */
    int write(OutputStream os,
              String content)
            throws
            Exception {
        if (!StringUtils.hasText(content)) return 0;

        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        os.write(bytes,
                 0,
                 bytes.length);
        return bytes.length;
    }

    @Override
    public Map<String, String> generateTypeMap() {
        return Arrays.stream(GenerateType.values())
                     .collect(Collectors.toMap(Enum::name,
                                               GenerateType::toString));
    }

    @Override
    public Map<String, Map<String, String>> previewCode(String tableId,
                                                        String packageName,
                                                        Collection<String> generateTypes) {
        try {
            Map<String, Map<String, String>> result = new HashMap<>();

            // 查询表信息
            T_Detail table = tableService.detail(tableId);

            List<GenerateType> generateTypes_;

            //生成代码
            if (CollectionsExtension.anyPlus(generateTypes)) generateTypes_ = generateTypes.stream()
                                                                                           .map(GenerateType::toEnum)
                                                                                           .collect(Collectors.toList());
            else generateTypes_ = Arrays.stream(GenerateType.values())
                                        .collect(Collectors.toList());

            for (GenerateType type : generateTypes_) {
                switch (type) {
                    case 实体类:
                        result.put(GenerateType.实体类.name(),
                                   generateEntity(table,
                                                  packageName));
                        break;
                    case 实体字段集合:
                        result.put(GenerateType.实体字段集合.name(),
                                   generateEntityFields(table,
                                                        packageName));
                        break;
                    case 业务模型:
                        result.put(GenerateType.业务模型.name(),
                                   generateDTO(table,
                                               packageName,
                                               false));
                        break;
                    case 常量_枚举:
                        result.put(GenerateType.常量_枚举.name(),
                                   generateConstEnum(table,
                                                     packageName,
                                                     false));
                        break;
                    case 服务接口类:
                        result.put(GenerateType.服务接口类.name(),
                                   generateService(table,
                                                   packageName,
                                                   false));
                        break;
                    case 服务实现类:
                        result.put(GenerateType.服务实现类.name(),
                                   generateServiceImpl(table,
                                                       packageName));
                        break;
                    case 控制器:
                        result.put(GenerateType.控制器.name(),
                                   generateController(table,
                                                      packageName));
                        break;
                    case TS模型字段集合:
                        result.put(GenerateType.TS模型字段集合.name(),
                                   generateModelFields(table,
                                                       packageName));
                        break;
                    case TS业务模型:
                        result.put(GenerateType.TS业务模型.name(),
                                   generateDTO(table,
                                               packageName,
                                               true));
                        break;
                    case TS接口服务:
                        result.put(GenerateType.TS接口服务.name(),
                                   generateService(table,
                                                   packageName,
                                                   true));
                        break;
                    case TS常量_枚举:
                        result.put(GenerateType.TS常量_枚举.name(),
                                   generateConstEnum(table,
                                                     packageName,
                                                     true));
                        break;
                    case Vue管理页:
                        result.put(GenerateType.Vue管理页.name(),
                                   generateVueIndex(table,
                                                    packageName));
                        break;
                    case Vue详情页:
                        result.put(GenerateType.Vue详情页.name(),
                                   generateVueDetails(table,
                                                      packageName));
                        break;
                    case Vue新增页:
                        result.put(GenerateType.Vue新增页.name(),
                                   generateVueAdd(table,
                                                  packageName));
                        break;
                    case Vue编辑页:
                        result.put(GenerateType.Vue编辑页.name(),
                                   generateVueEdit(table,
                                                   packageName));
                        break;
                    case 添加菜单的Sql语句:
                        result.put(GenerateType.添加菜单的Sql语句.name(),
                                   generateSql4Menu(table,
                                                    packageName));
                        break;
                }
            }

            return result;
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException("预览失败",
                                        ex);
        }
    }

    @Override
    public void downloadCode(Collection<String> tableIds,
                             String packageName,
                             Collection<String> generateTypes) {
        try {
            //设置内容类型
            response.setContentType("application/zip");
            //设置下载文件名
            response.setHeader("Content-Disposition",
                               "attachment; filename=\"output.zip\"");
            long length = 0;

            //压缩包
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));

            for (String tableId : tableIds) {
                //生成代码内容
                Map<String, Map<String, String>> previewMap = previewCode(tableId,
                                                                          packageName,
                                                                          generateTypes);

                for (String type : previewMap.keySet()) {
                    Map<String, String> codeMap = previewMap.get(type);
                    for (String entry : codeMap.keySet()) {
                        //创建条目
                        zipOutputStream.putNextEntry(new ZipEntry(Paths.get(type,
                                                                            entry)
                                                                       .toString()));
                        //写入内容
                        length += write(zipOutputStream,
                                        codeMap.get(entry));
                    }
                }
            }

            //刷新输出流
            zipOutputStream.flush();
            //关闭输出流
            zipOutputStream.close();

            response.setContentLengthLong(length);
            response.setStatus(HttpStatus.OK.value());
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new BusinessException("生成失败",
                                        ex);
        }
    }
}
