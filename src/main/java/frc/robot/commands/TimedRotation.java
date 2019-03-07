/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

/**
 * Add your docs here.
 */
public class TimedRotation extends TimedCommand {
  /**
   * Add your docs here.
   */
  public TimedRotation(double timeout) {
    super(timeout);
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

    Timer mytimer = new Timer();
    mytimer.reset();
    mytimer.start();

    System.out.println(mytimer.getFPGATimestamp());
  

    while(mytimer.get() <= 1.25)
    {
      Robot.rotateShooter.startRotating(.6);
    }

    while(mytimer.get() > 1.25 && mytimer.get() <= 8)
    {
      Robot.rotateShooter.startRotating(.3);

      while(mytimer.get() > 2 && mytimer.get() <= 6)
      {
        Robot.Motor2.start(.5);

          while(mytimer.get() > 5 && mytimer.get() <= 7)
          {
            Robot.piston.shootPiston();

            while(mytimer.get() > 7 && mytimer.get() <= 8)
            {
              Robot.piston.shootPistonInvert();
              Robot.Motor2.stop();              
            }
          } 
      }        
  }

  while(mytimer.get() > 8 && mytimer.get() <= 12)
    {
      Robot.rotateShooter.startRotating(.2);
    }
   mytimer.stop();

}

  // Called once after timeout
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