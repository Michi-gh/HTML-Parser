package org.deepnic.htmlparser;

public interface HTMLEvent {
    public String getString();
    
    public Characters asCharacters();
    public Comment asComment();
    public DTD asDTD();
    public EndElement asEndElement();
    public EndHTML asEndHTML();
    public StartElement asStartElement();
    public StartHTML asStartHTML();
    
    public boolean isCharacters();
    public boolean isComment();
    public boolean isDTD();
    public boolean isEndElement();
    public boolean isEndHTML();
    public boolean isStartElement();
    public boolean isStartHTML();
}
