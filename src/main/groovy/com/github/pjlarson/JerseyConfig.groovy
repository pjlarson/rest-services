package com.github.pjlarson

import com.github.pjlarson.controller.BookController
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.context.annotation.Configuration

import javax.ws.rs.ApplicationPath

/**
 * Created by Peter on 10/24/2015.
 */
@Configuration
@ApplicationPath('api')
class JerseyConfig extends ResourceConfig {

    JerseyConfig() {
        register(BookController.class)
    }
}
