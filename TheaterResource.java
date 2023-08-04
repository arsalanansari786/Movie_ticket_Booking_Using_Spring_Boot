package com.gfg.shoutreview.resource;

import lombok.*;

import java.util.List;
@Builder
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TheaterResource {
    private long id;

    private String name;

    private String city;

    private String address;

    private List<ShowResource> shows;
}
