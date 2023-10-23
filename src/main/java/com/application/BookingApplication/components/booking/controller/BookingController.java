package com.application.BookingApplication.components.booking.controller;

import com.application.BookingApplication.commonUtil.CommonUtil;
import com.application.BookingApplication.components.booking.model.BookingModel;
import com.application.BookingApplication.components.booking.services.BookingService;
import com.application.BookingApplication.components.user.model.UserModel;
import com.application.BookingApplication.components.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ionet")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CommonUtil commonUtil;

    @GetMapping("/bookings")
    public UserResponse findAll(){
        try {
            return bookingService.findAll();
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @PostMapping("/booking/create")
    public UserResponse createBooking(@RequestBody BookingModel bookingModel){
        try {
            return bookingService.createBooking(bookingModel);
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

//    @PostMapping("/booking/findById")
//    public UserResponse findBookingById(@RequestBody Map<String ,String> payload){
//        try {
//            return bookingService.findBookingById(payload.get("bookingId"));
//        }catch (Exception e){
//            Map<String,String> responsePayload = new HashMap<>();
//            responsePayload.put("ERROR MESSAGE",e.getMessage());
//            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
//        }
//    }

    @DeleteMapping("/booking/deleteById")
    public UserResponse deleteBookingsById(@RequestBody Map<String ,String> payload){
        try {
            return bookingService.deleteById(payload.get("bookingId"));
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

    @DeleteMapping("/booking/delete")
    public UserResponse deleteAllBooking(){
        try {
            return bookingService.deleteAll();
        }catch (Exception e){
            Map<String,String> responsePayload = new HashMap<>();
            responsePayload.put("ERROR MESSAGE",e.getMessage());
            return new UserResponse(false,"SOME THIS WENT WRONG",responsePayload);
        }
    }

//    @PostMapping("/booking/set1000")
//    public String findUser(@RequestBody Map<String ,String>payload){
//        bookingService.killList();
//        bookingService.setThousand(payload.get("userId"));
//        return "1000 implemented";
//    }
//
//    @PostMapping("/booking/findUserBySponsorId")
//    public List<UserModel> findUserBlock(@RequestBody Map<String ,String>payload){
//        bookingService.killList();
//        List<UserModel> response =bookingService.findUserBySponsor(payload.get("userId"));
//        System.out.println("USER :: "+response.toString() );
//        System.out.println("COUNT :: "+response.size() );
//        return response;
//    }
}
