package com.ontology2.birthdayMachine;


import org.apache.jena.atlas.web.auth.HttpAuthenticator;
import org.apache.jena.atlas.web.auth.SimpleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import arq.query;
import com.hp.hpl.jena.query.*;
import org.springframework.stereotype.Component;

@Component("sparqlService")
public class SparqlServiceImpl implements SparqlService {
    @Autowired String sparqlURI;
    @Autowired String sparqlUser;
    @Autowired String sparqlPassword;

    @Override
    public QueryExecution createExecutor(Query q) {
        HttpAuthenticator a =new SimpleAuthenticator(sparqlUser,sparqlPassword.toCharArray());
        return QueryExecutionFactory.sparqlService(sparqlURI, q, a);
    }

}
