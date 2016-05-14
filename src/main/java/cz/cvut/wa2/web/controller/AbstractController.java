package cz.cvut.wa2.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author jakubchalupa
 * @since 18.04.16
 */
public abstract class AbstractController {

    protected ResponseEntity<String> getResponseCreated(String location) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", location);
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

}
