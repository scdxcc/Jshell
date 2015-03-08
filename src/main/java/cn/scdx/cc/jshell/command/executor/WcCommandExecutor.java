package cn.scdx.cc.jshell.command.executor;

import java.util.Iterator;

import cn.scdx.cc.jshell.command.Command;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterators;

/**
 * @author scdxcc
 * @email scdxcc@163.com
 * @date 14-11-19
 */
public class WcCommandExecutor extends AbstractFileSourceCommandExecutor {
    @Override
    protected Iterator<String> doExecute(Command command, Iterator<String> pipeSource) {
        Preconditions.checkArgument(command.getArgumentList().size() > 0, "wc must have argument");
        Preconditions
                .checkArgument(StringUtils.equals(command.getArgumentList().get(0), "-l"), "argument must name -l");
        return Iterators.singletonIterator(String.valueOf(Iterators.size(pipeSource)));
    }
}
