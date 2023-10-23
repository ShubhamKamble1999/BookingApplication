package com.application.BookingApplication.components.test_ride.services;

import com.application.BookingApplication.commonUtil.CommonUtil;
import com.application.BookingApplication.components.test_ride.controller.TestRideController;
import com.application.BookingApplication.components.test_ride.model.TestRideModel;
import com.application.BookingApplication.components.test_ride.repository.TestRideRepository;
import com.application.BookingApplication.components.user.model.UserResponse;
import com.application.BookingApplication.components.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestRideService {

    @Autowired
    private TestRideRepository testRideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonUtil commonUtil;

    public List<TestRideModel> findAll() {
        return testRideRepository.findAll();
    }

    public UserResponse createTest(TestRideModel testRideModel) {
        if (testRideModel.equals(null)){
            return new UserResponse(true,"Passed Empty Object",null);
        }
        String testRideId = commonUtil.generateTestId(testRideModel.getBikeId(),testRideModel.getLocation());
        testRideModel.setTestRideId(testRideId);
        TestRideModel resultModel = testRideRepository.insert(testRideModel);
        return new UserResponse(true,"Successful",resultModel);
    }

    public UserResponse deleteAll() {
        List<TestRideModel> userList = testRideRepository.findAll();
        int count = userList.size();
        testRideRepository.deleteAll();
        Map<String , Integer> payload = new HashMap<>();
        payload.put("count",count);
        return new UserResponse(true,"Deleted All",payload);
    }


}