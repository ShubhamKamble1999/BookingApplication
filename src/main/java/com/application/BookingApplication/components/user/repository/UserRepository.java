package com.application.BookingApplication.components.user.repository;

import com.application.BookingApplication.components.user.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserModel,String> {
    UserModel findUserBySlug(String username);

    UserModel findUserByUserId(String username);


    @Query(value = "{'verified':false}")
    List<UserModel> findAllUnverifiecUser();

    @Query(value = "{'verified':true}")
    List<UserModel> findAllVerifiecUser();

    void deleteByUserId(String userId);

}
