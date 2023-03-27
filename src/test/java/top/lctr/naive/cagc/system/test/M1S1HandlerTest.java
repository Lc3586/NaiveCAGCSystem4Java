package top.lctr.naive.cagc.system.test;

import org.junit.jupiter.api.*;

/**
 * 1.1、模块测试
 *
 * @author LCTR
 * @date 2023-02-14
 */
@Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class M1S1HandlerTest {
    /**
     * 设置
     */
    @BeforeEach
    public void setup() {

    }

    /**
     * 清理
     */
    @AfterAll
    public static void cleanUp()
            throws
            Throwable {
        System.out.println("正在清理");

        System.out.println("模块已关闭");

        System.out.println("清理完成");
    }

    /**
     * 检查启动状态
     */
    @Test
    @DisplayName("1.1.1、检查启动状态")
    @Order(1001001)
    public void M1S1P1()
            throws
            Throwable {

    }

    /**
     * 检查合并结果
     */
    @Test
    @DisplayName("1.1.2、检查合并结果")
    @Order(1001002)
    public void M1S1P2()
            throws
            Throwable {

    }
}
