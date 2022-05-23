

const val SEPARATOR = ";"

 data class Task(val name :String, var status:Boolean = false) {
     override fun toString(): String {
         var done = ""
         if (status){
             done = "Completed"
         }
         return "$name \t $done"
     }

     fun toLine():String{
         return "$name$SEPARATOR$status"
     }
}

fun perseLine(line:String):Task{
    val array = line.split(SEPARATOR)
    if(array.size <2){
        return Task("", false)
    }
    return Task(sanitize(array[0]), sanitize(array[1]).toBoolean())
}

fun sanitize(s:String):String{
    return s.replace("\n", "")
}

