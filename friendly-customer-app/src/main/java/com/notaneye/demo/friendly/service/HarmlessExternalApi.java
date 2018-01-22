package com.notaneye.demo.friendly.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notaneye.demo.friendly.web.RegistrationCommand;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


@Service
public class HarmlessExternalApi {

    private static final Logger LOG = LoggerFactory.getLogger("friendly-customer-app");
    private static final MediaType JSON = MediaType.parse(APPLICATION_JSON_UTF8_VALUE);

    @Value("${com.notaneye.demo.friendly.harmless.url}")
    private String apiUrl;
    @Autowired
    private OkHttpClient httpClient;
    @Autowired
    ObjectMapper jsonMapper;


    public void enrolledEvent(RegistrationCommand command) {

        handleEvent(command);
    }


    public void piiEvent(RegistrationCommand command) {

        handleEvent(command);
    }


    private void handleEvent(RegistrationCommand command) {

        try {
            String json = jsonMapper.writeValueAsString(command);
            RequestBody body = RequestBody.create(JSON, json);
            Request request = new Builder()
                    .url(this.apiUrl)
                    .post(body)
                    .build();
            httpClient
                    .newCall(request)
                    .enqueue(new ResponseHandler());
        }
        catch (JsonProcessingException e) {
            // Swallow the error
        }
    }


    private class ResponseHandler implements Callback {

        @Override
        public void onResponse(Call call, Response response) {

            // Ignore response, not important
        }


        @Override
        public void onFailure(Call call, IOException e) {

            LOG.error("The call to the nasty API failed, but it did not disrupt the application");
            LOG.debug("If it wasn\'t for these log messages, no one would be the wiser");
        }
    }
}
