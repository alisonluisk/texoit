package com.teste.texoit.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class IntervalDTO {
    List<ProducerDTO> min;
    List<ProducerDTO> max;
}
