package com.application.BookingApplication.components.user.services;

import com.application.BookingApplication.commonUtil.CommonUtil;
import com.application.BookingApplication.components.bike.model.BikeModel;
import com.application.BookingApplication.components.bike.repository.BikeRepository;
import com.application.BookingApplication.components.user.model.AssignTestDriveRequest;
import com.application.BookingApplication.components.user.model.UserModel;
import com.application.BookingApplication.components.user.model.UserResponse;
import com.application.BookingApplication.components.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CommonUtil commonUtil;

    public int MAX_CAPACITY = 10;

    public UserModel findUserBySlug(String username) {
        System.out.println("USERNAME :: "+username);
        UserModel userModel = userRepository.findUserBySlug(username);
        System.out.println(" USER MODEL :: "+userModel);
        return userModel;
    }

    public UserResponse findUserByUserId(String username) {
        System.out.println("USERNAME :: "+username);
        UserModel userModel = userRepository.findUserByUserId(username);
        if (userModel == null){
            return new UserResponse(false,"User Not Found",null);
        }
        System.out.println(" USER MODEL :: "+userModel);
        return new UserResponse(true,"Successful",userModel);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserResponse updateUserDetails(UserModel data) {
        UserModel findUser = userRepository.findUserByUserId(data.getUsername());
        if (findUser == null){
            return new UserResponse(false,"User Not Found",null);
        }

        UserModel model = userRepository.save(data);
        return new UserResponse(true,"Success",model);
    }

    public UserResponse userIsExist(String userId) {
        Map<String,String> responsePayload = new HashMap<>();
        UserModel userModel = userRepository.findUserByUserId(userId);
        if (userModel == null){
            responsePayload.put("exist","0");
            return new UserResponse(false,"User Not Found",responsePayload);
        }
        responsePayload.put("exist","1");
        return new UserResponse(true,"Successful",responsePayload);
    }

    public UserResponse getAllInactive() {
        List<UserModel> responseData = userRepository.findAllUnverifiecUser();
        if (responseData.isEmpty()){
            return new UserResponse(true,"No data found",responseData);
        }
        return new UserResponse(true,"Successful",responseData);
    }

    public UserResponse getAllActive() {
        List<UserModel> responseData = userRepository.findAllVerifiecUser();
        if (responseData.isEmpty()){
            return new UserResponse(true,"No data found",responseData);
        }
        return new UserResponse(true,"Successful",responseData);
    }

    public UserResponse isUserVerified(String userId) {
        UserModel userModel = userRepository.findUserByUserId(userId);
        if (userModel == null){
            return new UserResponse(true,"User Not Found",null);
        }

        Map<String ,Boolean > payload = new HashMap<>();
        Boolean isVerified = userModel.getVerified();
        payload.put("status",isVerified);
        return new UserResponse(true,isVerified ? "VERIFIED" : "NOT VERIFIED",payload);
    }

    public UserResponse deleteById(String userId) {
        UserModel userModel = userRepository.findUserByUserId(userId);

        if (userModel == null){
            return new UserResponse(true,"No record to delete",null);
        }

        Map<String,UserModel> responsePayload = new HashMap<>();
        responsePayload.put("COUNT",userModel);
        return new UserResponse(true,"BOOKING DELETED SUCCESSFULLY",responsePayload);
    }

    public UserResponse deleteAll() {
        List<UserModel> userModels = userRepository.findAll();
        int count = userModels.size();
        userRepository.deleteAll();
        Map<String,Integer> responsePayload = new HashMap<>();
        responsePayload.put("COUNT",count);
        return new UserResponse(true,"All booking deleted",responsePayload);
    }

    public UserResponse assignOwnership(String userId, String[] bikeId) {
        List<BikeModel> list = new ArrayList<>();
        UserModel userModel = userRepository.findUserByUserId(userId);
        for (String bike:
             bikeId) {
            BikeModel bikeModel = bikeRepository.findBikeByBikeId(bike);
            list.add(bikeModel);
        }

        if (userModel == null){
            return new UserResponse(false,"User Not Found",null);
        } else if (list.isEmpty()) {
            return new UserResponse(false,"Bike Not Found",null);
        }

        userModel.setOwnedBike(list);
        userRepository.save(userModel);
        return new UserResponse(true,"Successful",userModel);
    }

    public UserResponse removeOwnership(String userId, String[] bikeIds) {

        UserModel userModel = userRepository.findUserByUserId(userId);

        if (userModel == null){
            return new UserResponse(false,"User Not Found",null);
        } else if (bikeIds == null) {
            return new UserResponse(false,"Bike Not Found",null);
        }
        List<BikeModel> updatedOwnedBikes = new ArrayList<>(userModel.getOwnedBike());

        for (String bikeId : bikeIds) {
            // Remove the bike with the specified ID from the list of owned bikes
            updatedOwnedBikes.removeIf(bike -> bike.getBikeId().equals(bikeId));
        }

        // Set the updated list of owned bikes to the userModel
        System.out.println("OWNED BIKE :: "+updatedOwnedBikes);
        userModel.setOwnedBike(updatedOwnedBikes);

        // Save the updated userModel to MongoDB
        userRepository.save(userModel);

        return new UserResponse(true, "Successful", userModel);
    }

    public UserResponse assignTestRide(AssignTestDriveRequest assignTestDriveRequest) {


        return new UserResponse(true,"Thank you for showing interest, Team will contact you",assignTestDriveRequest);
    }
}








//userVerifiedModel = new UserVerifiedModel(data.getUserId(),data.getVerified(),null);
//arrayList.add(userVerifiedModel);
//getUnactiveTree(data.getUserId());

//UserVerifiedModel userVerifiedModel = new UserVerifiedModel(data.getUserId(),data.getVerified(),null);
//localModel.add(userVerifiedModel);
//userVerifiedModel.setOfficer(localModel);
//getUnactiveTree(data.getUserId());
//arrayList.add(userVerifiedModel);
