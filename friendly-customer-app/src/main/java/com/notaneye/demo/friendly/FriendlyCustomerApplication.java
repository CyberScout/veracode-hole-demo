package com.notaneye.demo.friendly;


import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class FriendlyCustomerApplication {

    public static void main(String[] args) {

        SpringApplication.run(FriendlyCustomerApplication.class, args);
    }


    @Bean
    public OkHttpClient httpClient() {

        return new OkHttpClient();
    }

}
