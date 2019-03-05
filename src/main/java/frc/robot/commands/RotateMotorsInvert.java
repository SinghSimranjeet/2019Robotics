/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateMotorsInvert extends Command {
  public RotateMotorsInvert() {
    // Use requires() here to declare subsystem dependencies
   requires(Robot.rotateShooter);

  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.rotateShooter.invertRotating(-.3);
    
    /*double curr_time = System.currentTimeMillis();
    double end_time = curr_time + 1000;
    while(System.currentTimeMillis() < end_time)
    {
      Robot.rotateShooter.invertRotating(-.3);
    } */
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.rotateShooter.stopRotating();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
