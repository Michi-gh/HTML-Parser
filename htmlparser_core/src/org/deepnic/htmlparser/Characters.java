package org.deepnic.htmlparser;

public class Characters implements HTMLEvent {
    private String data;

    public Characters(String s) {
        data = s;
    }
    
    public String getData() {
        return getString();
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
        return this;
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
        return true;
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
