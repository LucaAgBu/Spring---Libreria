package libreria.libreria.configuraciones;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
   
    	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll().and().csrf().disable();
                http.authorizeRequests().anyRequest().authenticated();
        }
        
/*        
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.inMemoryAuthentication()
		  .withUser("user").password("{noop}password").roles("USER") .and()
		  .withUser("admin").password("{noop}password").roles("ADMIN");
	}*/


}
   