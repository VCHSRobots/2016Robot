package org.usfirst.frc.team4415.robot;

import java.util.Formatter;
import java.util.Locale;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;

public class TargetComputer extends Thread {
		
	@Override
	public void run() 
	{
		AnalogInput Sonar = new AnalogInput(3);
		SerialPort sp = new SerialPort(9600, SerialPort.Port.kMXP);
		//SerialPort sp = new SerialPort(9600, SerialPort.Port.kOnboard);
		sp.reset();
		sp.setReadBufferSize(20);

			
		int counter; 
		counter = 0;
		while(true)
		{
			String s = sp.readString();
			if(s.length() > 0) {
				SmartDashboard.putString("TC String", s);
			}
			
			counter++;
			StringBuilder sb = new StringBuilder();
			Formatter f = new Formatter(sb, Locale.US);
			f.format("Point: %d\n", counter);
			f.close();
			sp.writeString(sb.toString());
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			SmartDashboard.putDouble("Sonar", Sonar.getVoltage());
		}	
	}
}
