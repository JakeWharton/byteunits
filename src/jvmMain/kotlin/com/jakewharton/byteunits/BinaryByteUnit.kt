package com.jakewharton.byteunits

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * A [BinaryByteUnit] represents power-of-two byte sizes at a given unit of granularity and
 * provides utility methods to convert across units. A [BinaryByteUnit] does not maintain
 * byte size information, but only helps organize and use byte size representations that may be
 * maintained separately across various contexts.
 */
enum class BinaryByteUnit : ByteUnit {
  /** Byte unit representing one byte. */
  BYTES {
    override fun convert(sourceCount: Long, sourceUnit: BinaryByteUnit) =
      sourceUnit.toBytes(sourceCount)

    override fun toBytes(count: Long) =
      count

    override fun toKibibytes(count: Long) =
      count / (KB / B)

    override fun toMebibytes(count: Long) =
      count / (MB / B)

    override fun toGibibytes(count: Long) =
      count / (GB / B)

    override fun toTebibytes(count: Long) =
      count / (TB / B)

    override fun toPebibytes(count: Long) =
      count / (PB / B)
  },

  /** A byte unit representing 1024 bytes. */
  KIBIBYTES {
    override fun convert(sourceCount: Long, sourceUnit: BinaryByteUnit) =
      sourceUnit.toKibibytes(sourceCount)

    override fun toBytes(count: Long) =
      checkedMultiply(count, KB / B, MAX / (KB / B))

    override fun toKibibytes(count: Long) =
      count

    override fun toMebibytes(count: Long) =
      count / (MB / KB)

    override fun toGibibytes(count: Long) =
      count / (GB / KB)

    override fun toTebibytes(count: Long) =
      count / (TB / KB)

    override fun toPebibytes(count: Long) =
      count / (PB / KB)
  },

  /** A byte unit representing 1024 kibibytes. */
  MEBIBYTES {
    override fun convert(sourceCount: Long, sourceUnit: BinaryByteUnit) =
      sourceUnit.toMebibytes(sourceCount)

    override fun toBytes(count: Long) =
      checkedMultiply(count, MB / B, MAX / (MB / B))

    override fun toKibibytes(count: Long) =
      checkedMultiply(count, MB / KB, MAX / (MB / KB))

    override fun toMebibytes(count: Long) =
      count

    override fun toGibibytes(count: Long) =
      count / (GB / MB)

    override fun toTebibytes(count: Long) =
      count / (TB / MB)

    override fun toPebibytes(count: Long) =
      count / (PB / MB)
  },

  /** A byte unit representing 1024 mebibytes. */
  GIBIBYTES {
    override fun convert(sourceCount: Long, sourceUnit: BinaryByteUnit) =
      sourceUnit.toGibibytes(sourceCount)

    override fun toBytes(count: Long) =
      checkedMultiply(count, GB / B, MAX / (GB / B))

    override fun toKibibytes(count: Long) =
      checkedMultiply(count, GB / KB, MAX / (GB / KB))

    override fun toMebibytes(count: Long) =
      checkedMultiply(count, GB / MB, MAX / (GB / MB))

    override fun toGibibytes(count: Long) =
      count

    override fun toTebibytes(count: Long) =
      count / (TB / GB)

    override fun toPebibytes(count: Long) =
      count / (PB / GB)
  },

  /** A byte unit representing 1024 gibibytes. */
  TEBIBYTES {
    override fun convert(sourceCount: Long, sourceUnit: BinaryByteUnit) =
      sourceUnit.toTebibytes(sourceCount)

    override fun toBytes(count: Long) =
      checkedMultiply(count, TB / B, MAX / (TB / B))

    override fun toKibibytes(count: Long) =
      checkedMultiply(count, TB / KB, MAX / (TB / KB))

    override fun toMebibytes(count: Long) =
      checkedMultiply(count, TB / MB, MAX / (TB / MB))

    override fun toGibibytes(count: Long) =
      checkedMultiply(count, TB / GB, MAX / (TB / GB))

    override fun toTebibytes(count: Long) =
      count

    override fun toPebibytes(count: Long) =
      count / (PB / TB)
  },

  /** A byte unit representing 1024 tebibytes. */
  PEBIBYTES {
    override fun convert(sourceCount: Long, sourceUnit: BinaryByteUnit) =
      sourceUnit.toPebibytes(sourceCount)

    override fun toBytes(count: Long) =
      checkedMultiply(count, PB / B, MAX / (PB / B))

    override fun toKibibytes(count: Long) =
      checkedMultiply(count, PB / KB, MAX / (PB / KB))

    override fun toMebibytes(count: Long) =
      checkedMultiply(count, PB / MB, MAX / (PB / MB))

    override fun toGibibytes(count: Long) =
      checkedMultiply(count, PB / GB, MAX / (PB / GB))

    override fun toTebibytes(count: Long) =
      checkedMultiply(count, PB / TB, MAX / (PB / TB))

    override fun toPebibytes(count: Long) =
      count
  };

  /**
   * Converts the given size in the given unit to this unit. Conversions from finer to coarser
   * granularities truncate, so lose precision. For example, converting from `999` bytes to
   * kibibytes results in `0`. Conversions from coarser to finer granularities with arguments
   * that would numerically overflow saturate to [Long.MIN_VALUE] if negative or
   * [Long.MAX_VALUE] if positive.
   *
   *
   * For example, to convert 10 kilobytes to bytes, use:
   * `ByteUnit.KIBIBYTES.convert(10, ByteUnit.BYTES)`
   *
   * [sourceCount] the size in the given [sourceUnit].
   * [sourceUnit] the unit of the [sourceCount] argument.
   * @return the converted size in this unit, or [Long.MIN_VALUE] if conversion would
   * negatively overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun convert(sourceCount: Long, sourceUnit: BinaryByteUnit): Long

  /**
   * Equivalent to [KIBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toKibibytes(count: Long): Long

  /**
   * Equivalent to [MEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toMebibytes(count: Long): Long

  /**
   * Equivalent to [GIBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toGibibytes(count: Long): Long

  /**
   * Equivalent to [TEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toTebibytes(count: Long): Long

  /**
   * Equivalent to [PEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toPebibytes(count: Long): Long

  companion object {
    private const val B = 1L
    private const val KB = B * 1024L
    private const val MB = KB * 1024L
    private const val GB = MB * 1024L
    private const val TB = GB * 1024L
    private const val PB = TB * 1024L
    private const val MAX = Long.MAX_VALUE
    private val UNITS = arrayOf("B", "KiB", "MiB", "GiB", "TiB", "PiB")

    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GiB". This will use a
     * [DecimalFormat] instance with `pattern` for formatting the number.
     */
    fun format(bytes: Long, pattern: String): String {
      return format(bytes, DecimalFormat(pattern))
    }

    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GiB". This will use a default
     * [DecimalFormat] instance for formatting the number.
     */
    @JvmOverloads fun format(
      bytes: Long,
      format: NumberFormat = DecimalFormat(DEFAULT_FORMAT_PATTERN),
    ): String {
      require(bytes >= 0) { "bytes < 0: $bytes" }
      var unitIndex = 0
      var count = bytes.toDouble()
      while (count >= 1024.0 && unitIndex < UNITS.size - 1) {
        count /= 1024.0
        unitIndex += 1
      }
      return format.format(count) + ' ' + UNITS[unitIndex]
    }
  }
}
