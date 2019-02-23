package com.sustcoder.algorithm;

public class BitMap {
    private long length;
    private static int[] bitsMap;
    private static final int[] BIT_VALUE_ONE = {0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020,
            0x00000040, 0x00000080, 0x00000100, 0x00000200, 0x00000400, 0x00000800, 0x00001000, 0x00002000, 0x00004000,
            0x00008000, 0x00010000, 0x00020000, 0x00040000, 0x00080000, 0x00100000, 0x00200000, 0x00400000, 0x00800000,
            0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000};
    int[] BIT_VALUE_ZERO = {0xFFFFFFFE,0xFFFFFFFD,0xFFFFFFFB,0xFFFFFFF7,0xFFFFFFEF,0xFFFFFFDF,0xFFFFFFBF,
            0xFFFFFF7F,0xFFFFFEFF,0xFFFFFDFF,0xFFFFFBFF,0xFFFFF7FF,0xFFFFEFFF,0xFFFFDFFF,0xFFFFBFFF,
            0xFFFF7FFF,0xFFFEFFFF,0xFFFDFFFF,0xFFFBFFFF,0xFFF7FFFF,0xFFEFFFFF,0xFFDFFFFF,0xFFBFFFFF,
            0xFF7FFFFF,0xFEFFFFFF,0xFDFFFFFF,0xFBFFFFFF,0xF7FFFFFF,0xEFFFFFFF,0xDFFFFFFF,0xBFFFFFFF,0x7FFFFFFF};

    public BitMap(long length) {
        this.length = length;
        /**
         * 根据长度算出，所需数组大小
         * 当 length%32=0 时大小等于
         * = length/32
         * 当 length%32>0 时大小等于
         * = length/32+l
         */
        bitsMap = new int[(int) (length >> 5) + ((length & 31) > 0 ? 1 : 0)];
    }

    /**
     * @param n 设置n
     */
    public void setOne(long n) {
        if (n < 0 || n > length) {
            throw new IllegalArgumentException("length value "+n+" is  illegal!");
        }
        /**
         * 等价于
         * 求出该n所在bitMap的下标,等价于"n/5"
         * int index = (int) n>>5;
         * 求出该值的偏移量(求余),等价于"n%31"
         * int offset = (int) n & 31;
         * int bits = bitsMap[index];
         * bitsMap[index]=bits| BIT_VALUE[offset];
         */
        bitsMap[(int) n>>5] |= BIT_VALUE_ONE[(int) n & 31];
    }

    /**
     * @param n 设置n
     */
    public void setZero(long n) {
        if (n < 0 || n > length) {
            throw new IllegalArgumentException("length value "+n+" is  illegal!");
        }
        /**
         * 等价于
         * 求出该n所在bitMap的下标,等价于"n/5"
         */
         int index = (int) n>>5;
         // 求出该值的偏移量(求余),等价于"n%31"
         int offset = (int) n & 31;
         int bits = bitsMap[index];
         bitsMap[index]=bits & BIT_VALUE_ZERO[offset];
    }

    /**
     * 获取值N是否存在
     * @return 1：存在，0：不存在
     */
    public int getN(long n) {
        if (n < 0 || n > length) {
            throw new IllegalArgumentException("length value illegal!");
        }
        /**
         *  int index = (int) n>>5;
         *  int offset = (int) n & 31;
         *  int bits = (int) bitsMap[index];
         *  return ((bits & BIT_VALUE[offset])) >>> offset;
         */
        int offset = (int) n & 31;
        return ((bitsMap[(int) n>>5] & BIT_VALUE_ONE[offset])) >>> offset;
    }
}
