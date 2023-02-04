package com.jakewharton.byteunits

/**
 * A [DecimalByteUnit] represents power-of-ten byte sizes at a given unit of granularity and
 * provides utility methods to convert across units. A [DecimalByteUnit] does not maintain
 * byte size information, but only helps organize and use byte size representations that may be
 * maintained separately across various contexts.
 */
expect enum class DecimalByteUnit : ByteUnit {
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
  fun convert(sourceCount: Long, sourceUnit: DecimalByteUnit): Long

  /**
   * Equivalent to [KILOBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toKilobytes(count: Long): Long

  /**
   * Equivalent to [MEGABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toMegabytes(count: Long): Long

  /**
   * Equivalent to [GIGABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toGigabytes(count: Long): Long

  /**
   * Equivalent to [TERABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toTerabytes(count: Long): Long

  /**
   * Equivalent to [PETABYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toPetabytes(count: Long): Long

  actual companion object {
    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GB".
     */
    fun format(bytes: Long): String
  }
}
