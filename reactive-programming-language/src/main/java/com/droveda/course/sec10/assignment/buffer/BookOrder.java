package com.droveda.course.sec10.assignment.buffer;

import com.droveda.course.common.Util;

public record BookOrder(
        String genre,
        String title,
        Integer price
) {

    public static BookOrder create() {
        var book = Util.faker().book();

        return new BookOrder(
                book.genre(),
                book.title(),
                Util.faker().random().nextInt(10, 100)
        );
    }

}
