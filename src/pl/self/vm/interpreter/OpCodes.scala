package pl.self.vm.interpreter

object OpCodes {
    object Action {
      final val MOV = 0x00
      final val ADD = 0x01
      final val SUB = 0x02
      final val MUL = 0x03
      final val DIV = 0x04
      final val CMP = 0x10
      final val JMP = 0x11
      final val JNE = 0x12
      final val JE  = 0x13
      final val PRINT = 0x30
      final val PRINT_REG = 0x33
      final val READ = 0x40
      final val AND = 0x50
      final val OR = 0x51
      final val XOR = 0x52
      final val NOT = 0x53
      final val INC = 0x60
      final val DEC = 0x61
    }
    
    object Value {
      final val ERR = -1
      final val NO_VAL=0x09
      final val FAR = 0x11
    }
    
    
}