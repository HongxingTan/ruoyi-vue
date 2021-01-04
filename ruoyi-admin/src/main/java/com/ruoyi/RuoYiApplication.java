package com.ruoyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("(♥◠‿◠)formssi启动成功\n" +
                " _______  _______  _______  _______  _______  _______ _________\n" +
                "(  ____ \\(  ___  )(  ____ )(       )(  ____ \\(  ____ \\\\__   __/\n" +
                "| (    \\/| (   ) || (    )|| () () || (    \\/| (    \\/   ) (   \n" +
                "| (__    | |   | || (____)|| || || || (_____ | (_____    | |   \n" +
                "|  __)   | |   | ||     __)| |(_)| |(_____  )(_____  )   | |   \n" +
                "| (      | |   | || (\\ (   | |   | |      ) |      ) |   | |   \n" +
                "| )      | (___) || ) \\ \\__| )   ( |/\\____) |/\\____) |___) (___\n" +
                "|/       (_______)|/   \\__/|/     \\|\\_______)\\_______)\\_______/\n" +
                "                                                               " );
    }
}
