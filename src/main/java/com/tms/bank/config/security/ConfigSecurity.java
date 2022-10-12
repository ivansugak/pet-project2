package com.tms.bank.config.security;

import com.tms.bank.enums.Permission;
import com.tms.bank.exception.CustomAccessDeniedHandler;
import com.tms.bank.servises.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/registration", "/about", "/help", "/images/**", "/vacancies").permitAll()
                .antMatchers(HttpMethod.POST, "/vacancies/**", "/admin").hasAuthority(Permission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/vacancies/**").hasAuthority(Permission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/vacancies/**", "/admin").hasAuthority(Permission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/admin").hasAuthority(Permission.USER_WRITE.getPermission())
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
                .and()
                .formLogin()
                .loginPage("/auth")
                .defaultSuccessUrl("/vacancies.html")
                .failureUrl("/errorAuth.html?error=true")
                .permitAll()
                .and()
                .logout()
//                .logoutUrl("/")
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/home.html")
                .invalidateHttpSession(true)
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12, new SecureRandom());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }
}
