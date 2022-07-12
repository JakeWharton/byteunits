package com.jakewharton.byteunits

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * A [DecimalByteUnit] represents power-of-ten byte sizes at a given unit of granularity and
 * provides utility methods to convert across units. A [DecimalByteUnit] does not maintain
 * byte size information, but only helps organize and use byte size representations that may be
 * maintained separately across various contexts.
 */
enum class DecimalByteUnit : ByteUnit {
  /** Byte unit representing one byte. */
  BYTES {
    override fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long =
      sourceUnit.toBytes(sourceCount)

    override fun toBytes(count: Long): Long =
      count

    override fun toKilobytes(count: Long): Long =
      count / (KB / B)

    override fun toMegabytes(count: Long): Long =
      count / (MB / B)

    override fun toGigabytes(count: Long): Long =
      count / (GB / B)

    override fun toTerabytes(count: Long): Long =
      count / (TB / B)

    override fun toPetabytes(count: Long): Long =
      count / (PB / B)
  },

  /** A byte unit representing 1000 bytes. */
  KILOBYTES {
    override fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long =
      sourceUnit.toKilobytes(sourceCount)

    override fun toBytes(count: Long): Long =
      checkedMultiply(count, KB / B, MAX / (KB / B))

    override fun toKilobytes(count: Long): Long =
      count

    override fun toMegabytes(count: Long): Long =
      count / (MB / KB)

    override fun toGigabytes(count: Long): Long =
      count / (GB / KB)

    override fun toTerabytes(count: Long): Long =
      count / (TB / KB)

    override fun toPetabytes(count: Long): Long =
      count / (PB / KB)
  },

  /** A byte unit representing 1000 kilobytes. */
  MEGABYTES {
    override fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long =
      sourceUnit.toMegabytes(sourceCount)

    override fun toBytes(count: Long): Long =
      checkedMultiply(count, MB / B, MAX / (MB / B))

    override fun toKilobytes(count: Long): Long =
      checkedMultiply(count, MB / KB, MAX / (MB / KB))

    override fun toMegabytes(count: Long): Long =
      count

    override fun toGigabytes(count: Long): Long =
      count / (GB / MB)

    override fun toTerabytes(count: Long): Long =
      count / (TB / MB)

    override fun toPetabytes(count: Long): Long =
      count / (PB / MB)
  },

  /** A byte unit representing 1000 megabytes. */
  GIGABYTES {
    override fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long =
      sourceUnit.toGigabytes(sourceCount)

    override fun toBytes(count: Long): Long =
      checkedMultiply(count, GB / B, MAX / (GB / B))

    override fun toKilobytes(count: Long): Long =
      checkedMultiply(count, GB / KB, MAX / (GB / KB))

    override fun toMegabytes(count: Long): Long =
      checkedMultiply(count, GB / MB, MAX / (GB / MB))

    override fun toGigabytes(count: Long): Long =
      count

    override fun toTerabytes(count: Long): Long =
      count / (TB / GB)

    override fun toPetabytes(count: Long): Long =
      count / (PB / GB)
  },

  /** A byte unit representing 1000 gigabytes. */
  TERABYTES {
    override fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long =
      sourceUnit.toTerabytes(sourceCount)

    override fun toBytes(count: Long): Long =
      checkedMultiply(count, TB / B, MAX / (TB / B))

    override fun toKilobytes(count: Long): Long =
      checkedMultiply(count, TB / KB, MAX / (TB / KB))

    override fun toMegabytes(count: Long): Long =
      checkedMultiply(count, TB / MB, MAX / (TB / MB))

    override fun toGigabytes(count: Long): Long =
      checkedMultiply(count, TB / GB, MAX / (TB / GB))

    override fun toTerabytes(count: Long): Long =
      count

    override fun toPetabytes(count: Long): Long =
      count / (PB / TB)
  },

  /** A byte unit representing 1000 terabytes. */
  PETABYTES {
    override fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long =
      sourceUnit.toPetabytes(sourceCount)

    override fun toBytes(count: Long): Long =
      checkedMultiply(count, PB / B, MAX / (PB / B))

    override fun toKilobytes(count: Long): Long =
      checkedMultiply(count, PB / KB, MAX / (PB / KB))

    override fun toMegabytes(count: Long): Long =
      checkedMultiply(count, PB / MB, MAX / (PB / MB))

    override fun toGigabytes(count: Long): Long =
      checkedMultiply(count, PB / GB, MAX / (PB / GB))

    override fun toTerabytes(count: Long): Long =
      checkedMultiply(count, PB / TB, MAX / (PB / TB))

    override fun toPetabytes(count: Long): Long =
      count
  };

  /**
   * Converts the given size in the given unit to this unit. Conversions from finer to coarser
   * granularities truncate, so lose precision. For example, converting from `999` bytes to
   * kilobytes results in `0`. Conversions from coarser to finer granularities with arguments
   * that would numerically overflow saturate to [Long.MIN_VALUE] if negative or
   * [Long.MAX_VALUE] if positive.
   *
   *
   * For example, to convert 10 kilobytes to bytes, use:
   * `ByteUnit.KILOBYTES.convert(10, ByteUnit.BYTES)`
   *
   * [sourceCount] the size in the given [sourceUnit].
   * [sourceUnit] the unit of the [sourceCount] argument.
   * @return the converted size in this unit, or [Long.MIN_VALUE] if conversion would
   * negatively overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  open fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long {
    throw AbstractMethodError()
  }

  /**
   * Equivalent to [KILOBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  open fun toKilobytes(count: Long): Long {
    throw AbstractMethodError()
  }

  /**
   * Equivalent to [MEGABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  open fun toMegabytes(count: Long): Long {
    throw AbstractMethodError()
  }

  /**
   * Equivalent to [GIGABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  open fun toGigabytes(count: Long): Long {
    throw AbstractMethodError()
  }

  /**
   * Equivalent to [TERABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  open fun toTerabytes(count: Long): Long {
    throw AbstractMethodError()
  }

  /**
   * Equivalent to [PETABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  open fun toPetabytes(count: Long): Long {
    throw AbstractMethodError()
  }

  companion object {
    private const val B = 1L
    private const val KB = B * 1000L
    private const val MB = KB * 1000L
    private const val GB = MB * 1000L
    private const val TB = GB * 1000L
    private const val PB = TB * 1000L
    private const val MAX = Long.MAX_VALUE
    private val UNITS = arrayOf("B", "KB", "MB", "GB", "TB", "PB")

    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GB". This will use a
     * [DecimalFormat] instance with `pattern` for formatting the number.
     */
    fun format(bytes: Long, pattern: String): String {
      return format(bytes, DecimalFormat(pattern))
    }

    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GB". This will use a default
     * [DecimalFormat] instance for formatting the number.
     */
    @JvmOverloads fun format(
      bytes: Long,
      format: NumberFormat = DecimalFormat(DEFAULT_FORMAT_PATTERN),
    ): String {
      require(bytes >= 0) { "bytes < 0: $bytes" }
      var unitIndex = 0
      var count = bytes.toDouble()
      while (count >= 1000.0 && unitIndex < UNITS.size - 1) {
        count /= 1000.0
        unitIndex += 1
      }
      return format.format(count) + ' ' + UNITS[unitIndex]
    }
  }
}
