package com.oracle.oraclesoapsampleservice.endpoint;

import com.oracle.external.services.sampleservice.response.v1.Sampleservicerq;
import com.oracle.external.services.sampleservice.response.v1.Sampleservicers;
import com.oracle.oraclesoapsampleservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SampleServiceEndpoint {

    private static final String NAMESPACE_URI = "http://www.oracle.com/external/services/sampleservice/response/v1.0";
    @Autowired
    private PostService postService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "sampleservicerq")
    @ResponsePayload
    public Sampleservicers getCountry(@RequestPayload Sampleservicerq request) {
        return postService.postService(request);
    }
}
