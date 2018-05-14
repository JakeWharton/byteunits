package com.jakewharton.byteunits;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.jakewharton.byteunits.UnitUtil.DEFAULT_FORMAT;
import static com.jakewharton.byteunits.UnitUtil.multiply;

/**
 * A {@code DecimalByteUnit} represents power-of-ten byte sizes at a given unit of granularity and
 * provides utility methods to convert across units. A {@code DecimalByteUnit} does not maintain
 * byte size information, but only helps organize and use byte size representations that may be
 * maintained separately across various contexts.
 */
public enum DecimalByteUnit implements ByteUnit {
  /** Byte unit representing one byte. */
  BYTES {
    @Override public long convert(long sourceCount, DecimalByteUnit sourceUnit) {
      return sourceUnit.toBytes(sourceCount);
    }

    @Override public long toBytes(long count) {
      return count;
    }

    @Override public long toKilobytes(long count) {
      return count / (KB / B);
    }

    @Override public long toMegabytes(long count) {
      return count / (MB / B);
    }

    @Override public long toGigabytes(long count) {
      return count / (GB / B);
    }

    @Override public long toTerabytes(long count) {
      return count / (TB / B);
    }

    @Override public long toPetabytes(long count) {
      return count / (PB / B);
    }
  },

  /** A byte unit representing 1000 bytes. */
  KILOBYTES {
    @Override public long convert(long sourceCount, DecimalByteUnit sourceUnit) {
      return sourceUnit.toKilobytes(sourceCount);
    }

    @Override public long toBytes(long count) {
      return multiply(count, KB / B, MAX / (KB / B));
    }

    @Override public long toKilobytes(long count) {
      return count;
    }

    @Override public long toMegabytes(long count) {
      return count / (MB / KB);
    }

    @Override public long toGigabytes(long count) {
      return count / (GB / KB);
    }

    @Override public long toTerabytes(long count) {
      return count / (TB / KB);
    }

    @Override public long toPetabytes(long count) {
      return count / (PB / KB);
    }
  },

  /** A byte unit representing 1000 kilobytes. */
  MEGABYTES {
    @Override public long convert(long sourceCount, DecimalByteUnit sourceUnit) {
      return sourceUnit.toMegabytes(sourceCount);
    }

    @Override public long toBytes(long count) {
      return multiply(count, MB / B, MAX / (MB / B));
    }

    @Override public long toKilobytes(long count) {
      return multiply(count, MB / KB, MAX / (MB / KB));
    }

    @Override public long toMegabytes(long count) {
      return count;
    }

    @Override public long toGigabytes(long count) {
      return count / (GB / MB);
    }

    @Override public long toTerabytes(long count) {
      return count / (TB / MB);
    }

    @Override public long toPetabytes(long count) {
      return count / (PB / MB);
    }
  },

  /** A byte unit representing 1000 megabytes. */
  GIGABYTES {
    @Override public long convert(long sourceCount, DecimalByteUnit sourceUnit) {
      return sourceUnit.toGigabytes(sourceCount);
    }

    @Override public long toBytes(long count) {
      return multiply(count, GB / B, MAX / (GB / B));
    }

    @Override public long toKilobytes(long count) {
      return multiply(count, GB / KB, MAX / (GB / KB));
    }

    @Override public long toMegabytes(long count) {
      return multiply(count, GB / MB, MAX / (GB / MB));
    }

    @Override public long toGigabytes(long count) {
      return count;
    }

    @Override public long toTerabytes(long count) {
      return count / (TB / GB);
    }

    @Override public long toPetabytes(long count) {
      return count / (PB / GB);
    }
  },

  /** A byte unit representing 1000 gigabytes. */
  TERABYTES {
    @Override public long convert(long sourceCount, DecimalByteUnit sourceUnit) {
      return sourceUnit.toTerabytes(sourceCount);
    }

    @Override public long toBytes(long count) {
      return multiply(count, TB / B, MAX / (TB / B));
    }

    @Override public long toKilobytes(long count) {
      return multiply(count, TB / KB, MAX / (TB / KB));
    }

    @Override public long toMegabytes(long count) {
      return multiply(count, TB / MB, MAX / (TB / MB));
    }

    @Override public long toGigabytes(long count) {
      return multiply(count, TB / GB, MAX / (TB / GB));
    }

    @Override public long toTerabytes(long count) {
      return count;
    }

    @Override public long toPetabytes(long count) {
      return count / (PB / TB);
    }
  },

