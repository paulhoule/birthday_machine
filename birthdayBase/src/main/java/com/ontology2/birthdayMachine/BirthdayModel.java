package com.ontology2.birthdayMachine;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.ResultSet;
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
}
