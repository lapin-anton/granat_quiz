package ru.granat.granat_quiz.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.granat.granat_quiz.dao.PersonRepository;
import ru.granat.granat_quiz.mapper.CustomMapStruct;
import ru.granat.granat_quiz.model.dto.PersonDto;
import ru.granat.granat_quiz.model.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonRepository personRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        List<UserDetails> userDetails = personRepository.findAll()
                .stream().map(p -> {
                    PersonDto personDto = CustomMapStruct.INSTANCE.mapPerson(p);
                    return User.withDefaultPasswordEncoder()
                            .username(personDto.getLogin())
                            .password(personDto.getPassword())
                            .roles(personDto.getRole())
                            .build();
                })
                .collect(Collectors.toList());

        return new InMemoryUserDetailsManager(userDetails);
    }
}
