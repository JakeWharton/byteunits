package com.jakewharton.byteunits;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BinaryByteUnitTest {
  @Test public void convertInternal() {
    for (long s = 0; s < 999; s++) {
      assertEquals(s / 1_024L, BinaryByteUnit.BYTES.toKibibytes(s));
      assertEquals(s / 1_048_576L, BinaryByteUnit.BYTES.toMebibytes(s));
      assertEquals(s / 1_073_741_824L, BinaryByteUnit.BYTES.toGibibytes(s));
      assertEquals(s / 1_099_511_627_776L, BinaryByteUnit.BYTES.toTebibytes(s));
      assertEquals(s / 1_125_899_906_842_624L, BinaryByteUnit.BYTES.toPebibytes(s));

      assertEquals(s * 1_024L, BinaryByteUnit.KIBIBYTES.toBytes(s));
      assertEquals(s / 1_024L, BinaryByteUnit.KIBIBYTES.toMebibytes(s));
      assertEquals(s / 1_048_576L, BinaryByteUnit.KIBIBYTES.toGibibytes(s));
      assertEquals(s / 1_073_741_824L, BinaryByteUnit.KIBIBYTES.toTebibytes(s));
      assertEquals(s / 1_099_511_627_776L, BinaryByteUnit.KIBIBYTES.toPebibytes(s));

      assertEquals(s * 1_048_576L, BinaryByteUnit.MEBIBYTES.toBytes(s));
      assertEquals(s * 1_024L, BinaryByteUnit.MEBIBYTES.toKibibytes(s));
      assertEquals(s / 1_024L, BinaryByteUnit.MEBIBYTES.toGibibytes(s));
      assertEquals(s / 1_048_576L, BinaryByteUnit.MEBIBYTES.toTebibytes(s));
      assertEquals(s / 1_073_741_824L, BinaryByteUnit.MEBIBYTES.toPebibytes(s));

      assertEquals(s * 1_073_741_824L, BinaryByteUnit.GIBIBYTES.toBytes(s));
      assertEquals(s * 1_048_576L, BinaryByteUnit.GIBIBYTES.toKibibytes(s));
      assertEquals(s * 1_024L, BinaryByteUnit.GIBIBYTES.toMebibytes(s));
      assertEquals(s / 1_024L, BinaryByteUnit.GIBIBYTES.toTebibytes(s));
      assertEquals(s / 1_048_576L, BinaryByteUnit.GIBIBYTES.toPebibytes(s));

      assertEquals(s * 1_099_511_627_776L, BinaryByteUnit.TEBIBYTES.toBytes(s));
      assertEquals(s * 1_073_741_824L, BinaryByteUnit.TEBIBYTES.toKibibytes(s));
      assertEquals(s * 1_048_576L, BinaryByteUnit.TEBIBYTES.toMebibytes(s));
      assertEquals(s * 1_024L, BinaryByteUnit.TEBIBYTES.toGibibytes(s));
      assertEquals(s / 1_024L, BinaryByteUnit.TEBIBYTES.toPebibytes(s));

      assertEquals(s * 1_125_899_906_842_624L, BinaryByteUnit.PEBIBYTES.toBytes(s));
      assertEquals(s * 1_099_511_627_776L, BinaryByteUnit.PEBIBYTES.toKibibytes(s));
      assertEquals(s * 1_073_741_824L, BinaryByteUnit.PEBIBYTES.toMebibytes(s));
      assertEquals(s * 1_048_576L, BinaryByteUnit.PEBIBYTES.toGibibytes(s));
      assertEquals(s * 1_024L, BinaryByteUnit.PEBIBYTES.toTebibytes(s));
    }
  }

  @Test public void format() {
    assertEquals("0 B", BinaryByteUnit.format(0));
    assertEquals("1 B", BinaryByteUnit.format(1));
    assertEquals("1 KiB", BinaryByteUnit.format(1024));
    assertEquals("1 KiB", BinaryByteUnit.format(1025));
    assertEquals("16 KiB", BinaryByteUnit.format(16_384));
    assertEquals("1.2 MiB", BinaryByteUnit.format(1_234_567));
    assertEquals("1.2 GiB", BinaryByteUnit.format(1_288_490_189));
    assertEquals("8,192 PiB", BinaryByteUnit.format(Long.MAX_VALUE));
  }

  @Test public void formatWithPattern() {
    String pattern = "0.0#";
    assertEquals("0.0 B", BinaryByteUnit.format(0, pattern));
    assertEquals("1.0 B", BinaryByteUnit.format(1, pattern));
    assertEquals("1.0 KiB", BinaryByteUnit.format(1024, pattern));
    assertEquals("1.0 KiB", BinaryByteUnit.format(1025, pattern));
    assertEquals("16.0 KiB", BinaryByteUnit.format(16_384, pattern));
    assertEquals("1.18 MiB", BinaryByteUnit.format(1_234_567, pattern));
  }

  @Test public void formatWithDecimalFormat() {
    NumberFormat format = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.FRENCH));
    assertEquals("16 KiB", BinaryByteUnit.format(16_384, format));
    assertEquals("1,18 MiB", BinaryByteUnit.format(1_234_567, format));
  }

  @Test public void formatNegativeValuesThrows() {
    try {
      BinaryByteUnit.format(-1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bytes < 0: -1", e.getMessage());
    }
    try {
      BinaryByteUnit.format(-1, "#.##");
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bytes < 0: -1", e.getMessage());
    }
    NumberFormat format = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.FRENCH));
    try {
      BinaryByteUnit.format(-1, format);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("bytes < 0: -1", e.getMessage());
    }
  }
}
