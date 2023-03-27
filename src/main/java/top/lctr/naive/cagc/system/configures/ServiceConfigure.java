package top.lctr.naive.cagc.system.configures;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 服务配置
 *
 * @author LCTR
 * @date 2023-03-03
 */
@Component
@Order(value = 1)
public class ServiceConfigure
        implements ApplicationRunner {
    public ServiceConfigure() {

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

    /**
     * 关闭各个服务
     */
    @PreDestroy
    public void shutDown() {

    }
}
