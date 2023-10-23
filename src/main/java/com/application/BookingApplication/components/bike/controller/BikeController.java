package com.application.BookingApplication.components.bike.controller;

import com.application.BookingApplication.components.bike.model.BikeModel;
import com.application.BookingApplication.components.bike.services.BikeService;
import com.application.BookingApplication.components.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/amaz-bike")
public class BikeController {

    @Autowired
    private BikeService bIkeService;

    @GetMapping("/bike")
    private List<BikeModel> findAll(){
        return bIkeService.findAll();
    }

    @PostMapping("/bike/findBikeById")
    public UserResponse findBikeById(@RequestBody Map<String,String> payload){
        UserResponse userModel = bIkeService.findBikeById(payload.get("bikeId"));
        return userModel;
    }

    @PostMapping("/bike/create-bike")
    private UserResponse createBike(@RequestBody BikeModel bikeModel){
        try {
            return bIkeService.createBike(bikeModel);
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @DeleteMapping("/bike/deleteById")
    public UserResponse deleteUserById(@RequestBody Map<String ,String> payload){
        try {
            return bIkeService.deleteById(payload.get("bikeId"));
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @DeleteMapping("/bike/delete")
    public UserResponse deleteAllBooking(){
        try {
            return bIkeService.deleteAll();
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

}
