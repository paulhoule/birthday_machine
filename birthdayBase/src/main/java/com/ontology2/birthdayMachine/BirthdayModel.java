package com.ontology2.birthdayMachine;

import com.google.common.collect.ImmutableListMultimap;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.ontology2.sparqlEasy.DefaultSparqlClient;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;

import static com.ontology2.sparqlEasy.JenaStatics.dateNode;

@Component("birthdayModel")
public class BirthdayModel extends DefaultSparqlClient {
    public ResultSet lookupDate(final Calendar when) {
        return execution(query(
                "prefix ns: <http://rdf.basekb.com/ns/>" +
                        "prefix public: <http://rdf.basekb.com/public/>" +
                        "prefix xsd: <http://www.w3.org/2001/XMLSchema#>" +
                        "" +
                        "SELECT ?s ?eye {" +
                        "   ?s ns:people.person.date_of_birth ?when ." +
                        "   OPTIONAL { ?s public:subjectiveEye3D ?eye . }" +
                        "} ORDER BY DESC(?eye)",
                new HashMap<String, Node>() {{
                    put("when", dateNode(when));
                }}
        )).execSelect();
    }

    public NameCard lookupNames(final Node id) {
        ResultSet rs=execution(query(
                "prefix ns: <http://rdf.basekb.com/ns/>" +
                        "prefix public: <http://rdf.basekb.com/public/>" +
                        "prefix xsd: <http://www.w3.org/2001/XMLSchema#>" +
                        "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "" +
                        "SELECT ?label {" +
                        "   ?s rdfs:label ?label" +
                        "}",
                new HashMap<String, Node>() {{
                    put("s", id);
                }}
        )).execSelect();

        ImmutableListMultimap.Builder<String,String> builder=ImmutableListMultimap.builder();
        while(rs.hasNext()) {
            QuerySolution s=rs.next();
            Literal l=s.getLiteral("label");
            builder.put(l.getLanguage(),l.getString());
        };

        ImmutableListMultimap.Builder<String,String> links=ImmutableListMultimap.builder();
        if(id.toString().startsWith("http://rdf.basekb.com/ns/m.")) {
            links.put("freebaseLd",id.toString().replace("http://rdf.basekb.com/ns/m.","http://rdf.freebase.com/ns/m."));
            links.put("freebaseUi",id.toString().replace("http://rdf.basekb.com/ns/m.","http://www.freebase.com/m/"));
        }
        return new NameCard(id.toString(),builder.build(),links.build());
    };

    public ResultSet dateDistribution() {
        return execution(query(
                "prefix ns: <http://rdf.basekb.com/ns/>" +
                "prefix public: <http://rdf.basekb.com/public/>" +
                "prefix xsd: <http://www.w3.org/2001/XMLSchema#>" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                ""+
                "SELECT ?when (COUNT(*) as ?count) (SUM(?eye) as ?sumImportance) {"+
                "   ?s ns:people.person.date_of_birth ?when ." +
                "   OPTIONAL { ?s public:subjectiveEye3D ?eye . }" +
                "   FILTER(DATATYPE(?when)=xsd:date)" +
                "} GROUP BY(?when) ORDER BY ?when")).execSelect();
    }
}
