/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6498.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class Robot extends IterativeRobot {
	public DifferentialDrive drive;
	public XboxController j;
	
	@Override
	public void robotInit() {
		drive = new DifferentialDrive(new Spark(0),new Spark(1));
		j=new XboxController(0);
	}

	
	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
	}


	@Override
	public void teleopPeriodic() {
		driverArcadeDrive();
		System.out.println(j.getTriggerAxis(Hand.kRight));
	}

	boolean driveReduction=false;
	double driveReductionAmount = .7; //remember a higher number means less reduction
	
	double turnReduction=.85;
	
	public void driverArcadeDrive() {
		double speed=0;
		double turn=j.getX(Hand.kLeft)*turnReduction;
		if(j.getTriggerAxis(Hand.kRight)>.05) {
			speed=j.getTriggerAxis(Hand.kRight);			
		}else if(j.getTriggerAxis(Hand.kLeft)>.05) {
			speed=-j.getTriggerAxis(Hand.kLeft);
			turn=-turn;
		}else {
			speed=0;
		}
		if(driveReduction) {
			turn=turn*driveReductionAmount;
			speed=speed*driveReductionAmount;
		}
		drive.arcadeDrive(speed, turn);
	}
	
	
	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
