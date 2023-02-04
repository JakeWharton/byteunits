package com.jakewharton.byteunits

import platform.Foundation.NSLocale
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DecimalByteUnitIosTest {
  @Test fun formatWithDecimalFormat() {
    val formatter = NSNumberFormatter()
    formatter.numberStyle = NSNumberFormatterDecimalStyle
    formatter.maximumFractionDigits = 2uL
    formatter.locale = NSLocale("fr-FR")
    assertEquals("16 KB", DecimalByteUnit.format(16000, formatter))
    assertEquals("1,18 MB", DecimalByteUnit.format(1177171, formatter))
  }

  @Test fun formatNegativeValuesThrows() {
    val formatter = NSNumberFormatter()
    assertFailsWith<IllegalArgumentException> {
      DecimalByteUnit.format(-1, formatter)
    }.also {
      assertEquals("bytes < 0: -1", it.message)
    }
  }
}
