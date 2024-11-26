package com.isaac.learn.authserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain authServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // calling utility method to apply default configuration for the authorization server endpoint
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
               .oidc(Customizer.withDefaults());

        // Specifying the authentication page for users
        http.exceptionHandling((e)->e.authenticationEntryPoint(
                new LoginUrlAuthenticationEntryPoint("/login")));

        return http.build();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults());
        // configure to require authentication for all endpoint
        http.authorizeHttpRequests((c)->c.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withUsername("user")
                .password("password")
                .roles("USER").build();
        return  new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public RegisteredClientRepository registeredClientRepository(){
        RegisteredClient registeredClient = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("client")
                .clientSecret("secret")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .redirectUri("https://springone.io/authorized")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .scope("CUSTOM")
                .build();
        return new InMemoryRegisteredClientRepository(registeredClient);

    }
    @Bean
    public JWKSource<SecurityContext> jwkSource()throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        var rsaKey= new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }
    @Bean
    public AuthorizationServerSettings  authorizationServerSettings() {
        return  AuthorizationServerSettings.builder().build();
    }
//
//    // This bean adds a filter chain that adds OAuth2 Authorization Server support to the application.
//    @Bean
//    // Since we have multiple SecurityFilterChain beans, we need to specify the order of the filter chain.
//    @Order(1)
//    public SecurityFilterChain asFilterChain(HttpSecurity http)
//            throws Exception {
//        // Default configuration for the OAuth2 default endpoints.
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//
//        // Enable OIDC protocol and Apply the default.
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
//                .oidc(Customizer.withDefaults());
//
//        // I the user is not authenticated, redirect to the login page.
//        http.exceptionHandling((e) ->
//                e.authenticationEntryPoint(
//                        new LoginUrlAuthenticationEntryPoint("/login"))
//        );
//
//        return http.build();
//    }
//
//    @Bean
//    // Again, multiple SecurityFilterChain beans, so we need to specify the order.
//    @Order(2)
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//            throws Exception {
//        // Enable form login with defaults ()
//        http.formLogin(Customizer.withDefaults());
//
//        // All requests outside the OAuth2 Authorization Server endpoints require authentication.
//        http.authorizeHttpRequests(
//                c -> c.anyRequest().authenticated()
//        );
//        http.logout(Customizer.withDefaults());
//
//
//        return http.build();
//    }
//
//
//    @Bean
//    // A RegisteredClientRepository bean is needed to store the registered clients (RegisteredClient interface).
//    // A RegisteredClientRepo is to OAuth2 client as UserDetailsService is to User (UserDetails interface).
//    public RegisteredClientRepository registeredClientRepository() {
//        // Create a RegisteredClient object with the client details.
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("client")
//                .clientSecret("secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("https://www.manning.com/authorized")
//                .scope(OidcScopes.OPENID)
//                .build();
//
//        // Normally, we would store the registered clients in a database
//        // but for simplicity, we're using an in-memory repository.
//        return new InMemoryRegisteredClientRepository(registeredClient);
//    }
//
//
//    @Bean
//    // JWKSource manages key-pairs for the OAuth2 Authorization Server.
//    // This is a requirement if we use non-opaque tokens.
//    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
//        // For this example, we generate new key-pairs every time the application starts.
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(2048);
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        RSAKey rsaKey = new RSAKey.Builder(publicKey)
//                .privateKey(privateKey)
//                .keyID(UUID.randomUUID().toString())
//                .build();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return new ImmutableJWKSet<>(jwkSet);
//    }
//
//
//    @Bean
//    // Last piece in the minimal OAuth2 server configuration.
//    // AuthorizationServerSettings object lets us customize all endpoint paths that the authorization server  exposes.
//    public AuthorizationServerSettings authorizationServerSettings() {
//        return AuthorizationServerSettings.builder().build();
//    }
}
