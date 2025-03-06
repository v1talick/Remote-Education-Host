package com.phd.RemoteEducationHost.configuration;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.sql.DataSource;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Collections;
import java.util.List;

@Configuration
//@Import(JdbcConfiguration.class)
@Import(DBConfiguration.class)
@ComponentScan("com.phd.RemoteEducationHost.repositories")
@ComponentScan("com.phd.RemoteEducationHost.mappers")
//@PropertySource("classpath:src/test/resources/test-application.properties")
public class SystemTestConfiguration {

//    @Bean
//    @Primary
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return mock(SecurityFilterChain.class);
//    }
//
//    @Bean
//    @Primary
//    public HttpSecurity httpSecurity() {
//        return mock(HttpSecurity.class);
//    }
//    @Bean
//    JwtEncoder jwtEncoder(@Value("classpath:authz.pub") RSAPublicKey pub,
//                          @Value("classpath:authz.pem") RSAPrivateKey pem) {
//        RSAKey key = new RSAKey.Builder(pub).privateKey(pem).build();
//        return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(key)));
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder(@Value("classpath:authz.pub") RSAPublicKey pub) {
//        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(pub).build();
//        OAuth2TokenValidator<Jwt> defaults = JwtValidators.createDefaultWithIssuer("http://localhost:9000");
//        OAuth2TokenValidator<Jwt> audience = new JwtClaimValidator<List<Object>>(JwtClaimNames.AUD,
//                (aud) -> !Collections.disjoint(aud, Collections.singleton("cashcard-client")));
//        jwtDecoder.setJwtValidator(new DelegatingOAuth2TokenValidator<>(defaults, audience));
//        return jwtDecoder;
//    }
}
