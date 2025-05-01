package com.dialltay.leveragess;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryPointsController {

    @GetMapping("/hello")
    String sayHello() {
        return "Hello, World!";
    }
}
