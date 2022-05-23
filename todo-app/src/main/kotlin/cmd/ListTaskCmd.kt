package cmd

import Task
import org.apache.commons.cli.Option
import perseLine
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class ListTaskCmd:AbstractCmd(), ActionCmd {
    private val option = Option("l", false, "list all the tas")

    override fun execute(p: Path, args: String) {
        val task = arrayListOf<Task>()
        val stream = Files.newBufferedReader(p)
        stream.buffered().lines().forEach { lines ->task.add(perseLine(lines))}
        task.forEach { println(it) }
    }

    override fun getOption(): Option {
        return option
    }

}