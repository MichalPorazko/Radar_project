package Multipliers.Booth

import chisel3.UInt
import chisel3.Input
import chisel3.util.BitPat

class Qs_comparison extends Module {

    val io = IO(new Bundle {
        val Q0 = Input(UInt(1.W))
        val Qminus1 = Input(UInt(1.W))
    })

    val bitPatterns = List(
        BitPat("b00"), BitPat("b01"), BitPat("b10"), BitPat("b11") 
    )

    case class Burger(
  val name:       String,
  val encoding:   BitPat,
  val hasBuns:    Boolean,
  val hasCheese:  Boolean,
  val hasBacon:   Boolean,
  val hasPatty:   Boolean,
  val hasPickles: Boolean,
  val hasKetchup: Boolean)
    extends DecodePattern {
  override def bitPat: BitPat = encoding
}

object Case00 extends BoolDecodeField[Burger] {
  override def name = "hasBuns"
  override def genTable(burger: Burger): BitPat = {
    if (burger.hasBuns) y else n
  }
}

}