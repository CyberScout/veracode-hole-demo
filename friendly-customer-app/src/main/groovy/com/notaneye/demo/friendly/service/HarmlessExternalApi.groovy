package com.notaneye.demo.friendly.service

import com.notaneye.demo.friendly.web.RegistrationCommand
import groovy.json.JsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import static okhttp3.MediaType.parse
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE


@Service
class HarmlessExternalApi {

    private static final Logger LOG = LoggerFactory.getLogger('friendly-customer-app')
    private static final MediaType JSON = parse(APPLICATION_JSON_UTF8_VALUE)

    @Value('${com.notaneye.demo.friendly.harmless.url}')
    String apiUrl

    @Autowired
    OkHttpClient httpClient

    void enrolledEvent(RegistrationCommand command) {

        handleEvent(command)
    }


    void piiEvent(RegistrationCommand command) {

        handleEvent(command)
    }

    private void handleEvent(RegistrationCommand command) {

        String json = new JsonBuilder(command).toPrettyString()
        RequestBody body = RequestBody.create(JSON, json)
        Request request = new Request.Builder()
                .url(this.apiUrl)
                .post(body)
                .build()
        httpClient.newCall(request).enqueue(new ResponseHandler())
    }

    private class ResponseHandler implements Callback {

        @Override
        void onResponse(Call call, Response response) throws IOException {

            // Ignore response, not important
        }


        @Override
        void onFailure(Call call, IOException e) {

            LOG.error('The call to the nasty API failed, but it did not disrupt the application')
            LOG.debug('If it wasn\'t for these log messages, no one would be the wiser')
        }
    }
}
