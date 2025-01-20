package com.droveda.course.threadlocal;

import java.util.Map;

public class MyUserRepository {

    private static Map<Integer, String> users = Map.of(
            1, "sam",
            2, "mike"
    );

    public String getUserNameForUserId(Integer userId) {
        return users.get(userId);
    }

}
