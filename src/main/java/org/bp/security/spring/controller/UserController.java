package org.bp.security.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.security.Principal;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping("/user")
    public Principal user(final Principal principal) {
        logger.info("/user: Principal = " + principal);

        return principal;
    }

}
