First you need to start zookeeper and then kafka.

<h1>Start zookeeper</h1>
<p>Open a new Command Prompt window.</p>

cd C:/kafka

<b>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties </b>
<br>
This will start Zookeeper with the default settings. Leave this command prompt open.

<h1>Start Kafka</h1>
<p></p>Open a new Command Prompt window.</p>

cd C:/kafka
<br>
<b> .\bin\windows\kafka-server-start.bat .\config\server.properties </b>
