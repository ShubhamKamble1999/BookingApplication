package com.application.BookingApplication.components.booking.repository;

import com.application.BookingApplication.components.booking.model.BookingModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<BookingModel,String > {
    BookingModel findByOrderId(String userId);

    void deleteByOrderId(String userId);
}
