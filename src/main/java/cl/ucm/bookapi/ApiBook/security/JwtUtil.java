package cl.ucm.bookapi.ApiBook.security;

import cl.ucm.bookapi.ApiBook.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private final Algorithm ALGORITHM;

    public JwtUtil(@Value("${api.security.secret}") String secretKey) {
        this.ALGORITHM = Algorithm.HMAC256(secretKey);
    }

    public String create(User user) {
        return JWT.create()
                .withSubject(
                        user.getEmail() + "#" + user.getUserRol()
                                .stream()
                                .map(ur -> ur.getRolFk().getName())
                                .collect(Collectors.joining("&")))
                .withIssuer("api-book")
                .withClaim("email", user.getEmail())
                .withClaim("name", user.getName())
                .withClaim("lastName", user.getLastName())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

    public Boolean isValid(String jwt) {
        try {
            JWT.require(ALGORITHM).build().verify(jwt);
            return true;
        } catch (JWTDecodeException e) {
            return false;
        }
    }

    public String getEmail(String jwt) {
        DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(jwt);
        return decodedJWT.getClaim("email").asString();
    }

    public String getName(String jwt) {
        DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(jwt);
        return decodedJWT.getClaim("name").asString();
    }

    public String getLastName(String jwt) {
        DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(jwt);
        return decodedJWT.getClaim("lastName").asString();
    }

}
