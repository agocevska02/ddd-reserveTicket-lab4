First you need to start zookeeper and then kafka.

Start zookeeper
Open a new Command Prompt window.

cd C:/kafka

<b>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties <b>
This will start Zookeeper with the default settings. Leave this command prompt open.

Start Kafka
Open a new Command Prompt window.

cd C:/kafka
<b> .\bin\windows\kafka-server-start.bat .\config\server.properties <b>
