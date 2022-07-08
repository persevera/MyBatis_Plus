package com.liuyong.mybatisplus.myclass;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}
