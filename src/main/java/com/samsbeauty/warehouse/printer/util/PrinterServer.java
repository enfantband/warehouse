package com.samsbeauty.warehouse.printer.util;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PrinterServer {
	
	public static void send (String message, String url, String port) {		
		ObjectOutputStream out = null;
		String ReqMessage = "";
		String SendMessage = message;

		Socket requestSocket = null;
		try{
			requestSocket = new Socket();
			requestSocket.connect(new InetSocketAddress(url, Integer.parseInt(port)), 1000);
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			ObjectInputStream in; 
			out.flush();
			try{
				out.writeObject(SendMessage);
				in = new ObjectInputStream(requestSocket.getInputStream());
				if(in.available() > 0){
					ReqMessage = (String)in.readObject();
				}
			} catch (EOFException e) {
				e.printStackTrace();
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//4: Closing connection
			try{
				//in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace(System.out);
			}
		}
	}
}
