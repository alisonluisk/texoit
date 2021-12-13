package com.teste.texoit.config;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.teste.texoit.model.Movie;
import com.teste.texoit.service.MovieService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbInit
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private MovieService movieService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/static/movielist.csv"));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(new CSVParserBuilder().withSeparator(';').build()).build();

            String[] line;
            while ((line = csvReader.readNext()) != null) {
                List<String> producers = Arrays.stream(line[3].split(",| and ")).map(String::trim).collect(Collectors.toList());
                Movie movie = new Movie(null, Long.parseLong(line[0]), line[1], line[2], producers, line[4].equals("yes") );
                movieService.insert(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }
}