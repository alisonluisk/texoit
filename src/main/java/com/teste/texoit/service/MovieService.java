package com.teste.texoit.service;

import com.teste.texoit.model.Movie;
import com.teste.texoit.repository.MovieRepository;
import com.teste.texoit.resources.dto.IntervalDTO;
import com.teste.texoit.resources.dto.ProducerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public Movie insert(Movie obj) {
        return repository.save(obj);
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }

    public IntervalDTO getIntervalWinners(){
        List<Movie> movieWinners = findAll().stream().filter(movie -> movie.isWinner()).collect(Collectors.toList());
        List<ProducerDTO> producersWinners = new ArrayList<>();
        List<String> producers = movieWinners.stream().flatMap(movie -> movie.getProducers().stream()).distinct().collect(Collectors.toList());
        producers.stream().forEach(s -> {
            List<Long> years = movieWinners.stream().filter(movie -> movie.getProducers().contains(s)).map(Movie::getYear).collect(Collectors.toList());
            if(years.size() > 1){
                HashMap<Integer, Long> collect = years
                        .stream()
                        .collect(HashMap<Integer, Long>::new,
                                (map, streamValue) -> map.put(map.size(), streamValue),
                                (map, map2) -> {
                                });
                collect.forEach((index, year) -> {
                    if((index + 1) != years.size()){
                        Long nextYear = years.get(index+1);
                        Long interval = nextYear - year;
                        if(interval > 0)
                            producersWinners.add(new ProducerDTO(s, interval, year, nextYear));
                    }

                });
            }
        });

        Long minInterval = producersWinners.stream().mapToLong(value -> value.getInterval()).min().orElseThrow(NoSuchElementException::new);
        Long maxInterval = producersWinners.stream().mapToLong(value -> value.getInterval()).max().orElseThrow(NoSuchElementException::new);

        List<ProducerDTO> minList = producersWinners.stream().filter(producerDTO -> producerDTO.getInterval() == minInterval).collect(Collectors.toList());
        List<ProducerDTO> maxsList = producersWinners.stream().filter(producerDTO -> producerDTO.getInterval() == maxInterval).collect(Collectors.toList());

        return new IntervalDTO(minList, maxsList);
    }

}
