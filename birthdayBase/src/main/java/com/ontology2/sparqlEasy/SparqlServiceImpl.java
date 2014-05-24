package com.ontology2.sparqlEasy;


import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import org.apache.jena.atlas.web.auth.HttpAuthenticator;
import org.apache.jena.atlas.web.auth.SimpleAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import com.hp.hpl.jena.query.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("sparqlService")
public class SparqlServiceImpl implements SparqlService {
    @Autowired String sparqlURI;
    @Autowired String sparqlUser;
    @Autowired String sparqlPassword;

    @Override
    public Query query(String q) {
        return query(q,new HashMap<String,Node>());
    }

    @Override
    public Query query(String q, Map<String,Node> map) {
        QuerySolutionMap qsm=new QuerySolutionMap();
        for(Map.Entry<String,Node> entry:map.entrySet())
            qsm.add(entry.getKey(),model().asRDFNode(entry.getValue()));

        return new ParameterizedSparqlString(q,qsm).asQuery();
    }

    @Override
    public QueryExecution execution(Query q) {
        HttpAuthenticator a =new SimpleAuthenticator(sparqlUser,sparqlPassword.toCharArray());
        return QueryExecutionFactory.sparqlService(sparqlURI, q, a);
    }

    private final Model _model=ModelFactory.createDefaultModel();
    @Override
    public Model model() {
        return _model;
    };

}
