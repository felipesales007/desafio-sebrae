package br.com.mega.hack.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.mega.hack.service.exception.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret.verify.email}")
	private String secret;

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("user_name", username);
		return Jwts.builder().setClaims(claims).setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean isValid(String token) {
		Claims claims = getClaims(token);
		if (Objects.nonNull(claims)) {
			String username = claims.get("user_name").toString();
			Date expriration = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (Objects.nonNull(username) && Objects.nonNull(expriration) && now.before(expriration)) {
				return true;
			}
		}
		return false;
	}

	public static String getToken() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getHeader("Authorization");
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}

	}

	public Long userId() {
		try {
			DecodedJWT decode = JWT.decode(getToken().trim().substring(6));
			Map<String, Claim> claims = decode.getClaims();
			return claims.get("user_id").asLong();
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public String userUUID() {
		try {
			DecodedJWT decode = JWT.decode(getToken().trim().substring(6));
			Map<String, Claim> claims = decode.getClaims();
			return claims.get("uuid").asString();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public String userUUID(String token) {
		try {
			DecodedJWT decode = JWT.decode(token);
			Map<String, Claim> claims = decode.getClaims();
			return claims.get("uuid").asString();
		} catch (NullPointerException e) {
			return null;
		}
	}

	public String userName(String token) {
		try {
			if (isValid(token)) {
				DecodedJWT decode = JWT.decode(token);
				Map<String, Claim> claims = decode.getClaims();
				return claims.get("user_name").asString();
			} else {
				throw new TokenInvalidException("Token inválido");
			}
		} catch (NullPointerException e) {
			throw new TokenInvalidException("Token inválido");
		}
	}
}
