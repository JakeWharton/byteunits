package com.jakewharton.byteunits

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

class BitUnitJvmTest {
  @Test fun formatWithPattern() {
    val pattern = "0.0#"
    assertEquals("0.0 b", BitUnit.format(0, pattern))
    assertEquals("1.0 b", BitUnit.format(1, pattern))
    assertEquals("1.0 Kb", BitUnit.format(1000, pattern))
    assertEquals("1.0 Kb", BitUnit.format(1001, pattern))
    assertEquals("16.0 Kb", BitUnit.format(16000, pattern))
    assertEquals("1.18 Mb", BitUnit.format(1177171, pattern))
  }

  @Test fun formatWithDecimalFormat() {
    val format: NumberFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.FRENCH))
    assertEquals("16 Kb", BitUnit.format(16000, format))
    assertEquals("1,18 Mb", BitUnit.format(1177171, format))
  }

  @Test fun formatNegativeValuesThrows() {
    assertFailsWith<IllegalArgumentException> {
      BitUnit.format(-1, "#.##")
    }.also {
      assertEquals("bits < 0: -1", it.message)
    }

    val format: NumberFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.FRENCH))
    assertFailsWith<IllegalArgumentException> {
      BitUnit.format(-1, format)
    }.also {
      assertEquals("bits < 0: -1", it.message)
    }
  }
}
