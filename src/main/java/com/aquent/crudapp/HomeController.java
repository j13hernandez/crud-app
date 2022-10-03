package com.aquent.crudapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * Simple controller to redirect to the person listing.  In the future, we could
 * add other landing page behavior here if we were writing a real application.
 */
@RestController
public class HomeController {
    /**
     * Redirect to the person list.
     * In a real application this could be our landing or login page.
     *
     * @return redirect to the person list
     * @throws IOException
     */
    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/person/list");
    }
}
