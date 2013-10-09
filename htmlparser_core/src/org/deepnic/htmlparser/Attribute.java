package org.deepnic.htmlparser;

public class Attribute implements HTMLEvent {
	private String data;
	private String name;
	private String value;

	public Attribute(String n, String v) {
		name = n.toLowerCase();
		value = v;
		data = n + " = \"" + v.replace("\"", "&quot;");
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
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
		throw new IllegalTypeException();
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
		return false;
	}

	@Override
	public boolean isStartHTML() {
		return false;
	}
}
