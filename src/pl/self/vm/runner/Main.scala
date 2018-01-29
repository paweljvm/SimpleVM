package pl.self.vm.runner

import java.util.Scanner
import pl.self.vm.interpreter.Interpreter
import pl.self.vm.env.Environment
import pl.self.vm.loader.Loader
import java.io.IOException

object Main {

  def main(args: Array[String]) {
    if (args.length == 0) {
      println("Usage <code_file> !")
      return
    }
    val fileName = args(0)
    try {
      val loadedCode = Loader.load(fileName)
      val interpreter = new Interpreter(new Environment(loadedCode))
      interpreter.run()
    } catch {
      case ioe: IOException => printf("Cannot load file %s \n", fileName)
      case nfe: NumberFormatException => printf("Cannot convert file content to code array \n")
      case e: Exception => printf("Unspecified error %s \n", e.getMessage)
    }

  }
}