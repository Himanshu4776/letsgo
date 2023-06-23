package com.example.letsgo.Dao;

import com.example.letsgo.Dto.RiderGraph.RiderResponseDto;
import com.example.letsgo.Entity.RideDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RideDetailsRepository extends JpaRepository<RideDetailsEntity,Long> {
//    List<RiderResponseDto> listAllRIders();
}
