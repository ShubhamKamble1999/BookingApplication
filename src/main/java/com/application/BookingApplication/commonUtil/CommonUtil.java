package com.application.BookingApplication.commonUtil;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommonUtil {

    public String generateSlug(String name) {
        Slugify slugify = new Slugify();
        return slugify.slugify(name);
    }

    public String generateTestId(String bikeModel,String location) {
        LocalDateTime now = LocalDateTime.now();

        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear() % 100;
        System.out.println(" FORMAT YEAR :: "+String.format("%02d",year));
        String userId = bikeModel+"_"+location+padWithZero(minute)+padWithZero(hour)+padWithZero(second)+padWithZero(day)+padWithZero(month)+padWithZero(year);
        System.out.println(" USER ID :: "+userId);
        return userId;
    }

    public String generateId(String grade) {
        LocalDateTime now = LocalDateTime.now();

        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear() % 100;
        System.out.println(" FORMAT YEAR :: "+String.format("%02d",year));
        String userId = grade+"_"+padWithZero(minute)+padWithZero(hour)+padWithZero(second)+padWithZero(day)+padWithZero(month)+padWithZero(year);
        System.out.println(" USER ID :: "+userId);
        return userId;
    }

    public String padWithZero(int number) {
        return String.format("%02d", number);
    }


}
