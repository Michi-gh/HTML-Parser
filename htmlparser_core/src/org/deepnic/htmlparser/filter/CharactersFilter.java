package org.deepnic.htmlparser.filter;

import org.deepnic.htmlparser.HTMLEvent;

public class CharactersFilter implements EventFilter {

	@Override
	public boolean accept(HTMLEvent event) {
		return event.isCharacters();
	}
}
