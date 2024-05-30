package dmytro.bozhor.universitypetprojectwebsite.config;

import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import dmytro.bozhor.universitypetprojectwebsite.service.PersonService;
import dmytro.bozhor.universitypetprojectwebsite.util.SecurityRolesHolder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.Set;

import static dmytro.bozhor.universitypetprojectwebsite.util.EndpointValuesContainer.*;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@AllArgsConstructor
class SpringSecurityConfig {

    private final PersonService personService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(registry -> registry.anyRequest().permitAll())
                .formLogin(form -> form
                        .loginPage(LOGIN)
                        .loginProcessingUrl(LOGIN)
                        .defaultSuccessUrl(HOME, true)
                        .usernameParameter(SecurityUtil.USERNAME_PARAMETER)
                        .passwordParameter(SecurityUtil.PASSWORD_PARAMETER)
                        .failureUrl(SecurityUtil.FAILURE_URL)
                )
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutUrl(LOGOUT)
                        .logoutSuccessUrl(HOME))
                .oauth2Login(oAuth2LoginConfigurer -> oAuth2LoginConfigurer
                        .loginPage(LOGIN)
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oidcUserService()))
                        .failureUrl(SecurityUtil.FAILURE_URL)
                        .defaultSuccessUrl(HOME, true))
                .build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return userRequest -> {
            var idToken = userRequest.getIdToken();
            var email = (String) idToken.getClaim("email");
            var userDetails = getUserDetails(email);

            var oidcUser = new DefaultOidcUser(userDetails.getAuthorities(), idToken);

            var userDetailsMethods = Set.of(UserDetails.class.getMethods());

            return (OidcUser) Proxy.newProxyInstance(SpringSecurityConfig.class.getClassLoader(),
                    new Class[]{UserDetails.class, OidcUser.class},
                    (proxy, method, args) -> userDetailsMethods.contains(method)
                            ? method.invoke(userDetails, args)
                            : method.invoke(oidcUser, args));
        };
    }

    private UserDetails getUserDetails(String email) {

        if (personService.findByEmail(email).isPresent()) {
            return personService.loadUserByUsername(email);
        }

        var person = personService.create(Person.builder()
                .username(email)
                .email(email)
                .password(email)
                .roles(Collections.singletonList(SecurityRolesHolder.USER))
                .build());
        return personService.loadUserByUsername(person.getEmail());
    }

}

// TODO: add logout button
// TODO: add "already have an account? -> login"
// TODO: add "dont have an account? -> register"
// TODO: make login with google better looking
// TODO: sign in and sign up buttons disappear after authorization and authentication. logout button appears after that.

// TODO: Google Authorization
// TODO: JWT token
// TODO: OAuth2.0
