package top.lctr.naive.cagc.system.dto;

import project.extension.openapi.annotations.OpenApiDescription;

import java.util.Arrays;
import java.util.Optional;

/**
 * 代码自动生成类型
 *
 * @author LCTR
 * @date 2022-06-09
 */
public enum GenerateType {
    /**
     * 实体类
     */
    @OpenApiDescription("实体类")
    实体类(100,
        "Entity"),
    /**
     * 实体字段集合
     */
    @OpenApiDescription("实体字段集合")
    实体字段集合(101,
           "EntityFields"),
    /**
     * 业务模型
     */
    @OpenApiDescription("业务模型")
    业务模型(102,
         "DTO"),
    /**
     * 方法专用的业务模型
     */
    @OpenApiDescription("方法专用的业务模型")
    方法专用的业务模型(103,
              "DTOFunUse"),
    /**
     * 常量/枚举
     */
    @OpenApiDescription("常量/枚举")
    常量_枚举(104,
          "Const_Enum"),
    /**
     * 常量
     */
    @OpenApiDescription("常量")
    常量(105,
       "Const"),
    /**
     * 枚举
     */
    @OpenApiDescription("枚举")
    枚举(106,
       "Enum"),
    /**
     * 服务接口类
     */
    @OpenApiDescription("服务接口类")
    服务接口类(107,
          "Service"),
    /**
     * 服务实现类
     */
    @OpenApiDescription("服务实现类")
    服务实现类(108,
          "ServiceImpl"),
    /**
     * 控制器
     */
    @OpenApiDescription("控制器")
    控制器(109,
        "Controller"),
    /**
     * TS模型字段集合
     */
    @OpenApiDescription("TS模型字段集合")
    TS模型字段集合(201,
             "TS_ModelFields"),
    /**
     * TS业务模型
     */
    @OpenApiDescription("TS业务模型")
    TS业务模型(202,
           "TS_DTO"),
    /**
     * TS接口服务
     */
    @OpenApiDescription("TS接口服务")
    TS接口服务(203,
           "TS_Service"),
    /**
     * TS常量/枚举
     */
    @OpenApiDescription("TS常量/枚举")
    TS常量_枚举(204,
            "TS_Const_Enum"),
    /**
     * Vue管理页
     */
    @OpenApiDescription("Vue管理页")
    Vue管理页(301,
           "VUE_Index"),
    /**
     * Vue详情页
     */
    @OpenApiDescription("Vue详情页")
    Vue详情页(302,
           "VUE_Detail"),
    /**
     * Vue新增页
     */
    @OpenApiDescription("Vue新增页")
    Vue新增页(303,
           "VUE_Add"),
    /**
     * Vue编辑页
     */
    @OpenApiDescription("Vue编辑页")
    Vue编辑页(304,
           "VUE_Edit"),
    /**
     * 添加菜单的Sql语句
     */
    @OpenApiDescription("添加菜单的Sql语句")
    添加菜单的Sql语句(401,
               "SQL_Menu");

    /**
     * @param index 索引
     * @param value 值
     */
    GenerateType(int index,
                 String value) {
        this.index = index;
        this.value = value;
    }

    /**
     * 索引
     */
    final int index;

    /**
     * 值
     */
    final String value;

    /**
     * 获取索引
     *
     * @return 索引
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * 获取字符串
     *
     * @return 值
     */
    @Override
    public String toString() {
        return this.value;
    }

    /**
     * 获取枚举
     *
     * @param value 值
     * @return 枚举
     */
    public static GenerateType toEnum(String value)
            throws
            IllegalArgumentException {
        Optional<GenerateType> find = Arrays.stream(GenerateType.values())
                                            .filter(x -> x.value.equals(value))
                                            .findFirst();
        if (!find.isPresent())
            throw new IllegalArgumentException(String.format("未找到符合%s此值的GenerateType枚举中",
                                                             value));
        return find.get();
    }

    /**
     * 获取枚举
     *
     * @param index 索引
     * @return 枚举
     */
    public static GenerateType toEnum(int index)
            throws
            IllegalArgumentException {
        for (GenerateType value : GenerateType.values()) {
            if (value.getIndex() == index)
                return value;
        }

        throw new IllegalArgumentException(String.format("指定索引%s无效",
                                                         index));
    }
}
