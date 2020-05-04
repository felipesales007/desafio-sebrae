package br.com.mega.hack.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import br.com.mega.hack.model.dto.security.PrincipalUser;

@Service
public class TokenProvider {

	@Autowired
	private AuthorizationServerEndpointsConfiguration configuration;

	public OAuth2AccessToken createToken(PrincipalUser principalUser) {
		PrincipalUser principal = principalUser;

		AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, "N/A", principal.getAuthorities());

		Map<String, String> requestParameters = new HashMap<String, String>();

		Map<String, Serializable> extensionProperties = new HashMap<String, Serializable>();

		boolean approved = true;
		Set<String> responseTypes = new HashSet<String>();
		responseTypes.add("code");

		OAuth2Request oauth2Request = new OAuth2Request(requestParameters, "yourmoney", principal.getAuthorities(), approved,
				new HashSet<String>(Arrays.asList("read", "writer")), null, null, responseTypes,
				extensionProperties);

		OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);

		OAuth2AccessToken token = tokenService.createAccessToken(auth);

		return token;
	}

}
