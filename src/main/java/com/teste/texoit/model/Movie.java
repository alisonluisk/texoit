package com.teste.texoit.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long year;

    private String title;

    private String studios;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_producers", joinColumns = @JoinColumn(name = "id"))
    private List<String> producers;

    private boolean winner;

}
