package com.ontology2.birthdayMachine;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.apache.jena.atlas.web.auth.HttpAuthenticator;
import org.apache.jena.atlas.web.auth.SimpleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import com.hp.hpl.jena.query.*;
import org.springframework.stereotype.Component;

@Component("sparqlService")
public class SparqlServiceImpl implements SparqlService {
    @Autowired String sparqlURI;
    @Autowired String sparqlUser;
    @Autowired String sparqlPassword;

    @Override
    public Query query(String q) {
        return query(q,new QuerySolutionMap());
    }

    @Override
    public Query query(String q, QuerySolutionMap map) {
        return new ParameterizedSparqlString(q,map).asQuery();
    }

    @Override
    public QueryExecution executor(Query q) {
        HttpAuthenticator a =new SimpleAuthenticator(sparqlUser,sparqlPassword.toCharArray());
        return QueryExecutionFactory.sparqlService(sparqlURI, q, a);
    }

    private final Model _model=ModelFactory.createDefaultModel();
    @Override
    public Model model() {
        return _model;
    };

}
