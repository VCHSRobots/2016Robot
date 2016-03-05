package org.usfirst.frc.team4415.robot;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Locale;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TCGet extends Thread {
	String m_PiNetAddr = "10.44.15.14";  // Address of PI.  Hardwired into network!
	//DatagramSocket m_socket_send = null;
	//DatagramSocket m_socket_recv= null;
	//byte[] m_receiveData = new byte[1024];
	int m_PortCmd = 5800;                   //The Command Port on the Pi.    
	int m_nTry  = 0;
	int m_nFail = 0;
	int m_nTO = 0;
	int m_nGot = 0;
	
	@Override
	public void run() 
	{
		SmartDashboard.putString("TCGet",   "");
		SmartDashboard.putString("TCGet_1", "");
		SmartDashboard.putString("TCGet_2", "");

		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				SmartDashboard.putString("TCGet", "Fatal Error on Sleep! Aborting!");
				return;
			}
			GetIt("/ping HELLO " + m_nTry);
			if(m_nTry % 10 == 0) {
				StringBuilder sb = new StringBuilder();
				Formatter f = new Formatter(sb, Locale.US);
				f.format("IP TCGets Trys/nTOs/Failed: %d, %d, %d.", m_nTry, m_nTO, m_nFail);
				SmartDashboard.putString("TCGet_2",  sb.toString());
				f.close();
			}
			m_nTry++;
		}
	}
	
	// Each time GetIt is called, a new request is sent, and then we wait for a response.
	// We count success and failures.
	public void GetIt(String cmd) {
		
		DatagramSocket clientSocket = null;
		try {
			SmartDashboard.putString("TCGet_1", "Trying...");
	        clientSocket = new DatagramSocket();    
	        clientSocket.setSoTimeout(500);
	        InetAddress IPAddress = InetAddress.getByName(m_PiNetAddr);       
	        byte[] sendData = new byte[1024];       
	        byte[] receiveData = new byte[1024];           
	        sendData = cmd.getBytes();       
	        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, m_PortCmd);       
	        clientSocket.send(sendPacket);       
	        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);       
	        clientSocket.receive(receivePacket);       
	        String response = new String(receivePacket.getData());   
	        int i = response.indexOf('#');
	        if(i >= 0) response = response.substring(0,  i);
	        clientSocket.close();
		
			//if(m_socket_send == null) m_socket_send = new DatagramSocket();
			//m_socket_send.connect(InetAddress.getByName(m_PiNetAddr), m_PortCmd);
			//String s = "/ping HELLO " + m_nTry;
			//DatagramPacket p = new DatagramPacket(s.getBytes(), s.length());
			//m_socket_send.send(p);
			
			//if(m_socket_recv == null) m_socket_recv = new DatagramSocket(m_PortCmd);
			//DatagramPacket receivePacket = new DatagramPacket(m_receiveData, m_receiveData.length);
			//m_socket_recv.receive(receivePacket); 
			//String s_response = new String(receivePacket.getData());
			SmartDashboard.putString("TCGet", response);
			SmartDashboard.putString("TCGet_1", "" + i);
			SmartDashboard.putString("TCGet_2", "");
		}
		catch (java.net.SocketTimeoutException e) {
			m_nTO++;
	        if(clientSocket != null) clientSocket.close();
	        return;
		} catch (UnknownHostException e) {
			m_nFail++;
			SmartDashboard.putString("TCGet",   "ERROR");
			SmartDashboard.putString("TCGet_1", "Failed on getting socket #1.");
			SmartDashboard.putString("TCGet_2", e.getMessage());
	        if(clientSocket != null) clientSocket.close();
			return;
		} 
		catch (IOException e) {
			m_nFail++;
			SmartDashboard.putString("TCGet",   "ERROR");
			SmartDashboard.putString("TCGet_1", "Failed on getting socket #2.");
			SmartDashboard.putString("TCGet_2", e.getMessage());
	        if(clientSocket != null) clientSocket.close();
			return;
		}
		return;
	}
}