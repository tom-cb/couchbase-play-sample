#!/bin/bash

yum -y install java-1.8.0-openjdk-devel

wget http://www.gtlib.gatech.edu/pub/apache//jmeter/binaries/apache-jmeter-3.3.tgz

tar -zxvf apache-jmeter-3.3.tgz



#~/apache-jmeter-3.3/bin/jmeter -n -t ~/couchbase-play-sample/cb-scala-play.jmx

