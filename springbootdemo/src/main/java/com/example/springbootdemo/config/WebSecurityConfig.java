package com.example.springbootdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @Description:
 * @Author: liuweixin
 * @Date: 2019/3/7
 * @Time: 16:58
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Autowired
    private AccessDeniedHandler accessDeniedHandler;
*/
    /*@Autowired
    private CustAuthenticationProvider custAuthenticationProvider;*/


    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    //重写了其中的configure（）方法设置了不同url的不同访问权限
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/home", "/about","/img/*").permitAll()
                .antMatchers("/admin/**","/upload/**").hasAnyRole("ADMIN")
                .antMatchers("/order/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/room/**").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }*/

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("password")).roles("USER");
    }

    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication()
//                .withUser("user").password("user").roles("USER")
//                .and()
//                .withUser("admin").password("admin").roles("ADMIN");

//        auth.jdbcAuthentication()

        auth.authenticationProvider(custAuthenticationProvider);
    }*/

}
