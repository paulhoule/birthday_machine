package com.ontology2.birthdayMachine;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.ontology2.centipede.shell.CommandLineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ontology2.sparqlEasy.SparqlService;

@Component("runTestQuery")
public class RunTestQuery extends CommandLineApplication {
    @Autowired
    SparqlService sparqlService;

    @Override
    protected void _run(String[] strings) throws Exception {
        Query q= QueryFactory.create("select (count(*) as ?cnt) { ?s ?p ?o . }");
        ResultSet s=sparqlService.execution(q).execSelect();

        int count=s.next().get("cnt").asLiteral().getInt();
        System.out.println("Count = "+count);
    }
}
