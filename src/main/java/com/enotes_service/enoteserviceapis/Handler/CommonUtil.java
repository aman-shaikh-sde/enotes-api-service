package com.enotes_service.enoteserviceapis.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CommonUtil {

    public static ResponseEntity<?>  createBuildResponse(Object data, HttpStatus status){

        GenricResponse response=GenricResponse.builder()
                .responseStatus(status)
                .status("success")
                .message("message")
                .data(data)
                .build();
        return response.create();
    }
    public static ResponseEntity<?> createBuildResponseMessage(String message,HttpStatus status,Object data){
        GenricResponse response=GenricResponse.builder()
                .responseStatus(status)
                .status("success")
                .message(message)
                .data(data)
                .build();
        return response.create();
    }
    public static ResponseEntity<?> createErrorResponse(Object data,HttpStatus status){

        GenricResponse reponse= GenricResponse.builder()
                .responseStatus(status)
                .status("success")
                .message("failed")
                .build();
            return reponse.create();
    }

    public static ResponseEntity<?> createErrorResponseMessage(String message,HttpStatus status){

        GenricResponse response=GenricResponse.builder()
                .responseStatus(status)
                .status("failed")
                .message(message)
                .build();
        return response.create();
    }




}
