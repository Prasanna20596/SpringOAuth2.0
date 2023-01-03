package com.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${oauth.clientId}")
    private String clientID;

    @Value("${oauth.clientSecret}")
    private String clientSecret;

    @Value("${oauth.redirectUris}")
    private String redirectURLs;

    @Value("${oauth.accessTokenValidity}")
    private int accessTokenValidity;

    @Value("${oauth.refreshTokenValidity}")
    private int refreshTokenValidity;

    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
            .withClient(clientID)
            .secret(passwordEncoder.encode(clientSecret))
            .authorizedGrantTypes("password", "authorization_code", "refresh_token")
            .scopes("user_info")
            .authorities("READ_ONLY_CLIENT")
            .redirectUris(redirectURLs)
            .accessTokenValiditySeconds(accessTokenValidity)
            .refreshTokenValiditySeconds(refreshTokenValidity);
    }

}
