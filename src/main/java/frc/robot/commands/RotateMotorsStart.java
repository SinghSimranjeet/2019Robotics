/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.subsystems.Encoders;

public class RotateMotorsStart extends Command {
  public RotateMotorsStart() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.rotateShooter);
   // requires(Robot.encoders);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.rotateShooter.startRotating(.3);

    // double curr_time = System.currentTimeMillis();
    // double end_time = curr_time + 1000;
    // while(System.currentTimeMillis() < end_time)
    // {
    //   Robot.rotateShooter.startRotating(-0.3); 
      
    //   /*
    //   if(Encoders.getLeftEncRate() == 5){
    //     Robot.rotateShooter.stopRotating();
    //     Robot.encoders.resetEnc();
    //   }
    //   */
    // }

    // double curr_time2 = System.currentTimeMillis();
    // double end_time2 = curr_time2 + 1000;

    // double increment = 0;
    // double delay = 5000;

    // for(int i = 0; i < delay; i++)
    // {
    //   increment += curr_time2;

    //   while(increment >= curr_time2 && System.currentTimeMillis() < end_time2)
    //   {
    //     Robot.rotateShooter.invertRotating(.3);

    //     /*
    //     if(Encoders.getLeftEncRate() == 5){
    //       Robot.rotateShooter.stopRotating();
    //       Robot.encoders.resetEnc();
    //     }
    //     */

    //   }
    // }
    
   
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
