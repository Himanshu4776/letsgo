package com.example.letsgo.Dto.RiderGraph;

import com.example.letsgo.Entity.RideDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderGraphResponseDto {
    private List<RideDetailsEntity> riderResponseDto;

    private int noOfRiders;
}
