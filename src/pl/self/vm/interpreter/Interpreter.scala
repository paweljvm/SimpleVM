package pl.self.vm.interpreter

import pl.self.vm.env.Environment
import pl.self.vm.env.Reg
import pl.self.vm.interpreter.OpCodes.Action;
import pl.self.vm.env.Flag

class Interpreter(val env:Environment) {
  
   def run() {
     env.eip = 0
     while(env.eip < env.code.length){
       exec(env.code(env.eip))
       env.eip = env.eip+1
     }
   }
   def exec(opcode:Int) {
     val action = ((opcode >> 24) & 0xFF).toByte;
     val oper1 = ((opcode >> 16) & 0xFF).toByte;
     val oper2 =((opcode >> 8) & 0xFF).toByte;
     val const = ((opcode ) & 0xFF).toByte;
     
    var value =env.get(oper2)
    if(value == OpCodes.Value.ERR)
           value = const
    val far = const == OpCodes.Value.FAR;
    val jmpAddr = if (far ) oper1 << 8  + oper2 else oper1
    
     action match {
       case Action.MOV => env.set(oper1,value)
       case Action.ADD => env.set(oper1,env.get(oper1)+value)
       case Action.SUB => env.set(oper1,env.get(oper1)-value)
       case Action.MUL => env.set(oper1,env.get(oper1)*value)
       case Action.DIV => env.set(oper1,env.get(oper1)/value)
       case Action.CMP => env.setFlags(env.get(oper1) - value)
       case Action.JMP => env.jump(jmpAddr)
       case Action.JNE => env.jump(jmpAddr, () => !env.isFlagSet(Flag.ZF))
       case Action.JE  => env.jump(jmpAddr, () => env.isFlagSet(Flag.ZF))
       case Action.AND => env.set(oper1,env.get(oper1) & value)
       case Action.OR =>  env.set(oper1,env.get(oper1) | value)
       case Action.XOR => env.set(oper1,env.get(oper1) ^ value)
       case Action.NOT => env.set(oper1,~env.get(oper1))  
       case Action.PRINT_REG => env.printRegs()
       case Action.PRINT => printf("%d ",env.get(oper1))
       case Action.INC => env.set(oper1,env.get(oper1)+1) 
       case Action.DEC => env.set(oper1,env.get(oper1)-1)
     }

   }
}
