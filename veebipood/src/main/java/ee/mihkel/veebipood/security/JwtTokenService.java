package ee.mihkel.veebipood.security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenService {

    public String generateJwtToken() {
        return Jwts.builder().compact();
    }
}
