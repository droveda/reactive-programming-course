package com.droveda.course.threadlocal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedMapWithUserContext implements Runnable {

    private static Map<Integer, Context> userContextPerUserId = new ConcurrentHashMap<>();
    private final Integer userId;
    private MyUserRepository repository = new MyUserRepository();

    public SharedMapWithUserContext(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        String userName = repository.getUserNameForUserId(userId);
        System.out.println(userName);
        userContextPerUserId.put(userId, new Context(userName));
    }

    public static Map<Integer, Context> getUserContextPerUserId() {
        return userContextPerUserId;
    }
}
