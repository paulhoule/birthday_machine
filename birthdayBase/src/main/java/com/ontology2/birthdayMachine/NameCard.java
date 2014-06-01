package com.ontology2.birthdayMachine;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ListMultimap;
import com.hp.hpl.jena.graph.Node;

public class NameCard {
    final String id;
    final ImmutableListMultimap<String,String> names;
    final ImmutableListMultimap<String,String> links;

    public NameCard(String id,ImmutableListMultimap<String,String> names,ImmutableListMultimap<String,String> links) {
        this.id=id;
        this.names=names;
        this.links=links;
    }

    public ImmutableListMultimap<String,String> getNames() {
        return names;
    }

    public ImmutableList<String> getNames(String key) {
        return names.get(key);
    }

    public ImmutableListMultimap<String,String> getLinks() {
        return links;
    }

    public ImmutableList<String> getLinks(String key) {
        return links.get(key);
    }

    public String getName() {
        final ImmutableList<String> en = getNames().get("en");
        return en.isEmpty() ? "*no label*" : en.get(0);
    };
}
