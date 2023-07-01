package com.jakewharton.byteunits

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

/**
 * A [BitUnit] represents bit size at a given unit of granularity and provides utility
 * methods to convert across units. A [BitUnit] does not maintain bit size information,
 * but only helps organize and use bit size representations that may be maintained separately
 * across various contexts.
 */
actual enum class BitUnit : ByteUnit {
  /** Bit unit representing one bit. */
  BITS,

  /** A bit unit representing 1000 bits. */
  KILOBITS,

  /** A bit unit representing 1000 kilobits. */
  MEGABITS,

  /** A bit unit representing 1000 megabits. */
  GIGABITS,

  /** A bit unit representing 1000 gigabits. */
  TERABITS,

  /** A bit unit representing 1000 terabits. */
  PETABITS,
  ;

  override fun toBytes(count: Long): Long = when (this) {
    BITS -> bitsToBytes(count)
    KILOBITS -> kilobitsToBytes(count)
    MEGABITS -> megabitsToBytes(count)
    GIGABITS -> gigabitsToBytes(count)
    TERABITS -> terabitsToBytes(count)
    PETABITS -> petabitsToBytes(count)
  }

  /**
   * Converts the given size in the given unit to this unit. Conversions from finer to coarser
   * granularities truncate, so lose precision. For example, converting from `999` bit to
   * kilobits results in `0`. Conversions from coarser to finer granularities with arguments
   * that would numerically overflow saturate to [Long.MIN_VALUE] if negative or
   * [Long.MAX_VALUE] if positive.
   *
   * For example, to convert 10 kilobytes to bytes, use:
   * `ByteUnit.KILOBITS.convert(10, ByteUnit.BITS)`
   *
   * [sourceCount] the size in the given [sourceUnit].
   * [sourceUnit] the unit of the [sourceCount] argument.
   * @return the converted size in this unit, or [Long.MIN_VALUE] if conversion would
   * negatively overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun convert(sourceCount: Long, sourceUnit: BitUnit): Long = when (this) {
    BITS -> sourceUnit.toBits(sourceCount)
    KILOBITS -> sourceUnit.toKilobits(sourceCount)
    MEGABITS -> sourceUnit.toMegabits(sourceCount)
    GIGABITS -> sourceUnit.toGigabits(sourceCount)
    TERABITS -> sourceUnit.toTerabits(sourceCount)
    PETABITS -> sourceUnit.toPetabits(sourceCount)
  }

  /**
   * Equivalent to [BITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toBits(count: Long): Long = when (this) {
    BITS -> bitsToBits(count)
    KILOBITS -> kilobitsToBits(count)
    MEGABITS -> megabitsToBits(count)
    GIGABITS -> gigabitsToBits(count)
    TERABITS -> terabitsToBits(count)
    PETABITS -> petabitsToBits(count)
  }

  /**
   * Equivalent to [KILOBITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toKilobits(count: Long): Long = when (this) {
    BITS -> bitsToKilobits(count)
    KILOBITS -> kilobitsToKilobits(count)
    MEGABITS -> megabitsToKilobits(count)
    GIGABITS -> gigabitsToKilobits(count)
    TERABITS -> terabitsToKilobits(count)
    PETABITS -> petabitsToKilobits(count)
  }

  /**
   * Equivalent to [MEGABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toMegabits(count: Long): Long = when (this) {
    BITS -> bitsToMegabits(count)
    KILOBITS -> kilobitsToMegabits(count)
    MEGABITS -> megabitsToMegabits(count)
    GIGABITS -> gigabitsToMegabits(count)
    TERABITS -> terabitsToMegabits(count)
    PETABITS -> petabitsToMegabits(count)
  }

  /**
   * Equivalent to [GIGABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toGigabits(count: Long): Long = when (this) {
    BITS -> bitsToGigabits(count)
    KILOBITS -> kilobitsToGigabits(count)
    MEGABITS -> megabitsToGigabits(count)
    GIGABITS -> gigabitsToGigabits(count)
    TERABITS -> terabitsToGigabits(count)
    PETABITS -> petabitsToGigabits(count)
  }

  /**
   * Equivalent to [TERABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toTerabits(count: Long): Long = when (this) {
    BITS -> bitsToTerabits(count)
    KILOBITS -> kilobitsToTerabits(count)
    MEGABITS -> megabitsToTerabits(count)
    GIGABITS -> gigabitsToTerabits(count)
    TERABITS -> terabitsToTerabits(count)
    PETABITS -> petabitsToTerabits(count)
  }

  /**
   * Equivalent to [PETABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  actual fun toPetabits(count: Long): Long = when (this) {
    BITS -> bitsToPetabits(count)
    KILOBITS -> kilobitsToPetabits(count)
    MEGABITS -> megabitsToPetabits(count)
    GIGABITS -> gigabitsToPetabits(count)
    TERABITS -> terabitsToPetabits(count)
    PETABITS -> petabitsToPetabits(count)
  }

  actual companion object {
    /**
     * Return `bits` as human-readable size string (e.g., "1.2 Gb".
     */
    actual fun format(bits: Long): String {
      val formatter = NSNumberFormatter()
      formatter.numberStyle = NSNumberFormatterDecimalStyle
      formatter.maximumFractionDigits = 1uL
      return format(bits, formatter)
    }

    /**
     * Return `bits` as human-readable size string (e.g., "1.2 Gb". This will use a default
     * [DecimalFormat] instance for formatting the number.
     */
    fun format(
      bits: Long,
      formatter: NSNumberFormatter,
    ): String = formatBitUnit(
      bits = bits,
      formatter = { formatter.stringFromNumber(NSNumber(it))!! },
    )
  }
}
