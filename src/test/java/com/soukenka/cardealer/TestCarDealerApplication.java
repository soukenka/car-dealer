package com.soukenka.cardealer;

import org.springframework.boot.SpringApplication;

public class TestCarDealerApplication {

    public static void main(String[] args) {
        SpringApplication.from(CarDealerApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
