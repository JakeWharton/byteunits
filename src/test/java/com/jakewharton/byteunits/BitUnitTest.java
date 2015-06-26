package com.jakewharton.byteunits;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

  @Test public void format() {
    assertEquals("0 b", BitUnit.format(0));
    assertEquals("1 b", BitUnit.format(1));
    assertEquals("1 Kb", BitUnit.format(1000));
    assertEquals("1 Kb", BitUnit.format(1001));
    assertEquals("16 Kb", BitUnit.format(16_000));
    assertEquals("1.2 Mb", BitUnit.format(1_177_171));
    assertEquals("1.2 Gb", BitUnit.format(1_200_000_000));
    assertEquals("9,223.4 Pb", BitUnit.format(Long.MAX_VALUE));
  }

  @Test public void formatWithPattern() {
    String pattern = "0.0#";
    assertEquals("0.0 b", BitUnit.format(0, pattern));
    assertEquals("1.0 b", BitUnit.format(1, pattern));
    assertEquals("1.0 Kb", BitUnit.format(1000, pattern));
    assertEquals("1.0 Kb", BitUnit.format(1001, pattern));
    assertEquals("16.0 Kb", BitUnit.format(16_000, pattern));
    assertEquals("1.18 Mb", BitUnit.format(1_177_171, pattern));
  }

  @Test public void formatWithDecimalFormat() {
    NumberFormat format = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.FRENCH));
    assertEquals("16 Kb", BitUnit.format(16_000, format));
    assertEquals("1,18 Mb", BitUnit.format(1_177_171, format));
  }

  @Test public void formatNegativeValuesThrows() {
    try {
      BitUnit.format(-1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bits < 0: -1", e.getMessage());
    }
    try {
      BitUnit.format(-1, "#.##");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bits < 0: -1", e.getMessage());
    }
    NumberFormat format = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.FRENCH));
    try {
      BitUnit.format(-1, format);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bits < 0: -1", e.getMessage());
    }
  }
}
