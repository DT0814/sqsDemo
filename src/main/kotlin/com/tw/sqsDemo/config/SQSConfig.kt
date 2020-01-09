package com.tw.sqsDemo.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Regions
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SQSConfig {
    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate? {
        return QueueMessagingTemplate(amazonSQSAsync())
    }


    fun amazonSQSAsync(): AmazonSQSAsync? {
        return AmazonSQSAsyncClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(DefaultAWSCredentialsProviderChain())
                .build()
    }
}
