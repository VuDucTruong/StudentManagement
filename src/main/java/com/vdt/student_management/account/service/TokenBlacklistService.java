package com.vdt.student_management.account.service;

public interface TokenBlacklistService {
  void moveTokenToBlacklist(String token);

  boolean isTokenBlacklisted(String token);
}
