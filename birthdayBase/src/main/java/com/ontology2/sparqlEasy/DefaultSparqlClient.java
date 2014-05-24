package com.ontology2.sparqlEasy;

import org.springframework.beans.factory.annotation.Autowired;

public class DefaultSparqlClient extends SparqlClient {
    @Autowired
    protected SparqlService sparqlService;

    @Override
    protected SparqlService service() {
        return sparqlService;
    }
}
