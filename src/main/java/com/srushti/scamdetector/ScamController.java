package com.srushti.scamdetector;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ScamController {

    @GetMapping("/")
    public String home() {
        return "Scam Link Detection System Running";
    }

    @PostMapping("/scan")
    public Map<String, String> scanUrl(@RequestBody Map<String, String> request) {

        String url = request.get("url");

        String status = "Safe";
        String risk = "Low";
        String score = "15";

        if (url.contains("@") ||
                url.contains("login") ||
                url.contains("verify") ||
                url.contains("free") ||
                url.contains("gift") ||
                url.contains(".xyz") ||
                url.contains("bank") ||
                url.contains("otp") ||
                url.contains("secure") ||
                url.contains("bit.ly") ||
                url.contains("tinyurl") ||
                url.length() > 50 ||
                url.contains("-")) {

            status = "Scam";
            risk = "High";
            score = "90";
        }

        Map<String, String> response = new HashMap<>();

        response.put("url", url);
        response.put("status", status);
        response.put("risk", risk);
        response.put("score", score);

        return response;
    }
}