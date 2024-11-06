package com.jakewharton.byteunits

import java.text.DecimalFormat
import java.text.NumberFormat

/**
 * A [BitUnit] represents bit size at a given unit of granularity and provides utility
 * methods to convert across units. A [BitUnit] does not maintain bit size information,
 * but only helps organize and use bit size representations that may be maintained separately
 * across various contexts.
 */
enum class BitUnit : ByteUnit {
  /** Bit unit representing one bit. */
  BITS {
    override fun convert(sourceCount: Long, sourceUnit: BitUnit): Long = sourceUnit.toBits(sourceCount)

    override fun toBytes(count: Long): Long = count / BYTE

    override fun toBits(count: Long): Long = count

    override fun toKilobits(count: Long): Long = count / (KB / B)

    override fun toMegabits(count: Long): Long = count / (MB / B)

    override fun toGigabits(count: Long): Long = count / (GB / B)

    override fun toTerabits(count: Long): Long = count / (TB / B)

    override fun toPetabits(count: Long): Long = count / (PB / B)
  },

  /** A bit unit representing 1000 bits. */
  KILOBITS {
    override fun convert(sourceCount: Long, sourceUnit: BitUnit): Long = sourceUnit.toKilobits(sourceCount)

    override fun toBytes(count: Long): Long = count * KBYTE

    override fun toBits(count: Long): Long = checkedMultiply(count, KB / B, MAX / (KB / B))

    override fun toKilobits(count: Long): Long = count

    override fun toMegabits(count: Long): Long = count / (MB / KB)

    override fun toGigabits(count: Long): Long = count / (GB / KB)

    override fun toTerabits(count: Long): Long = count / (TB / KB)

    override fun toPetabits(count: Long): Long = count / (PB / KB)
  },

  /** A bit unit representing 1000 kilobits. */
  MEGABITS {
    override fun convert(sourceCount: Long, sourceUnit: BitUnit): Long = sourceUnit.toMegabits(sourceCount)

    override fun toBytes(count: Long): Long = count * MBYTE

    override fun toBits(count: Long): Long = checkedMultiply(count, MB / B, MAX / (MB / B))

    override fun toKilobits(count: Long): Long = checkedMultiply(count, MB / KB, MAX / (MB / KB))

    override fun toMegabits(count: Long): Long = count

    override fun toGigabits(count: Long): Long = count / (GB / MB)

    override fun toTerabits(count: Long): Long = count / (TB / MB)

    override fun toPetabits(count: Long): Long = count / (PB / MB)
  },

  /** A bit unit representing 1000 megabits. */
  GIGABITS {
    override fun convert(sourceCount: Long, sourceUnit: BitUnit): Long = sourceUnit.toGigabits(sourceCount)

    override fun toBytes(count: Long): Long = count * GBYTE

    override fun toBits(count: Long): Long = checkedMultiply(count, GB / B, MAX / (GB / B))

    override fun toKilobits(count: Long): Long = checkedMultiply(count, GB / KB, MAX / (GB / KB))

    override fun toMegabits(count: Long): Long = checkedMultiply(count, GB / MB, MAX / (GB / MB))

    override fun toGigabits(count: Long): Long = count

    override fun toTerabits(count: Long): Long = count / (TB / GB)

    override fun toPetabits(count: Long): Long = count / (PB / GB)
  },

  /** A bit unit representing 1000 gigabits. */
  TERABITS {
    override fun convert(sourceCount: Long, sourceUnit: BitUnit): Long = sourceUnit.toTerabits(sourceCount)

    override fun toBytes(count: Long): Long = count * TBYTE

    override fun toBits(count: Long): Long = checkedMultiply(count, TB / B, MAX / (TB / B))

    override fun toKilobits(count: Long): Long = checkedMultiply(count, TB / KB, MAX / (TB / KB))

    override fun toMegabits(count: Long): Long = checkedMultiply(count, TB / MB, MAX / (TB / MB))

    override fun toGigabits(count: Long): Long = checkedMultiply(count, TB / GB, MAX / (TB / GB))

    override fun toTerabits(count: Long): Long = count

    override fun toPetabits(count: Long): Long = count / (PB / TB)
  },

