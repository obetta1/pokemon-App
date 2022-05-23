package cmd


import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Option

interface Cmd {
    fun getOption(): Option
    fun isEnabled(line: CommandLine):Boolean
    fun getOptionValue(line :CommandLine):String
}

val LISTTASK = ListTaskCmd()
val ADDTASK = AddTaskCmd()
val MARKASDONE = MarkAsDoneCmd()
val HELP = HelpCmd()
val CONFIG = ConfigCmd()