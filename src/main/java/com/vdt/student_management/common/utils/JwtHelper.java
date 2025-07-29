package com.vdt.student_management.common.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.vdt.student_management.account.model.Account;
import com.vdt.student_management.common.enums.RoleType;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtHelper {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.exp}")
  private Long exp;

  public String generateToken(Account account, boolean isRefreshToken) {
    JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
    long tokenExp = exp * (isRefreshToken ? 10 : 1);
    JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(account.getUsername())
        .issuer("VDT").expirationTime(new Date(
            Instant.now().toEpochMilli() + tokenExp)).issueTime(new Date())
        .claim("type", isRefreshToken ? "refresh": "access")
        .claim("roles", buildRolesString(account.getRoles())).build();

    JWSObject jwsObject = new JWSObject(jwsHeader, jwtClaimsSet.toPayload());

    try {
      jwsObject.sign(new MACSigner(secret));
    } catch (JOSEException e) {
      throw new RuntimeException(e);
    }
    return jwsObject.serialize();
  }

  public boolean isTokenValid(String token) {
    try {

      Date expiration = (Date) getClaimFromToken(token , "exp");

      return expiration != null && !expiration.before(new Date());

    } catch (Exception e) {
      return false;
    }

  }

  public Object getClaimFromToken(String token, String claimName) {
    try {
      JWSObject jwsObject = JWSObject.parse(token);
      JWSVerifier verifier = new MACVerifier(secret);

      if (!jwsObject.verify(verifier)) {
        throw new IllegalArgumentException("Invalid JWT signature");
      }

      JWTClaimsSet claimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());

      return claimsSet.getClaim(claimName);

    } catch (Exception e) {
      throw new RuntimeException("Invalid token or claim", e);
    }
  }



  private String buildRolesString(Set<RoleType> roles) {
    return roles.stream().map(RoleType::getValue).collect(Collectors.joining(" "));
  }
}
