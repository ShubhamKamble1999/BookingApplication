package com.application.BookingApplication.components.user.controller;

import com.application.BookingApplication.components.user.model.AssignOwnershipRequest;
import com.application.BookingApplication.components.user.model.AssignTestDriveRequest;
import com.application.BookingApplication.components.user.model.UserModel;
import com.application.BookingApplication.components.user.model.UserResponse;
import com.application.BookingApplication.components.user.repository.UserRepository;
import com.application.BookingApplication.components.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static com.application.BookingApplication.BookingApplication.VERSION_SUFIX;

@RestController
@RequestMapping("v1/amaz-bike/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServices userServices;

    @PostMapping("/user/findUserByUserId")
    public UserResponse findUserById(@RequestBody Map<String,String>  payload){
        UserResponse userModel = userServices.findUserByUserId(payload.get("userId"));
        return userModel;
    }

    @PostMapping("/user/findUserByUsername")
    public UserModel findUserBySlug(@RequestBody Map<String,String> payload){
        UserModel userModel = userServices.findUserBySlug(payload.get("username"));
        return userModel;
    }

    @GetMapping("/user")
    public List<UserModel> findAll(){
        return userServices.findAll();
    }

    @PutMapping("/user/updateUser")
    public UserResponse updateUser(@RequestBody UserModel objectMap){
        UserResponse userResponse = userServices.updateUserDetails(objectMap);
        return userResponse;
    }

    @PostMapping("/user/userIsExist")
    public UserResponse userIsExist(@RequestBody Map<String,String>  payload){
        try {
            UserResponse userModel = userServices.userIsExist(payload.get("userId"));
            return userModel;
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @GetMapping("/user/getAllInactive")
    public UserResponse getAllInactive(){
        try{
            UserResponse allInactive = userServices.getAllInactive();
            return allInactive;
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @PostMapping("/user/isVerified")
    public UserResponse isUserVerified(@RequestBody Map<String,String>  payload ) {
        try {
            return userServices.isUserVerified(payload.get("userId"));
        } catch (Exception e) {
            Map<String, String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE", e.getMessage());
            return new UserResponse(false, "SOME THIS WENT WRONG", responsePayload);
        }
    }

    @DeleteMapping("/user/deleteById")
    public UserResponse deleteUserById(@RequestBody Map<String ,String> payload){
        try {
            return userServices.deleteById(payload.get("userId"));
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @DeleteMapping("/user/delete")
    public UserResponse deleteAllBooking(){
        try {
            return userServices.deleteAll();
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @PostMapping("/user/assign-ownership")
    public UserResponse assignOwnership(@RequestBody AssignOwnershipRequest assignOwnershipRequest){
        try {
            return userServices.assignOwnership(assignOwnershipRequest.getUserId(),assignOwnershipRequest.getBikeId());
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @PostMapping("/user/remove-ownership")
    public UserResponse removeOwnership(@RequestBody AssignOwnershipRequest assignOwnershipRequest){
        try {
            return userServices.removeOwnership(assignOwnershipRequest.getUserId(),assignOwnershipRequest.getBikeId());
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @PostMapping("/user/assign-testride")
    public UserResponse assignTestRIde(@RequestBody AssignTestDriveRequest assignTestDriveRequest){
        try {
            return userServices.assignTestRide(assignTestDriveRequest);
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }
}
