package org.usfirst.frc.team4415.robot;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import java.io.SocketConnection;

// This class assumes the setup as follows..

// This class communicates with the PI computer to get
// Targeting Info.

public class TCCom {
	//String m_PiNetName = "pi-4415-tc-1";
	String m_PiNetName = "192.168.0.101";
	DatagramSocket m_socket = null;
	int m_Port = 5800;
	//Socket m_socket = null;
	int counter = 0;
	
	public void SendData() {

		//OutputStream w = null;
		counter = counter + 1;
		SmartDashboard.putString("JavaNetStatus", "I got to here");
		SmartDashboard.putString("JavaNetError",  "No Error yet.");
		try {
			SmartDashboard.putString("JavaNetStatus", "I got to here #2");
			//m_socket = new Socket("192.168.0.104", m_Port);
			if(m_socket == null) m_socket = new DatagramSocket();
			//m_socket.connect(InetAddress.getByName("192.168.0.101"), m_Port);
			m_socket.connect(InetAddress.getByName(m_PiNetName), m_Port);
			String s = "Hello #" + counter;
			DatagramPacket p = new DatagramPacket(s.getBytes(), s.length());
			m_socket.send(p);
			
		}
		catch (UnknownHostException e) {
			SmartDashboard.putString("JavaNetStatus", "Failed on getting socket #1. (" + counter + ")");
			SmartDashboard.putString("JavaNetError", e.getMessage());
			return;
		} catch (IOException e) {
			SmartDashboard.putString("JavaNetStatus", "Failed on getting socket #2.(" + counter + ")");
			SmartDashboard.putString("JavaNetError", e.getMessage());
			return;
		}
		//try {
		//	w = m_socket.getOutputStream();
		//} catch (IOException e) {
		//	SmartDashboard.putString("JavaNetStatus", "Failed on output stream.");
		//	SmartDashboard.putString("JavaNetError", e.getMessage());
		//	return;
		//}
		//try {
		//	w.write("Hello Nathan".getBytes());
		//} catch (IOException e) {
		//	SmartDashboard.putString("JavaNetStatus", "Failed on write.");
		//	SmartDashboard.putString("JavaNetError", e.getMessage());
		//	return;
		//}
		//try {
		//	w.close();
		//}/ catch (IOException e) {
		//	SmartDashboard.putString("JavaNetStatus", "Failed on close.");
		//	SmartDashboard.putString("JavaNetError", e.getMessage());
		//	return;
		//}
		SmartDashboard.putString("JavaNetStatus", "Write Success. (" + counter + ")");
	}
}
