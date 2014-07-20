package com.jakewharton.byteunits;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
