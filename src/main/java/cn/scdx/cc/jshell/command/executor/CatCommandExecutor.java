package cn.scdx.cc.jshell.command.executor;

import java.util.Iterator;

import cn.scdx.cc.jshell.command.Command;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public class CatCommandExecutor extends AbstractFileSourceCommandExecutor {

    @Override
    protected Iterator<String> doExecute(Command command, Iterator<String> pipeSource) {
        return pipeSource;
    }
}
