package top.lctr.cagc.configures;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 关闭各个服务
 *
 * @author LCTR
 * @date 2023-02-10
 */
@Component
public class ShutdownService {
    public ShutdownService() {

    }

    /**
     * 关闭各个服务
     */
    @PreDestroy
    public void shutDown() {

    }
}
