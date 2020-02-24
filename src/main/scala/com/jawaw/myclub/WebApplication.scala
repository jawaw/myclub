package com.jawaw.myclub

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@MapperScan(Array("com.jawaw.myclub.dao.mybatis"))
class AppConfig


object WebApplication extends App {
    SpringApplication.run(classOf[AppConfig])
}

