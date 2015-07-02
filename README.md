# log-creator
Testing tool that allows to simulate log creation with configurable rates



## Installation
It's written in Scala so a "modern" JVM is required (7+), scala (only tested with version 2.11.5) and sbt.

1. Clone repo
2. cd log-creator
3. sbt compile

## Setup
You can easily change the log configuration by changing src/main/resources/Log4j.properties

## Run

### Default setup

Asumes a random feed of 50 and 10 seconds in each iteration

sbt run 

### Customized setup
Allows user to customize the log creation rate

sbt "run feed milliseconds"

For example:

sbt "run 20 1000" 

will use 20 as feed when creating random number of logs in each log level

will iterate a complete cycle each second

Adjusting both paramters users can easily customize the log creation rate.
