package cn.scdx.cc.jshell;

import java.io.IOException;

import org.apache.commons.io.LineIterator;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public class CloseableLineIterator implements CloseableIterator<String> {

    private LineIterator lineIterator;

    public CloseableLineIterator(LineIterator lineIterator) {
        this.lineIterator = lineIterator;
    }

    @Override
    public void close() throws IOException {
        this.lineIterator.close();
    }

    @Override
    public boolean hasNext() {
        return this.lineIterator.hasNext();
    }

    @Override
    public String next() {
        return this.lineIterator.next();
    }

    @Override
    public void remove() {
        this.lineIterator.remove();
    }
}
