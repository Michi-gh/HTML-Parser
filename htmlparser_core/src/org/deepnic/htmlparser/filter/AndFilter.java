package org.deepnic.htmlparser.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.deepnic.htmlparser.HTMLEvent;

public class AndFilter implements EventFilter {
    List<EventFilter> filters;
    
    public AndFilter(EventFilter... f) {
        filters = new ArrayList<EventFilter>();
        filters.addAll(Arrays.asList(f));
    }

    @Override
    public boolean accept(HTMLEvent event) {
        for (EventFilter f : filters) {
            if (!f.accept(event)) {
                return false;
            }
        }
        return true;
    }
}
