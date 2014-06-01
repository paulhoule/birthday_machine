package com.ontology2.birthdayMachine;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.ontology2.centipede.shell.CommandLineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.ontology2.sparqlEasy.JenaStatics.*;

@Component("dateDistribution")
public class DateDistribution extends CommandLineApplication {
    @Autowired
    BirthdayModel birthdayModel;

    @Override
    protected void _run(String[] strings) throws Exception {
        ResultSet rs=birthdayModel.dateDistribution();
        while(rs.hasNext()) {
            QuerySolution row=rs.next();
            String when=asString(row,"when");
            Double sumImportance=asDouble(row, "sumImportance");
            Integer count=asInteger(row, "count");
            System.out.println(when+"     "+count+"     "+sumImportance);
        }
    }
}
