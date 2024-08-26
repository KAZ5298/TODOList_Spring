package todolist.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import todolist.security.CustomAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity   // このアノテーションはこのアプリではなくてよい
public class SecurityConfig {
    
    @Bean
    public AuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers(mvc.pattern("/login")).permitAll()
                .anyRequest().authenticated()
        );
        
        http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .failureHandler(customAuthenticationFailureHandler())
                .failureUrl("/login?error")
                .usernameParameter("user")
                .passwordParameter("pass")
                .defaultSuccessUrl("/todo", true)
                .permitAll()
        ).logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
        );
        
        http.headers(headers -> headers
                .frameOptions(FrameOptionsConfig::disable)
        );
        
        return http.build();
    }
    
}