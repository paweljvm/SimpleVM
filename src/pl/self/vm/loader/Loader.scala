package pl.self.vm.loader

import scala.io.Source
import java.io.IOException

object Loader {
  
  final val DEFAULT_SEPARATOR=" "
  
  
  def load(fileName:String,opcodeSeparator:String = DEFAULT_SEPARATOR): Array[Int] = {
    
    val bufferedSource = Source.fromFile(fileName)
    try {
     return bufferedSource.getLines()
    .map { line => line.split(opcodeSeparator) }
    .flatten
    .map {v => Integer.decode(v).intValue() }
    .toArray
    } finally {
      bufferedSource.close()
    }
    return Array(0)
  }
}