  /** A bit unit representing 1000 terabits. */
  PETABITS {
    override fun convert(sourceCount: Long, sourceUnit: BitUnit): Long = sourceUnit.toPetabits(sourceCount)

    override fun toBytes(count: Long): Long = count * PBYTE

    override fun toBits(count: Long): Long = checkedMultiply(count, PB / B, MAX / (PB / B))

    override fun toKilobits(count: Long): Long = checkedMultiply(count, PB / KB, MAX / (PB / KB))

    override fun toMegabits(count: Long): Long = checkedMultiply(count, PB / MB, MAX / (PB / MB))

    override fun toGigabits(count: Long): Long = checkedMultiply(count, PB / GB, MAX / (PB / GB))

    override fun toTerabits(count: Long): Long = checkedMultiply(count, PB / TB, MAX / (PB / TB))

    override fun toPetabits(count: Long): Long = count
  },
  ;

  /**
   * Converts the given size in the given unit to this unit. Conversions from finer to coarser
   * granularities truncate, so lose precision. For example, converting from `999` bit to
   * kilobits results in `0`. Conversions from coarser to finer granularities with arguments
   * that would numerically overflow saturate to [Long.MIN_VALUE] if negative or
   * [Long.MAX_VALUE] if positive.
   *
   *
   * For example, to convert 10 kilobytes to bytes, use:
   * `ByteUnit.KILOBITS.convert(10, ByteUnit.BITS)`
   *
   * [sourceCount] the size in the given [sourceUnit].
   * [sourceUnit] the unit of the [sourceCount] argument.
   * @return the converted size in this unit, or [Long.MIN_VALUE] if conversion would
   * negatively overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun convert(sourceCount: Long, sourceUnit: BitUnit): Long

  /**
   * Equivalent to [BITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toBits(count: Long): Long

  /**
   * Equivalent to [KILOBITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toKilobits(count: Long): Long

  /**
   * Equivalent to [MEGABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toMegabits(count: Long): Long

  /**
   * Equivalent to [GIGABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toGigabits(count: Long): Long

  /**
   * Equivalent to [TERABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toTerabits(count: Long): Long

  /**
   * Equivalent to [PETABITS.convert(count, this)][convert].
   * [count] the bit count
   * @return the converted count, or [Long.MIN_VALUE] if conversion would negatively
   * overflow, or [Long.MAX_VALUE] if it would positively overflow.
   */
  abstract fun toPetabits(count: Long): Long

  companion object {
    private const val B = 1L
    private const val KB = B * 1000L
    private const val MB = KB * 1000L
    private const val GB = MB * 1000L
    private const val TB = GB * 1000L
    private const val PB = TB * 1000L
    private const val BYTE = B * 8
    private const val KBYTE = 1000L / BYTE
    private const val MBYTE = KBYTE * 1000L
    private const val GBYTE = MBYTE * 1000L
    private const val TBYTE = GBYTE * 1000L
    private const val PBYTE = TBYTE * 1000L
    private const val MAX = Long.MAX_VALUE
    private val UNITS = arrayOf("b", "Kb", "Mb", "Gb", "Tb", "Pb")

    /**
     * Return `bits` as human-readable size string (e.g., "1.2 Gb". This will use a
     * [DecimalFormat] instance with [pattern] for formatting the number.
     */
    fun format(bits: Long, pattern: String): String {
      return format(bits, DecimalFormat(pattern))
    }

    /**
     * Return `bits` as human-readable size string (e.g., "1.2 Gb". This will use a default
     * [DecimalFormat] instance for formatting the number.
     */
    @JvmOverloads fun format(
      bits: Long,
      format: NumberFormat = DecimalFormat(DEFAULT_FORMAT_PATTERN),
    ): String {
      require(bits >= 0) { "bits < 0: $bits" }
      var unitIndex = 0
      var count = bits.toDouble()
      while (count >= 1000.0 && unitIndex < UNITS.size - 1) {
        count /= 1000.0
        unitIndex += 1
      }
      return format.format(count) + ' ' + UNITS[unitIndex]
    }
  }
}
