package project2.muabannhadat.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import project2.muabannhadat.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String loginPage = "/login";
        String logoutPage = "/logout";

        http
                .authorizeRequests()
                .antMatchers("/","/login","/detail/**").permitAll()
                .antMatchers("/tim-kiem/**", "/nha-dat-ban/**", "/nha-dat-cho-thue/**").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/quan-ly/**").hasAuthority("ADMIN")
                .antMatchers("/cap-nhat-thong-tin/**").authenticated()
                .antMatchers("/dang-tin-nha-dat/**").authenticated()
                .anyRequest().permitAll()
                .and().csrf().disable()
                .formLogin()
                .loginPage(loginPage)
                .defaultSuccessUrl("/")
                .failureUrl("/login?errors=true")
                .usernameParameter("user_name")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
                .logoutSuccessUrl(loginPage).and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

}
