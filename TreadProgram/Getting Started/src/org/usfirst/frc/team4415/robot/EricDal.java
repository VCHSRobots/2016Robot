package org.usfirst.frc.team4415.robot;

import java.util.Formatter;
import java.util.Locale;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EricDal extends Thread {

	@Override
	public void run() 
	{
		int counter; 
		counter = 0;
		while(true)
		{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {}
			counter++;
			StringBuilder sb = new StringBuilder();
			Formatter f = new Formatter(sb, Locale.US);
			f.format("Hello %d\n", counter);
			SmartDashboard.putString("Test String", sb.toString());
		}
		
	}
	
	
}
