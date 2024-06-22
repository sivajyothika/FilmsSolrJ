package org.example.solr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SolrIndexer {
    public static ArrayList<Films> readFilms() throws IOException {
        String filmsData = new String(Files.readAllBytes(Paths.get("films.json")));
        System.out.println(String.valueOf(filmsData));
        ObjectMapper objectmap = new ObjectMapper();
        ArrayList<Films> films = objectmap.readValue(filmsData, new TypeReference<ArrayList<Films>>() {});
        return films;
    }
    public static void main(String[] args) throws IOException {
        ArrayList<Films> results = readFilms();
        System.out.println(results.get(0));
    }
}
