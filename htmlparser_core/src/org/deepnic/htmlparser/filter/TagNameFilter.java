package org.deepnic.htmlparser.filter;

import org.deepnic.htmlparser.HTMLEvent;

public class TagNameFilter implements EventFilter {
    private String tagName;
    
    public TagNameFilter(String name) {
        tagName = name;
    }

    @Override
    public boolean accept(HTMLEvent event) {
        if (event.isStartElement()) {
            return tagName.equals(event.asStartElement().getName());
        }
        if (event.isEndElement()) {
            return tagName.equals(event.asEndElement().getName());
        }
        return false;
    }

}
