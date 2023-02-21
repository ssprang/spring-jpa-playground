package com.example.springjpaplayground

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class SpringJpaPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<SpringJpaPlaygroundApplication>(*args)
}

@Configuration
@EnableJpaAuditing
class ApplicationConfiguration