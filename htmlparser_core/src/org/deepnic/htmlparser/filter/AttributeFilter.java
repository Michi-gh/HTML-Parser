package org.deepnic.htmlparser.filter;

import org.deepnic.htmlparser.Attribute;
import org.deepnic.htmlparser.HTMLEvent;

public class AttributeFilter implements EventFilter {

	private String attrName;
	private String attrValue;

	public AttributeFilter(String name, String value) {
		attrName = name;
		attrValue = value;
	}

	@Override
	public boolean accept(HTMLEvent event) {
		if (event.isStartElement()) {
			Attribute attr = event.asStartElement().getAttributeByName(attrName);
			if (attr == null) {
				return false;
			}
			return attr.getValue().equals(attrValue);
		}
		return false;
	}

}
