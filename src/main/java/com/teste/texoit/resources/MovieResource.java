package com.teste.texoit.resources;

import com.teste.texoit.resources.dto.IntervalDTO;
import com.teste.texoit.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/movies")
@Slf4j
public class MovieResource {

    @Autowired
    private MovieService service;

    @GetMapping(value="/interval-awards")
    public ResponseEntity<IntervalDTO> getInterval() {
        return ResponseEntity.ok().body(service.getIntervalWinners());
    }

}
