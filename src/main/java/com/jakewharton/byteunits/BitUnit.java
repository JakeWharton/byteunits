package com.jakewharton.byteunits;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.jakewharton.byteunits.UnitUtil.DEFAULT_FORMAT;
import static com.jakewharton.byteunits.UnitUtil.multiply;

/**
 * A {@code BitUnit} represents bit size at a given unit of granularity and provides utility
 * methods to convert across units. A {@code BitUnit} does not maintain bit size information,
 * but only helps organize and use bit size representations that may be maintained separately
 * across various contexts.
 */
public enum BitUnit implements ByteUnit {
  /** Bit unit representing one bit. */
  BITS {
    @Override public long convert(long sourceCount, BitUnit sourceUnit) {
      return sourceUnit.toBits(sourceCount);
    }

    @Override public long toBytes(long count) {
      return count / BYTE;
    }

    @Override public long toBits(long count) {
      return count;
    }

    @Override public long toKilobits(long count) {
      return count / (KB / B);
    }

    @Override public long toMegabits(long count) {
      return count / (MB / B);
    }

    @Override public long toGigabits(long count) {
      return count / (GB / B);
    }

    @Override public long toTerabits(long count) {
      return count / (TB / B);
    }

    @Override public long toPetabits(long count) {
      return count / (PB / B);
    }
  },

  /** A bit unit representing 1000 bits. */
  KILOBITS {
    @Override public long convert(long sourceCount, BitUnit sourceUnit) {
      return sourceUnit.toKilobits(sourceCount);
    }

    @Override public long toBytes(long count) {
      return count * KBYTE;
    }

    @Override public long toBits(long count) {
      return multiply(count, KB / B, MAX / (KB / B));
    }

    @Override public long toKilobits(long count) {
      return count;
    }

    @Override public long toMegabits(long count) {
      return count / (MB / KB);
    }

    @Override public long toGigabits(long count) {
      return count / (GB / KB);
    }

    @Override public long toTerabits(long count) {
      return count / (TB / KB);
    }

    @Override public long toPetabits(long count) {
      return count / (PB / KB);
    }
  },

  /** A bit unit representing 1000 kilobits. */
  MEGABITS {
    @Override public long convert(long sourceCount, BitUnit sourceUnit) {
      return sourceUnit.toMegabits(sourceCount);
    }

    @Override public long toBytes(long count) {
      return count * MBYTE;
    }

    @Override public long toBits(long count) {
      return multiply(count, MB / B, MAX / (MB / B));
    }

    @Override public long toKilobits(long count) {
      return multiply(count, MB / KB, MAX / (MB / KB));
    }

    @Override public long toMegabits(long count) {
      return count;
    }

    @Override public long toGigabits(long count) {
      return count / (GB / MB);
    }

    @Override public long toTerabits(long count) {
      return count / (TB / MB);
    }

    @Override public long toPetabits(long count) {
      return count / (PB / MB);
    }
  },

  /** A bit unit representing 1000 megabits. */
  GIGABITS {
    @Override public long convert(long sourceCount, BitUnit sourceUnit) {
      return sourceUnit.toGigabits(sourceCount);
    }

    @Override public long toBytes(long count) {
      return count * GBYTE;
    }

    @Override public long toBits(long count) {
      return multiply(count, GB / B, MAX / (GB / B));
    }

    @Override public long toKilobits(long count) {
      return multiply(count, GB / KB, MAX / (GB / KB));
    }

    @Override public long toMegabits(long count) {
      return multiply(count, GB / MB, MAX / (GB / MB));
    }

    @Override public long toGigabits(long count) {
      return count;
    }

    @Override public long toTerabits(long count) {
      return count / (TB / GB);
    }

    @Override public long toPetabits(long count) {
      return count / (PB / GB);
    }
  },

  /** A bit unit representing 1000 gigabits. */
  TERABITS {
    @Override public long convert(long sourceCount, BitUnit sourceUnit) {
      return sourceUnit.toTerabits(sourceCount);
    }

    @Override public long toBytes(long count) {
      return count * TBYTE;
    }

    @Override public long toBits(long count) {
      return multiply(count, TB / B, MAX / (TB / B));
    }

    @Override public long toKilobits(long count) {
      return multiply(count, TB / KB, MAX / (TB / KB));
    }

    @Override public long toMegabits(long count) {
      return multiply(count, TB / MB, MAX / (TB / MB));
    }

    @Override public long toGigabits(long count) {
      return multiply(count, TB / GB, MAX / (TB / GB));
    }

    @Override public long toTerabits(long count) {
      return count;
    }

    @Override public long toPetabits(long count) {
      return count / (PB / TB);
    }
  },

