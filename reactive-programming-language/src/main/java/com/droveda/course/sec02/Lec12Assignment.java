package com.droveda.course.sec02;

import com.droveda.course.common.Util;
import com.droveda.course.sec02.assignment.FileServiceImpl;

public class Lec12Assignment {

    public static void main(String[] args) {
        var fileService = new FileServiceImpl();

        fileService.write("file.txt", "This is a test file")
                .subscribe();

        fileService.read("file.txt")
                .subscribe(Util.subscriber());

        fileService.delete("file.txt")
                .subscribe(Util.subscriber());
    }

}
