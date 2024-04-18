package dmytro.bozhor.universitypetprojectwebsite.config;

import dmytro.bozhor.universitypetprojectwebsite.domain.Person;
import dmytro.bozhor.universitypetprojectwebsite.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

import static dmytro.bozhor.universitypetprojectwebsite.config.Role.*;
import static dmytro.bozhor.universitypetprojectwebsite.util.EndpointValuesContainer.*;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
class SpringSecurityConfig {

    private final PersonService personService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(HttpMethod.GET, C_PLUS_PLUS, C_SHARP, JAVA, PYTHON)
                        .hasAnyAuthority(USER.getAuthority(), ADMIN.getAuthority())
                        .anyRequest().permitAll()
                )
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
            var userDetails = personService.loadUserByUsername(email);

//            TODO: create user if not exists

            var oidcUser = new DefaultOidcUser(userDetails.getAuthorities(), idToken);

            var userDetailsMethods = Set.of(UserDetails.class.getMethods());

            return (OidcUser) Proxy.newProxyInstance(SpringSecurityConfig.class.getClassLoader(),
                    new Class[]{UserDetails.class, OidcUser.class},
                    (proxy, method, args) -> userDetailsMethods.contains(method)
                            ? method.invoke(userDetails, args)
                            : method.invoke(oidcUser, args));
        };
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
