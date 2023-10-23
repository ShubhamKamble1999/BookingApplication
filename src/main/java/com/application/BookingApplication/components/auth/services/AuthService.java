package com.application.BookingApplication.components.auth.services;

import com.application.BookingApplication.commonUtil.CommonUtil;
import com.application.BookingApplication.components.auth.apiInterface.AuthInterface;
import com.application.BookingApplication.components.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthInterface authInterface;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserModel signUp(UserModel userModelData) {

        userModelData.setSlug(commonUtil.generateSlug(userModelData.getFullname()));
        userModelData.setPassword(passwordEncoder.encode(userModelData.getPassword()));
        System.out.println("POST ADD :: "+userModelData);
        UserModel addUser = authInterface.insert(userModelData);

        return addUser;
    }


//    private String generateUserId(String grade) {
//        LocalDateTime now = LocalDateTime.now();
//
//        int hour = now.getHour();
//        int minute = now.getMinute();
//        int second = now.getSecond();
//        int day = now.getDayOfMonth();
//        int month = now.getMonthValue();
//        int year = now.getYear() % 100;
//        System.out.println(" FORMAT YEAR :: "+String.format("%02d",year));
//        String userId = grade+"_"+padWithZero(minute)+padWithZero(hour)+padWithZero(second)+padWithZero(day)+padWithZero(month)+padWithZero(year);
//        System.out.println(" USER ID :: "+userId);
//        return userId;
//    }
//
//    public String padWithZero(int number) {
//        return String.format("%02d", number);
//    }
}
