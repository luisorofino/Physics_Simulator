# 3D Physics Simulator

A robust Java application designed to simulate 3D particles in motion. The engine develops a vector field and applies various physical laws to observe complex particle interactions in real-time.

## Overview

This project implements a physics engine capable of simulating the dynamics of bodies in a 3D space. It utilizes **Design Patterns** (such as Builder and Factory) to create a flexible architecture where different physical laws and body types can be easily added and configured.

The simulation calculates trajectories based on:
* Newton's Law of Universal Gravitation.
* Vector fields for position, velocity, and acceleration.
* Custom force laws.

## Project Structure

The source code is organized as follows:

* `src/simulator/model`: Contains the core logic (Body, PhysicsSimulator, ForceLaws).
* `src/simulator/factories`: Implementation of Builder/Factory patterns to generate objects from JSON or configuration.
* `src/simulator/control`: Manages the flow of the simulation (Controller).
* `src/simulator/launcher`: Entry point of the application (Main class).

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
**Author:** [Luis Orofino]
