package pl.self.vm.env

import pl.self.vm.interpreter.OpCodes
import java.lang.reflect.Field

object Reg {
  final val EAX = 0
  final val EBX = 1
  final val ECX = 2
  final val EDX = 3
  final val R1 = 4
  final val R2 = 5
  final val R3 = 6
  final val R4 = 7
  final val FLAGS = 8
}
object Flag {
  final val ZF: Byte = 1
  final val SF: Byte = 1
}

class Environment(val code: Array[Int]) {
  final val REGISTERS = 9
  final val BUFFER_SIZE = 256 - 10
  var ram: Array[Int] = new Array(BUFFER_SIZE)
  var reg: Array[Int] = new Array(REGISTERS)
  var eip = 0
  def set(adr: Byte, value: Int) {
    if (isRegister(adr)) {
      reg(adr) = value
    } else {
      ram(adr - 10) = value
    }
  }
  def get(adr: Byte): Int = {
    if (adr == OpCodes.Value.NO_VAL) {
      return OpCodes.Value.ERR
    } else {
      if (isRegister(adr)) reg(adr) else ram(adr - 10)
    }

  }
  def isFlagSet(flag: Byte): Boolean = {
    return (reg(Reg.FLAGS) & flag) == flag
  }

  def setFlags(value: Int) {
    if (value < 0)
      reg(Reg.FLAGS) |= Flag.SF
    if (value == 0)
      reg(Reg.FLAGS) |= Flag.ZF
  }

  def printRegs() {
    var i = 0
    val fields = Reg.getClass.getDeclaredFields
    fields.filter { f => f.getName.length < 6}.foreach { f =>
      printReg(i,f)
      i+=1
    }
  }
  def printReg(index:Int,f:Field) {
     try {
          val name = f.getName
          f.setAccessible(true)
          printf(" %s  = %d \n", f.getName, reg(index))
        } catch {
          case e: Exception => e.printStackTrace()
        }
  }
  
  
  def jump(adr: Int) {
    jump(adr, () => true)
  }

  def jump(adr: Int, condition: () => Boolean) {
    if (condition()) {
      //trick is that eip will be incremented after
      //so we need to set eip as a previous inst that adr
      eip = adr-1
    }
  }
  private def isRegister(adr: Byte): Boolean = {
    return adr >= Reg.EAX && adr <= Reg.R4
  }
}