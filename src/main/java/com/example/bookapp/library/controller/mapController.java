package com.example.bookapp.library.controller;

import com.example.bookapp.library.MapServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public ResponseEntity<?> getLibraryListByKakao(String city, String province,double mylat, double mylong,int radius) throws IOException {
        List<?> libryList =mapService.getLibraryListByKaKaoMap(city, province, mylat, mylong, radius);
        return ResponseEntity.ok(libryList);
    }
}
