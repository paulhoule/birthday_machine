package com.ontology2.sparqlEasy;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;

import java.util.Map;

//
// This class ought to be spun out into another package that has a collection of shims for Jena that deal
// with verbosity and other SE issues.
//
// The key idea of the SparqlService is that,  for a given "sparql environment" it is good to
// bundle together both a specially configured QueryFactory with a specially configured QueryExecutionFactory
//
//

public interface SparqlService {
    com.hp.hpl.jena.rdf.model.Model model();
    Query query(String q);
    Query query(String q,Map<String,Node> map);
    QueryExecution execution(Query q);
}
