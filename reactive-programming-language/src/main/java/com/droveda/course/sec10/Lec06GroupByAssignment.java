package com.droveda.course.sec10;

import com.droveda.course.common.Util;
import com.droveda.course.sec10.assignment.groupby.OrderProcessingService;
import com.droveda.course.sec10.assignment.groupby.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec06GroupByAssignment {

    public static void main(String[] args) {

        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.create());
    }

}
