package com.ontology2.birthdayMachine;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.util.NodeFactoryExtra;
import com.ontology2.centipede.shell.CommandLineApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.ontology2.birthdayMachine.JenaStatics.*;
import static com.hp.hpl.jena.datatypes.xsd.XSDDatatype.*;

@Component("birthdayQuery")
public class RunBirthdayQuery extends CommandLineApplication {
    @Autowired
    SparqlService sparqlService;

    @Override
    protected void _run(String[] strings) throws Exception {
        final Calendar when=new GregorianCalendar(1972,4,24);
        final Model m= sparqlService.model();
        final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        QueryExecution x=sparqlService.executor(sparqlService.query(
                        "prefix ns: <http://rdf.basekb.com/ns/>" +
                        "prefix public: <http://rdf.basekb.com/public/>" +
                        "prefix xsd: <http://www.w3.org/2001/XMLSchema#>"+
                        ""+
                        "SELECT ?s ?eye ?o{" +
                        "   ?s ns:people.person.date_of_birth ?o ." +
                        "   ?s public:subjectiveEye3D ?eye ." +
                        "   FILTER(?o=?when)" +
                        "} ORDER BY DESC(?eye) LIMIT 20",
                new QuerySolutionMap() {{
                    add("when", m.asRDFNode(dateNode(when)));
                }}
        ));

        ResultSet r=x.execSelect();

        while(r.hasNext()) {
            QuerySolution row=r.next(); {
                Resource s=row.get("s").asResource();
                Double eye=row.get("eye").asLiteral().getDouble();
                Object d=(XSDDateTime) row.get("o").asLiteral().getValue();
                System.out.println(eye+"    "+s+"    "+d.getClass());
            }
        }


    }
}
