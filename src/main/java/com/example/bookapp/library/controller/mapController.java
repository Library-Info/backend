package com.example.bookapp.library.controller;

import com.example.bookapp.library.MapServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/library/map")
public class mapController {

    MapServiceImpl mapService;
    public mapController(MapServiceImpl mapService) {
        this.mapService = mapService;
    }
    @GetMapping("/kakao")
    public ResponseEntity<?> getLibraryListByKakao(@RequestParam String city, @RequestParam String province, @RequestParam double mylat, @RequestParam double mylong, @RequestParam int radius) throws IOException {
        List<?> libryList =mapService.getLibraryListByKaKaoMap(city, province, mylat, mylong, radius);
        return ResponseEntity.ok(libryList);
    }
}
