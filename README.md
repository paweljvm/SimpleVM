# SimpleVM
Very Simple Virtual machine in Scala

This VM contains following:
- 10 registers EAX EBX ECX EDX R1 R2 R3 R4 FLAGS eip
- 2 flags ZF SF
- ram array 246

Run with code file path argument

Example code file is factorial12 which calculates factorial for 12.

File should contains opcodes separated by space, opcode contains following:
- one byte for  Action {
      final val MOV = 0x00
      final val ADD = 0x01
      final val SUB = 0x02
      final val MUL = 0x03
      final val DIV = 0x04
      final val CMP = 0x10
      final val JMP = 0x11 // oper1 refers to position in code f.e. 0x11020000 jumps to third operand 
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
- one byte operand 1 - >=0 && <=7 <- points to register >=10 points to ram array
- one byte operand 2 -||- if 09 - no val then const will be used as source
- one byte const 

Max jump value is FFFF in order to jump more than FF set 0x11 for const
