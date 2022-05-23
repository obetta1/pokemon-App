import cmd.CONFIG
import cmd.HELP
import config.loadApplicationConfig
import org.apache.commons.cli.DefaultParser
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

var DEBUG_ENABLED = false
fun main(args: Array<String>) {
    val applicationConfig = loadApplicationConfig()
    val perser = DefaultParser()
    val line = perser.parse(applicationConfig.getOption(), args)
    when {
        HELP.isEnabled(line) -> {
            HELP.execute(applicationConfig.getOption())
            System.exit(0)
        }
    }
    val homeFolder = System.getProperty("User.home")
    val configPath: String = CONFIG.getOptionValue(line) ?: "$homeFolder/.todo.properties"
    val config = CONFIG.execute(configPath)


    val path = createFileIfNotExist(config.dataFilePath)
    val cmd = applicationConfig.actionCmd.find { it.isEnabled(line) }
    cmd?.execute(path, cmd.getOptionValue(line)?:"")


    println("Hello word")

}
    fun createFileIfNotExist(filepath:String):Path{
        val path = Paths.get(filepath)
        if (!Files.exists(path)){
            Files.createFile(path)
        }
            return path
    }

    fun debug(message:String){
        if (DEBUG_ENABLED){
            println("[DEBUG] $message")
        }
    }
