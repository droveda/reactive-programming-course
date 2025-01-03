package com.droveda.course.sec09;

import com.droveda.course.common.Util;
import com.droveda.course.sec09.helper.Kayak;

public class Lec06MergeUseCase {

    public static void main(String[] args) {
        Kayak.getFlights()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }

}
