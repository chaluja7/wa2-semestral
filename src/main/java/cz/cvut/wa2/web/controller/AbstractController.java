package cz.cvut.wa2.web.controller;

import cz.cvut.wa2.entity.Person;
import cz.cvut.wa2.service.PersonService;
import cz.cvut.wa2.web.controller.exception.UnauthorizedException;
import cz.cvut.wa2.web.interceptor.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jakubchalupa
 * @since 18.04.16
 */
public abstract class AbstractController {

    @Autowired
    protected PersonService personService;

    protected Person getCurrentPersonOrThrowUnauthorized(HttpServletRequest httpServletRequest) {
        try {
            Person person = personService.findPersonByToken(httpServletRequest.getHeader(SecurityInterceptor.SECURITY_HEADER));
            if(person != null) {
                return person;
            }
        } catch (Exception e) {
            //nothing
        }

        throw new UnauthorizedException();
    }

    protected ResponseEntity<String> getResponseCreated(String location) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", location);
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

}
