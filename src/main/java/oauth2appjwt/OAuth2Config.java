package oauth2appjwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
	private String clientid = "tutorialspoint";
	private String clientSecret = "my-secret-key";
	private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEogIBAAKCAQEAuXxif5ESB7tQwiJaJrbRkryQ7ZTrVyvOZPmXr1rrj9KuBNE0\n" + 
			"+58ymxhxzZ3lmF5CtFA1YApvH1zjPwWouJ4ZGDk4kv/S6HfuDgTJNAF8svkdNOjK\n" + 
			"qyVJG8jlzAxecy/CYDv0oyWDwrPuf5pRugW4jGjr/6pEmdL5RFFijbfvuVdF83ky\n" + 
			"Na9LxSFfTOAXWJJzuKbFTo5dN0lewsEqFyJLPtAnfhTSKpO9d3HCpjEvXwDX/HFY\n" + 
			"PikbKiFiVEmtjBWNWYp/KKVhPkNjRBbwEm/FeM6cQvq5QUkHQJC+JMgC0loUUuUl\n" + 
			"gUm2a7wTJ8xWbJiWKR9o+6JngnDV6IiZ0LzwdwIDAQABAoIBAGTKbkubbQy+gJe9\n" + 
			"SveWiDMQPECdl3R63WCJMB+dbDtu5BjCBeRlEX6tvclZb5VoQx7wJtDVWqTUOLfa\n" + 
			"P2ByqHg9P7zBPr4LzVPYzexwkm4+Z9yEUvzE+gEbHaPPZNN6tEjOy5SLohl+5K56\n" + 
			"EMbgrPO/X95Bi516wvCYuNEBjkZLdJ+PhenWaW8nUYOEZKcQfyR1+p0tcJ+eG5yh\n" + 
			"6DGDC3iVkzeMtUPQJyRR11sstb/rgqRb/jGLnf1CLVXwEQeXMMvcOUtfK7FICxaV\n" + 
			"s7UfZ3eFPu9iyaZvYtFxx/yXHSx+OqS1Zb4QYCgCJd+3IZi0MNvZUQaKJOZJ6saL\n" + 
			"I4fpJSECgYEA7Gk87VHrmJpSmtNYuYRWvmp1JSjLpaowV3I0mblIfOLLTEDAly/k\n" + 
			"iviT/g7XrLXCmYiNlMyGX/T+3jAKsAz23ASioWmHJwq+TToJmoXvWDfU7pCmbMfS\n" + 
			"RzbqLS1ksrjp2zC4EpqSCMYe5km2HNBANT2ykeoYpc+Ex34f0qDvG0MCgYEAyNrr\n" + 
			"SUeCznj8QIeEuEtL96KFtb+Cft5lO690zpECoVUyWONUFKy4m4DJJ0TS/aruOx49\n" + 
			"hCT+B5oA/6thpYoaO6WcovJiPPVa1+HZCtMg9UUGSLTu/VzJz/2Kbym26vkfbf3b\n" + 
			"6VOJmQ5ZeVc4v+UNWOWZ5FNGyrIne+FS0tbN8L0CgYAXRjUNWCdNmsPPIkd13CV+\n" + 
			"EZT+14lVAfBIa6HqEGemg91n4GgVek2RsO9A780j37EM1Hi70XbZM5Fz5l7m/h+/\n" + 
			"gWVRZsY7fb9GcxRG9jCeCsEXy33W78Uv5aQgVnu3bKT5Q06b5kPCQV8BZPhc1dO8\n" + 
			"ahqOavGKQWfdC/muDtH7aQKBgHRpbbUrHUVOGeXMpb+q5HzCVXzhYmDlxQayyqBu\n" + 
			"q3eWSXf7SeAxu01ldzCrUx5y02CwRjcwuYruV2XXjuDDCjFH62EF2qo6CkzVOVZs\n" + 
			"x81gFCRPubRe8xj5eiKzqrDFctHtMGgrYrSy/Gi3hMyzlOjRKkCLg5w3ZWsHTVml\n" + 
			"5faBAoGAAzmu37iW1zmMIlZQRxy6ta5CsvHU6uUB7Or3bEtrF+QlToc56M7iFT1q\n" + 
			"SOUi2zFot8t77ZG5RWApOPhdOBUQ0fwZlFlDSLWAOio7FzODpCmYhxsXI6JGLGE2\n" + 
			"eW9k3bvEZt7ut3stXxBl0JAV7BvBgn+WqOWNhobt3MJzxWYybZA=\n" + 
			"-----END RSA PRIVATE KEY-----";
	private String publicKey = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuXxif5ESB7tQwiJaJrbR\n" + 
			"kryQ7ZTrVyvOZPmXr1rrj9KuBNE0+58ymxhxzZ3lmF5CtFA1YApvH1zjPwWouJ4Z\n" + 
			"GDk4kv/S6HfuDgTJNAF8svkdNOjKqyVJG8jlzAxecy/CYDv0oyWDwrPuf5pRugW4\n" + 
			"jGjr/6pEmdL5RFFijbfvuVdF83kyNa9LxSFfTOAXWJJzuKbFTo5dN0lewsEqFyJL\n" + 
			"PtAnfhTSKpO9d3HCpjEvXwDX/HFYPikbKiFiVEmtjBWNWYp/KKVhPkNjRBbwEm/F\n" + 
			"eM6cQvq5QUkHQJC+JMgC0loUUuUlgUm2a7wTJ8xWbJiWKR9o+6JngnDV6IiZ0Lzw\n" + 
			"dwIDAQAB\n" + 
			"-----END PUBLIC KEY-----";

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(tokenEnhancer());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clientid).secret(clientSecret).scopes("read", "write")
				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
				.refreshTokenValiditySeconds(20000);

	}
}