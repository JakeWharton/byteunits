package com.jakewharton.byteunits

import platform.Foundation.NSLocale
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BitUnitIosTest {
  @Test fun formatWithDecimalFormat() {
    val formatter = NSNumberFormatter()
    formatter.numberStyle = NSNumberFormatterDecimalStyle
    formatter.maximumFractionDigits = 2uL
    formatter.locale = NSLocale("fr-FR")
    assertEquals("16 Kb", BitUnit.format(16000, formatter))
    assertEquals("1,18 Mb", BitUnit.format(1177171, formatter))
  }

  @Test fun formatNegativeValuesThrows() {
    val formatter = NSNumberFormatter()
    assertFailsWith<IllegalArgumentException> {
      BitUnit.format(-1, formatter)
    }.also {
      assertEquals("bits < 0: -1", it.message)
    }
  }
}
