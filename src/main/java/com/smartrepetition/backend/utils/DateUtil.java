package com.smartrepetition.backend.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;


public class DateUtil {
  private DateUtil() {
  }

  public static ZonedDateTime date(int year, int month, int day) {
    return ZonedDateTime.of(year, month, day, 0, 0, 0, 0, ZoneId.of("US/Pacific"));
  }
}
