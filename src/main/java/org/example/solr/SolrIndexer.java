package org.example.solr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SolrIndexer {
    /**
     * reads the dataset for indexing it into the solr
     * @return
     * @throws IOException
     */
    public static ArrayList<Films> readFilms() throws IOException {
        String filmsData = new String(Files.readAllBytes(Paths.get("films.json")));
//        System.out.println(String.valueOf(filmsData));
        ObjectMapper objectmap = new ObjectMapper();
        ArrayList<Films> films = objectmap.readValue(filmsData, new TypeReference<ArrayList<Films>>() {});
        return films;
    }
    public static void main(String[] args) throws IOException, SolrServerException {
        ArrayList<Films> results = readFilms();
//        System.out.println(results.get(0));
        SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/films").build();
        solrClient.addBeans(results);
        solrClient.commit();
        System.out.println("Indexing Completed!!!");
    }
}
