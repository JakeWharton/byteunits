Byte Units
==========

Utility classes for converting between granularities of SI and IEC byte units
and bit units.



Example Usage
-------------

What's the difference in hard drive space between perception and actual?
```java
long perception = BinaryByteUnit.TEBIBYTES.toBytes(2);
long usable = DecimalByteUnit.TERABYTES.toBytes(2);
long lost = BinaryByteUnit.BYTES.toGibibytes(perception - usable);
System.out.println(lost + " GiB lost on a 2TB drive.");
```

Method parameter for specifying a resource size.
```java
public void installDiskCache(long count, ByteUnit unit) {
  long size = unit.toBytes(count);
  // TODO Install disk cache of 'size' bytes.
}
```

Print human-readable strings for byte and bit amounts.
```java
long bytes = 2 * 1024 * 1024 * 1024;
System.out.println(BinaryByteUnit.format(bytes));
```



Download
--------

Grab the [latest .jar][1] or add via Gradle:
```groovy
compile 'com.jakewharton.byteunits:byteunits:0.9.1'
```
or Maven:
```xml
<dependency>
  <groupId>com.jakewharton.byteunits</groupId>
  <artifactId>byteunits</artifactId>
  <version>0.9.1</version>
</dependency>
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].



License
-------

    Copyright 2014 Jake Wharton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




 [1]: https://search.maven.org/remote_content?g=com.jakewharton.byteunits&a=byteunits&v=LATEST
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/
