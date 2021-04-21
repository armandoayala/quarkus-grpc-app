package org.lab.arm.app.client.rest;

import org.lab.arm.app.model.dto.CreateOperationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@Component
public class AppProxyRestComponent {

    @Value("${rest.service.uri}")
    private String restServiceUri;

    private static final Logger logger = Logger.getLogger(AppProxyRestComponent.class.getName());

    public ResponseEntity<CreateOperationResponse> createPerson(RequestRestModel request) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = new URI(restServiceUri);

        ResponseEntity<CreateOperationResponse> result = restTemplate.postForEntity(uri, request, CreateOperationResponse.class);

        return result;
    }
}
