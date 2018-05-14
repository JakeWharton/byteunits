package com.jakewharton.byteunits;

import java.text.DecimalFormat;
import java.text.NumberFormat;

final class UnitUtil {
  static final NumberFormat DEFAULT_FORMAT = new DecimalFormat("#,##0.#");

  /** Multiply {@code size} by {@code factor} accounting for overflow. */
  static long multiply(long size, long factor, long over) {
    if (size > over) {
      return Long.MAX_VALUE;
    }
    if (size < -over) {
      return Long.MIN_VALUE;
    }
    return size * factor;
  }

  private UnitUtil() {
    throw new AssertionError("No instances.");
  }
}
