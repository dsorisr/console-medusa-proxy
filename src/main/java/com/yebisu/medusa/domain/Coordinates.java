package com.yebisu.medusa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coordinates {
    private Long x;
    private Long y;
    private Long z;
}