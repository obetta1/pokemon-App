package cmd

import java.nio.file.Path

interface ActionCmd:Cmd {
fun execute(p:Path, args:String)
}