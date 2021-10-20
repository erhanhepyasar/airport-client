package com.afkl.cases.df.locations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;



@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private WebClient webClient;

    Logger logger = LoggerFactory.getLogger(AirportController.class);
    final String serviceUrl = "http://localhost:8080/airports";

    @GetMapping
    public void list() {
        webClient.get()
                .uri(serviceUrl + "/airports")
                .retrieve()
                .bodyToMono(String.class)
                .map(string -> "Returned: " + string)
                .subscribe(logger::info);
    }

    @GetMapping("/{key}")
    public void geyByCode(@RequestParam(value = "lang", defaultValue = "en") String lang, @PathVariable("key") String key) {
        webClient.get()
                .uri(serviceUrl + key)
                .retrieve()
                .bodyToMono(String.class)
                .map(string -> "Returned: " + string)
                .subscribe(logger::info);
    }

    @GetMapping(params = "term")
    public void find(@RequestParam(value = "lang", defaultValue = "en") String lang,
                     @RequestParam("term") String term) {
        webClient.get()
                .uri(serviceUrl + term)
                .retrieve()
                .bodyToMono(String.class)
                .map(string -> "Returned: " + string)
                .subscribe(logger::info);
    }

}
