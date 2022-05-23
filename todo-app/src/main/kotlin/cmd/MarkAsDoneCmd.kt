package cmd

import Task
import org.apache.commons.cli.Option
import perseLine
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class MarkAsDoneCmd:AbstractCmd(),ActionCmd {
    private  val option = Option("m", "markAsDone",true,"mark a task as done" )
    override fun execute(p: Path, args: String) {
        val task = Task(args)
        val tasks = p.toFile().readLines().map { perseLine(it) }

            tasks.filter {  it.name == task.name}.forEach{it.status = true}
        File(p.toString()).printWriter().use { out ->
            tasks.forEach { out.println(it.toLine()) }
        }
            }





    override fun getOption(): Option {
        return option
    }
}