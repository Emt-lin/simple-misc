package spring.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 客户端启动配置中心
 *
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = {"com.misc.spring"})
public class ChatClientConfiguration {


}
