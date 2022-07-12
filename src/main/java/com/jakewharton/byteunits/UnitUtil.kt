package com.jakewharton.byteunits

internal const val DEFAULT_FORMAT_PATTERN = "#,##0.#"

/** Multiply [size] by [factor] accounting for overflow. */
internal fun multiply(size: Long, factor: Long, over: Long): Long = when {
  size > over -> Long.MAX_VALUE
  size < -over -> Long.MIN_VALUE
  else -> size * factor
}
