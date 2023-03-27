package top.lctr.naive.cagc.system.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.extension.openapi.annotations.*;
import project.extension.standard.api.response.ApiResultData;
import top.lctr.naive.cagc.system.business.service.Interface.ICagcService;

import java.util.Collection;
import java.util.Map;

/**
 * 代码自动生成服务控制器
 *
 * @author LCTR
 * @date 2022-06-15
 */
@RestController
@RequestMapping("/cagc")
@Scope("prototype")
@Api(tags = "代码自动生成")
@OpenApiGroup("代码自动生成")
public class CagcController
        extends BaseController {
    public CagcController(ICagcService cagcService) {
        this.cagcService = cagcService;
    }

    private final ICagcService cagcService;

    /**
     * 获取所有生成类型
     *
     * @return 生成类型键值对集合
     */
    @GetMapping(value = "/generateType-map",
                consumes = "*/*")
    @OpenApiAllowAnonymous
    @ApiOperation("获取所有生成类型")
    @OpenApiModel(type = Map.class,
                  genericTypes = {
                          @OpenApiGenericType(arguments = {@OpenApiGenericTypeArgument(String.class),
                                                           @OpenApiGenericTypeArgument(String.class)})},
                  description = "生成类型键值对集合")
    public Object generateTypeMap() {
        return ApiResultData.success(cagcService.generateTypeMap());
    }

    /**
     * 预览代码
     *
     * @param tableId       数据库表Id
     * @param packageName   包名（默认值 com.ruoyi）
     * @param generateTypes 生成类型集合
     * @return <类型, <条目, 内容>>
     */
    @PreAuthorize("@aph.isPass('cagc:preview')")
    @GetMapping(value = "/preview-code/{tableId}",
                consumes = "*/*")
    @ApiOperation("预览代码")
    @OpenApiModel(type = Map.class,
                  genericTypes = {@OpenApiGenericType(arguments = {@OpenApiGenericTypeArgument(String.class),
                                                                   @OpenApiGenericTypeArgument(Map.class)}),
                                  @OpenApiGenericType(arguments = {@OpenApiGenericTypeArgument(String.class),
                                                                   @OpenApiGenericTypeArgument(String.class)},
                                                      level = 1)},
                  description = "<类型, <条目, 内容>>")
    public Object previewCode(
            @Parameter(name = "tableId",
                       description = "数据库表Id")
            @PathVariable("tableId")
                    String tableId,
            @Parameter(name = "packageName",
                       description = "包名")
            @RequestParam(name = "packageName",
                          required = false,
                          defaultValue = "com.ruoyi")
                    String packageName,
            @Parameter(name = "generateTypes",
                       description = "生成类型集合")
            @RequestParam(name = "generateTypes",
                          required = false)
                    Collection<String> generateTypes) {
        return ApiResultData.success(cagcService.previewCode(tableId,
                                                             packageName,
                                                             generateTypes));
    }

    /**
     * 下载代码
     *
     * @param tableIds      数据库表Id集合
     * @param packageName   包名（默认值 com.ruoyi）
     * @param generateTypes 生成类型集合
     */
    @PreAuthorize("@aph.isPass('cagc:generate')")
    @GetMapping(value = "/download-code",
                consumes = "*/*")
    @ApiOperation(value = "生成代码",
                  notes = "输出文件流",
                  consumes = "*/*",
                  response = Object.class)
    public void downloadCode(
            @Parameter(name = "tableIds",
                       description = "数据库表Id集合")
            @RequestParam(name = "tableIds")
                    Collection<String> tableIds,
            @Parameter(name = "packageName",
                       description = "包名")
            @RequestParam(name = "packageName",
                          required = false,
                          defaultValue = "com.ruoyi")
                    String packageName,
            @Parameter(name = "generateTypes",
                       description = "生成类型集合")
            @RequestParam(name = "generateTypes",
                          required = false)
                    Collection<String> generateTypes) {
        cagcService.downloadCode(tableIds,
                                 packageName,
                                 generateTypes);
    }
}
