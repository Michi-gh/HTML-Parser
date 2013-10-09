package org.deepnic.htmlparser.filter;

import org.deepnic.htmlparser.HTMLEvent;

public class EndElementFilter implements EventFilter {
	private String tagName;
	
	public EndElementFilter(String name) {
		tagName = name;
	}

	@Override
	public boolean accept(HTMLEvent event) {
        if (event.isEndElement()) {
            return tagName.equals(event.asEndElement().getName());
        }
        return false;
	}

}
