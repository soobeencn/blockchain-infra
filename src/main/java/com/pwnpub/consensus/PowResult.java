package com.pwnpub.consensus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作量计算结果
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PowResult {
    /**
     * 维护hash、nonce
     */
    private long nonce;

    private String hash;
}
