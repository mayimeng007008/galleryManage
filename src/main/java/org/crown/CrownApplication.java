package org.crown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ServletComponentScan//打包成war包的必须配置项 extends SpringBootServletInitializer
public class CrownApplication{

	/*
	 * @Override protected SpringApplicationBuilder
	 * configure(SpringApplicationBuilder application) { return
	 * application.sources(CrownApplication.class); }
	 */
	
    public static void main(String[] args) {
        SpringApplication.run(CrownApplication.class, args);
    }
}
