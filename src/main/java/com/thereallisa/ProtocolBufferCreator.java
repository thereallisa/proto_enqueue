package com.thereallisa;

import java.util.List;
import java.io.IOException;
import java.util.Arrays;

import com.thereallisa.proto.MessageConstructor;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
/**
* @author lisasmith
* @date Dec 11, 2013
*/
public class ProtocolBufferCreator {
	public static void main(String[] args) {
		if(args.length < 3)
			throw new IllegalArgumentException("Arguments expected: Host IP, Queue Name, Message Args...");
		
		String host = args[0];
		String queue = args[1];
		List<String> protoArgs = Arrays.asList(args).subList(2, args.length);		
		
		Connection connection = null;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		
		try{
			connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			channel.queueDeclare(queue, true, false, false, null);
			channel.basicPublish("", queue, null, MessageConstructor.constructMessage(protoArgs));
			System.out.println("Message Enqueued Successfully");
		}
		catch(IOException e){
			System.out.println("Could not connect to host " + host + " queue " + queue + ": " + e.getMessage());
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}	
		finally{
			try{
				connection.close();
			}
			catch(IOException e){
				System.out.println(e);
			}
		}
	}
}