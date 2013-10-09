package org.deepnic.htmlparser.filter;

import org.deepnic.htmlparser.HTMLEvent;

public class HasAttributeFilter implements EventFilter {
    private String attrName;
    
    public HasAttributeFilter(String name) {
        attrName = name;
    }

    @Override
    public boolean accept(HTMLEvent event) {
        if (event.isStartElement()) {
            return (event.asStartElement().getAttributeByName(attrName) != null);
        }
        return false;
    }

}
