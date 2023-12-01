package store.books.authSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("admin_9112997")).build()); // Admin Credentials :)
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/GeneralStyling.css").permitAll()
                        .requestMatchers("/functionalscript.js").permitAll()
                        .requestMatchers("index.html").permitAll()
                        .requestMatchers("/lb-literature/get-all/**").permitAll()
                        .requestMatchers("/lb-literature/get/books/author/**").permitAll()
                        .requestMatchers("/lb-literature/get/books/title/**").permitAll()
                        .requestMatchers("/lb-literature/get/books/category/**").permitAll()
                        .requestMatchers("/lb-literature/get/bookstores/state/**").permitAll()
                        .requestMatchers("/lb-literature/get/bookstores/city/**").permitAll()
                        .requestMatchers("/lb-literature/get/bookstores/zipcode/**").permitAll()
                        .requestMatchers("/get-html-state/home").permitAll()
                        .requestMatchers("/get-html-state/browsebooks").permitAll()
                        .requestMatchers("/get-html-state/loginorregister").permitAll()
                        .anyRequest().authenticated()) // locks all non/matching paths down
                .httpBasic(withDefaults()); // Auth Type

        http.csrf(csrf -> csrf.disable()); //disables cross site request forgery checking

        http.cors();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

}
