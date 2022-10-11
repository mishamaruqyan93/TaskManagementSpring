package am.itspace.taskmanagementspring.config;

import am.itspace.taskmanagementspring.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .failureUrl("/loginPage?error=true")
                .defaultSuccessUrl("/loginSuccess")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/tasks/add").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers("/tasks").authenticated() //vaveracvac login exac mard@
                .antMatchers("/users").hasAnyAuthority(Role.MANAGER.name())// uni cankacac ishxanutyun
                .antMatchers("/users/delete").hasAnyAuthority(Role.MANAGER.name())// uni cankacac ishxanutyun
                .antMatchers("/manager").hasAnyAuthority(Role.MANAGER.name())
                .antMatchers("/user").hasAnyAuthority(Role.USER.name())
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");//exceptioni abrabotka, argelvac eji dostup@, dostup@ pake
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
