package org.usfirst.frc.team4415.robot;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Locale;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AdvertiseIP extends Thread {
	String m_PiNetName = "10.44.15.14";  // Address of PI.  Hardwired into network!
	DatagramSocket m_socket = null;
	int m_Port = 5800;                   // Port that Pi will listen on to figure out who we are.
	String m_OurName="RoboRIO";          // Our name to tell the Pi.
	String m_OurPI = "10.44.15.24";      // FIX THIS -- cannot be hardwired!
	int m_nTry  = 0;
	int m_nFail = 0;
	int m_nSent = 0;
	
	@Override
	public void run() 
	{
		SmartDashboard.putString("AdvertiseIP",   "");
		SmartDashboard.putString("AdvertiseIP_1", "");
		SmartDashboard.putString("AdvertiseIP_2", "");

		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				SmartDashboard.putString("AdvertiseIP_1", "Fatal Error on Sleep!");
				return;
			}
			SendAddress();
			if(m_nTry % 10 == 0) {
				StringBuilder sb = new StringBuilder();
				Formatter f = new Formatter(sb, Locale.US);
				f.format("IP Advertisements Sent/Failed: %d, %d.", m_nSent, m_nFail);
				SmartDashboard.putString("AdvertiseIP",  sb.toString());
				f.close();
			}
			m_nTry++;
		}
	}
	
	// Each time SendAddress is called, a new UDP packet is sent to PI,
	// advertising who we are.  We also count success and failures.
	public void SendAddress() {
		try {
			if(m_socket == null) m_socket = new DatagramSocket();
			m_socket.connect(InetAddress.getByName(m_PiNetName), m_Port);
			String s = m_OurName + ":" + m_OurPI + " " + m_nTry;
			DatagramPacket p = new DatagramPacket(s.getBytes(), s.length());
			m_socket.send(p);
		}
		catch (UnknownHostException e) {
			m_nFail++;
			SmartDashboard.putString("AdvertiseIP_1", "Failed on getting socket #1.");
			SmartDashboard.putString("AdvertiseIP_2", e.getMessage());
			return;
		} catch (IOException e) {
			m_nFail++;
			SmartDashboard.putString("AdvertiseIP_1", "Failed on getting socket #2.");
			SmartDashboard.putString("AdvertiseIP_2", e.getMessage());
			return;
		}
		m_nSent++;
		return;
	}
}