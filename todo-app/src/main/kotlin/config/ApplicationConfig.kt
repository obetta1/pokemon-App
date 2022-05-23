package config

import cmd.*
import org.apache.commons.cli.Options
import java.util.*

class ApplicationConfig(val version:String, val command:List<Cmd>, val actionCmd : List<ActionCmd>) {
    fun getOption(): Options {
        val options = Options()
        command.forEach { options.addOption(it.getOption()) }
        return options
    }
}

fun loadApplicationConfig():ApplicationConfig{
    val properties = Properties()
    properties.load(ClassLoader.getSystemResourceAsStream("application.properties"))
    val actionCmd : List<ActionCmd> = listOf(
        ADDTASK,
        LISTTASK,
        MARKASDONE
    )
    val command = actionCmd + listOf(
        CONFIG,
        HELP
    )
    return ApplicationConfig(properties.getProperty("version"), command, actionCmd)
}