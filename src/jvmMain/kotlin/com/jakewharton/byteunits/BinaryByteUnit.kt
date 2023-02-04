package com.jakewharton.byteunits

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * A [BinaryByteUnit] represents power-of-two byte sizes at a given unit of granularity and
 * provides utility methods to convert across units. A [BinaryByteUnit] does not maintain
 * byte size information, but only helps organize and use byte size representations that may be
 * maintained separately across various contexts.
 */
actual enum class BinaryByteUnit : ByteUnit {
  /** Byte unit representing one byte. */
  BYTES,

  /** A byte unit representing 1024 bytes. */
  KIBIBYTES,

  /** A byte unit representing 1024 kibibytes. */
  MEBIBYTES,

  /** A byte unit representing 1024 mebibytes. */
  GIBIBYTES,

  /** A byte unit representing 1024 gibibytes. */
  TEBIBYTES,

  /** A byte unit representing 1024 tebibytes. */
  PEBIBYTES;

  override fun toBytes(count: Long): Long = when (this) {
    BYTES -> bytesToBytes(count)
    KIBIBYTES -> kibibytesToBytes(count)
    MEBIBYTES -> mebibytesToBytes(count)
    GIBIBYTES -> gibibytesToBytes(count)
    TEBIBYTES -> tebibytesToBytes(count)
    PEBIBYTES -> pebibytesToBytes(count)
  }

  /**
   * Converts the given size in the given unit to this unit. Conversions from finer to coarser
   * granularities truncate, so lose precision. For example, converting from `999` bytes to
   * kibibytes results in `0`. Conversions from coarser to finer granularities with arguments
   * that would numerically overflow saturate to [Long.MIN_VALUE] if negative or
   * [Long.MAX_VALUE] if positive.
   *
   * For example, to convert 10 kilobytes to bytes, use:
   * `ByteUnit.KIBIBYTES.convert(10, ByteUnit.BYTES)`
   *
   * [sourceCount] the size in the given [sourceUnit].
   * [sourceUnit] the unit of the [sourceCount] argument.
   * @return the converted size in this unit, or [Long.MIN_VALUE] if conversion would
   * negatively overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun convert(
    sourceCount: Long,
    sourceUnit: BinaryByteUnit,
  ): Long = when (this) {
    BYTES -> sourceUnit.toBytes(sourceCount)
    KIBIBYTES -> sourceUnit.toKibibytes(sourceCount)
    MEBIBYTES -> sourceUnit.toMebibytes(sourceCount)
    GIBIBYTES -> sourceUnit.toGibibytes(sourceCount)
    TEBIBYTES -> sourceUnit.toTebibytes(sourceCount)
    PEBIBYTES -> sourceUnit.toPebibytes(sourceCount)
  }

  /**
   * Equivalent to [KIBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toKibibytes(count: Long): Long = when (this) {
    BYTES -> bytesToKibibytes(count)
    KIBIBYTES -> kibibytesToKibibytes(count)
    MEBIBYTES -> mebibytesToKibibytes(count)
    GIBIBYTES -> gibibytesToKibibytes(count)
    TEBIBYTES -> tebibytesToKibibytes(count)
    PEBIBYTES -> pebibytesToKibibytes(count)
  }

  /**
   * Equivalent to [MEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toMebibytes(count: Long): Long = when (this) {
    BYTES -> bytesToMebibytes(count)
    KIBIBYTES -> kibibytesToMebibytes(count)
    MEBIBYTES -> mebibytesToMebibytes(count)
    GIBIBYTES -> gibibytesToMebibytes(count)
    TEBIBYTES -> tebibytesToMebibytes(count)
    PEBIBYTES -> pebibytesToMebibytes(count)
  }

  /**
   * Equivalent to [GIBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toGibibytes(count: Long): Long = when (this) {
    BYTES -> bytesToGibibytes(count)
    KIBIBYTES -> kibibytesToGibibytes(count)
    MEBIBYTES -> mebibytesToGibibytes(count)
    GIBIBYTES -> gibibytesToGibibytes(count)
    TEBIBYTES -> tebibytesToGibibytes(count)
    PEBIBYTES -> pebibytesToGibibytes(count)
  }

  /**
   * Equivalent to [TEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toTebibytes(count: Long): Long = when (this) {
    BYTES -> bytesToTebibytes(count)
    KIBIBYTES -> kibibytesToTebibytes(count)
    MEBIBYTES -> mebibytesToTebibytes(count)
    GIBIBYTES -> gibibytesToTebibytes(count)
    TEBIBYTES -> tebibytesToTebibytes(count)
    PEBIBYTES -> pebibytesToTebibytes(count)
  }

  /**
   * Equivalent to [PEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toPebibytes(count: Long): Long = when (this) {
    BYTES -> bytesToPebibytes(count)
    KIBIBYTES -> kibibytesToPebibytes(count)
    MEBIBYTES -> mebibytesToPebibytes(count)
    GIBIBYTES -> gibibytesToPebibytes(count)
    TEBIBYTES -> tebibytesToPebibytes(count)
    PEBIBYTES -> pebibytesToPebibytes(count)
  }

  actual companion object {
    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GiB").
     */
    actual fun format(bytes: Long): String {
      return format(bytes, DEFAULT_FORMAT_PATTERN)
    }

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
    fun format(
      bytes: Long,
      format: NumberFormat,
    ): String = formatBinaryByteUnit(
      bytes = bytes,
      formatter = { format.format(it) },
    )
  }
}
