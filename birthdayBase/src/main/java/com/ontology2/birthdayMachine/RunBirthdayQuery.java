package com.ontology2.birthdayMachine;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Resource;
import com.ontology2.centipede.shell.CommandLineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ontology2.sparqlEasy.SparqlService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static com.ontology2.sparqlEasy.JenaStatics.*;

@Component("birthdayQuery")
public class RunBirthdayQuery extends CommandLineApplication {
    @Autowired
    BirthdayModel birthdayModel;

    @Override
    protected void _run(String[] strings) throws Exception {
        final Calendar when=new GregorianCalendar(1852,7,6);
        ResultSet r=birthdayModel.lookupDate(when);

        while(r.hasNext()) {
            QuerySolution row=r.next();
            Resource s=asResource(row, "s");
            Double eye=asDouble(row, "eye");
            NameCard card=birthdayModel.lookupNames(s.asNode());
            System.out.println(eye+"    "+card.getLinks("freebaseUi").get(0)+"    "+card.getName());
        }
    }
}
