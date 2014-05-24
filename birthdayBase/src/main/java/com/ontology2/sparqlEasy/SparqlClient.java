package com.ontology2.sparqlEasy;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;

import java.util.Map;

//
// A SparqlClient is a class that contains an embedded SparqlService;  this contains convenience methods
// that make the top level methods of the SparqlService available as "functions"
//

public abstract class SparqlClient {
    abstract protected SparqlService service();

    protected Query query(String q) {
        return service().query(q);
    }

    protected Query query(String q,Map<String,Node> nodeMap) {
        return service().query(q,nodeMap);
    }

    protected QueryExecution execution(Query q) {
        return service().execution(q);
    }


}
