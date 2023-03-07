package top.lctr.cagc;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import top.lctr.cagc.application.SpringBootTestApplication;
import top.lctr.cagc.test.M1S0JasyptTest;
import top.lctr.cagc.test.M1S1HandlerTest;

/**
 * 测试合集
 *
 * @author LCTR
 * @date 2023-02-08
 */
@DisplayName("测试合集")
@SpringBootTest(classes = SpringBootTestApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TestCollection {
    @Nested
    @Order(1000)
    @DisplayName("1.0.Jasypt加密解密测试")
    public class ForM1S0JasyptTest
            extends M1S0JasyptTest {
    }

    @Nested
    @Order(1001)
    @DisplayName("1.1.模块测试")
    public class ForM1S1HandlerTest
            extends M1S1HandlerTest {
    }
}
