package com.jakewharton.byteunits

import com.jakewharton.byteunits.BinaryByteUnit.BYTES
import com.jakewharton.byteunits.BinaryByteUnit.GIBIBYTES
import com.jakewharton.byteunits.BinaryByteUnit.KIBIBYTES
import com.jakewharton.byteunits.BinaryByteUnit.MEBIBYTES
import com.jakewharton.byteunits.BinaryByteUnit.PEBIBYTES
import com.jakewharton.byteunits.BinaryByteUnit.TEBIBYTES
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

class BinaryByteUnitTest {
  @Suppress("KotlinConstantConditions")
  @Test fun convertInternal() {
    for (s in 0..998) {
      assertEquals(s / 1_024L, BYTES.toKibibytes(s.toLong()))
      assertEquals(s / 1_048_576L, BYTES.toMebibytes(s.toLong()))
      assertEquals(s / 1_073_741_824L, BYTES.toGibibytes(s.toLong()))
      assertEquals(s / 1_099_511_627_776L, BYTES.toTebibytes(s.toLong()))
      assertEquals(s / 1_125_899_906_842_624L, BYTES.toPebibytes(s.toLong()))

      assertEquals(s * 1_024L, KIBIBYTES.toBytes(s.toLong()))
      assertEquals(s / 1_024L, KIBIBYTES.toMebibytes(s.toLong()))
      assertEquals(s / 1_048_576L, KIBIBYTES.toGibibytes(s.toLong()))
      assertEquals(s / 1_073_741_824L, KIBIBYTES.toTebibytes(s.toLong()))
      assertEquals(s / 1_099_511_627_776L, KIBIBYTES.toPebibytes(s.toLong()))

      assertEquals(s * 1_048_576L, MEBIBYTES.toBytes(s.toLong()))
      assertEquals(s * 1_024L, MEBIBYTES.toKibibytes(s.toLong()))
      assertEquals(s / 1_024L, MEBIBYTES.toGibibytes(s.toLong()))
      assertEquals(s / 1_048_576L, MEBIBYTES.toTebibytes(s.toLong()))
      assertEquals(s / 1_073_741_824L, MEBIBYTES.toPebibytes(s.toLong()))

      assertEquals(s * 1_073_741_824L, GIBIBYTES.toBytes(s.toLong()))
      assertEquals(s * 1_048_576L, GIBIBYTES.toKibibytes(s.toLong()))
      assertEquals(s * 1_024L, GIBIBYTES.toMebibytes(s.toLong()))
      assertEquals(s / 1_024L, GIBIBYTES.toTebibytes(s.toLong()))
      assertEquals(s / 1_048_576L, GIBIBYTES.toPebibytes(s.toLong()))

      assertEquals(s * 1_099_511_627_776L, TEBIBYTES.toBytes(s.toLong()))
      assertEquals(s * 1_073_741_824L, TEBIBYTES.toKibibytes(s.toLong()))
      assertEquals(s * 1_048_576L, TEBIBYTES.toMebibytes(s.toLong()))
      assertEquals(s * 1_024L, TEBIBYTES.toGibibytes(s.toLong()))
      assertEquals(s / 1_024L, TEBIBYTES.toPebibytes(s.toLong()))

      assertEquals(s * 1_125_899_906_842_624L, PEBIBYTES.toBytes(s.toLong()))
      assertEquals(s * 1_099_511_627_776L, PEBIBYTES.toKibibytes(s.toLong()))
      assertEquals(s * 1_073_741_824L, PEBIBYTES.toMebibytes(s.toLong()))
      assertEquals(s * 1_048_576L, PEBIBYTES.toGibibytes(s.toLong()))
      assertEquals(s * 1_024L, PEBIBYTES.toTebibytes(s.toLong()))
    }
  }

  @Test fun format() {
    assertEquals("0 B", BinaryByteUnit.format(0))
    assertEquals("1 B", BinaryByteUnit.format(1))
    assertEquals("1 KiB", BinaryByteUnit.format(1024))
    assertEquals("1 KiB", BinaryByteUnit.format(1025))
    assertEquals("16 KiB", BinaryByteUnit.format(16384))
    assertEquals("1.2 MiB", BinaryByteUnit.format(1234567))
    assertEquals("1.2 GiB", BinaryByteUnit.format(1288490189))
    assertEquals("8,192 PiB", BinaryByteUnit.format(Long.MAX_VALUE))
  }

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
    try {
      BinaryByteUnit.format(-1)
      fail("Should have thrown.")
    } catch (e: IllegalArgumentException) {
      assertEquals("bytes < 0: -1", e.message)
    }
    try {
      BinaryByteUnit.format(-1, "#.##")
      fail("Should have thrown.")
    } catch (e: IllegalArgumentException) {
      assertEquals("bytes < 0: -1", e.message)
    }
    val format: NumberFormat = DecimalFormat("#.##", DecimalFormatSymbols(Locale.FRENCH))
    try {
      BinaryByteUnit.format(-1, format)
      fail("Should have thrown.")
    } catch (e: IllegalArgumentException) {
      assertEquals("bytes < 0: -1", e.message)
    }
  }
}
