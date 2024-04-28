package com.example.demo.security;


import com.example.demo.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = customUserDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable())
                .authorizeRequests()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement((sessionManagement)-> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}
/*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                .anyRequest().permitAll());


        return http.build();
    }
/*
        http
                .csrf((csrf)-> csrf.disable())

                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/notfound").permitAll()
                        .requestMatchers("/access_denied").permitAll()
                        .requestMatchers("/error/*").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())

                .formLogin(form -> form
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/users")
                        .permitAll())

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .permitAll())

                .exceptionHandling((exceptionHandling) -> exceptionHandling
                    .authenticationEntryPoint(authEntryPoint)
                    .accessDeniedPage("/accessdenied"))

                .sessionManagement((sessionManagement)-> sessionManagement
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                .httpBasic((basic) -> basic
                    .addObjectPostProcessor(new ObjectPostProcessor<BasicAuthenticationFilter>() {
                    @Override
                    public <O extends BasicAuthenticationFilter> O postProcess(O filter) {
                        filter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
                        return filter;
                    }
                    })
                );

        return http.build();
    }
*/