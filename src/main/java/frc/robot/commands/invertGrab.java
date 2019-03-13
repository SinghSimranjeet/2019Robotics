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

    Timer igrab_timer = new Timer();
    igrab_timer.start();

    while(igrab_timer.get() <= 1)
    {
      Robot.piston.grab();
    }

    while(igrab_timer.get() > 1 && igrab_timer.get() <= 1.5);
    {
      Robot.piston.shootPiston2(); 
    }

    Timer igrab_timer2 = new Timer();
    igrab_timer2.start();

    while(igrab_timer2.get() > 1 && igrab_timer2.get() <= 2)
    {
      Robot.piston.invertLift();
    }
    igrab_timer2.stop();

      Robot.piston.reverseGrab();
      Robot.piston.shootPistonInvert2();
     
    

  /*

    while(igrab_timer.get() > 1.5 && igrab_timer.get() <= 2)
    {
      Robot.piston.invertLift();
      break;
    }
    igrab_timer.stop();

    Robot.piston.reverseGrab();
      Robot.piston.shootPistonInvert2();  
      */
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
