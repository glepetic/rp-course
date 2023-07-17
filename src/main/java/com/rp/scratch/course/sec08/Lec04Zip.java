package com.rp.scratch.course.sec08;

import com.rp.scratch.course.sec08.helper.ComponentsGenerator;
import com.rp.scratch.util.ScratchUtil;
import reactor.core.publisher.Flux;

public class Lec04Zip {

    public static void main(String[] args) {

        ComponentsGenerator componentsGenerator = ComponentsGenerator.instance();

        // zip returns a tuple with N elements
        Flux.zip(componentsGenerator.getBody(), componentsGenerator.getEngine(), componentsGenerator.getTires())
                .subscribe(ScratchUtil.subscriber());

    }

}
