package cn.scdx.cc.jshell;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public class CloseableIteratorAdaptor<T> implements CloseableIterator<T> {
    private CloseableIterator<T> original;
    private Iterator<T> piped;

    public CloseableIteratorAdaptor(CloseableIterator<T> original, Iterator<T> piped) {
        this.original = original;
        this.piped = piped;
    }

    @Override
    public void close() throws IOException {
        original.close();
    }

    @Override
    public boolean hasNext() {
        return piped.hasNext();
    }

    @Override
    public T next() {
        return piped.next();
    }

    @Override
    public void remove() {
        piped.remove();
    }
}
