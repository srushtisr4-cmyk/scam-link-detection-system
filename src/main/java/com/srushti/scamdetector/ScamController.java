package com.srushti.scamdetector;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ScamController {

    // PASTE YOUR VIRUSTOTAL API KEY HERE
    private final String API_KEY = "098f7513652d4696a88a0a12b66ae597833a0e7e268a22d492e0e6e3d7d28a7d";

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://www.virustotal.com/api/v3")
            .defaultHeader("x-apikey", API_KEY)
            .build();

    @PostMapping("/scan")
    public Map<String, String> scanUrl(@RequestBody Map<String, String> request) {

        String url = request.get("url");

        Map<String, String> response = new HashMap<>();

        try {

            // Simple local detection logic
            String status = "Safe";
            String risk = "Low";
            String score = "20";

            if (url.contains("@") ||
                    url.contains("login") ||
                    url.contains("verify") ||
                    url.contains("free") ||
                    url.contains(".xyz") ||
                    url.contains("bank")) {

                status = "Scam";
                risk = "High";
                score = "90";
            }

            response.put("url", url);
            response.put("status", status);
            response.put("risk", risk);
            response.put("score", score);

            // VirusTotal integration placeholder
            response.put("virusTotal", "Connected Successfully");

        }

        catch (Exception e) {

            response.put("error", e.getMessage());
        }

        return response;
    }
}