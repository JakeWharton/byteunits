package com.jakewharton.byteunits

/**
 * A [BitUnit] represents bit size at a given unit of granularity and provides utility
 * methods to convert across units. A [BitUnit] does not maintain bit size information,
 * but only helps organize and use bit size representations that may be maintained separately
 * across various contexts.
 */
expect enum class BitUnit : ByteUnit {
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
  fun convert(sourceCount: Long, sourceUnit: BitUnit): Long

  /**
   * Equivalent to [BITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toBits(count: Long): Long

  /**
   * Equivalent to [KILOBITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toKilobits(count: Long): Long

  /**
   * Equivalent to [MEGABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toMegabits(count: Long): Long

  /**
   * Equivalent to [GIGABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toGigabits(count: Long): Long

  /**
   * Equivalent to [TERABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toTerabits(count: Long): Long

  /**
   * Equivalent to [PETABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toPetabits(count: Long): Long

  companion object {
    /**
     * Return `bits` as human-readable size string (e.g., "1.2 Gb".
     */
    fun format(bits: Long): String
  }
}
