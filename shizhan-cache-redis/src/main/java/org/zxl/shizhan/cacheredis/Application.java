package org.zxl.shizhan.cacheredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//@ServletComponentScan("org.zxl.shizhan.cacheredis")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
