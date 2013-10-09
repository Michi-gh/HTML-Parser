package org.deepnic.htmlparser;

public class EndElement implements HTMLEvent {
    private String data;
    private String tagName;

    public EndElement(String s) {
        data = s;
        tagName = null;
    }
    
    public String getName() {
        if (tagName == null) {
            tagName = HTMLParseUtility.dropDecoration(data, 2, 1).toLowerCase();
        }
        return tagName;
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
        return this;
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
        return true;
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
