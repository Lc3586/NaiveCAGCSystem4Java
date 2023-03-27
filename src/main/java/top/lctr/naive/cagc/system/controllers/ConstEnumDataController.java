package top.lctr.naive.cagc.system.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.extension.mybatis.edge.extention.datasearch.DataSearchDTO;
import project.extension.openapi.annotations.*;
import project.extension.openapi.fastjson.ApiDataSchemaExtension;
import project.extension.openapi.fastjson.JsonExtension;
import project.extension.openapi.model.ApiData.*;
import project.extension.standard.api.response.ApiResultData;
import top.lctr.naive.cagc.system.business.service.Interface.IConstEnumDataService;
import top.lctr.naive.cagc.system.dto.constEnumDataDTO.*;

import java.util.Collection;

/**
 * 常量/枚举数据服务控制器
 *
 * @author LCTR
 * @date 2022-06-15
 */
@RestController
@RequestMapping(path = "/cagc/const-enum-data",
                consumes = "application/json",
                produces = "application/json")
@Scope("prototype")
@Api(tags = "常量/枚举数据")
@OpenApiGroup("代码自动生成")
public class ConstEnumDataController
        extends BaseController {
    private final IConstEnumDataService constEnumDataService;

    public ConstEnumDataController(IConstEnumDataService constEnumDataService) {
        this.constEnumDataService = constEnumDataService;
    }

    /**
     * 列表数据
     *
     * @param dataSearch 搜索参数
     * @return 列表数据
     */
    @PreAuthorize("@aph.isPass('cagc:const-enum-data:list')")
    @PostMapping("/list")
    @ApiOperation("获取列表数据")
    @OpenApiModels(defaultGenericTypes = {@OpenApiGenericType(arguments = {@OpenApiGenericTypeArgument(CED_List.class)})},
                   defaultDescription = "列表数据",
                   value = {@OpenApiModel(type = RuoyiResult.class,
                                          summary = "Ruoyi框架数据结构方案（默认）",
                                          defaultModel = true),
                            @OpenApiModel(type = LayuiResult.class,
                                          summary = "Layui数据结构方案"),
                            @OpenApiModel(type = JqGridResult.class,
                                          summary = "JqGrid数据结构方案"),
                            @OpenApiModel(type = EasyuiResult.class,
                                          summary = "Easyui、BootstrapConstEnumData数据结构方案"),
                            @OpenApiModel(type = AntdVueResult.class,
                                          summary = "AntdVue数据结构方案"),
                            @OpenApiModel(type = ElementVueResult.class,
                                          summary = "ElementVue数据结构方案")})
    public Object list(
            @OpenApiModel(description = "搜索参数")
            @RequestBody
                    DataSearchDTO dataSearch) {
        return JsonExtension.toOpenApiJson(ApiDataSchemaExtension.buildResult(dataSearch.getSchema(),
                                                                              constEnumDataService.list(dataSearch),
                                                                              dataSearch.getPagination()),
                                           CED_List.class);
    }

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    @PreAuthorize("@aph.isPass('cagc:const-enum-data:detail')")
    @GetMapping(value = "/detail/{id}",
                consumes = "*/*")
    @ApiOperation("获取详情数据")
    @OpenApiModel(value = CED_Detail.class,
                  description = "详情数据")
    public Object detail(
            @Parameter(name = "id",
                       description = "主键")
            @PathVariable("id")
                    String id) {
        return JsonExtension.toOpenApiJson(ApiResultData.success(constEnumDataService.detail(id)),
                                           CED_Detail.class);
    }

    /**
     * 新增
     *
     * @param data 数据
     */
    @PreAuthorize("@aph.isPass('cagc:const-enum-data:create')")
    @PostMapping("/create")
    @ApiOperation("新增")
    public Object create(
            @OpenApiModel(description = "数据")
            @RequestBody
                    CED_Create data) {
        constEnumDataService.create(data);
        return ApiResultData.success();
    }

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    @PreAuthorize("@aph.isPass('cagc:const-enum-data:edit')")
    @GetMapping(value = "/edit/{id}",
                consumes = "*/*")
    @ApiOperation("获取编辑数据")
    @OpenApiModel(value = CED_Edit.class,
                  description = "编辑数据")
    public Object edit(
            @Parameter(name = "id",
                       description = "主键")
            @PathVariable("id")
                    String id) {
        return JsonExtension.toOpenApiJson(ApiResultData.success(constEnumDataService.edit(id)),
                                           CED_Edit.class);
    }

    /**
     * 编辑
     *
     * @param data 数据
     */
    @PreAuthorize("@aph.isPass('cagc:const-enum-data:edit')")
    @PostMapping("/edit")
    @ApiOperation("编辑")
    public Object edit(
            @OpenApiModel(description = "数据")
            @RequestBody
                    CED_Edit data) {
        constEnumDataService.edit(data);
        return ApiResultData.success();
    }

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    @PreAuthorize("@aph.isPass('cagc:const-enum-data:delete')")
    @PostMapping("/delete")
    @ApiOperation("删除")
    public Object delete(
            @RequestBody
                    Collection<String> ids) {
        constEnumDataService.delete(ids);
        return ApiResultData.success();
    }
}
