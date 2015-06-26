package com.jakewharton.byteunits;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DecimalByteUnitTest {
  @Test public void convertInternal() {
    for (long s = 0; s < 999; s++) {
      assertEquals(s / 1_000L, DecimalByteUnit.BYTES.toKilobytes(s));
      assertEquals(s / 1_000_000L, DecimalByteUnit.BYTES.toMegabytes(s));
      assertEquals(s / 1_000_000_000L, DecimalByteUnit.BYTES.toGigabytes(s));
      assertEquals(s / 1_000_000_000_000L, DecimalByteUnit.BYTES.toTerabytes(s));
      assertEquals(s / 1_000_000_000_000_000L, DecimalByteUnit.BYTES.toPetabytes(s));

      assertEquals(s * 1_000L, DecimalByteUnit.KILOBYTES.toBytes(s));
      assertEquals(s / 1_000L, DecimalByteUnit.KILOBYTES.toMegabytes(s));
      assertEquals(s / 1_000_000L, DecimalByteUnit.KILOBYTES.toGigabytes(s));
      assertEquals(s / 1_000_000_000L, DecimalByteUnit.KILOBYTES.toTerabytes(s));
      assertEquals(s / 1_000_000_000_000L, DecimalByteUnit.KILOBYTES.toPetabytes(s));

      assertEquals(s * 1_000_000L, DecimalByteUnit.MEGABYTES.toBytes(s));
      assertEquals(s * 1_000L, DecimalByteUnit.MEGABYTES.toKilobytes(s));
      assertEquals(s / 1_000L, DecimalByteUnit.MEGABYTES.toGigabytes(s));
      assertEquals(s / 1_000_000L, DecimalByteUnit.MEGABYTES.toTerabytes(s));
      assertEquals(s / 1_000_000_000L, DecimalByteUnit.MEGABYTES.toPetabytes(s));

      assertEquals(s * 1_000_000_000L, DecimalByteUnit.GIGABYTES.toBytes(s));
      assertEquals(s * 1_000_000L, DecimalByteUnit.GIGABYTES.toKilobytes(s));
      assertEquals(s * 1_000L, DecimalByteUnit.GIGABYTES.toMegabytes(s));
      assertEquals(s / 1_000L, DecimalByteUnit.GIGABYTES.toTerabytes(s));
      assertEquals(s / 1_000_000L, DecimalByteUnit.GIGABYTES.toPetabytes(s));

      assertEquals(s * 1_000_000_000_000L, DecimalByteUnit.TERABYTES.toBytes(s));
      assertEquals(s * 1_000_000_000L, DecimalByteUnit.TERABYTES.toKilobytes(s));
      assertEquals(s * 1_000_000L, DecimalByteUnit.TERABYTES.toMegabytes(s));
      assertEquals(s * 1_000L, DecimalByteUnit.TERABYTES.toGigabytes(s));
      assertEquals(s / 1_000L, DecimalByteUnit.TERABYTES.toPetabytes(s));

      assertEquals(s * 1_000_000_000_000_000L, DecimalByteUnit.PETABYTES.toBytes(s));
      assertEquals(s * 1_000_000_000_000L, DecimalByteUnit.PETABYTES.toKilobytes(s));
      assertEquals(s * 1_000_000_000L, DecimalByteUnit.PETABYTES.toMegabytes(s));
      assertEquals(s * 1_000_000L, DecimalByteUnit.PETABYTES.toGigabytes(s));
      assertEquals(s * 1_000L, DecimalByteUnit.PETABYTES.toTerabytes(s));
    }
  }

  @Test public void format() {
    assertEquals("0 B", DecimalByteUnit.format(0));
    assertEquals("1 B", DecimalByteUnit.format(1));
    assertEquals("1 KB", DecimalByteUnit.format(1000));
    assertEquals("1 KB", DecimalByteUnit.format(1001));
    assertEquals("16 KB", DecimalByteUnit.format(16_000));
    assertEquals("1.2 MB", DecimalByteUnit.format(1_177_171));
    assertEquals("1.2 GB", DecimalByteUnit.format(1_200_000_000));
    assertEquals("9,223.4 PB", DecimalByteUnit.format(Long.MAX_VALUE));
  }

  @Test public void formatWithPattern() {
    String pattern = "0.0#";
    assertEquals("0.0 B", DecimalByteUnit.format(0, pattern));
    assertEquals("1.0 B", DecimalByteUnit.format(1, pattern));
    assertEquals("1.0 KB", DecimalByteUnit.format(1000, pattern));
    assertEquals("1.0 KB", DecimalByteUnit.format(1001, pattern));
    assertEquals("16.0 KB", DecimalByteUnit.format(16_000, pattern));
    assertEquals("1.18 MB", DecimalByteUnit.format(1_177_171, pattern));
  }

  @Test public void formatWithDecimalFormat() {
    NumberFormat format = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.FRENCH));
    assertEquals("16 KB", DecimalByteUnit.format(16_000, format));
    assertEquals("1,18 MB", DecimalByteUnit.format(1_177_171, format));
  }

  @Test public void formatNegativeValuesThrows() {
    try {
      DecimalByteUnit.format(-1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bytes < 0: -1", e.getMessage());
    }
    try {
      DecimalByteUnit.format(-1, "#.##");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bytes < 0: -1", e.getMessage());
    }
    NumberFormat format = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.FRENCH));
    try {
      DecimalByteUnit.format(-1, format);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bytes < 0: -1", e.getMessage());
    }
  }
}
