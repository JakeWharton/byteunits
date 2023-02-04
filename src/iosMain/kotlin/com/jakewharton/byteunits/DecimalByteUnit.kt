package com.jakewharton.byteunits

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

/**
 * A [DecimalByteUnit] represents power-of-ten byte sizes at a given unit of granularity and
 * provides utility methods to convert across units. A [DecimalByteUnit] does not maintain
 * byte size information, but only helps organize and use byte size representations that may be
 * maintained separately across various contexts.
 */
actual enum class DecimalByteUnit : ByteUnit {
  /** Byte unit representing one byte. */
  BYTES,

  /** A byte unit representing 1000 bytes. */
  KILOBYTES,

  /** A byte unit representing 1000 kilobytes. */
  MEGABYTES,

  /** A byte unit representing 1000 megabytes. */
  GIGABYTES,

  /** A byte unit representing 1000 gigabytes. */
  TERABYTES,

  /** A byte unit representing 1000 terabytes. */
  PETABYTES,
  ;

  override fun toBytes(count: Long): Long = when (this) {
    BYTES -> bytesToBytes(count)
    KILOBYTES -> kilobytesToBytes(count)
    MEGABYTES -> megabytesToBytes(count)
    GIGABYTES -> gigabytesToBytes(count)
    TERABYTES -> terabytesToBytes(count)
    PETABYTES -> petabytesToBytes(count)
  }

  /**
   * Converts the given size in the given unit to this unit. Conversions from finer to coarser
   * granularities truncate, so lose precision. For example, converting from `999` bytes to
   * kilobytes results in `0`. Conversions from coarser to finer granularities with arguments
   * that would numerically overflow saturate to [Long.MIN_VALUE] if negative or
   * [Long.MAX_VALUE] if positive.
   *
   * For example, to convert 10 kilobytes to bytes, use:
   * `ByteUnit.KILOBYTES.convert(10, ByteUnit.BYTES)`
   *
   * [sourceCount] the size in the given [sourceUnit].
   * [sourceUnit] the unit of the [sourceCount] argument.
   * @return the converted size in this unit, or [Long.MIN_VALUE] if conversion would
   * negatively overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long = when (this) {
    BYTES -> sourceUnit.toBytes(sourceCount)
    KILOBYTES -> sourceUnit.toKilobytes(sourceCount)
    MEGABYTES -> sourceUnit.toMegabytes(sourceCount)
    GIGABYTES -> sourceUnit.toGigabytes(sourceCount)
    TERABYTES -> sourceUnit.toTerabytes(sourceCount)
    PETABYTES -> sourceUnit.toPetabytes(sourceCount)
  }

  /**
   * Equivalent to [KILOBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toKilobytes(count: Long): Long = when (this) {
    BYTES -> bytesToKilobytes(count)
    KILOBYTES -> kilobytesToKilobytes(count)
    MEGABYTES -> megabytesToKilobytes(count)
    GIGABYTES -> gigabytesToKilobytes(count)
    TERABYTES -> terabytesToKilobytes(count)
    PETABYTES -> petabytesToKilobytes(count)
  }

  /**
   * Equivalent to [MEGABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toMegabytes(count: Long): Long = when (this) {
    BYTES -> bytesToMegabytes(count)
    KILOBYTES -> kilobytesToMegabytes(count)
    MEGABYTES -> megabytesToMegabytes(count)
    GIGABYTES -> gigabytesToMegabytes(count)
    TERABYTES -> terabytesToMegabytes(count)
    PETABYTES -> petabytesToMegabytes(count)
  }

  /**
   * Equivalent to [GIGABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toGigabytes(count: Long): Long = when (this) {
    BYTES -> bytesToGigabytes(count)
    KILOBYTES -> kilobytesToGigabytes(count)
    MEGABYTES -> megabytesToGigabytes(count)
    GIGABYTES -> gigabytesToGigabytes(count)
    TERABYTES -> terabytesToGigabytes(count)
    PETABYTES -> petabytesToGigabytes(count)
  }

  /**
   * Equivalent to [TERABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toTerabytes(count: Long): Long = when (this) {
    BYTES -> bytesToTeraBytes(count)
    KILOBYTES -> kilobytesToTeraBytes(count)
    MEGABYTES -> megabytesToTeraBytes(count)
    GIGABYTES -> gigabytesToTeraBytes(count)
    TERABYTES -> terabytesToTeraBytes(count)
    PETABYTES -> petabytesToTeraBytes(count)
  }

  /**
   * Equivalent to [PETABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toPetabytes(count: Long): Long = when (this) {
    BYTES -> bytesToPetaBytes(count)
    KILOBYTES -> kilobytesToPetaBytes(count)
    MEGABYTES -> megabytesToPetaBytes(count)
    GIGABYTES -> gigabytesToPetaBytes(count)
    TERABYTES -> terabytesToPetaBytes(count)
    PETABYTES -> petabytesToPetaBytes(count)
  }

  actual companion object {
    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GB".
     */
    actual fun format(bytes: Long): String {
      val formatter = NSNumberFormatter()
      formatter.numberStyle = NSNumberFormatterDecimalStyle
      formatter.maximumFractionDigits = 1uL
      return format(bytes, formatter)
    }

    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GB". This will use a default
     * [DecimalFormat] instance for formatting the number.
     */
    fun format(
      bytes: Long,
      formatter: NSNumberFormatter,
    ): String = formatDecimalByteUnit(
      bytes = bytes,
      formatter = { formatter.stringFromNumber(NSNumber(it))!! },
    )
  }
}
