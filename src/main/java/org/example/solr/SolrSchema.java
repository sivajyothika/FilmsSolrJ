package org.example.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.apache.solr.common.LinkedHashMapWriter;
import org.apache.solr.common.util.NamedList;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SolrSchema {
    public static void main(String[] args) throws SolrServerException, IOException {
        SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/films").build();
        schemaAddField(solrClient,"movie_id","string",true,true,false);
        schemaAddField(solrClient,"directed_by","text_general",true,true,true);
        schemaAddField(solrClient,"initial_release_date","string",true,true,false);
        schemaAddField(solrClient,"genre","text_general",true,true,true);
    }
    public static void schemaAddField(SolrClient solrClient,String fieldName,String filedType,boolean stored,boolean indexed,boolean multivalued) throws SolrServerException, IOException {
        Map<String,Object> schemaFields = new LinkedHashMap<>();
        schemaFields.put("name",fieldName);
        schemaFields.put("type",filedType);
        schemaFields.put("stored",stored);
        schemaFields.put("indexed",indexed);
        schemaFields.put("multiValued",multivalued);
        SchemaRequest.AddField addFileds = new SchemaRequest.AddField(schemaFields);
        addFileds.process(solrClient);
    }
}
