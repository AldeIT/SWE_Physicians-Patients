# Software Engineering -- Physicians & Patients

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## Overview
This project was developed to fulfill the needs of two main actors: Medics and Patients. Both categories have pre-registered accounts created by system administrators, and their information will be provided directly (you should find them in model/DB_Model.java). The software development process was supported by the GitHub web platform, utilizing the Git version control system to develop different parts of the code in parallel. Additionally, for remote collaboration, we used the CodeTogether plugin.

## Development Team
- Aldegheri Alessandro
- Venturi Davide
- Zerman Nicolò

This project was developed as the final project for our Software Engineering class at University of Verona



## Development Approach
The chosen development approach was primarily Incremental, based on a Plan Driven organization. The initial phase involved analyzing the specified requirements to divide them into sub-problems and ensure optimal implementation. Use cases and activity diagrams were generated. The code was then written, and significant modifications were accompanied by extensive testing. Refactoring was conducted on the existing code to resolve any issues that arose.

## Documentation
At the end of each version, documentation was produced regarding the implemented features. UML artifacts, including class diagrams and descriptive UML, were collected. You can find these artifacts in the file "DocumentazioneIngegneriaDelSoftware".

## Technology Stack
- Java
- JavaFX

## MVC Pattern
The software was developed using the Model-View-Controller (MVC) architectural pattern, supported by JavaFX libraries. This choice was made because the pattern is based on the clear separation of three components within the software. Each component is assigned a different package. The components are:

- **Model**: Provides methods to access data and various stored information.
- **View**: Responsible for visually representing the model to the user. It is the component with which the user can interact.
- **Controller**: Receives user commands through the View and acts on them, sometimes modifying the state of the other two components.

In general, the separation of MVC components helps ensure that the software is easily maintainable and modifiable.


## Design Patterns Used
In this project, the following design patterns were utilized:

- **Iterator Pattern**: Implicitly used as it is fundamental to Java programming.
- **Facade Pattern**: The `DBModel` class offers methods that abstract the complexity of the system from the controllers.
- **Observer Pattern**: This mechanism is natively implemented in the JavaFX framework components, forming the basis for binding actions in the View to Controller functions.
- **Singleton Pattern**: Adopted for the `DBModel` and `Alert` classes, providing globally accessible objects with a single instance.

## Installation and Usage
1. Clone the repository.
   ```shell
   git clone https://github.com/AldeIT/SWE_Physicians-Patients.git


