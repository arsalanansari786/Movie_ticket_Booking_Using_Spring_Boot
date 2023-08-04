package com.gfg.shoutreview.repository;

import com.gfg.shoutreview.domain.Show;
import com.gfg.shoutreview.domain.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre,Long> {
     public Show findShow(String theatre );
}
