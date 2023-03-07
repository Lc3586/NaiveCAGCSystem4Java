package top.lctr.cagc.configures;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 启动各个服务
 *
 * @author LCTR
 * @date 2023-03-03
 */
@Component
@Order(value = 1)
public class StartService
        implements ApplicationRunner {
    public StartService() {

    }

    /**
     * 运行各个服务
     *
     * @param args 参数
     */
    @Override
    public void run(ApplicationArguments args) {
//        CompletableFuture.runAsync(handler::start,
//                                   Executors.newSingleThreadExecutor());
    }
}
