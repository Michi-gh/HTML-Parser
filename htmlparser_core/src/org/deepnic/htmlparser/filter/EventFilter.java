package org.deepnic.htmlparser.filter;

import org.deepnic.htmlparser.HTMLEvent;

public interface EventFilter {
    public boolean accept(HTMLEvent event);
}
