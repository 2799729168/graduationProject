package com.myclient2;

import com.myclient2.config.MainConfig;
import com.myclient2.mapper.TestMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author pb
 */
@SpringBootApplication
@EnableSwagger2
public class Myclient2Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        TestMapper bean = annotationConfigApplicationContext.getBean(TestMapper.class);
        System.out.println(bean.selectAll("1"));
        bean.test();
//        SpringApplication.run(Myclient2Application.class, args);

    }

}
