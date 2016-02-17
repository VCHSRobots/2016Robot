// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4416.RobotSensor.subsystems;

import org.usfirst.frc4416.RobotSensor.RobotMap;
import org.usfirst.frc4416.RobotSensor.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController rightFront = RobotMap.driveTrainRightFront;
    SpeedController leftFront = RobotMap.driveTrainLeftFront;
    SpeedController leftRear = RobotMap.driveTrainLeftRear;
    SpeedController rightRear = RobotMap.driveTrainRightRear;
    RobotDrive robotDrive4 = RobotMap.driveTrainRobotDrive4;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new ArcadeDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveForward() {
    	
    	rightFront.set(.75);
    	leftFront.set(-.75);
    	leftRear.set(-.75);
    	rightRear.set(.75);
    	
    }
 
    public void driveBackward() {
    	
    	rightFront.set(-.75);
    	leftFront.set(.75);
    	leftRear.set(.75);
    	rightRear.set(-.75);	
    	
    }
    
    public void stop() {
    	
    	rightFront.set(0);
    	leftFront.set(0);
    	leftRear.set(0);
    	rightRear.set(0);	
    	
    }
    
/*    public void arcadeDrive(Joystick stick){
    	
    	robotDrive4.arcadeDrive(stick);
    }
*/  		
   
}
