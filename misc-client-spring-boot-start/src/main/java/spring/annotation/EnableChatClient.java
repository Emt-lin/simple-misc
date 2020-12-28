package spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ChatClientConfiguration.class)
public @interface EnableChatClient {



}
