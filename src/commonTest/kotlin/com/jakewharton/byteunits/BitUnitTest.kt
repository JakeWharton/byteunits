package com.jakewharton.byteunits

import com.jakewharton.byteunits.BitUnit.BITS
import com.jakewharton.byteunits.BitUnit.GIGABITS
import com.jakewharton.byteunits.BitUnit.KILOBITS
import com.jakewharton.byteunits.BitUnit.MEGABITS
import com.jakewharton.byteunits.BitUnit.PETABITS
import com.jakewharton.byteunits.BitUnit.TERABITS
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BitUnitTest {
  @Test fun convertInternal() {
    for (s in 0..998) {
      assertEquals(s / 8L, BITS.toBytes(s.toLong()))
      assertEquals(s / 1000L, BITS.toKilobits(s.toLong()))
      assertEquals(s / 1000000L, BITS.toMegabits(s.toLong()))
      assertEquals(s / 1000000000L, BITS.toGigabits(s.toLong()))
      assertEquals(s / 1000000000000L, BITS.toTerabits(s.toLong()))
      assertEquals(s / 1000000000000000L, BITS.toPetabits(s.toLong()))
      assertEquals(s * 125L, KILOBITS.toBytes(s.toLong()))
      assertEquals(s * 1000L, KILOBITS.toBits(s.toLong()))
      assertEquals(s / 1000L, KILOBITS.toMegabits(s.toLong()))
      assertEquals(s / 1000000L, KILOBITS.toGigabits(s.toLong()))
      assertEquals(s / 1000000000L, KILOBITS.toTerabits(s.toLong()))
      assertEquals(s / 1000000000000L, KILOBITS.toPetabits(s.toLong()))
      assertEquals(s * 125000L, MEGABITS.toBytes(s.toLong()))
      assertEquals(s * 1000000L, MEGABITS.toBits(s.toLong()))
      assertEquals(s * 1000L, MEGABITS.toKilobits(s.toLong()))
      assertEquals(s / 1000L, MEGABITS.toGigabits(s.toLong()))
      assertEquals(s / 1000000L, MEGABITS.toTerabits(s.toLong()))
      assertEquals(s / 1000000000L, MEGABITS.toPetabits(s.toLong()))
      assertEquals(s * 125000000L, GIGABITS.toBytes(s.toLong()))
      assertEquals(s * 1000000000L, GIGABITS.toBits(s.toLong()))
      assertEquals(s * 1000000L, GIGABITS.toKilobits(s.toLong()))
      assertEquals(s * 1000L, GIGABITS.toMegabits(s.toLong()))
      assertEquals(s / 1000L, GIGABITS.toTerabits(s.toLong()))
      assertEquals(s / 1000000L, GIGABITS.toPetabits(s.toLong()))
      assertEquals(s * 125000000000L, TERABITS.toBytes(s.toLong()))
      assertEquals(s * 1000000000000L, TERABITS.toBits(s.toLong()))
      assertEquals(s * 1000000000L, TERABITS.toKilobits(s.toLong()))
      assertEquals(s * 1000000L, TERABITS.toMegabits(s.toLong()))
      assertEquals(s * 1000L, TERABITS.toGigabits(s.toLong()))
      assertEquals(s / 1000L, TERABITS.toPetabits(s.toLong()))
      assertEquals(s * 125000000000000L, PETABITS.toBytes(s.toLong()))
      assertEquals(s * 1000000000000000L, PETABITS.toBits(s.toLong()))
      assertEquals(s * 1000000000000L, PETABITS.toKilobits(s.toLong()))
      assertEquals(s * 1000000000L, PETABITS.toMegabits(s.toLong()))
      assertEquals(s * 1000000L, PETABITS.toGigabits(s.toLong()))
      assertEquals(s * 1000L, PETABITS.toTerabits(s.toLong()))
    }
  }

  @Test fun format() {
    assertEquals("0 b", BitUnit.format(0))
    assertEquals("1 b", BitUnit.format(1))
    assertEquals("1 Kb", BitUnit.format(1000))
    assertEquals("1 Kb", BitUnit.format(1001))
    assertEquals("16 Kb", BitUnit.format(16000))
    assertEquals("1.2 Mb", BitUnit.format(1177171))
    assertEquals("1.2 Gb", BitUnit.format(1200000000))
    assertEquals("9,223.4 Pb", BitUnit.format(Long.MAX_VALUE))
  }

  @Test fun formatNegativeValuesThrows() {
    assertFailsWith<IllegalArgumentException> {
      BitUnit.format(-1)
    }.also {
      assertEquals("bits < 0: -1", it.message)
    }
  }
}
