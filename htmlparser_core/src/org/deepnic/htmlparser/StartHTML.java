package org.deepnic.htmlparser;

import java.nio.charset.Charset;
import java.util.Map;

public class StartHTML implements HTMLEvent {
	private static final String DEFAULT_VERSION = "1.0";
	private static final String DEFAULT_CHARSET = "UTF-8";

	private String data;
	private Charset charset;
	private String version;

	public StartHTML(String s) {
		data = s;
		charset = null;
		version = null;
	}

	public StartHTML() {
		data = "";
		charset = Charset.forName(DEFAULT_CHARSET);
		version = DEFAULT_VERSION;
	}

	public Charset getCharacterEncodingScheme() {
		if (charset == null) {
			parse(data);
		}
		return charset;
	}

	public boolean isStandalone() {
		return true;
	}

	public boolean standaloneSet() {
		return isStandalone();
	}

	public String getVersion() {
		if (version == null) {
			parse(data);
		}
		return version;
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
		return this;
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
		return true;
	}

	private void parse(String str) {
		if (data.length() == 0) {
			charset = Charset.forName(DEFAULT_CHARSET);
			version = DEFAULT_VERSION;
		}
		Map<String, String> attrs = HTMLParseUtility.getAttirbutes(HTMLParseUtility.dropDecoration(data, 2, 2));
		version = attrs.get(HTMLParseUtility.VERSION_KEY);
		charset = Charset.forName(attrs.get(HTMLParseUtility.ENCODING_KEY));
	}
}