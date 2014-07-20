/**
 * Utility classes for converting between granularities of SI (power-of-ten) and IEC (power-of-two)
 * byte units and bit units.
 * <p>
 * <h3>Example Usage</h3>
 * What's the difference in hard drive space between perception and actual?
 * <pre><code>
 * long perception = BinaryByteUnit.TEBIBYTES.toBytes(2);
 * long usable = DecimalByteUnit.TERABYTES.toBytes(2);
 * long lost = BinaryByteUnit.BYTES.toGibibytes(perception - usable);
 * System.out.println(lost + " GiB lost on a 2TB drive.");
 * </code></pre>
 * <p>
 * Method parameter for specifying a resource size.
 * <pre><code>
 * public void installDiskCache(long count, ByteUnit unit) {
 *   long size = unit.toBytes(count);
 *   // TODO Install disk cache of 'size' bytes.
 * }
 * </code></pre>
 */
package com.jakewharton.byteunits;
