package com.vdt.student_management.account.service.impl;

import com.vdt.student_management.account.service.TokenBlacklistService;
import com.vdt.student_management.common.utils.JwtHelper;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

  RedisTemplate<String,String> redisTemplate;
  JwtHelper jwtHelper;


  @Override
  public void moveTokenToBlacklist(String token) {
    long remainingExpTime = jwtHelper.getRemainingExpTime(token);
    redisTemplate.opsForValue().set(token, "blacklisted", remainingExpTime, TimeUnit.MILLISECONDS);
  }

  @Override
  public boolean isTokenBlacklisted(String token) {
    return Boolean.TRUE.equals(redisTemplate.hasKey(token));
  }
}
