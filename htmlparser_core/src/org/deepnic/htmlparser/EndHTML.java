package org.deepnic.htmlparser;

public class EndHTML implements HTMLEvent {

    @Override
    public String getString() {
        return "";
    }

	@Override
	public String toString() {
		return "End of HTML";
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
        return this;
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
        return true;
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
