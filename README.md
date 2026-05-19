This project aims to explore FPGA and ASIC concepts through the design of a phased array radar system. Even if it may not be the most desired approach, as ASICs are cost demanding than FPGA's - where possible, a given part of the design will eventually be dedicated for transforming to an ASIC

The project aims at incorporating various concept not necessarily striving for the most optimal design performance but rather focusing on understanding specific aspect through practical implementation.

The basis of this project is the use of open-source tools, which are incomparably better than proprietary tools when it comes to deeper understanding of FPGA or ASIC design flow.

Radar Principles

For radars , the reflected echo from the target provides information about distance, velocity, and angle of elevation of the target with respect to the source. The angle of elevation measurement relies of Direction of Arrival (DOA) estimation, also known as spatial spectrum estimation. There are multiple algorithms helping determining this parameter, like for example the Multiple Signal Classification (MUSIC) algorithm. The algorithm estimates the direction based on multiple signal samples received in an antenna array. \[1,2,3\]

The project will use Scala-based hardware construction frameworks, mainly Chisel and/or SpinalHDL, to describe parameterizable digital hardware. MATLAB, Python or C++ may still be used as reference-modeling and verification environments, but the long-term goal is to express as much synthesizable hardware structure as practical in Scala-based HDL frameworks.

As reference designs, the ADALM-PLUTO evaluation board and the CN0566 Phaser are used, both developed by Analog Devices. In the initial phase, the focus will be primarily on the former, a RF Agile Transceiver, as the foundation of the project.

The design will be centered around the GateMate FPGA from Cologne Chip, potentially in multiple instances. A RISC-V soft-core processor will serve as a manager with a Wishbone interface, as the main bus for communication with other components.

LiteX will primarily serve as a reference for FPGA SoC construction and Wishbone integration, whereas Rocket Chip/Chipyard will serve as a reference for Chisel-based SoC generation. Since Rocket Chip does not support the Wishbone interface,an tempt will be made to implement Wishbone integration with the Rocket SoC gnerator. However as this is a demanding attempt, alternative tool combinations such as LiteX with VexRiscv, are also possible. Alternatively, the bus could be changed to **AXI** or **TileLink**, as these are already implemented.

Another feature of the project will be the integration of Linux, derived from a projects like "seL4 on the RISC-V Rocket Chip" \[4\], although such complex solutions as virtualization of the Guest OS will not be required.

The rest of the tools, mostly comming into use from the synthesis step are to be determined

DSP

Filtering

When analyzing the transmit or the receive signal path of the AD9363 (RF Agile Transceiver, one of the basic components of ADALM-Pluto), one can see that both of these paths incorporate a polyphase FIR filter, respectively a interpolator and a decimator. As FIR filters are based on addition and multiplication operations, for experimental purposes several different types of Adders and multipliers can be used

Adders:

Carry Look Ahead Adder

Carry Safe Adder

Carry Skip Adder

Carry Select Adder

Multipliers

Vedic Multiplier

Booth's Multiplier

References

\[1\] Aaltonen, Tuomas. _FPGA Implementation of MUSIC Direction of Arrival Algorithm Using High-Level Synthesis_. 2023.

\[2\]Sikka, P. (2023). High-level synthesis assisted, low-latency, area- and power-optimized FPGA implementation of MUSIC algorithm for direction-of-arrival estimation. _Sustainable Energy Technologies and Assessments_, _57_, 103201. [https://doi.org/10.1016/j.seta.2023.103201](https://doi.org/10.1016/j.seta.2023.103201)

\[3\] Zhou, S.; Zhou, L. Field Programmable Gate Array (FPGA) Implementation of Parallel Jacobi for Eigen-Decomposition in Direction of Arrival (DOA) Estimation Algorithm. _Remote Sens._ **2024**, _16_, 3892. [https://doi.org/10.3390/rs16203892](https://doi.org/10.3390/rs16203892)

\[4\] [https://www.dornerworks.com/blog/sel4-on-risc-v-rocket-chip](https://www.dornerworks.com/blog/sel4-on-risc-v-rocket-chip/)
