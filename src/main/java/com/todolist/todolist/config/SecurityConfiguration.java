package com.todolist.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserAutenticationService userAutenticationService;

    @Autowired
    public SecurityConfiguration(UserAutenticationService userAutenticationService){
        this.userAutenticationService = userAutenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userAutenticationService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/roles").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/test/public").permitAll()
                .antMatchers("/test/private").authenticated()
                .antMatchers("/test/managers").hasAuthority("MANAGER")
                .antMatchers("/test/developer").hasAuthority("DEVELOPER")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer(){
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){
            corsRegistry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
            }
        };

    }
}
