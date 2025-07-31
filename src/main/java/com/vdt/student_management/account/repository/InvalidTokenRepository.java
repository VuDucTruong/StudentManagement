package com.vdt.student_management.account.repository;

import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvalidTokenRepository {
  RedisTemplate<String,String> redisTemplate;

  public void saveInvalidToken(String token, long remainingExpTime) {
    redisTemplate.opsForValue().set(token, "blacklisted", remainingExpTime, TimeUnit.MILLISECONDS);
  }

  public boolean isTokenBlacklisted(String token) {
    return Boolean.TRUE.equals(redisTemplate.hasKey(token));
  }
}
