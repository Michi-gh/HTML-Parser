package org.deepnic.htmlparser.filter;

import org.deepnic.htmlparser.HTMLEvent;

public class NullFilter implements EventFilter {

    @Override
    public boolean accept(HTMLEvent event) {
        return true;
    }
}
