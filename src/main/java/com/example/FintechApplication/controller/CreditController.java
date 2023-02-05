package com.example.FintechApplication.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CreditController {
    private final CreditService creditService;
}
