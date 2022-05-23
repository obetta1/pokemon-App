package config

import java.io.Reader
import java.util.*


const val DATA_FOLDER = "data.folder"
const val DATA_FILE = "todo-app"
class DataConfig(val dataFilePath: String)

    fun getConfigFromFile(reader:Reader):DataConfig{
        val properties = Properties()
        properties.load(reader)
        val dataFolder = properties.getProperty(DATA_FOLDER, "/tmp")
        return DataConfig("$dataFolder/$DATA_FILE")
    }

