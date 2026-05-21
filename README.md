# FPGA-Based Phased Array Radar Project

This repository documents a long-term learning project focused on FPGA and ASIC concepts through the design of a phased array radar system.

The goal is not to build the most optimized radar implementation from the beginning. Instead, the project is intended as a practical framework for exploring digital design, FPGA SoC construction, open-source EDA tools, RISC-V soft cores, DSP blocks, bus interconnects, and selected ASIC-oriented design-flow concepts.

## Motivation

A phased array radar system is a useful learning target because it combines many areas of digital hardware design:

- high-throughput signal processing,
- arithmetic blocks such as adders and multipliers,
- memory and streaming architectures,
- FPGA-based control logic,
- bus interconnects,
- RISC-V soft-core integration,
- software/hardware co-design,
- and, in selected parts, possible ASIC-oriented implementation experiments.

The project is intentionally broad. Some parts may be implemented only as experiments or reference blocks, while others may later become parts of a more complete radar-oriented FPGA system.

## Open-source design philosophy

A major assumption of the project is the use of open-source tools wherever practical. Open-source flows make it easier to study what happens inside the design process: from hardware description, through synthesis, to implementation and verification.

The project is therefore not only about creating hardware modules, but also about understanding the tools and abstractions used to build them.

## Radar background

In radar systems, the reflected echo from a target can provide information about:

- distance,
- velocity,
- and angle of arrival / elevation.

Angle estimation in antenna arrays is related to Direction of Arrival (DOA) estimation, also called spatial spectrum estimation. Algorithms such as MUSIC can estimate the incoming signal direction based on multiple received signal samples from an antenna array.

## Reference platforms

The project uses Analog Devices reference platforms as architectural inspiration:

- **ADALM-PLUTO** - an RF Agile Transceiver platform used as the initial reference point,
- **CN0566 Phaser** - a phased array reference design useful for studying beamforming and radar-related architecture.

The initial focus is mainly on ADALM-PLUTO and the AD936x transceiver family.

## Target FPGA / SoC direction

The planned digital architecture is centered around the **GateMate FPGA** from Cologne Chip.

A RISC-V soft-core processor is planned to act as a manager for configuration and control. The preferred bus direction is currently **Wishbone**, but alternative interconnects such as **AXI** or **TileLink** may also be considered depending on the selected SoC generator and integration complexity.

Possible SoC construction paths include:

- **LiteX + VexRiscv** as a practical open-source FPGA SoC direction,
- **Rocket Chip / Chipyard** as a reference for Chisel-based SoC generation,
- possible experimentation with Wishbone integration around Rocket Chip, although this is expected to be more demanding because Rocket Chip is based around TileLink rather than Wishbone.

## HDL and modeling direction

The project aims to use Scala-based hardware construction frameworks, mainly:

- **Chisel HDL**,
- and potentially **SpinalHDL**.

MATLAB, Python, or C++ may still be used for reference models, verification, and algorithm exploration. However, the long-term goal is to express as much synthesizable hardware structure as practical in Scala-based HDL frameworks.

## DSP and arithmetic building blocks

Radar and communication systems depend heavily on digital signal processing. FIR filters, FFTs, beamforming, and DOA estimation all require arithmetic structures such as adders, multipliers, and complex-number datapaths.

For this reason, the repository also contains lower-level arithmetic experiments. These are not separate from the radar goal; they are used to understand the building blocks that eventually appear inside DSP and FPGA signal-processing pipelines.

### Implemented / explored components

- [Booth's Multiplier in Chisel HDL](https://github.com/MichalPorazko/Radar_project/tree/main/ChiselHDL/src/main/scala/Multipliers/Booth)
- [Carry Look-Ahead Adder in Verilog](https://github.com/MichalPorazko/Radar_project/tree/main/Verilog/Adders/Look%20Ahead%20Carry%20Adder)
- [Vedic Multiplier in Verilog](https://github.com/MichalPorazko/Radar_project/tree/main/Verilog/Multipliers/Vedic%20Multiplier)

Other arithmetic blocks and DSP-oriented modules may be added as the project develops.

## Linux and software direction

Another long-term topic is the integration of a Linux-capable or microkernel-based software layer on top of a RISC-V soft-core system. Projects such as seL4 on RISC-V Rocket Chip are used as inspiration, although full virtualization or a complex guest-OS environment is not required for the initial stages.

## Current status

The project is in an early exploratory phase. The repository currently serves as a place to collect design assumptions, experiments, arithmetic blocks, HDL implementations, and notes related to the future radar architecture.

The main value of the project at this stage is educational: it is used to study how FPGA, RISC-V, bus interconnects, DSP blocks, and open-source hardware design tools can fit together in a realistic system-level context.

## References

1. Aaltonen, T. *FPGA Implementation of MUSIC Direction of Arrival Algorithm Using High-Level Synthesis*. 2023.
2. Sikka, P. *High-level synthesis assisted, low-latency, area- and power-optimized FPGA implementation of MUSIC algorithm for direction-of-arrival estimation*. Sustainable Energy Technologies and Assessments, 57, 103201, 2023. https://doi.org/10.1016/j.seta.2023.103201
3. Zhou, S.; Zhou, L. *Field Programmable Gate Array (FPGA) Implementation of Parallel Jacobi for Eigen-Decomposition in Direction of Arrival (DOA) Estimation Algorithm*. Remote Sensing, 16, 3892, 2024. https://doi.org/10.3390/rs16203892
4. DornerWorks. *seL4 on the RISC-V Rocket Chip*. https://www.dornerworks.com/blog/sel4-on-risc-v-rocket-chip/
