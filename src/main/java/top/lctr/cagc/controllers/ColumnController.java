package top.lctr.cagc.controllers;

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
import project.extension.standard.api.request.datasort.DataSortDTO;
import project.extension.standard.api.request.datasort.DragSortDTO;
import project.extension.standard.api.response.ApiResultData;
import top.lctr.cagc.business.service.Interface.IColumnService;
import top.lctr.cagc.dto.columnDTO.*;

import java.util.Collection;

/**
 * 数据库列服务控制器
 *
 * @author LCTR
 * @date 2022-06-15
 */
@RestController
@RequestMapping(path = "/cagc/column",
                consumes = "application/json",
                produces = "application/json")
@Scope("prototype")
@Api(tags = "数据库列")
@OpenApiGroup("代码自动生成")
public class ColumnController
        extends BaseController {
    private final IColumnService columnService;

    public ColumnController(IColumnService columnService) {
        this.columnService = columnService;
    }

    /**
     * 列表数据
     *
     * @param dataSearch 搜索参数
     * @return 列表数据
     */
    @PreAuthorize("@aph.isPass('cagc:column:list')")
    @PostMapping("/list")
    @ApiOperation("获取列表数据")
    @OpenApiModels(defaultGenericTypes = {@OpenApiGenericType(arguments = {@OpenApiGenericTypeArgument(C_List.class)})},
                   defaultDescription = "列表数据",
                   value = {@OpenApiModel(type = RuoyiResult.class,
                                          summary = "Ruoyi框架数据结构方案（默认）",
                                          defaultModel = true),
                            @OpenApiModel(type = LayuiResult.class,
                                          summary = "Layui数据结构方案"),
                            @OpenApiModel(type = JqGridResult.class,
                                          summary = "JqGrid数据结构方案"),
                            @OpenApiModel(type = EasyuiResult.class,
                                          summary = "Easyui、BootstrapColumn数据结构方案"),
                            @OpenApiModel(type = AntdVueResult.class,
                                          summary = "AntdVue数据结构方案"),
                            @OpenApiModel(type = ElementVueResult.class,
                                          summary = "ElementVue数据结构方案")})
    public Object list(
            @OpenApiModel(description = "搜索参数")
            @RequestBody
                    DataSearchDTO dataSearch) {
        return JsonExtension.toOpenApiJson(ApiDataSchemaExtension.buildResult(dataSearch.getSchema(),
                                                                              columnService.list(dataSearch),
                                                                              dataSearch.getPagination()),
                                           C_List.class);
    }

    /**
     * 详情数据
     *
     * @param id 主键
     * @return 详情数据
     */
    @PreAuthorize("@aph.isPass('cagc:column:detail')")
    @GetMapping(value = "/detail/{id}",
                consumes = "*/*")
    @ApiOperation("获取详情数据")
    @OpenApiModel(value = C_Detail.class,
                  description = "详情数据")
    public Object detail(
            @Parameter(name = "id",
                       description = "主键")
            @PathVariable("id")
                    String id) {
        return JsonExtension.toOpenApiJson(ApiResultData.success(columnService.detail(id)),
                                           C_Detail.class);
    }

//    /**
//     * 同步
//     *
//     * @param names 表名集合
//     */
//    @PreAuthorize("@aph.isPass('cagc:column:sync')")
//    @PostMapping("/sync")
//    @ApiOperation("同步")
//    public Object sync(
//            @RequestBody
//                    Collection<String> names)
//            throws
//            ServiceException {
//        columnService.sync(names);
//        return ApiResultData.success();
//    }

    /**
     * 新增
     *
     * @param data 数据
     */
    @PreAuthorize("@aph.isPass('cagc:column:create')")
    @PostMapping("/create")
    @ApiOperation("新增")
    public Object create(
            @OpenApiModel(description = "数据")
            @RequestBody
                    C_Create data) {
        columnService.create(data);
        return ApiResultData.success();
    }

    /**
     * 获取编辑数据
     *
     * @param id 主键
     * @return 编辑数据
     */
    @PreAuthorize("@aph.isPass('cagc:column:edit')")
    @GetMapping(value = "/edit/{id}",
                consumes = "*/*")
    @ApiOperation("获取编辑数据")
    @OpenApiModel(value = C_Edit.class,
                  description = "编辑数据")
    public Object edit(
            @Parameter(name = "id",
                       description = "主键")
            @PathVariable("id")
                    String id) {
        return JsonExtension.toOpenApiJson(ApiResultData.success(columnService.edit(id)),
                                           C_Edit.class);
    }

    /**
     * 编辑
     *
     * @param data 数据
     */
    @PreAuthorize("@aph.isPass('cagc:column:edit')")
    @PostMapping("/edit")
    @ApiOperation("编辑")
    public Object edit(
            @OpenApiModel(description = "数据")
            @RequestBody
                    C_Edit data) {
        columnService.edit(data);
        return ApiResultData.success();
    }

    /**
     * 删除
     *
     * @param ids 主键集合
     */
    @PreAuthorize("@aph.isPass('cagc:column:delete')")
    @PostMapping("/delete")
    @ApiOperation("删除")
    public Object delete(
            @RequestBody
                    Collection<String> ids) {
        columnService.delete(ids);
        return ApiResultData.success();
    }

    /**
     * 启用/禁用
     *
     * @param id     主键
     * @param enable true：启用，false：禁用
     */
    @PreAuthorize("@aph.isPass('cagc:column:enable')")
    @GetMapping(value = "/enable/{id}/{enable}",
                consumes = "*/*")
    @ApiOperation("启用/禁用")
    public Object enable(
            @Parameter(name = "id",
                       description = "主键")
            @PathVariable("id")
                    String id,
            @Parameter(name = "enable",
                       description = "true：启用，false：禁用")
            @PathVariable("enable")
                    Boolean enable) {
        columnService.enable(id,
                             enable);
        return ApiResultData.success();
    }

    /**
     * 锁定/解锁
     *
     * @param id     主键
     * @param locked true：锁定，false：解锁
     */
    @PreAuthorize("@aph.isPass('cagc:column:lock')")
    @GetMapping(value = "/lock/{id}/{locked}",
                consumes = "*/*")
    @ApiOperation("锁定/解锁")
    public Object lock(
            @Parameter(name = "id",
                       description = "主键")
            @PathVariable("id")
                    String id,
            @Parameter(name = "enable",
                       description = "true：锁定，false：解锁")
            @PathVariable("locked")
                    Boolean locked) {
        columnService.lock(id,
                           locked);
        return ApiResultData.success();
    }

    /**
     * 排序
     *
     * @param data 数据
     */
    @PreAuthorize("@aph.isPass('cagc:column:sort')")
    @PostMapping("/sort")
    @ApiOperation("排序")
    public Object sort(
            @OpenApiModel(description = "数据")
            @RequestBody
                    DataSortDTO<String> data) {
        columnService.sort(data);
        return ApiResultData.success();
    }

    /**
     * 拖动排序
     *
     * @param data 数据
     */
    @PreAuthorize("@aph.isPass('cagc:column:drag-sort')")
    @PostMapping("/drag-sort")
    @ApiOperation("拖动排序")
    public Object dragSort(
            @OpenApiModel(description = "数据")
            @RequestBody
                    DragSortDTO<String> data) {
        columnService.dragSort(data);
        return ApiResultData.success();
    }
}
