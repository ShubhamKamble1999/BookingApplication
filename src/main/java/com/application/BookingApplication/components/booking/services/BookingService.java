package com.application.BookingApplication.components.booking.services;

import com.application.BookingApplication.commonUtil.CommonUtil;
import com.application.BookingApplication.components.booking.model.BookingModel;
import com.application.BookingApplication.components.booking.repository.BookingRepository;
import com.application.BookingApplication.components.user.model.UserModel;
import com.application.BookingApplication.components.user.model.UserResponse;
import com.application.BookingApplication.components.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public String BOOKING_GRADE = "BOOK_";
    public Double SBO_PRICE = 200.0;
    public Double BO_PRICE = 250.0;
    public Double BP_PRICE = 600.0;

    public UserResponse findAll() {
        List<BookingModel> list = bookingRepository.findAll();
        if (list.isEmpty()){
            return new UserResponse(true,"Empty List",null);
        }

        String id = BOOKING_GRADE+ commonUtil.generateId(BOOKING_GRADE);

        return new UserResponse(true,"SUCCESSFUL",list);
    }

    public UserResponse createBooking(BookingModel bookingModel) {
        bookingModel.setOrderId(commonUtil.generateId("BOOK"));
        BookingModel bookingResponse = bookingRepository.insert(bookingModel);
        if (bookingResponse == null){
            return new UserResponse(true,"Data not added",null);
        }
        UserModel userModel = userRepository.findUserByUserId(bookingModel.getUserId());

//        updateFinance(userModel);
//        updateTarget(userModel);

        return new UserResponse(true,"BOOKING ADDED SUCCESSFULLY",bookingResponse);
    }

//    private void updateTarget(UserModel userModel) {
//        String userId = userModel.getUserId();
////        userRepository.updateDailyTarget(userId,userModel.getDayTargetsDone() + 1);
//
//        Query query = new Query(Criteria.where("userId").is(userId));
//        Update update = new Update().set("dayTargetsDone", userModel.getDayTargetsDone() + 1);
//        mongoTemplate.updateFirst(query, update, UserModel.class);
//
//        UserModel updateUserData = userRepository.findUserByUserId(userId);
//        System.out.println("UPDATE USER :: "+updateUserData);
//    }

//    private void updateFinance(UserModel userModel) {
//        String role = userModel.getGrade();
//
//        switch (role){
//            case "SBO": {
//                callFromSBO(userModel.getUserId());
//                break;
//            }
//            case "BO": {
//                callFromBO(userModel.getUserId());
//                break;
//            }
//            case "BP": {
//                callFromBP(userModel.getUserId());
//                break;
//            }
//        }
//    }


//    public List<UserModel> list = new ArrayList<>();
//    public List<UserModel> findUserBySponsor(String userId){
//        UserModel officer = userRepository.findUserByUserId(userId);
//        System.out.println("BOOLEAN :: "+list.contains(officer));
//
//        if (officer != null && !list.contains(officer)) {
//            list.add(officer);
//        }
//
//        if (officer != null && officer.getGrade().equals("SBO")) {
//            return list;
//        } else if (officer != null) {
//            findUserBySponsor(officer.getSponserId()); // Recursively find the sponsor
//        }
//        return list;
//    }
//
//    private void updateAccountBalance(Double price, UserModel userModel) {
//
//        userModel.setWalletBalance( userModel.getWalletBalance() + price);
//        userRepository.save(userModel);
//    }
//
//    private void setToThousand(UserModel userModel) {
//
//        userModel.setWalletBalance(1000.0);
//        userRepository.save(userModel);
//    }
//
//    private List<UserModel> callFromBP(String userId) {
//        UserModel officer = userRepository.findUserByUserId(userId);
//        System.out.println("BOOLEAN :: "+list.contains(officer));
//
//        if (officer != null && !list.contains(officer)) {
//            list.add(officer);
//            switch (officer.getGrade()) {
//                case "SBO" -> {
//                    updateAccountBalance(SBO_PRICE,officer);
//                }
//                case "BO" -> {
//                    updateAccountBalance(BO_PRICE,officer);
//                }
//                case "BP" -> {
//                    updateAccountBalance(BP_PRICE,officer);
//                }
//            }
//        }
//
//        if (officer != null && officer.getGrade().equals("SBO")) {
//            return list;
//        } else if (officer != null) {
//            callFromBP(officer.getSponserId()); // Recursively find the sponsor
//        }
//        return list;
//    }
//
//    private List<UserModel> callFromBO(String userId) {
//        UserModel officer = userRepository.findUserByUserId(userId);
//        System.out.println("BOOLEAN :: "+list.contains(officer));
//
//        if (officer != null && !list.contains(officer)) {
//            list.add(officer);
//            switch (officer.getGrade()) {
//                case "SBO" -> {
//                    updateAccountBalance(SBO_PRICE,officer);
//                }
//                case "BO" -> {
//                    updateAccountBalance(BO_PRICE,officer);
//                }
//            }
//        }
//
//        if (officer != null && officer.getGrade().equals("SBO")) {
//            return list;
//        } else if (officer != null) {
//            callFromBP(officer.getSponserId()); // Recursively find the sponsor
//        }
//        return list;
//    }
//
//    private void callFromSBO(String userId) {
//        UserModel officer = userRepository.findUserByUserId(userId);
//        System.out.println("BOOLEAN :: "+list.contains(officer));
//        updateAccountBalance(SBO_PRICE,officer);
//    }
//
//    public UserResponse findBookingById(String userId){
//        BookingModel bookingData = bookingRepository.findByOrderId(userId);
//        if (bookingData == null){
//            return new UserResponse(true,"NO BOOKING FOUND",null);
//        }
//        return new UserResponse(true,"BOOKING FOUND",bookingData);
//    }
//
    public UserResponse deleteById(String userId){
        BookingModel bookingData = bookingRepository.findByOrderId(userId);

        if (bookingData == null){
            return new UserResponse(true,"No record to delete",null);
        }

        BookingModel bookingModel = bookingRepository.findByOrderId(userId);
        bookingRepository.deleteByOrderId(userId);

        Map<String,BookingModel> responsePayload = new HashMap<>();
        responsePayload.put("COUNT",bookingModel);
        return new UserResponse(true,"BOOKING DELETED SUCCESSFULLY",bookingModel);
    }

    public UserResponse deleteAll(){
        List<BookingModel> bookingModel = bookingRepository.findAll();
        int count = bookingModel.size();
        bookingRepository.deleteAll();
        Map<String,Integer> responsePayload = new HashMap<>();
        responsePayload.put("COUNT",count);
        return new UserResponse(true,"All booking deleted",responsePayload);
    }
//
//
//    public void killList() {
//        list.clear();
//    }
//
//    public void setThousand(String userId) {
//        UserModel officer = userRepository.findUserByUserId(userId);
//        System.out.println("BOOLEAN :: "+list.contains(officer));
//        setToThousand(officer);
//    }
}
