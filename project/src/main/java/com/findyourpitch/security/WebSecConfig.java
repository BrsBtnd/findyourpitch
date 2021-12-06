package com.findyourpitch.security;

import com.findyourpitch.entities.User;
import com.findyourpitch.repository.UserRepository;
import com.findyourpitch.services.CustomAuthenticationManager;
import com.findyourpitch.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        try {
            //auth.authenticationProvider(authProvider());
            auth.userDetailsService(username -> userRepository
                    .findByUserName(username)).passwordEncoder(passwordEncoder());
        } catch (Exception exception) {
            throw new UsernameNotFoundException("User with requested username not found ");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint((request, respons, ex) -> {
                    respons.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not recognised. AuthorizationFailed");
                })
                .and();
        //itt allitom be, hogy melyik endpointokat ki lathat, nalam eddig csak a pitcheket kerheti le mindenki
        http.authorizeRequests()
                .antMatchers("/api/pitches/**").permitAll()
                .antMatchers("/api/users/**").permitAll()
                .antMatchers("/api/login/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(
                jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {

        return new CustomAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

    @Bean
    public MyUserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
