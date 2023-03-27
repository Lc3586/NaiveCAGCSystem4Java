package top.lctr.naive.cagc.system.dto;

import project.extension.openapi.annotations.OpenApiDescription;

import java.util.Arrays;
import java.util.Optional;

/**
 * 常量/枚举类型
 *
 * @author LCTR
 * @date 2022-07-04
 */
public enum ConstEnumType {
    /**
     * 常量
     */
    @OpenApiDescription("常量" )
    常量(1,
       "常量" ),
    /**
     * 枚举
     */
    @OpenApiDescription("枚举" )
    枚举(2,
       "枚举" );

    /**
     * @param index 索引
     * @param value 值
     */
    ConstEnumType(int index,
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
    public static ConstEnumType toEnum(String value)
            throws
            IllegalArgumentException {
        Optional<ConstEnumType> find = Arrays.stream(ConstEnumType.values())
                                             .filter(x -> x.value.equals(value))
                                             .findFirst();
        if (!find.isPresent())
            throw new IllegalArgumentException(String.format("未找到符合%s此值的ConstEnumType枚举中" ,
                                                             value));
        return find.get();
    }

    /**
     * 获取枚举
     *
     * @param index 索引
     * @return 枚举
     */
    public static ConstEnumType toEnum(int index)
            throws
            IllegalArgumentException {
        for (ConstEnumType value : ConstEnumType.values()) {
            if (value.getIndex() == index)
                return value;
        }

        throw new IllegalArgumentException(String.format("指定索引%s无效" ,
                                                         index));
    }
}
