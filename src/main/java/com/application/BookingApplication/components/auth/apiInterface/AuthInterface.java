package com.application.BookingApplication.components.auth.apiInterface;


import com.application.BookingApplication.components.user.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthInterface extends MongoRepository<UserModel,Integer> {

}
