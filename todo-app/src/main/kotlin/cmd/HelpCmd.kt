package cmd

import cmd.AbstractCmd
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options

class HelpCmd:AbstractCmd() {
    private val option = Option("h", "help" , true,"displays the help and exit")
     fun execute(t: Options) {
        val formater = HelpFormatter()
        formater.printHelp("java -jar todo.jar", t)

    }

    override fun getOption(): Option {
        return option
    }
}