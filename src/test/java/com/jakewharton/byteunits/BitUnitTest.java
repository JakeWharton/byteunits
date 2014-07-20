package com.jakewharton.byteunits;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BitUnitTest {
  @Test public void convertInternal() {
    for (long s = 0; s < 999; s++) {
      assertEquals(s / 8L, BitUnit.BITS.toBytes(s));
      assertEquals(s / 1_000L, BitUnit.BITS.toKilobits(s));
      assertEquals(s / 1_000_000L, BitUnit.BITS.toMegabits(s));
      assertEquals(s / 1_000_000_000L, BitUnit.BITS.toGigabits(s));
      assertEquals(s / 1_000_000_000_000L, BitUnit.BITS.toTerabits(s));
      assertEquals(s / 1_000_000_000_000_000L, BitUnit.BITS.toPetabits(s));

      assertEquals(s * 125L, BitUnit.KILOBITS.toBytes(s));
      assertEquals(s * 1_000L, BitUnit.KILOBITS.toBits(s));
      assertEquals(s / 1_000L, BitUnit.KILOBITS.toMegabits(s));
      assertEquals(s / 1_000_000L, BitUnit.KILOBITS.toGigabits(s));
      assertEquals(s / 1_000_000_000L, BitUnit.KILOBITS.toTerabits(s));
      assertEquals(s / 1_000_000_000_000L, BitUnit.KILOBITS.toPetabits(s));

      assertEquals(s * 125_000L, BitUnit.MEGABITS.toBytes(s));
      assertEquals(s * 1_000_000L, BitUnit.MEGABITS.toBits(s));
      assertEquals(s * 1_000L, BitUnit.MEGABITS.toKilobits(s));
      assertEquals(s / 1_000L, BitUnit.MEGABITS.toGigabits(s));
      assertEquals(s / 1_000_000L, BitUnit.MEGABITS.toTerabits(s));
      assertEquals(s / 1_000_000_000L, BitUnit.MEGABITS.toPetabits(s));

      assertEquals(s * 125_000_000L, BitUnit.GIGABITS.toBytes(s));
      assertEquals(s * 1_000_000_000L, BitUnit.GIGABITS.toBits(s));
      assertEquals(s * 1_000_000L, BitUnit.GIGABITS.toKilobits(s));
      assertEquals(s * 1_000L, BitUnit.GIGABITS.toMegabits(s));
      assertEquals(s / 1_000L, BitUnit.GIGABITS.toTerabits(s));
      assertEquals(s / 1_000_000L, BitUnit.GIGABITS.toPetabits(s));

      assertEquals(s * 125_000_000_000L, BitUnit.TERABITS.toBytes(s));
      assertEquals(s * 1_000_000_000_000L, BitUnit.TERABITS.toBits(s));
      assertEquals(s * 1_000_000_000L, BitUnit.TERABITS.toKilobits(s));
      assertEquals(s * 1_000_000L, BitUnit.TERABITS.toMegabits(s));
      assertEquals(s * 1_000L, BitUnit.TERABITS.toGigabits(s));
      assertEquals(s / 1_000L, BitUnit.TERABITS.toPetabits(s));

      assertEquals(s * 125_000_000_000_000L, BitUnit.PETABITS.toBytes(s));
      assertEquals(s * 1_000_000_000_000_000L, BitUnit.PETABITS.toBits(s));
      assertEquals(s * 1_000_000_000_000L, BitUnit.PETABITS.toKilobits(s));
      assertEquals(s * 1_000_000_000L, BitUnit.PETABITS.toMegabits(s));
      assertEquals(s * 1_000_000L, BitUnit.PETABITS.toGigabits(s));
      assertEquals(s * 1_000L, BitUnit.PETABITS.toTerabits(s));
    }
  }
}
