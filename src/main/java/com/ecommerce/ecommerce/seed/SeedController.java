package com.ecommerce.ecommerce.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/seed")
@RequiredArgsConstructor
public class SeedController {
    private final SeedService seedService;


    @GetMapping
    public ResponseEntity<Boolean> seed(){
        return ResponseEntity.ok(seedService.seed());
    }
}
