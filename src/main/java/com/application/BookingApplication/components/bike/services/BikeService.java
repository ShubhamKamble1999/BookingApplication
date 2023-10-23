package com.application.BookingApplication.components.bike.services;

import com.application.BookingApplication.components.bike.model.BikeModel;
import com.application.BookingApplication.components.bike.repository.BikeRepository;
import com.application.BookingApplication.components.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BikeService {

    @Autowired
    private BikeRepository bikeRepository;

    public List<BikeModel> findAll() {
        return bikeRepository.findAll();
    }

    public UserResponse createBike(BikeModel bikeModel) {
        BikeModel insertBike = bikeRepository.insert(bikeModel);
        if (insertBike == null){
            return new UserResponse(true,"Data not added",null);
        }
        BikeModel updatedBike = bikeRepository.findBikeByBikeName(insertBike.getBikeName());
        return new UserResponse(true,"BOOKING ADDED SUCCESSFULLY",updatedBike);
    }

    public UserResponse findBikeById(String bikeId) {
        BikeModel userModel = bikeRepository.findBikeByBikeId(bikeId);
        if (userModel == null){
            return new UserResponse(false,"User Not Found",null);
        }
        System.out.println(" USER MODEL :: "+userModel);
        return new UserResponse(true,"Successful",userModel);
    }

    public UserResponse deleteById(String bikeId) {
        BikeModel bikeModel = bikeRepository.findBikeByBikeId(bikeId);

        if (bikeModel == null){
            return new UserResponse(true,"No record to delete",null);
        }
        bikeRepository.deleteById(bikeModel.getId());
        Map<String,BikeModel> responsePayload = new HashMap<>();
        responsePayload.put("data",bikeModel);
        return new UserResponse(true,"BIKE DELETED SUCCESSFULLY",responsePayload);
    }

    public UserResponse deleteAll() {
        List<BikeModel> userModels = bikeRepository.findAll();
        int count = userModels.size();
        bikeRepository.deleteAll();
        Map<String,Integer> responsePayload = new HashMap<>();
        responsePayload.put("COUNT",count);
        return new UserResponse(true,"All bike deleted",responsePayload);
    }
}
