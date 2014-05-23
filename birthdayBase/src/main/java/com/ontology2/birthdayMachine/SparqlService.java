package com.ontology2.birthdayMachine;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QuerySolutionMap;
import org.springframework.ui.Model;

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
    Query query(String q,QuerySolutionMap map);
    QueryExecution executor(Query q);
}
