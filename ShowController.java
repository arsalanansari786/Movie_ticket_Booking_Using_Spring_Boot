package com.gfg.shoutreview.controller;

import com.gfg.shoutreview.resource.ShowResource;
import com.gfg.shoutreview.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
     @Autowired
    private ShowService showService;
     @GetMapping("/search")
    public ResponseEntity<List<ShowResource>> search(@RequestParam(name="movieName", required = false)String movieName ,
                                                     @RequestParam(name="city", required = true)String cityName,
                                                     @RequestParam(name="theatre", required = false)String theatreName){
         return ResponseEntity.ok(showService.searchShow(movieName, cityName, theatreName));

     }
}
