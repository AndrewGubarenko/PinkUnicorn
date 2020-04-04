package com.pink.unicorn.security;

import com.pink.unicorn.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AppUserDetailsService userDetailsService;
    private final AuthenticationProviderImpl authProvider;

    @Autowired
    public WebSecurityConfiguration (AppUserDetailsService userDetailsService,
                                     AuthenticationProviderImpl authProvider) {
        this.userDetailsService = userDetailsService;
        this.authProvider = authProvider;
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Value("10")
    private int bcryptStrength;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    //TODO: Change it before production
    @Override
    public void configure(WebSecurity web) {
        web.debug(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/index*", "/json/**" , "/*.ico", "/images/**", "/PinkUnicorn/**").permitAll()
                /*.antMatchers(HttpMethod.GET, "/PinkUnicorn/users/**").hasRole(Role.USER.name())
                .antMatchers(HttpMethod.GET, "/PinkUnicorn/users/**").authenticated()
                .antMatchers(HttpMethod.POST, "/PinkUnicorn/users/**").hasRole(Role.USER.name())
                .antMatchers(HttpMethod.POST, "/PinkUnicorn/users/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/PinkUnicorn/users/**").hasRole(Role.USER.name())
                .antMatchers(HttpMethod.PUT, "/PinkUnicorn/users/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/PinkUnicorn/users/**").hasRole(Role.USER.name())
                .antMatchers(HttpMethod.DELETE, "/PinkUnicorn/users/**").authenticated()

                .antMatchers("/PinkUnicorn/admin/**").hasRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/PinkUnicorn/authentication").permitAll()
                .defaultSuccessUrl("/PinkUnicorn/users/{id}",true)
                .failureForwardUrl("/PinkUnicorn/access-denied")
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/GonnaCode/access-denied")*/
                .and()
                .csrf().disable()
                .cors()
                .and()
                .headers().frameOptions().sameOrigin()
        ;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "content-type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(bcryptStrength);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
