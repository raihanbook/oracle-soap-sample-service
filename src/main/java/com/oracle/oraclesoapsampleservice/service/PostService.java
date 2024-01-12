package com.oracle.oraclesoapsampleservice.service;

import com.oracle.external.services.sampleservice.response.v1.Sampleservicerq;
import com.oracle.external.services.sampleservice.response.v1.Sampleservicers;
import com.oracle.oraclesoapsampleservice.model.RequestPayload;
import com.oracle.oraclesoapsampleservice.model.ResponsePayload;
import com.oracle.oraclesoapsampleservice.model.SampleServiceRq;
import com.oracle.oraclesoapsampleservice.model.SampleServiceRs;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class PostService {
        RestClient restClient = RestClient.create();

    public Sampleservicers postService (Sampleservicerq request) {
        Sampleservicers response = new Sampleservicers();

        SampleServiceRq sampleServiceRq = new SampleServiceRq(
                request.getServiceId(),
                request.getOrderType(),
                request.getType(),
                request.getTrxId()
        );
        RequestPayload jsonRequest = new RequestPayload(sampleServiceRq);

        try {
            ResponsePayload jsonResponse = restClient.post()
                    .uri("http://localhost:8081/external/services/rest/sample-service")
                    .contentType(APPLICATION_JSON)
                    .body(jsonRequest)
                    .retrieve()
                    .body(ResponsePayload.class);

            SampleServiceRs sampleServiceRs = jsonResponse.getSampleServiceRs();

            response.setErrorCode(sampleServiceRs.getErrorCode());
            response.setErrorMsg(sampleServiceRs.getErrorMsg());
            response.setTrxId(sampleServiceRs.getTrxId());
        } catch (Exception e) {
            response.setErrorCode("500");
            response.setErrorMsg("Something went wrong - Please check if the other service is running");
        }

        return response;
    }
}
