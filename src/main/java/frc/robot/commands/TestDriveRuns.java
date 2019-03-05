/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TestDriveRuns extends Command {
  public TestDriveRuns() {
    // Use requires() here to declare subsystem dependencies
   requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   // Robot.drivetrain.setSpeed(Robot.m_oi.joystick1.getY(), Robot.m_oi.joystick1.getY());
    //Robot.drivetrain._drive.tankDrive(Robot.m_oi.joystick1.getY(), Robot.m_oi.joystick1.getX());
    //Robot.drivetrain._drive.arcadeDrive(.5, 0);
    //Robot.drivetrain.correctWhileDriving();
    Robot.drivetrain.roatewiththejoystick();
  }

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
