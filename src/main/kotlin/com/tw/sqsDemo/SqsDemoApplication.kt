package com.tw.sqsDemo

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration
import org.springframework.cloud.aws.autoconfigure.messaging.MessagingAutoConfiguration

@EnableAutoConfiguration(exclude = [ContextRegionProviderAutoConfiguration::class])
@SpringBootApplication
class SqsDemoApplication

fun main(args: Array<String>) {
    runApplication<SqsDemoApplication>(*args)
}
