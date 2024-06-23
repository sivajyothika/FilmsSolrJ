package org.example.solr;

import com.ctc.wstx.shaded.msv_core.verifier.jarv.FactoryImpl;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.ArrayList;
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
        List<FacetField> facetFields = response.getFacetFields();
        facetFields.forEach(System.out::println);
    }

    public static void main(String[] args) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.set("q","genre:Thriller");
//        query.set("fl","name");
        query.addSort("name", SolrQuery.ORDER.desc);
        //
        /**
         * brings data based on facets we defined here
         * genre:[thriller (266), film (187), fiction (141), drama (139), action (112), crime (112), adventure (68), horror (68), mystery (61), psychological (46), science (43), cinema (38), comedy (38), world (38), romance (34), indie (27), fantasy (20),
         * bollywood (16), movie (15), teen (14), suspense (13), arts (12), martial (12), slasher (12), supernatural (12), musical (11), animation (9), historical (9), adaptation (8), movies (8), political (8), superhero (8), war (8), black (7), erotic (7),
         * family (7), biographical (6), cult (6), anime (5), gangster (5), japanese (5), natural (5), noir (5), period (5), asian (4), buddy (4), dystopia (4), east (4), heist (4), tamil (4), and (3), b (3), chinese (3), detective (3), disaster (3), ensemble (3),
         * epic (3), future (3), lgbt (3), monster (3), romantic (3), short (3), spy (3), time (3), tollywood (3), travel (3), alien (2), apocalyptic (2), art (2), caper (2), computer (2), creature (2), doomsday (2), erotica (2), malayalam (2), post (2), story (2),
         * television (2), western (2), zombie (2), chase (1), children's (1), christian (1)
         * , cop (1), costume (1), documentary (1), escape (1), farce (1), feminist (1), fi (1), glamorized (1), jungle (1), marriage (1), melodrama (1), mockbuster (1), music (1), neo (1), remake (1), road (1), satire (1)]
         * directed_by:[david (13), john (11), michael (11), paul (6), scott (5), j (4), james (4), richard (4), s (4), steven (4), andrzej (3), boll (3), danny (3), gary (3), gopal (3), jean (3), johnnie (3), johnson (3),
         * kitamura (3), mamoru (3), mark (3), mike (3), ram (3), robert (3), roger (3), ryuhei (3), stephen (3), to (3), tony (3), uwe (3), varma (3), william (3), abbas (2), alejandro (2), bartkowiak (2), bhatt (2), brett (2),
         * brian (2), burke (2), burmawalla (2), cronenberg (2), d (2), de (2), ellis (2), eric (2), glen (2), gonzález (2), gordon (2), gregory (2), howard (2), iñárritu (2), jacobson (2), jeff (2), joe (2), joel (2), jonathan (2),
         * jones (2), lafia (2), lee (2), mario (2), martin (2), michell (2), mustan (2), oshii (2), r (2), ridley (2), rob (2), ron (2), roth (2), shane (2), singleton (2), takeshi (2), van (2), vikram (2), wong (2), a.r (1), acker (1), adajania (1),
         * agustín (1), akane (1), akhtar (1), albert (1), aleksei (1), alex (1), alfonso (1), allen (1), almereyda (1), anant (1), anderson (1), andrea (1), andreas (1), andrew (1), annaud (1), antoine (1), anubhav (1), anurag (1), apoorva (1), apted (1), aruna (1), ashu (1)]
         */
        query.addFacetField("genre");
        query.addFacetField("directed_by");
        search(query);
    }
}
