package demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

/** Config de seguridad para Actuator (principalmente).
 *
 * @author Enrique Zamudio
 * Date: 08/08/15 11:54
 */
@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN")
    }

    void configure(HttpSecurity http) {
        http.antMatcher("/manage/**").authorizeRequests().anyRequest().hasRole("ADMIN").and().httpBasic()
    }
}
