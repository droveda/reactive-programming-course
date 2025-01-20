package com.droveda.course.threadlocal;

public class ThreadLocalWithUserContext implements Runnable {

    private static ThreadLocal<Context> userContext = new ThreadLocal<>();

    private final Integer userId;

    private final MyUserRepository repository = new MyUserRepository();

    public ThreadLocalWithUserContext(Integer userId) {
        this.userId = userId;
    }

    @Override
    public void run() {
        String username = repository.getUserNameForUserId(userId);
        userContext.set(new Context(username));
        System.out.println("thread context for given userId: " + userId + " is: " + userContext.get());
    }
}
