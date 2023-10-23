package com.application.BookingApplication.components.test_ride.controller;

import com.application.BookingApplication.components.bike.model.BikeModel;
import com.application.BookingApplication.components.test_ride.model.TestRideModel;
import com.application.BookingApplication.components.test_ride.services.TestRideService;
import com.application.BookingApplication.components.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/amaz-bike/")
public class TestRideController {

    @Autowired
    private TestRideService testRideService;

//    @GetMapping("/test")
//    public UserResponse getAllTestRides(){
//        try{
//            return testRideService.getAllTestRide();
//        }catch (Exception e){
//            Map<String,String> responsePayload = new HashMap<>();
//            responsePayload.put("ERROR MESSAGE",e.getMessage());
//            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
//        }
//    }

    @GetMapping("/test")
    public List<TestRideModel> findAll(){
        return testRideService.findAll();
    }

    @PostMapping("/test/registerTest")
    public UserResponse createTest(@RequestBody TestRideModel testRideModel){
        try{
            return testRideService.createTest(testRideModel);
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @PostMapping("/test/deleteAll")
    public UserResponse deleteAll(){
        try{
            return testRideService.deleteAll();
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

}
