package com.eazybank.simpleSpringbootSecurity.springSecurityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //lambda expression for the CorsConfigurationSource class
        http.cors().configurationSource(request ->  {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.addAllowedOrigin("http://localhost:4200");
                        configuration.setAllowCredentials(true);
                        configuration.addAllowedHeader("*");
                        configuration.addAllowedMethod("*");
                        configuration.setMaxAge(3600L);
                        return configuration;

                }).and().csrf().ignoringAntMatchers("/contact").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .authorizeRequests().antMatchers("/myAccount").hasAuthority("write")
                .antMatchers("/myBalance").hasAuthority("read")
                .antMatchers("/myCards").hasAuthority("read")
                .antMatchers("/user").authenticated()
                .antMatchers("/contacts").permitAll()
                .antMatchers("/myLoans").hasAuthority("delete")
                .antMatchers("/notices").permitAll();
        http.formLogin();
        http.httpBasic();
    }
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails user1  = User.withUsername("admin").password("1234").authorities("admin").build();
        UserDetails user2 = User.withUsername("amr").password("1234").authorities("read").build();
        userDetailsManager.createUser(user1);
        userDetailsManager.createUser(user2);
        auth.userDetailsService(userDetailsManager);
    }*/
/*    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
