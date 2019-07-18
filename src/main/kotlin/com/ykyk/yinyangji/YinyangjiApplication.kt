package com.ykyk.yinyangji

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication
open class YinyangjiApplication

fun main(args: Array<String>) {
    runApplication<YinyangjiApplication>(*args)
}
