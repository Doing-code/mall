package cn.forbearance.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author cristina
 */
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
@MapperScan({"cn.forbearance.mall.dao", "cn.forbearance.mall.dao.ex"})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}