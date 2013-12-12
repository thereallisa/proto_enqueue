# Proto Buffer Enqueuer

This program is written in Java and uses Maven.  It gives you the ability to construct and enqueue protocol buffers to a RabbitMQ queue. It was written to enqueue one simple type of message with three values which are specified via command line along with the RabbitMQ host and queue name.

PRs welcome :)

## How do I use this for my own message format?

This project uses Google's Protocol Buffers:
[https://developers.google.com/protocol-buffers/docs/javatutorial](https://developers.google.com/protocol-buffers/docs/javatutorial)

In order to queue a RabbitMQ message with your custom format you will first need to follow the Google insructions (see above link) for:

* Define your Protocol format
* Compiling Your Protocol Buffers

Next:

* Place your .proto and resulting .java files in src/main/java/com/thereallisa/proto
* Edit MessageConstructor.java to build the appropriate message with the given arguments.

##Compiling

mvn clean install -P default

##Running Command Line example
The following example is for the default Protocol Buffer files (message.proto & JobMessageProto.java)  
arg0 = RabbitMQ host  
arg1 = RabbitMQ queue name  
arg2 = User ID  
arg3 = Report ID  
arg4 = File Location  

java -cp .:/workspace/proto_enqueue/target/lib/amqp-client-3.2.2.jar:/workspace/proto_enqueue/target/lib/protobuf-java-2.5.0.jar com.thereallisa.ProtocolBufferCreator 127.0.0.1 myQueue 1 1 fileLocation
