package com.gfg.shoutreview.controller;

import com.gfg.shoutreview.resource.TheaterResource;
import com.gfg.shoutreview.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/theatre")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;
    @PostMapping("/add")
    public ResponseEntity<TheaterResource> add(@RequestBody TheaterResource theaterResource){
        return ResponseEntity.ok(theatreService.addTheatre(theaterResource));
    }
//    @GetMapping("/search")
//    public ResponseEntity<TheaterResource>search(@RequestParam TheaterResource){
//
//    }
}
