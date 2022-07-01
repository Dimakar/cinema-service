package com.dimakar.cinemaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
public class Cinema {
    private final int type;
    private final String name;
    private final String address;
}
