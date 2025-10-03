package hien.project.config;

import hien.project.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/products/new").hasRole("ADMIN")
						.requestMatchers("/products/edit/**").hasRole("ADMIN").requestMatchers("/products/delete/**")
						.hasRole("ADMIN").requestMatchers("/products").authenticated().requestMatchers("/register")
						.permitAll() // Cho phép tất cả truy cập
						.requestMatchers("/login").permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/products", true))
				.logout(logout -> logout.permitAll().logoutSuccessUrl("/login"))
				.exceptionHandling(exception -> exception.accessDeniedPage("/403")).csrf().disable();
		return http.build();
	}
}