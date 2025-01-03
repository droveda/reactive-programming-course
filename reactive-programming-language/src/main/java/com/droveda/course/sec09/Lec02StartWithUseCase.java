package com.droveda.course.sec09;

import com.droveda.course.common.Util;
import com.droveda.course.sec09.helper.NameGenerator;

public class Lec02StartWithUseCase {

    public static void main(String[] args) {

        var nameGenerator = new NameGenerator();

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("sam"));

        nameGenerator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("mike"));

        nameGenerator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("jake"));

    }

}
