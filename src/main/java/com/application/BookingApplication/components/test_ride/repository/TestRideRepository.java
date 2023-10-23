package com.application.BookingApplication.components.test_ride.repository;

import com.application.BookingApplication.components.test_ride.controller.TestRideController;
import com.application.BookingApplication.components.test_ride.model.TestRideModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRideRepository extends MongoRepository<TestRideModel,String> {

}


