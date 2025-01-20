package com.droveda.course.threadlocal;

import com.droveda.course.common.Util;

public class TestThreadLocal2 {

    public static void main(String[] args) {

        ThreadLocalWithUserContext firstUser = new ThreadLocalWithUserContext(1);
        ThreadLocalWithUserContext secondUser = new ThreadLocalWithUserContext(2);

        new Thread(firstUser).start();
        new Thread(secondUser).start();

        Util.sleepSeconds(2);

    }

}
