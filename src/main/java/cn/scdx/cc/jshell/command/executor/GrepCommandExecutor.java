package cn.scdx.cc.jshell.command.executor;

import java.util.Iterator;

import cn.scdx.cc.jshell.command.Command;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public class GrepCommandExecutor extends AbstractFileSourceCommandExecutor {
    @Override
    protected Iterator<String> doExecute(Command command, Iterator<String> pipeSource) {
        Preconditions.checkArgument(command.getArgumentList().size() > 0, "grep keyword does not exist");
        final String keyword = command.getArgumentList().get(0);
        return Iterators.filter(pipeSource, new Predicate<String>() {
            @Override
            public boolean apply(String line) {
                return line.contains(keyword);
            }
        });
    }
}
