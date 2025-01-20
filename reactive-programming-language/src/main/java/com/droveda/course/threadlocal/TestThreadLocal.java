package com.droveda.course.threadlocal;

import com.droveda.course.common.Util;

public class TestThreadLocal {

    public static void main(String[] args) {
        SharedMapWithUserContext firstUser = new SharedMapWithUserContext(1);
        SharedMapWithUserContext secondUser = new SharedMapWithUserContext(2);

        new Thread(firstUser).start();
        new Thread(secondUser).start();

        Util.sleepSeconds(1);

        System.out.println(SharedMapWithUserContext.getUserContextPerUserId().size());

        Util.sleepSeconds(1);

    }

}
