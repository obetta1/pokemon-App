package cmd

import Task
import org.apache.commons.cli.Option
import java.nio.file.Path

class AddTaskCmd:AbstractCmd(),ActionCmd {
    private val option = Option("a", "add", true, "add task to the list")
    override fun execute(p: Path, args: String) {
        p.toFile().appendText("" + Task(args).toLine())

    }

    override fun getOption(): Option {
        return option
    }
}