package com.mercadolibre.coupon.delivery.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.coupon.delivery.exception.ClientException;
import com.mercadolibre.coupon.delivery.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MercadoLibreClient {

    private RestTemplate restTemplate;
    private static final String URL = "https://api.mercadolibre.com/";
    public static final String ITEM_URL="items/";
    private static final Logger logger = LoggerFactory.getLogger(MercadoLibreClient.class);

    public MercadoLibreClient() {
        restTemplate = new RestTemplate();
    }

    public <T> T executeGet(String resourceUrl, Class<T> className) throws ClientException {

        try {
            logger.info("getting prices of item");
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseEntity<String> response
                    = restTemplate.getForEntity(URL + resourceUrl, String.class);
            logger.info("Response : "+response.getBody());
            if (!HttpStatus.OK.equals(response.getStatusCode())) {
                ErrorResponse error = objectMapper.readerFor(ErrorResponse.class).readValue(response.getBody());
                throw new ClientException(error.getMessage(), error.getError());
            }

            T successResponse = objectMapper.readerFor(className).readValue(response.getBody());
            return successResponse;

        } catch (Exception e) {
            throw new ClientException("Error Calling Mc Service", e.getMessage(), e);
        }
    }
}
