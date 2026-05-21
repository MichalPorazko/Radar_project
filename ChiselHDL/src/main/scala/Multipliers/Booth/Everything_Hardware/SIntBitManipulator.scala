package Multipliers.Booth.Everything_Hardware

import chisel3._
import chisel3.util._

class SIntBitManipulator(width: Int) extends Module {
  val io = IO(new Bundle {
    val number = Input(SInt(width.W))
    val isPositive = Output(Bool())
    val isNegative = Output(Bool())
    val bits = Output(Vec(width, Bool()))
    val onesCount = Output(UInt(log2Ceil(width+1).W))
  })

  // 1. Check sign
  io.isNegative := io.number(width-1)
  io.isPositive := !io.number(width-1)

  // 2. Convert to Vec[Bool]
  io.bits := VecInit(io.number.asBools)

  // 3. Count the number of '1's
  io.onesCount := PopCount(io.number.asUInt)
}