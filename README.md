# blockchain-infra
从零到一实现区块链基础设施(Java版)

## 工作量证明

### 目标hash计算
1. Difficulty
2. 难度目标Bits:
- 使整个网络的计算力大约每10分钟产生一个区块所需要的难度数值;
- Bits是用来存储难度目标的16进制数值.
3. 难度重定
- 全网每新增2016个区块, 全网难度将重新计算, 该新难度值将依据前2016个区块的哈希算力而定.
- 按照每10分钟产生一个区块的速度计算, 每产生2016个区块大约14天.
