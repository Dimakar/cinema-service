package com.dimakar.cinemaservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class Cinema {
    private Integer id;
    private String name;
    private String address;
    private Boolean isOpen;
}
