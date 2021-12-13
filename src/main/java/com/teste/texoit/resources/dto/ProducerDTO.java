package com.teste.texoit.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProducerDTO {
    private String producer;
    private Long interval;
    private Long previousWin;
    private Long followingWin;
}
