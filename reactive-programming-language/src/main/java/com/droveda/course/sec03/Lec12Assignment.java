package com.droveda.course.sec03;

import com.droveda.course.common.Util;
import com.droveda.course.sec03.assignment.StockPriceObserver;
import com.droveda.course.sec03.client.ExternalServiceClient;

public class Lec12Assignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        var subscriber = new StockPriceObserver();

        client.getPriceChanges()
                .subscribe(subscriber);

        Util.sleepSeconds(25);

    }

}
