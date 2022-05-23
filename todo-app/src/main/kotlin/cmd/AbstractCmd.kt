package cmd

import org.apache.commons.cli.CommandLine


abstract class AbstractCmd :Cmd{
    override fun isEnabled(line: CommandLine): Boolean {
        return line.hasOption(getOption().longOpt)

    }

    override fun getOptionValue(line: CommandLine): String {
        return line.getOptionValue(getOption().longOpt)

    }

}