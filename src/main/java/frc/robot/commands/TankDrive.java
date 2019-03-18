/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class TankDrive extends Command {
  public TankDrive() {
    requires(Robot.drivetrain);
    
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
     Timer stopTimer = new Timer();
     double distance1 = 0.40;
     double distance2 = 0.51;
     Robot.m_oi.button3_j1.whenPressed(new Flag());
     

    if(((Robot.limelightAPI.ta_getTargetArea() >= distance1) && (Robot.limelightAPI.ta_getTargetArea() <= distance2))){
      Robot.drivetrain.leftSC.set(0);
      Robot.drivetrain.rightSC.set(0);


      
    }
    else{
      Robot.drivetrain._arcadeDrive(Robot.m_oi.joystick2.getY(),Robot.m_oi.joystick2.getZ());
    }
     
    }
    /* else if(Robot.limelightAPI.ta_getTargetArea() == distance1 && isFinished() == true){
      Robot.drivetrain._arcadeDrive(Robot.m_oi.joystick2.getY(),Robot.m_oi.joystick2.getZ());
    } */
 
   // Robot.drivetrain._drive.arcadeDrive(Robot.m_oi.joystick1.getY(), Robot.m_oi.joystick1.getZ());
    //Robot.drivetrain._drive.tankDrive(Robot.m_oi.joystick1.getY(), Robot.m_oi.joystick1.getX());
  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  } 
 
  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.stopDrive();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
