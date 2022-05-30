package com.udacity.jwdnd.course1.cloudstorage.security;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   // @Autowired
  //  private AuthenticationService authenticationService;

    //public  SecurityConfiguration(AuthenticationService authenticationService) {
      //  this.authenticationService = authenticationService;
   // }

   // @Override
    ///protected void configure(AuthenticationManagerBuilder auth) {
      //  auth.authenticationProvider(this.authenticationService);
    //}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.headers().frameOptions().disable();

        http
                .authorizeRequests()
                .antMatchers("/**/*.css", "/**/*.js").permitAll()
                .antMatchers("/signup", "/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
        http.csrf().disable();
    }


}



















