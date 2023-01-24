package com.jakewharton.byteunits

/**
 * A [BinaryByteUnit] represents power-of-two byte sizes at a given unit of granularity and
 * provides utility methods to convert across units. A [BinaryByteUnit] does not maintain
 * byte size information, but only helps organize and use byte size representations that may be
 * maintained separately across various contexts.
 */
expect enum class BinaryByteUnit : ByteUnit {
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
  PEBIBYTES,
  ;

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
  fun convert(sourceCount: Long, sourceUnit: BinaryByteUnit): Long

  /**
   * Equivalent to [KIBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toKibibytes(count: Long): Long

  /**
   * Equivalent to [MEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toMebibytes(count: Long): Long

  /**
   * Equivalent to [GIBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toGibibytes(count: Long): Long

  /**
   * Equivalent to [TEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toTebibytes(count: Long): Long

  /**
   * Equivalent to [PEBIBYTES.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  fun toPebibytes(count: Long): Long

  companion object {
    /**
     * Return `bytes` as human-readable size string (e.g., "1.2 GiB").
     */
    fun format(bytes: Long): String
  }
}
