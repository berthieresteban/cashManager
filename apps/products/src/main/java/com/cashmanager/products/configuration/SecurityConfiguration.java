 
package com.cashmanager.products.configuration;

import com.cashmanager.products.security.JwtAuthenticationFilter;
import com.cashmanager.products.security.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/Products").authenticated()  // List products
            .antMatchers(HttpMethod.GET, "/Products/{productId:[\\d+]}").authenticated() // Get product by id
            .antMatchers(HttpMethod.POST, "/Products").hasRole("ADMIN") // Create new product 
            .antMatchers(HttpMethod.PUT, "/Products/{productId:[\\d+]}").hasRole("ADMIN") // Update product
            .antMatchers(HttpMethod.DELETE, "/Products/{productId:[\\d+]}").hasRole("ADMIN") // Delete product

            .antMatchers(HttpMethod.GET, "/Carts").hasRole("ADMIN") // List carts
            .antMatchers(HttpMethod.GET, "/Carts/{productId:[\\d+]}").authenticated() // get cart by id 
            .antMatchers(HttpMethod.POST, "/Carts").hasRole("ADMIN") // Create cart
            .antMatchers(HttpMethod.PUT, "/Carts/{productId:[\\d+]}").authenticated() // update cart
            .antMatchers(HttpMethod.DELETE, "/Carts/{productId:[\\d+]}").hasRole("ADMIN") // Delete cart
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager()))
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource)
          .usersByUsernameQuery("select username,password, enabled from users where username=?")
          .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

        return source;
    }
}