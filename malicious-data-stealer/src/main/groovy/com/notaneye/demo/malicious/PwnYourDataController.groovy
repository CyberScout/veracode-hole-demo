package com.notaneye.demo.malicious

import groovy.json.JsonOutput
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PwnYourDataController {

    private static final Logger LOG = LoggerFactory.getLogger('Sensitive Data')

    private static final List<String> SNARKY_MESSAGES = [
            'You have been pwned!',
            'BWAHAHAHAHA!!!!!1!!!1!',
            'All your PII are belong to us!'
    ]
    private static final Iterator<String> MESSAGE_SOURCE = new Random(System.currentTimeMillis())
            .ints(0, SNARKY_MESSAGES.size())
            .mapToObj() { int i -> SNARKY_MESSAGES[i] }
            .iterator()

    @PostMapping(path = '/', consumes = 'application/json')
    ResponseEntity<String> stealIt(@RequestBody String body) {

        LOG.error(randomSnarkyMessage())
        LOG.info(JsonOutput.prettyPrint(body))
    }


    private String randomSnarkyMessage() {

        return MESSAGE_SOURCE.next()
    }
}
