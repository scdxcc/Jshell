package cn.scdx.cc.jshell.command.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import cn.scdx.cc.jshell.CloseableIteratorAdaptor;
import cn.scdx.cc.jshell.command.Command;
import org.apache.commons.io.LineIterator;

import com.google.common.base.Preconditions;
import cn.scdx.cc.jshell.CloseableIterator;
import cn.scdx.cc.jshell.CloseableLineIterator;
import cn.scdx.cc.jshell.command.CommandExecuteException;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public abstract class AbstractFileSourceCommandExecutor implements CommandExecutor {

    @Override
    public CloseableIterator<String> execute(Command command) {
        Preconditions.checkNotNull(command, "command can not be null");
        Preconditions.checkNotNull(command.getTarget(), "target can not be null");
        CloseableIterator<String> pipeSource = getSource(command.getTarget());
        return pipeExecute(command, pipeSource);
    }

    @Override
    public CloseableIterator<String> pipeExecute(Command command, CloseableIterator<String> pipeSource) {
        Preconditions.checkNotNull(command, "command can not be null");
        pipeSource = pipeSource == null ? getSource(command.getTarget()) : pipeSource;
        return new CloseableIteratorAdaptor<String>(pipeSource, doExecute(command, pipeSource));
    }

    protected abstract Iterator<String> doExecute(Command command, Iterator<String> pipeSource);

    private CloseableIterator<String> getSource(String target) {
        File file = new File(target);
        try {
            return new CloseableLineIterator(new LineIterator(new BufferedReader(new FileReader(file))));
        } catch (IOException e) {
            throw new CommandExecuteException(e);
        }
    }
}
