package com.jakewharton.byteunits

private val UNITS = arrayOf("b", "Kb", "Mb", "Gb", "Tb", "Pb")

internal fun formatBitUnit(
  bits: Long,
  formatter: (Double) -> String,
): String {
  require(bits >= 0) { "bits < 0: $bits" }
  var unitIndex = 0
  var count = bits.toDouble()
  while (count >= 1000.0 && unitIndex < UNITS.size - 1) {
    count /= 1000.0
    unitIndex += 1
  }
  return formatter(count) + ' ' + UNITS[unitIndex]
}

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

internal fun bitsToBytes(count: Long) = count / BYTE
internal fun kilobitsToBytes(count: Long) = count * KBYTE
internal fun megabitsToBytes(count: Long) = count * MBYTE
internal fun gigabitsToBytes(count: Long) = count * GBYTE
internal fun terabitsToBytes(count: Long) = count * TBYTE
internal fun petabitsToBytes(count: Long) = count * PBYTE

internal fun bitsToBits(count: Long) = count
internal fun kilobitsToBits(count: Long) = checkedMultiply(count, KB / B, MAX / (KB / B))
internal fun megabitsToBits(count: Long) = checkedMultiply(count, MB / B, MAX / (MB / B))
internal fun gigabitsToBits(count: Long) = checkedMultiply(count, GB / B, MAX / (GB / B))
internal fun terabitsToBits(count: Long) = checkedMultiply(count, TB / B, MAX / (TB / B))
internal fun petabitsToBits(count: Long) = checkedMultiply(count, PB / B, MAX / (PB / B))

internal fun bitsToKilobits(count: Long) = count / (KB / B)
internal fun kilobitsToKilobits(count: Long) = count
internal fun megabitsToKilobits(count: Long) = checkedMultiply(count, MB / KB, MAX / (MB / KB))
internal fun gigabitsToKilobits(count: Long) = checkedMultiply(count, GB / KB, MAX / (GB / KB))
internal fun terabitsToKilobits(count: Long) = checkedMultiply(count, TB / KB, MAX / (TB / KB))
internal fun petabitsToKilobits(count: Long) = checkedMultiply(count, PB / KB, MAX / (PB / KB))

internal fun bitsToMegabits(count: Long) = count / (MB / B)
internal fun kilobitsToMegabits(count: Long) = count / (MB / KB)
internal fun megabitsToMegabits(count: Long) = count
internal fun gigabitsToMegabits(count: Long) = checkedMultiply(count, GB / MB, MAX / (GB / MB))
internal fun terabitsToMegabits(count: Long) = checkedMultiply(count, TB / MB, MAX / (TB / MB))
internal fun petabitsToMegabits(count: Long) = checkedMultiply(count, PB / MB, MAX / (PB / MB))

internal fun bitsToGigabits(count: Long) = count / (GB / B)
internal fun kilobitsToGigabits(count: Long) = count / (GB / KB)
internal fun megabitsToGigabits(count: Long) = count / (GB / MB)
internal fun gigabitsToGigabits(count: Long) = count
internal fun terabitsToGigabits(count: Long) = checkedMultiply(count, TB / GB, MAX / (TB / GB))
internal fun petabitsToGigabits(count: Long) = checkedMultiply(count, PB / GB, MAX / (PB / GB))

internal fun bitsToTerabits(count: Long) = count / (TB / B)
internal fun kilobitsToTerabits(count: Long) = count / (TB / KB)
internal fun megabitsToTerabits(count: Long) = count / (TB / MB)
internal fun gigabitsToTerabits(count: Long) = count / (TB / GB)
internal fun terabitsToTerabits(count: Long) = count
internal fun petabitsToTerabits(count: Long) = checkedMultiply(count, PB / TB, MAX / (PB / TB))

internal fun bitsToPetabits(count: Long) = count / (PB / B)
internal fun kilobitsToPetabits(count: Long) = count / (PB / KB)
internal fun megabitsToPetabits(count: Long) = count / (PB / MB)
internal fun gigabitsToPetabits(count: Long) = count / (PB / GB)
internal fun terabitsToPetabits(count: Long) = count / (PB / TB)
internal fun petabitsToPetabits(count: Long) = count
