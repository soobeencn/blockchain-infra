package com.pwnpub.block;

import com.pwnpub.util.ByteUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import java.math.BigInteger;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Block {
    /**
     * block hash val
     */
    private String hash;

    /**
     * the previous/last block hash value
     */
    private String prevBlockHash;

    /**
     * storage txs
     */
    private String data;

    /**
     * the time
     */
    private long timeStamp;

    /**
     * the block height
     */
    private long height;

    public static Block createBlock(String prevBlockHash, String data, long height) {
        Block block = new Block(
                "",
                prevBlockHash,
                data,
                Instant.now().getEpochSecond(),
                height);
        // set current block value
        block.setHash();
        return block;
    }

    /**
     * set current obj hash val
     * Note: before prepare to input block val, we have to change original data to byte[]
     */
    private void setHash() {
        byte[] prevBlockHashBytes = {};
        if (StringUtils.isNoneBlank(this.getPrevBlockHash())) {
            prevBlockHashBytes = new BigInteger(this.getPrevBlockHash(), 16).toByteArray();
        }
        // 拼接数据, 高度字段不拼接
        byte[] headers = ByteUtils.merge(prevBlockHashBytes,
                this.getData().getBytes(),
                ByteUtils.toBytes(this.getTimeStamp()));
        this.setHash(DigestUtils.sha256Hex(headers));
    }

    private static final String ZERO_HASH = Hex.encodeHexString(new byte[32]);
    public static Block createGenesisBlock() {
        return Block.createBlock(ZERO_HASH, "Genesis Block", 0);
    }

}
