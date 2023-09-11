package com.pwnpub.consensus;

import com.pwnpub.block.Block;
import com.pwnpub.util.ByteUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pow {
    /**
     *
     * hash val: 256bit二进制数
     * 1 byte = 8 bit  32
     * 1 hex = 4bit 64
     * 0000 0000 0000 0000 10101010101 ... 256 target hash
     */
    public static final int TARGET_BITS = 16;

    /**
     * 需要验证的区块
     */
    private Block block;

    /**
     * 难度目标值
     */
    private BigInteger target;

    /**
     * 创建新的工作量证明对象
     * 对1进行移位计算, 将1向左移动(256 - TARGET_BITS)位, 得到我们的难度目标值
     * @param block
     * @return
     */
    public static Pow createProofOfWork(Block block) {
        /**
         * 以8个bit为例
         * 0000 0001
         *
         * 0010 0000 目标hash
         * 0001 0000 实际hash
         */
        BigInteger targetVal = BigInteger.ONE.shiftLeft(256 - TARGET_BITS);
        return new Pow(block, targetVal);
    }

    /**
     * 根据block数据, 以及nonce值, 生成一个byte数组
     * @param nonce
     * @return
     */
    private byte[] prepareData(long nonce) {
        byte[] prevBlockHashBytes = {};
        if(StringUtils.isNoneBlank(this.getBlock().getPrevBlockHash())) {
            prevBlockHashBytes = new BigInteger(this.getBlock().getPrevBlockHash(), 16).toByteArray();
        }
        return ByteUtils.merge(
                prevBlockHashBytes,
                this.getBlock().getData().getBytes(),
                ByteUtils.toBytes(this.getBlock().getTimeStamp()),
                //可选择目标难度位加入计算
                ByteUtils.toBytes(TARGET_BITS),
                ByteUtils.toBytes(nonce)
        );
    }

    /**
     * 运行工作量证明
     * @return PowResult
     */
    public PowResult collision() {
        long nonce = 0;
        String shaHex = "";
        System.out.println("Ready to mine: ");
        System.out.println(this.getBlock().getData());
        while (nonce < Long.MAX_VALUE) {
            //1.获取要生成hash的byte数据
            byte[] data = this.prepareData(nonce);
            //2.通过sha256算法, 生成hash
            shaHex = DigestUtils.sha256Hex(data);
            //3.判断实际hash, 是否小于目标hash
            if (new BigInteger(shaHex, 16).compareTo(this.target) == -1) {
                break;
            }else {
                nonce++;
            }
        }
        return new PowResult(nonce, shaHex);
    }




}
