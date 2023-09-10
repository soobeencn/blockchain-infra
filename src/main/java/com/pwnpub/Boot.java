package com.pwnpub;

import com.pwnpub.block.Block;
import com.pwnpub.block.BlockChain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Boot {
    public static void main(String[] args) {
        //1.create genesis block
        Block block = Block.createGenesisBlock();
        String time = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss")
                .format(new Date(block.getTimeStamp() * 1000L));
        System.out.println(time);
        System.out.println("创世区块: " + block);

        //2.创建第二个区块
        Block block_2 = Block.createBlock(block.getHash(), "第二个区块", 1);
        System.out.println("第二个区块: " + block_2);

        //3.创建一条链
        BlockChain blockChain = BlockChain.createBlockChain();
        System.out.println("第一条区块链: " + blockChain);

        //4.添加区块
        blockChain.addBlock("添加第二个区块");
        blockChain.addBlock("添加第三个区块");
        blockChain.addBlock("添加第四个区块");

        for (int i = 0; i < blockChain.getBlockList().size(); i++) {
            Block singleBlock = blockChain.getBlockList().get(i);
            System.out.println("输出第 " + i + " 个区块: ");
            System.out.println(singleBlock);
        }
    }
}
