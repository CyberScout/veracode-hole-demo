package com.notaneye.demo.friendly


import okhttp3.OkHttpClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class FriendlyCustomerApplication {

	static void main(String[] args) {
		SpringApplication.run FriendlyCustomerApplication, args
	}

	@Bean
    OkHttpClient httpClient() {

        OkHttpClient client = new OkHttpClient()
        return client
    }
}
