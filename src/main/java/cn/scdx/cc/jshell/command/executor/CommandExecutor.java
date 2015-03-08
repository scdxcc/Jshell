package cn.scdx.cc.jshell.command.executor;

import cn.scdx.cc.jshell.command.Command;
import cn.scdx.cc.jshell.CloseableIterator;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public interface CommandExecutor {
    CloseableIterator<String> execute(Command command);

    CloseableIterator<String> pipeExecute(Command command, CloseableIterator<String> pipeSource);
}