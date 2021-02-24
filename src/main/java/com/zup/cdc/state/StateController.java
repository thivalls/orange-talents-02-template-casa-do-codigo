package com.zup.cdc.state;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("states")
public class StateController {
    @PostMapping
    public String store() {
        System.out.println("Route states mapped");
        return "";
    }
}
