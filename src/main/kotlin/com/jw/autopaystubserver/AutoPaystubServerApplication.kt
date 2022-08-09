package com.jw.autopaystubserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class AutoPaystubServerApplication

fun main(args: Array<String>) {
    runApplication<AutoPaystubServerApplication>(*args)
}