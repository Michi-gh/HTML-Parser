package org.deepnic.htmlparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.deepnic.htmlparser.filter.EventFilter;
import org.deepnic.htmlparser.filter.NullFilter;

public class HTMLEventReader implements Iterator<HTMLEvent> {
    private Charset charset;
    private BufferedReader reader;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Reader srcReader;
    
    private boolean isEnded;
    private boolean nextEnd;
    private String restString;
    private EventFilter filter;
    
    
    public HTMLEventReader(InputStream in, Charset ch) {
        inputStream = in;
        charset = ch;
        srcReader = null;
        isEnded = false;
        nextEnd = false;
        outputStream = null;
        reader = null;
        filter = new NullFilter();
    }
    
    public HTMLEventReader(InputStream in) {
        this(in, Charset.defaultCharset());
    }
    
    public HTMLEventReader(Reader re) {
        inputStream = null;
        charset = null;
        srcReader = re;
        isEnded = false;
        nextEnd = false;
        outputStream = null;
        reader = null;
        filter = new NullFilter();
    }

    public void setOutputStream(OutputStream out) {
        outputStream = out;
    }
    
    @Override
    public boolean hasNext() {
        return !isEnded;
    }
    
    @Override
    public HTMLEvent next() {
        return nextEvent();
    }
    
    public HTMLEvent nextEvent() {
        if (isEnded) {
            throw new NoSuchElementException();
        }
        HTMLEvent ev = nonFilteredNextEvent();
        return (filter.accept(ev) || ev.isEndHTML()) ? ev : nextEvent();
    }
    
    private HTMLEvent nonFilteredNextEvent() {
        if (nextEnd) {
            isEnded = true;
            return new EndHTML();
        }
        try {
            if (reader == null) {
            	if (srcReader == null) {
                    InputStream ins = (outputStream == null) ? inputStream : new OutputInputStream(inputStream, outputStream);
                    reader = new BufferedReader(new InputStreamReader(ins, charset));
            	} else {
            		reader = new BufferedReader(srcReader);
            	}
                String line = reader.readLine();
                StringBuffer sBuffer = new StringBuffer(line);
                sBuffer.append(System.getProperty("line.separator"));
                if (line.trim().startsWith("<?")) {
                    int pos = -1;
                    while ((pos = sBuffer.indexOf(">")) < 0) {
                        sBuffer.append(reader.readLine());
                        sBuffer.append(System.getProperty("line.separator"));
                    }
                    pos++;
                    String xmlDeclaration = sBuffer.substring(0, pos);
                    restString = sBuffer.substring(pos);
                    return new StartHTML(xmlDeclaration);
                }
                restString = line;
                return new StartHTML();
            }

            StringBuffer sBuffer = new StringBuffer();
            if ((restString == null) || (restString.length() == 0)) {
                String line = reader.readLine();
                if (line == null) {
                    reader.close();
                    isEnded = true;
                    return new EndHTML();
                }
                sBuffer.append(line);
                sBuffer.append(System.getProperty("line.separator"));
            } else {
                sBuffer.append(restString);
            }
            
            int pos = -1;
            boolean inTag = (sBuffer.charAt(0) == '<'); 
            if (inTag) {
                int p = 0;
                String qMark = null;
                while (pos  < 0) {
                    int endPosition = sBuffer.indexOf(">", p);
                    int singleQPos = sBuffer.indexOf("'", p);
                    int doubleQPos = sBuffer.indexOf("\"", p);
                    switch (whichIsFirst(endPosition, singleQPos, doubleQPos)) {
                    case 0: // ">" is first occurence
                        if (qMark == null) {
                            pos = endPosition;
                        } else {
                            p = endPosition + 1;
                        }
                        break;
                        
                    case 1: // "'" is first occurence
                        p = singleQPos + 1;
                        if (qMark == null) {
                            qMark = "'";
                        } else if (qMark.equals("'")) {
                            qMark = null;
                        }
                        break;
                        
                    case 2: // "\"" is first occurence
                        p = doubleQPos + 1;
                        if (qMark == null) {
                            qMark = "\"";
                        } else if (qMark.equals("\"")) {
                            qMark = null;
                        }
                        break;
                        
                    default: // none of them is found
                        String line = null;
                        if (!nextEnd && (line = reader.readLine())!= null) {
                            sBuffer.append(line);
                            sBuffer.append(System.getProperty("line.separator"));
                        } else {
                            reader.close();
                            nextEnd = true;
                        }
                    }
                    if (nextEnd) {
                        break;
                    }
                }
                if (nextEnd) {
                    return new Characters(sBuffer.toString());
                }
                
                pos++;
                
                String elementStr = sBuffer.substring(0, pos);
                restString = sBuffer.substring(pos);
                inTag = false;
                if (elementStr.length() <= 2) {
                    return new Characters(elementStr);
                }
                if (elementStr.charAt(1) == '!') {
                    if (elementStr.substring(2).startsWith("DOCTYPE")) {
                        return new DTD(elementStr);
                    } // else
                    return new Comment(elementStr);
                }
                if (elementStr.charAt(1) == '/') {
                    return new EndElement(elementStr);
                }
                return new StartElement(elementStr);
            } else {
                while ((pos = sBuffer.indexOf("<")) < 0) {
                    String line = reader.readLine();
                    if (line == null) {
                        reader.close();
                        nextEnd = true;
                        break;
                    }
                    sBuffer.append(line);
                    sBuffer.append(System.getProperty("line.separator"));
                }
                if (pos < 0) { // next "<" has not found
                    pos = sBuffer.length();
                }
                restString = sBuffer.substring(pos);
                return new Characters(sBuffer.substring(0, pos));
            }
        } catch (IOException e) {
            // TODO some io error procedure
            System.out.println("IOException");
            e.printStackTrace();
        }
        return null;
    }

    private int whichIsFirst(int... pos) {
        int minPos = -1;
        for (int i = 0 ; i < pos.length ; i++) {
            if (pos[i] < 0) {
                continue;
            }
            if (minPos < 0) {
                minPos = i;
            } else {
                if (pos[i] < pos[minPos]) {
                    minPos = i;
                }
            }
        }
        return minPos;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
    public void setFilter(EventFilter f) {
        filter = f;
    }
    
    public void close() throws IOException {
    	if (reader != null) {
    		reader.close();
    	}
    }
}
