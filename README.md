# log-creator

Testing tool that allows to simulate log creation with configurable rates

## Installation

It's written in Scala so a "modern" JVM is required (7+), [Scala](http://www.scala-lang.org/download/) (only tested with version 2.11.5) and [sbt](http://www.scala-sbt.org/download.html).

> git clone git@pdihub.hi.inet:iris/log-creator.git

> cd log-creator

> sbt compile

## Setup

You can easily change the log configuration by changing [src/main/resources/log4j.properties](src/main/resources/log4j.properties)

## Run

### Default setup

Asumes a random feed of 50 and 10 seconds in each iteration

> sbt run 

### Customized setup

Users can easily adjust the log creation rate by setting three parameters.

> sbt "run feed milliseconds total"

For example:

> sbt "run 20 1000 100000" 

Will use 20 as feed when creating random number of logs in each logging level. It will iterate a complete cycle each second and won't stop until generating a total of 100000 log entries.
