package com.pwnpub.block;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlockChain {

    private List<Block> blockList;

    public static BlockChain createBlockChain() {
        List<Block> blocks = new LinkedList<>();
        blocks.add(Block.createGenesisBlock());
        return new BlockChain(blocks);
    }

    /**
     * 根据block对象, 添加区块
     * @param block
     */
    public void addBlock(Block block) {
        this.blockList.add(block);
    }

    /**
     * 重载: 根据data字符串添加数据
     * @param data 要添加的数据
     */
    public void addBlock(String data) {
        Block prevBlock = blockList.get(blockList.size() - 1);
        this.addBlock(Block.createBlock(
                prevBlock.getHash(),
                data,
                prevBlock.getHeight() + 1));
    }
}
