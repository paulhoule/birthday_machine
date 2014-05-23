package com.ontology2.birthdayMachine;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.graph.NodeFactory;

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
}
