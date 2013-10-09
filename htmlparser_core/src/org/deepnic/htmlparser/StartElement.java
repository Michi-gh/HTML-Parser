package org.deepnic.htmlparser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class StartElement implements HTMLEvent {
	private String data;
	private String name;
	private Map<String, Attribute> attributes;

	public StartElement(String s) {
		data = s;
		name = null;
		attributes = null;
	}

	public Attribute getAttributeByName(String name) {
		if (attributes == null) {
			parse();
		}
		return attributes.get(name);
	}

	public Iterator<String>  getAttributes() {
		if (attributes == null) {
			parse();
		}
		return attributes.keySet().iterator();
	}

	public String getName() {
		if (attributes == null) {
			parse();
		}
		return name;
	}

	@Override
	public String getString() {
		return data;
	}

	@Override
	public String toString() {
		return data;
	}

	@Override
	public Characters asCharacters() {
		throw new IllegalTypeException();
	}

	@Override
	public Comment asComment() {
		throw new IllegalTypeException();
	}

	@Override
	public DTD asDTD() {
		throw new IllegalTypeException();
	}

	@Override
	public EndElement asEndElement() {
		throw new IllegalTypeException();
	}

	@Override
	public EndHTML asEndHTML() {
		throw new IllegalTypeException();
	}

	@Override
	public StartElement asStartElement() {
		return this;
	}

	@Override
	public StartHTML asStartHTML() {
		throw new IllegalTypeException();
	}

	@Override
	public boolean isCharacters() {
		return false;
	}

	@Override
	public boolean isComment() {
		return false;
	}

	@Override
	public boolean isDTD() {
		return false;
	}

	@Override
	public boolean isEndElement() {
		return false;
	}

	@Override
	public boolean isEndHTML() {
		return false;
	}

	@Override
	public boolean isStartElement() {
		return true;
	}

	@Override
	public boolean isStartHTML() {
		return false;
	}

	private void parse() {
		String stripped = HTMLParseUtility.dropDecoration(data, 1, 1);
		if (stripped.endsWith("/")) {
			stripped = HTMLParseUtility.dropDecoration(stripped, 0, 1);
		}
		StringTokenizer tokens = new StringTokenizer(stripped);
		name = tokens.nextToken().toLowerCase();

		attributes = new HashMap<String, Attribute>();
		Map<String, String> prop = HTMLParseUtility.getAttirbutes(stripped.substring(name.length()));
		Set<String> keys = prop.keySet();
		for (String key : keys) {
			attributes.put(key.toLowerCase(), new Attribute(key, prop.get(key)));
		}
	}
}
