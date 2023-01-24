package com.jakewharton.byteunits

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BinaryByteUnitJavaTest {
  @Test fun formatWithPattern() {
    val pattern = "0.0#"
    assertEquals("0.0 B", BinaryByteUnit.format(0, pattern))
    assertEquals("1.0 B", BinaryByteUnit.format(1, pattern))
    assertEquals("1.0 KiB", BinaryByteUnit.format(1024, pattern))
    assertEquals("1.0 KiB", BinaryByteUnit.format(1025, pattern))
    assertEquals("16.0 KiB", BinaryByteUnit.format(16384, pattern))
    assertEquals("1.18 MiB", BinaryByteUnit.format(1234567, pattern))
  }

  @Test fun formatWithDecimalFormat() {
    val format: NumberFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.FRENCH))
    assertEquals("16 KiB", BinaryByteUnit.format(16384, format))
    assertEquals("1,18 MiB", BinaryByteUnit.format(1234567, format))
  }

  @Test fun formatNegativeValuesThrows() {
    assertFailsWith<IllegalArgumentException> {
      BinaryByteUnit.format(-1, "#.##")
    }.also {
      assertEquals("bytes < 0: -1", it.message)
    }

    val format: NumberFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.FRENCH))
    assertFailsWith<IllegalArgumentException> {
      BinaryByteUnit.format(-1, format)
    }.also {
      assertEquals("bytes < 0: -1", it.message)
    }
  }
}
