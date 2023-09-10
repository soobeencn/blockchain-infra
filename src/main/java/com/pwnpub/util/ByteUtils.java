package com.pwnpub.util;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.stream.Stream;

public class ByteUtils {
    /**
     * 将多个字节数据合并为一个字节数据
     * @param bytes 字节数组
     * @return 整个字节数组
     */
    public static byte[] merge(byte[]... bytes) {
        Stream<Byte> stream = Stream.of();
        for (byte[] b : bytes) {
            stream = Stream.concat(stream,
                    Arrays.stream(ArrayUtils.toObject(b)));
        }
        return ArrayUtils.toPrimitive(stream.toArray(Byte[]::new));
    }

    /**
     * long类型转为byte[]类型
     * @param val lang类型数据
     * @return  字节数组
     */
    public static byte[] toBytes(long val) {
        return ByteBuffer.allocate(Long.BYTES).putLong(val).array();
    }

}
