# IoT-Button

![iot-button](images/iot-button.jpg?raw=true "iot-button")

This poject consists of an IoT enabled button wired to a NodeMCU (ESP8266) board, that when pressed will send (publish) a message to the other conneted devices (subscribers).

# Getting Started!
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Hardware
* NodeMCU Board or ESP8266
* Push Button
* 10K Ohm Resistor
* Jumper wires
## Software
* Arduino IDE
* ESP8266WiFi Library
* PubSubClient Library


## Installation

### Prepare the Circuit

![circuit](images/circuit.jpg?raw=true "Circuit")
### Download the sketch
```sh
$ git clone https://github.com/kaizoku-619/IoT-Button.git
```
### Install the Libraries

To install a new library into your Arduino IDE you can use the Library Manager (available from IDE version 1.6.2). Open the IDE and click to the "Sketch" menu and then Include Library > Manage Libraries. Then search for the **ESP8266WiFi**  Library, click install and do the same for the **PubSubClient** Library.

### Todos

 - Add More comments

License
----

#### MIT
    This project is licensed under the terms of the MIT license.
**Free Software, Hell Yeah!**
