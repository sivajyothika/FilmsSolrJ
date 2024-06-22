package org.example.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.util.NamedList;

import java.io.IOException;

public class SolrSchema {
    public static void main(String[] args){
        SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/#/films").build();
    }
}
