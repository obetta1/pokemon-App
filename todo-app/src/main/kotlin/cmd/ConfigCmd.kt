package cmd

import config.DataConfig
import config.getConfigFromFile
import createFileIfNotExist
import debug
import org.apache.commons.cli.Option
import java.nio.file.Files
import java.nio.file.Path

class ConfigCmd:AbstractCmd(){
    private  val options = Option("c", "config", true , " set the config file path")
     fun execute(filePath: String): DataConfig {
        val path = createFileIfNotExist(filePath)
         debug("Reading from config '$path'...")
         val config = getConfigFromFile(Files.newBufferedReader(path))
         debug("DataConfig loaded with the following values: $config")
         return config
    }

    override fun getOption(): Option {
        return options
    }
}