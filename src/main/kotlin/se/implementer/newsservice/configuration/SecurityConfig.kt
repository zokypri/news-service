package se.implementer.newsservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig {

  @Bean
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    return http
      .sessionManagement { customizer: SessionManagementConfigurer<HttpSecurity?> ->
        customizer.sessionCreationPolicy(
          SessionCreationPolicy.STATELESS,
        )
      }
      .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
      .authorizeHttpRequests { customizer: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry ->
        customizer // opening swagger, /admin, etc
          .requestMatchers(
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
          )
          .permitAll() // Security for internal APIs of this service
          .requestMatchers("/external/**")
          .permitAll()
          .anyRequest().authenticated()
      }
      .build()
  }
}
