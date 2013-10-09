package org.deepnic.htmlparser.filter;

import org.deepnic.htmlparser.HTMLEvent;

public class StartElementFilter implements EventFilter {
	private String tagName;
	
	public StartElementFilter(String name) {
		tagName = name;
	}

	@Override
	public boolean accept(HTMLEvent event) {
        if (event.isStartElement()) {
            return tagName.equals(event.asStartElement().getName());
        }
        return false;
	}

}
