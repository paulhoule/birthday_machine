package com.ontology2.birthdayMachine;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;

public interface SparqlService {
    QueryExecution createExecutor(Query q);
}
