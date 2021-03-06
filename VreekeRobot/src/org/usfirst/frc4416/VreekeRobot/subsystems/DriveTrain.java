// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4416.VreekeRobot.subsystems;

import org.usfirst.frc4416.VreekeRobot.RobotMap;
import org.usfirst.frc4416.VreekeRobot.commands.*;
import edu.wpi.first.wpilibj.*;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController frontRight = RobotMap.driveTrainFrontRight;
    SpeedController frontLeft = RobotMap.driveTrainFrontLeft;
    SpeedController rearLeft = RobotMap.driveTrainRearLeft;
    SpeedController rearRight = RobotMap.driveTrainRearRight;
    RobotDrive robotDrive4 = RobotMap.driveTrainRobotDrive4;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new RyanDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveForward(){
    	
    	frontRight.set(.75);
    	frontLeft.set(.75);
    	rearLeft.set(.75);
    	rearRight.set(.75);
    	
    }
    
    public void driveBack(){
    	
    	frontRight.set(-.75);
    	frontLeft.set(-.75);
    	rearLeft.set(-.75);
    	rearRight.set(-.75);
    	
    }
    
    public void stop(){
    	
    	frontRight.set(0);
    	frontLeft.set(0);
    	rearLeft.set(0);
    	rearRight.set(0);
    	
    }
    
    public void arcadeDrive(Joystick stick){
    	
    	robotDrive4.arcadeDrive(stick);
    }
	
    public void ryanDrive(Joystick stick){
    	
    	frontRight.set((stick.getY()+stick.getZ())/2);
    	frontLeft.set((stick.getY()*-1+stick.getZ())/2);
    	rearLeft.set((stick.getY()*-1+stick.getZ())/2);
    	rearRight.set((stick.getY()+stick.getZ())/2);
    	
    	
    }
}

