@echo off

..\..\..\jdk\bin\java -Dlog4j.configuration=file:conf/log4j.xml -jar logstash-alert-extension.jar %*