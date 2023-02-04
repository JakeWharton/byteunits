package com.jakewharton.byteunits

import com.jakewharton.byteunits.DecimalByteUnit.BYTES
import com.jakewharton.byteunits.DecimalByteUnit.GIGABYTES
import com.jakewharton.byteunits.DecimalByteUnit.KILOBYTES
import com.jakewharton.byteunits.DecimalByteUnit.MEGABYTES
import com.jakewharton.byteunits.DecimalByteUnit.PETABYTES
import com.jakewharton.byteunits.DecimalByteUnit.TERABYTES
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DecimalByteUnitTest {
  @Test fun convertInternal() {
    for (s in 0..998) {
      assertEquals(s / 1000L, BYTES.toKilobytes(s.toLong()))
      assertEquals(s / 1000000L, BYTES.toMegabytes(s.toLong()))
      assertEquals(s / 1000000000L, BYTES.toGigabytes(s.toLong()))
      assertEquals(s / 1000000000000L, BYTES.toTerabytes(s.toLong()))
      assertEquals(s / 1000000000000000L, BYTES.toPetabytes(s.toLong()))
      assertEquals(s * 1000L, KILOBYTES.toBytes(s.toLong()))
      assertEquals(s / 1000L, KILOBYTES.toMegabytes(s.toLong()))
      assertEquals(s / 1000000L, KILOBYTES.toGigabytes(s.toLong()))
      assertEquals(s / 1000000000L, KILOBYTES.toTerabytes(s.toLong()))
      assertEquals(s / 1000000000000L, KILOBYTES.toPetabytes(s.toLong()))
      assertEquals(s * 1000000L, MEGABYTES.toBytes(s.toLong()))
      assertEquals(s * 1000L, MEGABYTES.toKilobytes(s.toLong()))
      assertEquals(s / 1000L, MEGABYTES.toGigabytes(s.toLong()))
      assertEquals(s / 1000000L, MEGABYTES.toTerabytes(s.toLong()))
      assertEquals(s / 1000000000L, MEGABYTES.toPetabytes(s.toLong()))
      assertEquals(s * 1000000000L, GIGABYTES.toBytes(s.toLong()))
      assertEquals(s * 1000000L, GIGABYTES.toKilobytes(s.toLong()))
      assertEquals(s * 1000L, GIGABYTES.toMegabytes(s.toLong()))
      assertEquals(s / 1000L, GIGABYTES.toTerabytes(s.toLong()))
      assertEquals(s / 1000000L, GIGABYTES.toPetabytes(s.toLong()))
      assertEquals(s * 1000000000000L, TERABYTES.toBytes(s.toLong()))
      assertEquals(s * 1000000000L, TERABYTES.toKilobytes(s.toLong()))
      assertEquals(s * 1000000L, TERABYTES.toMegabytes(s.toLong()))
      assertEquals(s * 1000L, TERABYTES.toGigabytes(s.toLong()))
      assertEquals(s / 1000L, TERABYTES.toPetabytes(s.toLong()))
      assertEquals(s * 1000000000000000L, PETABYTES.toBytes(s.toLong()))
      assertEquals(s * 1000000000000L, PETABYTES.toKilobytes(s.toLong()))
      assertEquals(s * 1000000000L, PETABYTES.toMegabytes(s.toLong()))
      assertEquals(s * 1000000L, PETABYTES.toGigabytes(s.toLong()))
      assertEquals(s * 1000L, PETABYTES.toTerabytes(s.toLong()))
    }
  }

  @Test fun format() {
    assertEquals("0 B", DecimalByteUnit.format(0))
    assertEquals("1 B", DecimalByteUnit.format(1))
    assertEquals("1 KB", DecimalByteUnit.format(1000))
    assertEquals("1 KB", DecimalByteUnit.format(1001))
    assertEquals("16 KB", DecimalByteUnit.format(16000))
    assertEquals("1.2 MB", DecimalByteUnit.format(1177171))
    assertEquals("1.2 GB", DecimalByteUnit.format(1200000000))
    assertEquals("9,223.4 PB", DecimalByteUnit.format(Long.MAX_VALUE))
  }

  @Test fun formatNegativeValuesThrows() {
    assertFailsWith<IllegalArgumentException> {
      DecimalByteUnit.format(-1)
    }.also {
      assertEquals("bytes < 0: -1", it.message)
    }
  }
}
