package com.jakewharton.byteunits

private val UNITS = arrayOf("B", "KB", "MB", "GB", "TB", "PB")

internal fun formatDecimalByteUnit(
  bytes: Long,
  formatter: (Double) -> String,
): String {
  require(bytes >= 0) { "bytes < 0: $bytes" }
  var unitIndex = 0
  var count = bytes.toDouble()
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
private const val MAX = Long.MAX_VALUE

internal fun kilobytesToBytes(count: Long) = checkedMultiply(count, KB / B, MAX / (KB / B))
internal fun megabytesToBytes(count: Long) = checkedMultiply(count, MB / B, MAX / (MB / B))
internal fun gigabytesToBytes(count: Long) = checkedMultiply(count, GB / B, MAX / (GB / B))
internal fun terabytesToBytes(count: Long) = checkedMultiply(count, TB / B, MAX / (TB / B))
internal fun petabytesToBytes(count: Long) = checkedMultiply(count, PB / B, MAX / (PB / B))

internal fun bytesToKilobytes(count: Long) = count / (KB / B)
internal fun kilobytesToKilobytes(count: Long) = count
internal fun megabytesToKilobytes(count: Long) = checkedMultiply(count, MB / KB, MAX / (MB / KB))
internal fun gigabytesToKilobytes(count: Long) = checkedMultiply(count, GB / KB, MAX / (GB / KB))
internal fun terabytesToKilobytes(count: Long) = checkedMultiply(count, TB / KB, MAX / (TB / KB))
internal fun petabytesToKilobytes(count: Long) = checkedMultiply(count, PB / KB, MAX / (PB / KB))

internal fun bytesToMegabytes(count: Long) = count / (MB / B)
internal fun kilobytesToMegabytes(count: Long) = count / (MB / KB)
internal fun megabytesToMegabytes(count: Long) = count
internal fun gigabytesToMegabytes(count: Long) = checkedMultiply(count, GB / MB, MAX / (GB / MB))
internal fun terabytesToMegabytes(count: Long) = checkedMultiply(count, TB / MB, MAX / (TB / MB))
internal fun petabytesToMegabytes(count: Long) = checkedMultiply(count, PB / MB, MAX / (PB / MB))

internal fun bytesToGigabytes(count: Long) = count / (GB / B)
internal fun kilobytesToGigabytes(count: Long) = count / (GB / KB)
internal fun megabytesToGigabytes(count: Long) = count / (GB / MB)
internal fun gigabytesToGigabytes(count: Long) = count
internal fun terabytesToGigabytes(count: Long) = checkedMultiply(count, TB / GB, MAX / (TB / GB))
internal fun petabytesToGigabytes(count: Long) = checkedMultiply(count, PB / GB, MAX / (PB / GB))

internal fun bytesToTeraBytes(count: Long) = count / (TB / B)
internal fun kilobytesToTeraBytes(count: Long) = count / (TB / KB)
internal fun megabytesToTeraBytes(count: Long) = count / (TB / MB)
internal fun gigabytesToTeraBytes(count: Long) = count / (TB / GB)
internal fun terabytesToTeraBytes(count: Long) = count
internal fun petabytesToTeraBytes(count: Long) = checkedMultiply(count, PB / TB, MAX / (PB / TB))

internal fun bytesToPetaBytes(count: Long) = count / (PB / B)
internal fun kilobytesToPetaBytes(count: Long) = count / (PB / KB)
internal fun megabytesToPetaBytes(count: Long) = count / (PB / MB)
internal fun gigabytesToPetaBytes(count: Long) = count / (PB / GB)
internal fun terabytesToPetaBytes(count: Long) = count / (PB / TB)
internal fun petabytesToPetaBytes(count: Long) = count
