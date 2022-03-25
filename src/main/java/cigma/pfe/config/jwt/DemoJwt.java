package cigma.pfe.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

public class DemoJwt {
    private static final String SECRET_KEY="sara123456";
    public static void main(String[] args) {
        String token=createJWT( "id", "issuer", "subject", 10000000l);
        System.out.println(token);
        Claims claims=decodeJWT(token);
        System.out.println(claims);
    }
    //Sample method to construct a JWT
    public static String createJWT(String id, String issuer, String
            subject, long ttlMillis) {
//The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
//We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
        Key signingKey = new SecretKeySpec(apiKeySecretBytes,
                signatureAlgorithm.getJcaName());
//Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);
//if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
//Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Claims decodeJWT(String jwt) {
//This line will throw an exception if it is not a signed
        //JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .parseClaimsJws(jwt).getBody();
        return claims;
    }



}

