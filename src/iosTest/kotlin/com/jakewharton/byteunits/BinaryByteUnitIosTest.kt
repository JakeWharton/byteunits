package com.jakewharton.byteunits

import platform.Foundation.NSLocale
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BinaryByteUnitIosTest {
  @Test fun formatWithDecimalFormat() {
    val formatter = NSNumberFormatter()
    formatter.numberStyle = NSNumberFormatterDecimalStyle
    formatter.maximumFractionDigits = 2uL
    formatter.locale = NSLocale("fr-FR")
    assertEquals("16 KiB", BinaryByteUnit.format(16384, formatter))
    assertEquals("1,18 MiB", BinaryByteUnit.format(1234567, formatter))
  }

  @Test fun formatNegativeValuesThrows() {
    val formatter = NSNumberFormatter()
    assertFailsWith<IllegalArgumentException> {
      BinaryByteUnit.format(-1, formatter)
    }.also {
      assertEquals("bytes < 0: -1", it.message)
    }
  }
}
