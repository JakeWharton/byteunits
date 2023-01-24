package com.jakewharton.byteunits

private val UNITS = arrayOf("B", "KiB", "MiB", "GiB", "TiB", "PiB")

internal fun format(
  bytes: Long,
  formatter: (Double) -> String,
): String {
  require(bytes >= 0) { "bytes < 0: $bytes" }
  var unitIndex = 0
  var count = bytes.toDouble()
  while (count >= 1024.0 && unitIndex < UNITS.size - 1) {
    count /= 1024.0
    unitIndex += 1
  }
  return formatter(count) + ' ' + UNITS[unitIndex]
}

private const val B = 1L
private const val KB = B * 1024L
private const val MB = KB * 1024L
private const val GB = MB * 1024L
private const val TB = GB * 1024L
private const val PB = TB * 1024L
private const val MAX = Long.MAX_VALUE

internal fun bytesToBytes(count: Long) = count
internal fun bytesToKibibytes(count: Long) = count / (KB / B)
internal fun bytesToMebibytes(count: Long) = count / (MB / B)
internal fun bytesToGibibytes(count: Long) = count / (GB / B)
internal fun bytesToTebibytes(count: Long) = count / (TB / B)
internal fun bytesToPebibytes(count: Long) = count / (PB / B)

internal fun kibibytesToBytes(count: Long) = checkedMultiply(count, KB / B, MAX / (KB / B))
internal fun kibibytesToKibibytes(count: Long) = count
internal fun kibibytesToMebibytes(count: Long) = count / (MB / KB)
internal fun kibibytesToGibibytes(count: Long) = count / (GB / KB)
internal fun kibibytesToTebibytes(count: Long) = count / (TB / KB)
internal fun kibibytesToPebibytes(count: Long) = count / (PB / KB)

internal fun mebibytesToBytes(count: Long) = checkedMultiply(count, MB / B, MAX / (MB / B))
internal fun mebibytesToKibibytes(count: Long) = checkedMultiply(count, MB / KB, MAX / (MB / KB))
internal fun mebibytesToMebibytes(count: Long) = count
internal fun mebibytesToGibibytes(count: Long) = count / (GB / MB)
internal fun mebibytesToTebibytes(count: Long) = count / (TB / MB)
internal fun mebibytesToPebibytes(count: Long) = count / (PB / MB)

internal fun gibibytesToBytes(count: Long) = checkedMultiply(count, GB / B, MAX / (GB / B))
internal fun gibibytesToKibibytes(count: Long) = checkedMultiply(count, GB / KB, MAX / (GB / KB))
internal fun gibibytesToMebibytes(count: Long) = checkedMultiply(count, GB / MB, MAX / (GB / MB))
internal fun gibibytesToGibibytes(count: Long) = count
internal fun gibibytesToTebibytes(count: Long) = count / (TB / GB)
internal fun gibibytesToPebibytes(count: Long) = count / (PB / GB)

internal fun tebibytesToBytes(count: Long) = checkedMultiply(count, TB / B, MAX / (TB / B))
internal fun tebibytesToKibibytes(count: Long) = checkedMultiply(count, TB / KB, MAX / (TB / KB))
internal fun tebibytesToMebibytes(count: Long) = checkedMultiply(count, TB / MB, MAX / (TB / MB))
internal fun tebibytesToGibibytes(count: Long) = checkedMultiply(count, TB / GB, MAX / (TB / GB))
internal fun tebibytesToTebibytes(count: Long) = count
internal fun tebibytesToPebibytes(count: Long) = count / (PB / TB)

internal fun pebibytesToBytes(count: Long) = checkedMultiply(count, PB / B, MAX / (PB / B))
internal fun pebibytesToKibibytes(count: Long) = checkedMultiply(count, PB / KB, MAX / (PB / KB))
internal fun pebibytesToMebibytes(count: Long) = checkedMultiply(count, PB / MB, MAX / (PB / MB))
internal fun pebibytesToGibibytes(count: Long) = checkedMultiply(count, PB / GB, MAX / (PB / GB))
internal fun pebibytesToTebibytes(count: Long) = checkedMultiply(count, PB / TB, MAX / (PB / TB))
internal fun pebibytesToPebibytes(count: Long) = count
