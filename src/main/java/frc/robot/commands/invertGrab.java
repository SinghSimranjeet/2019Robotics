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

public class invertGrab extends Command {
  public invertGrab() {
    requires(Robot.piston);
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
    //Robot.piston.reverseGrab();

    Timer mytimer = new Timer();
    mytimer.reset();
    mytimer.start();

    while(mytimer.get() <= 1)
    {
      Robot.piston.grab();
      Robot.piston.shootPiston2();
    }

    while(mytimer.get() > 1 && mytimer.get() <= 1.01)
    {
      Robot.piston.lift();
    }

    while(mytimer.get() > 1.01 && mytimer.get() <= 1.02)  
    {
      Robot.piston.reverseGrab(); 
      Robot.piston.shootPistonInvert2();
    }

    mytimer.stop();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
