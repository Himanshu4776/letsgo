package com.example.letsgo.Service.Impl;

import com.example.letsgo.Dao.RideDetailsRepository;
import com.example.letsgo.Dto.RideDetailsRequestDto;
import com.example.letsgo.Dto.RiderGraph.RiderGraphResponseDto;
import com.example.letsgo.Entity.RideDetailsEntity;
import com.example.letsgo.Service.RiderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.letsgo.constants.Constants.*;
import static com.example.letsgo.utils.calculateDistance.distance;

@Service
public class RiderDetailServiceImpl implements RiderDetailService {

    @Autowired
    private RideDetailsRepository rideDetailsRepository;


    public RiderDetailServiceImpl(RideDetailsRepository rideDetailsRepository){
        this.rideDetailsRepository = rideDetailsRepository;
    }


    @Override
    public String generateRideNumber(RideDetailsRequestDto rideDetailsRequestDto) {
        // Call other services to get user ID and ride type based on RideDetailsRequestDto

        //generation of unique ride number
        String rideNumber = generateUniqueRideNumber();

        RideDetailsEntity ride = new RideDetailsEntity();

        if(rideDetailsRequestDto.getDestinationLatitude() > -90 && rideDetailsRequestDto.getDestinationLatitude() < 90){
            ride.setDestinationLatitude(rideDetailsRequestDto.getDestinationLatitude());
        }
        else{
            throw new RuntimeException("not in range");
        }

        if(rideDetailsRequestDto.getDestinationLongitude() > -90 && rideDetailsRequestDto.getDestinationLongitude() < 90){
            ride.setDestinationLongitude(rideDetailsRequestDto.getDestinationLongitude());
        }
        else{
            throw new RuntimeException("not in range");
        }

        if(rideDetailsRequestDto.getDepartureLatitude() > -90 && rideDetailsRequestDto.getDepartureLatitude() < 90){
            ride.setDepartureLatitude(rideDetailsRequestDto.getDepartureLatitude());
        }
        else{
            throw new RuntimeException("not in range");
        }

        if(rideDetailsRequestDto.getDepartureLongitude() > -90 && rideDetailsRequestDto.getDepartureLongitude() < 90){
            ride.setDepartureLongitude(rideDetailsRequestDto.getDepartureLongitude());
        }
        else{
            throw new RuntimeException("not in range");
        }
        ride.setDepartureLatitude(rideDetailsRequestDto.getDepartureLatitude());
        ride.setDepartureLongitude(rideDetailsRequestDto.getDepartureLongitude());
        ride.setPrice(rideDetailsRequestDto.getPrice());
        ride.setRideNumber(rideNumber);
        ride.setRideType(rideDetailsRequestDto.getRideType());
        ride.setUserId(rideDetailsRequestDto.getUserId());

        rideDetailsRepository.save(ride);

        return rideNumber;
    }

    @Override
    public RiderGraphResponseDto getAllRiders(double userLatitude, double userLongitude) {
        List<RideDetailsEntity> ridersArr = rideDetailsRepository.findAll();
        RiderGraphResponseDto ridersResponse = new RiderGraphResponseDto();

        List<RideDetailsEntity> riders = new ArrayList<RideDetailsEntity>();

        for (RideDetailsEntity rideDetailsEntity : ridersArr) {
            if (distance(userLatitude, userLongitude, rideDetailsEntity.getDestinationLatitude(), rideDetailsEntity.getDestinationLongitude()) <= searchRadius) {
                riders.add(rideDetailsEntity);
            }
        }

        ridersResponse.setNoOfRiders(riders.size());
        ridersResponse.setRiderResponseDto(riders);
        return ridersResponse;
    }

    private String generateUniqueRideNumber() {
        // Generate a random ride number using alphanumeric characters and a timestamp
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 4; // Define the desired length of the ride number
        StringBuilder sb = new StringBuilder();

        // Append random characters from the 'characters' string
        Random random = new Random();
        for (int i = 0; i <= length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        // Append timestamp to ensure uniqueness
        long timestamp = System.currentTimeMillis();
        sb.append(timestamp);


        return sb.toString();
    }

}
