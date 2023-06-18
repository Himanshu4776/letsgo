package com.example.letsgo.Dto.RiderGraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderGraphResponseDto {
    private RiderResponseDto riderResponseDto;

    private int noOfRiders;
}
