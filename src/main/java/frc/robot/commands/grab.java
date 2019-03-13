/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class grab extends Command {
  public grab() {
    requires(Robot.piston);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  
  //public static void delay(double seconds){}

  @Override
  protected void execute() {

    Timer grab_timer = new Timer();
    grab_timer.start();

    while(grab_timer.get() <= 1)
    {
      Robot.piston.grab();
    }

    while(grab_timer.get() > 1 && grab_timer.get() < 2)
    {
      Robot.piston.lift();
      break;
    }
    
    grab_timer.stop();
    Robot.piston.reverseGrab();  
      
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }                                                                                                                           
                                                                                                                                                                                                                                                          
  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.piston.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
