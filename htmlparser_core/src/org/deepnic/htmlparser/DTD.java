package org.deepnic.htmlparser;


public class DTD implements HTMLEvent {
    private String data;

    public DTD(String s) {
        data = s;
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
        return this;
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
        return true;
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
