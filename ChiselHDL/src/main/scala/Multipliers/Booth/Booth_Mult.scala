
import chisel3._
import scala._ 
import chisel3.util.experimental.decode.DecodePattern
import chisel3.util.experimental.decode.DecodeField
import chisel3.simulator.PeekPokeAPI.TestableData
import chisel3.util.BitPat
import chisel3.util.experimental.decode.BoolDecodeField
import chisel3.util.experimental.decode.DecodeTable
import circt.stage.ChiselStage

case class Booth_Mult_Param(A_Width: Int, B_Width: Int, Output_Width: Int = B_Width + A_Width)



class Multiplicand(width: Int, number: Bits){
    
    for (i <- 0 until width -1){
        number(i).unary
    }

}

class Multiplier(width: Int, number: Bits){

    val la = number.asBools
    Seq.tabulate(width){ i}
}

case class BoothPattern(bits: String, coeff: Int) extends DecodePattern {
  def bitPat: BitPat = BitPat("b" + bits)
}



object isNegative extends BoolDecodeField[BitPattern]{
    def name: String = "isNegative"
    def genTable(op: BitPattern): BitPat = 
        if (op.coeff < 0)
            y
        else
            n     
}

object IsZero extends BoolDecodeField[BoothPattern] {
  def name = "isZero"
  def genTable(p: BoothPattern) =
    if (p.coeff == 0) y else n
}

object IsDouble extends BoolDecodeField[BoothPattern] {
  def name = "isDouble"
  def genTable(p: BoothPattern) =
    if (p.coeff.abs == 2) y else n
}




class Booth_Mult(parameters: Booth_Mult_Param) extends Module{

    val io = IO(new Bundle{
        val A_number = Input(Bits(parameters.A_Width.W))
        val B_number = Input(Bits(parameters.B_Width.W))
        val output = Input(Bits(parameters.Output_Width.W))
    })

    val bitPatterns = Seq(
        BoothPattern("000",  0),
        BoothPattern("001",  1),
        BoothPattern("010",  1),
        BoothPattern("011",  2),
        BoothPattern("100", -2),
        BoothPattern("101", -1),
        BoothPattern("110", -1),
        BoothPattern("111",  0)
    )

    val bitPatternResponses = Seq(
            isNegative, IsZero, IsDouble
    )

    val boothsMultDecodeTable = new DecodeTable(bitPatterns, bitPatternResponses)
    val result = boothsMultDecodeTable.decode()

