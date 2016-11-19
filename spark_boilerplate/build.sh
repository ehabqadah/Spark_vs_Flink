#!/bin/bash


if [ "$#" -ne 1 ];
then
	className="de.kdml.bigdatalab.spark.App" #default class 
  
else
     	className="de.kdml.bigdatalab.spark.$1"
fi


mvn clean package
spark-submit \
--class $className \
--master local[4] \
--jars jars/spark-streaming-kafka-0-8-assembly_2.11-2.0.2.jar \
target/spark_boilerplate-0.0.1-SNAPSHOT.jar