  /** A bit unit representing 1000 terabits. */
  PETABITS {
    @Override public long convert(long sourceCount, BitUnit sourceUnit) {
      return sourceUnit.toPetabits(sourceCount);
    }

    @Override public long toBytes(long count) {
      return count * PBYTE;
    }

    @Override public long toBits(long count) {
      return multiply(count, PB / B, MAX / (PB / B));
    }

    @Override public long toKilobits(long count) {
      return multiply(count, PB / KB, MAX / (PB / KB));
    }

    @Override public long toMegabits(long count) {
      return multiply(count, PB / MB, MAX / (PB / MB));
    }

    @Override public long toGigabits(long count) {
      return multiply(count, PB / GB, MAX / (PB / GB));
    }

    @Override public long toTerabits(long count) {
      return multiply(count, PB / TB, MAX / (PB / TB));
    }

    @Override public long toPetabits(long count) {
      return count;
    }
  };

  private static final long B = 1L;
  private static final long KB = B * 1000L;
  private static final long MB = KB * 1000L;
  private static final long GB = MB * 1000L;
  private static final long TB = GB * 1000L;
  private static final long PB = TB * 1000L;
  private static final long BYTE = B * 8;
  private static final long KBYTE = 1000L / BYTE;
  private static final long MBYTE = KBYTE * 1000L;
  private static final long GBYTE = MBYTE * 1000L;
  private static final long TBYTE = GBYTE * 1000L;
  private static final long PBYTE = TBYTE * 1000L;

  private static final long MAX = Long.MAX_VALUE;

  /**
   * Converts the given size in the given unit to this unit. Conversions from finer to coarser
   * granularities truncate, so lose precision. For example, converting from {@code 999} bit to
   * kilobits results in {@code 0}. Conversions from coarser to finer granularities with arguments
   * that would numerically overflow saturate to {@code Long.MIN_VALUE} if negative or
   * {@code Long.MAX_VALUE} if positive.
   * <p>
   * For example, to convert 10 kilobytes to bytes, use:
   * {@code ByteUnit.KILOBITS.convert(10, ByteUnit.BITS)}
   *
   * @param sourceCount the size in the given {@code sourceUnit}.
   * @param sourceUnit the unit of the {@code sourceCount} argument.
   * @return the converted size in this unit, or {@code Long.MIN_VALUE} if conversion would
   * negatively overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long convert(long sourceCount, BitUnit sourceUnit) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, BitUnit) BITS.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toBits(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, BitUnit) KILOBITS.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toKilobits(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, BitUnit) MEGABITS.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toMegabits(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, BitUnit) GIGABITS.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toGigabits(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, BitUnit) TERABITS.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toTerabits(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, BitUnit) PETABITS.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toPetabits(long count) {
    throw new AbstractMethodError();
  }

  private static final String[] UNITS = { "b", "Kb", "Mb", "Gb", "Tb", "Pb" };

  /**
   * Return {@code bits} as human-readable size string (e.g., "1.2 Gb". This will use a default
   * {@link DecimalFormat} instance for formatting the number.
   */
  public static String format(long bits) {
    return format(bits, DEFAULT_FORMAT);
  }

  /**
   * Return {@code bits} as human-readable size string (e.g., "1.2 Gb". This will use a
   * {@link DecimalFormat} instance with {@code pattern} for formatting the number.
   */
  public static String format(long bits, String pattern) {
    return format(bits, new DecimalFormat(pattern));
  }

  /**
   * Return {@code bits} as human-readable size string (e.g., "1.2 Gb". This will use {@code
   * format} for formatting the number.
   */
  public static String format(long bits, NumberFormat format) {
    if (bits < 0) {
      throw new IllegalArgumentException("bits < 0: " + bits);
    }

    int unitIndex = 0;
    double count = bits;
    while (count >= 1000d && unitIndex < UNITS.length - 1) {
      count /= 1000d;
      unitIndex += 1;
    }
    return format.format(count) + ' ' + UNITS[unitIndex];
  }
}
