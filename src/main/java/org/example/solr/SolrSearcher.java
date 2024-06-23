package org.example.solr;

import com.ctc.wstx.shaded.msv_core.verifier.jarv.FactoryImpl;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;

public class SolrSearcher {
    /**
     * This function search the solr documents based on the search query
     * @param query
     * @throws SolrServerException
     * @throws IOException
     */
    public static void search(SolrQuery query) throws SolrServerException, IOException {
        SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/films").build();
        QueryResponse response = solrClient.query(query);
        List<Films> results = response.getBeans(Films.class);
        SolrDocumentList solrdoumnets = response.getResults();
        //retrives only name of the film of particular document rather than entire documnet/record .
        for(SolrDocument doc:solrdoumnets){
            System.out.println(doc.getFieldValue("name"));
        }
        //retrun all the fields in the records
        System.out.println(results);
    }

    public static void main(String[] args) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.set("q","genre:Thriller");
//        query.set("fl","name");
        query.addSort("name", SolrQuery.ORDER.desc);
        search(query);
    }
}
