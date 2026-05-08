package com.example.SpringSecurity6WithSpringBoot.config;

import com.example.SpringSecurity6WithSpringBoot.service.MyOrgUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http){
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request->
                        request.requestMatchers("/login","/register").permitAll()
                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults()) //enables form login with browser
                .httpBasic(Customizer.withDefaults()) //enables REST api access,like postman usage
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
//    /////////////////////////////////
//    explanation of -> http.csrf(AbstractHttpConfigurer::disable)
//    Customizer<CsrfConfigurer<HttpSecurity>> customCsrf = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//    @Override
//    public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//        httpSecurityCsrfConfigurer.disable();
//    }
//    Thus it gets disbled as using lambda expression.
//    .........................

//    return http.build();

//  when this UserDetailsService bean is declared the Springframework do not
//  use the spring.security.user.name and passowrd from application properties anymore.
//    UserDetailsService is an interface. class InMemoryUserDetailsManager implements UserDetailsManager. So we can return InMemoryUserDetailsManager
//  @Bean
//    UserDetailsService userDetailsService(){
//      UserDetails user1 = User
//              .withDefaultPasswordEncoder()
//              .username("Sayan")
//              .password("12345")
//              .roles("USER")
//              .build();
//      UserDetails user2 = User.withDefaultPasswordEncoder() .username("Sayantan") .password("12345").roles("USER").build();
//        return new InMemoryUserDetailsManager(user1, user2);
//  }
//    ///////////////////////////////


//    //////////////////////////////
//  AuthenticationObject (UnAuthenticated) --> ((AuthenticationProvider)) --> AuthenticationObject (Authenticated)
//    We can use our own AuthenticationProvider which can use database or LDAP to check user details
    @Bean
    public AuthenticationProvider authenticationProvider(){
        MyOrgUserDetailsService myOrgUserDetailsService = new MyOrgUserDetailsService();
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(myOrgUserDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}





