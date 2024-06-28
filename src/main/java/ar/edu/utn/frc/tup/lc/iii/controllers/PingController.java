package ar.edu.utn.frc.tup.lc.iii.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    /**
     * This method is used to check if the server is up and running.
     *
     * @return "pong"
     */
    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }
}
