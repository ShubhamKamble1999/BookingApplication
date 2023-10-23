package com.application.BookingApplication.components.bike.repository;

import com.application.BookingApplication.components.bike.model.BikeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends MongoRepository<BikeModel,String > {
    BikeModel findBikeByBikeName(String bikeName);

    BikeModel findBikeByBikeId(String bikeId);
}
