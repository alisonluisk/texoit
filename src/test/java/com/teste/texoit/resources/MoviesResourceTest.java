package com.teste.texoit.resources;

import com.teste.texoit.resources.dto.IntervalDTO;
import com.teste.texoit.resources.dto.ProducerDTO;
import com.teste.texoit.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MoviesResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private MovieService movieService;

    @Test
    public void listIntervalWinnerStatus200(){
        ResponseEntity<String> response = restTemplate.getForEntity("/movies/interval-awards", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void listIntervalWinner(){
        IntervalDTO interval = new IntervalDTO(Arrays.asList(new ProducerDTO("Adam Sandler", 1L, 2002L, 2003L)), Arrays.asList(new ProducerDTO("Kevin James", 10L, 2011L,2021L)));
        BDDMockito.when(movieService.getIntervalWinners()).thenReturn(interval);
        ResponseEntity<IntervalDTO> response = restTemplate.getForEntity("/movies/interval-awards", IntervalDTO.class);
        Assertions.assertEquals(response.getBody(), interval);
    }

}
