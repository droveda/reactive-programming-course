package com.droveda.course.sec04;

import com.droveda.course.common.Util;
import com.droveda.course.sec04.assignment.FileReaderServiceImpl;

import java.nio.file.Path;

public class Lec09Assignment {

    public static void main(String[] args) {
        var path = Path.of("src/main/resources/sec04/file.txt");
        var fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(path)
                .take(2)
                .subscribe(Util.subscriber());

    }

}