  /** A byte unit representing 1000 terabytes. */
  PETABYTES {
    @Override public long convert(long sourceCount, DecimalByteUnit sourceUnit) {
      return sourceUnit.toPetabytes(sourceCount);
    }

    @Override public long toBytes(long count) {
      return multiply(count, PB / B, MAX / (PB / B));
    }

    @Override public long toKilobytes(long count) {
      return multiply(count, PB / KB, MAX / (PB / KB));
    }

    @Override public long toMegabytes(long count) {
      return multiply(count, PB / MB, MAX / (PB / MB));
    }

    @Override public long toGigabytes(long count) {
      return multiply(count, PB / GB, MAX / (PB / GB));
    }

    @Override public long toTerabytes(long count) {
      return multiply(count, PB / TB, MAX / (PB / TB));
    }

    @Override public long toPetabytes(long count) {
      return count;
    }
  };

  private static final long B = 1L;
  private static final long KB = B * 1000L;
  private static final long MB = KB * 1000L;
  private static final long GB = MB * 1000L;
  private static final long TB = GB * 1000L;
  private static final long PB = TB * 1000L;

  private static final long MAX = Long.MAX_VALUE;

  /**
   * Converts the given size in the given unit to this unit. Conversions from finer to coarser
   * granularities truncate, so lose precision. For example, converting from {@code 999} bytes to
   * kilobytes results in {@code 0}. Conversions from coarser to finer granularities with arguments
   * that would numerically overflow saturate to {@code Long.MIN_VALUE} if negative or
   * {@code Long.MAX_VALUE} if positive.
   * <p>
   * For example, to convert 10 kilobytes to bytes, use:
   * {@code ByteUnit.KILOBYTES.convert(10, ByteUnit.BYTES)}
   *
   * @param sourceCount the size in the given {@code sourceUnit}.
   * @param sourceUnit the unit of the {@code sourceCount} argument.
   * @return the converted size in this unit, or {@code Long.MIN_VALUE} if conversion would
   * negatively overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long convert(long sourceCount, DecimalByteUnit sourceUnit) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, DecimalByteUnit) KILOBYTES.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toKilobytes(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, DecimalByteUnit) MEGABYTES.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toMegabytes(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, DecimalByteUnit) GIGABYTES.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toGigabytes(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, DecimalByteUnit) TERABYTES.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toTerabytes(long count) {
    throw new AbstractMethodError();
  }

  /**
   * Equivalent to {@link #convert(long, DecimalByteUnit) PETABYTES.convert(count, this)}.
   * @param count the bit count
   * @return the converted count, or {@code Long.MIN_VALUE} if conversion would negatively
   * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
   */
  public long toPetabytes(long count) {
    throw new AbstractMethodError();
  }

  private static final String[] UNITS = { "B", "KB", "MB", "GB", "TB", "PB" };

  /**
   * Return {@code bytes} as human-readable size string (e.g., "1.2 GB". This will use a default
   * {@link DecimalFormat} instance for formatting the number.
   */
  public static String format(long bytes) {
    return format(bytes, DEFAULT_FORMAT);
  }

  /**
   * Return {@code bytes} as human-readable size string (e.g., "1.2 GB". This will use a
   * {@link DecimalFormat} instance with {@code pattern} for formatting the number.
   */
  public static String format(long bytes, String pattern) {
    return format(bytes, new DecimalFormat(pattern));
  }

  /**
   * Return {@code bytes} as human-readable size string (e.g., "1.2 GB". This will use {@code
   * format} for formatting the number.
   */
  public static String format(long bytes, NumberFormat format) {
    if (bytes < 0) {
      throw new IllegalArgumentException("bytes < 0: " + bytes);
    }

    int unitIndex = 0;
    double count = bytes;
    while (count >= 1000d && unitIndex < UNITS.length - 1) {
      count /= 1000d;
      unitIndex += 1;
    }
    return format.format(count) + ' ' + UNITS[unitIndex];
  }
}
