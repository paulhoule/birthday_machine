package com.ontology2.sparqlEasy;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.NodeFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import static com.hp.hpl.jena.datatypes.xsd.XSDDatatype.*;

public class JenaStatics {
    static final SimpleDateFormat yyyyMMdd=new SimpleDateFormat("yyyy-MM-dd");

    public static Node dateNode(Calendar when) {
        return dateNode(when.getTime());
    }

    public static Node dateNode(Date when) {
        return NodeFactory.createLiteral(yyyyMMdd.format(when),XSDdate);
    }

    public static Resource asResource(RDFNode n) {
        return n.asResource();
    }

    public static Resource asResource(QuerySolution s,String key) {
        return asResource(s.get(key));
    }

    public static Double asDouble(RDFNode n) {
        if(n==null)
            return null;

        return n.asLiteral().getDouble();
    }

    public static Double asDouble(QuerySolution s,String key) {
        return asDouble(s.get(key));
    }

    public static Integer asInteger(RDFNode n) {
        if(n==null)
            return null;

        return n.asLiteral().getInt();
    }

    public static Integer asInteger(QuerySolution s,String key) {
        return asInteger(s.get(key));
    }

    public static String asString(RDFNode n) {
        if(n==null)
            return null;

        return n.asLiteral().getString();
    }

    public static String asString(QuerySolution s,String key) {
        return asString(s.get(key));
    }
}
