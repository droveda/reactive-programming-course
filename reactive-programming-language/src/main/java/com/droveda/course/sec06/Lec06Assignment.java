package com.droveda.course.sec06;

import com.droveda.course.common.Util;
import com.droveda.course.sec06.assignment.ExternalServiceClient;
import com.droveda.course.sec06.assignment.InventoryService;
import com.droveda.course.sec06.assignment.RevenueService;

public class Lec06Assignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream()
                .subscribe(inventoryService::consume);

        client.orderStream()
                .subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("Inventory"));

        revenueService.stream()
                .subscribe(Util.subscriber("Revenue"));

        Util.sleepSeconds(30);

    }

}
