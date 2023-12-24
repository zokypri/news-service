package se.implementer.newsservice.configuration

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OkHttpConfig {

    @Bean
    fun okHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            // Add any configurations if needed later
            .build()
    }
}