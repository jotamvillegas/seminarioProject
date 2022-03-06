package com.seminario.sleepingMotorhome.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public BCryptPasswordEncoder passwordEncoder (){
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/sleepingMotorhome/all")
                .hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/sleepingMotorhome/login")
                .permitAll()
                .defaultSuccessUrl("/sleepingMotorhome/all")
                .failureUrl("/sleepingMotorhome/login?error=true")
        ;
    }


    // de aca para abajo anda todo


    //@Override
    //protected void configure (AuthenticationManagerBuilder auth) throws Exception {
    //    auth.inMemoryAuthentication()
    //            .withUser("admin")
    //            .password("{noop}12345")
    //            .roles("ADMIN", "USER")
    //            .and()
    //            .withUser("user")
    //            .password("{noop}12345")
    //            .roles("USER")
    //    ;
    //}

    //@Override
    //protected void configure (HttpSecurity http) throws Exception {
    //    http.csrf().disable()
    //            .authorizeRequests()
    //            .antMatchers("/sleepingMotorhome/all")
    //            .hasRole("ADMIN")
    //            .and()
    //            .formLogin()
    //            .loginPage("/sleepingMotorhome/login")
    //            .permitAll()
    //            .defaultSuccessUrl("/sleepingMotorhome/all")
    //            .failureUrl("/sleepingMotorhome/login?error=true")
    //    ;
    //}

    @Override
    public void configure (WebSecurity web){
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
    }

}
