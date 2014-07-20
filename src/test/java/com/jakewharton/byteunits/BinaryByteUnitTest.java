package com.jakewharton.byteunits;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
