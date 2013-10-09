package org.deepnic.htmlparser;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OutputInputStream extends FilterInputStream {
    private OutputStream outputStream;

    public OutputInputStream(InputStream in, OutputStream out) {
        super(in);
        outputStream = out;
    }
    
    @Override
    public int read() throws IOException {
        int r = super.read();
        outputStream.write(r);
        return r;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int r = super.read(b, off, len);
        if (r > 0) {
            outputStream.write(b, off, r); // some Reader or Stream has some bug?
        }
        return r;
    }
    
    @Override
    public void close() throws IOException {
        super.close();
        outputStream.close();
    }
}
