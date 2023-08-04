package com.gfg.shoutreview.service;

import com.gfg.shoutreview.domain.Show;
import com.gfg.shoutreview.repository.ShowRepository;
import com.gfg.shoutreview.resource.ShowResource;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Builder
@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;

    public List<ShowResource> searchShow(String movieName, String cityName, String theatreName) {
        if(!StringUtils.hasText(cityName))
            return new ArrayList<>();
        List<Show>show=new ArrayList<>();
        if(StringUtils.hasText(movieName)){
            show=showRepository.findByMovieNameAndCity(movieName, cityName);
        }
        else if(StringUtils.hasText(theatreName)){
            show= showRepository.findByTheatreNameAndCity(theatreName, cityName);
        }
        else show= showRepository.findByCity(cityName);
        if(show.isEmpty())
            return new ArrayList<>();
        else
            return show.stream().map(Show::toResource).collect(Collectors.toList());
    }

}
