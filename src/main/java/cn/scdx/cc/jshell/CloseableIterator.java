package cn.scdx.cc.jshell;

import java.io.Closeable;
import java.util.Iterator;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public interface CloseableIterator<T> extends Iterator<T>, Closeable {

}
