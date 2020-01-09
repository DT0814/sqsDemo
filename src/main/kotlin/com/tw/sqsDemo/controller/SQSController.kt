package com.tw.sqsDemo.controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/sqs")
class SQSController {
    @Autowired
    private lateinit var queueMessagingTemplate: QueueMessagingTemplate
    val headers: MutableMap<String, Any> = hashMapOf("message-group-id" to "1", "message-deduplication-id" to UUID.randomUUID().toString())


    @GetMapping("/send/Initiator")
    fun sendInitiator() {
        queueMessagingTemplate.convertAndSend("Initiator-sqs.fifo", "this is a Initiator", headers)
    }

    @GetMapping("/send/report")
    fun sendReport() {

        queueMessagingTemplate
                .convertAndSend("merchant-transaction-report-sqs.fifo",
                        "this is a report", headers)
    }

    @GetMapping("/get/report")
    fun getReport() = queueMessagingTemplate.receive("merchant-transaction-report-sqs.fifo")

    @SqsListener("https://sqs.us-east-2.amazonaws.com/044753920482/sqs-listener.fifo")
    fun getMessage(message: String) {
        LOG.info("Message from SQS Queue - $message")
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SQSController::class.java)
    }
}
