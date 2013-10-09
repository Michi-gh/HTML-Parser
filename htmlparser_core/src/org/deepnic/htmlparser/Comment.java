package org.deepnic.htmlparser;

public class Comment implements HTMLEvent {
    String data;
    String text;

    public Comment(String s) {
        data = s;
        text = null;
    }

    public String getText() {
        if (text == null) {
            int head = (data.startsWith("<!--")) ? 4 : 1;
            int tail = (data.endsWith("-->")) ? 3 : 1;
            text = HTMLParseUtility.dropDecoration(data, head, tail);
        }
        return text;
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
        return this;
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
        return true;
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
