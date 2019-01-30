/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final SpeedController rightSC;
  private final SpeedController leftSC;
  private final DifferentialDrive _drive = RobotMap.drive;
  
  

  public DriveTrain(){
    rightSC = RobotMap.right;
    leftSC = RobotMap.left;


  }

  @Override
  public void initDefaultCommand() {

   setDefaultCommand(new TankDrive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
 /* public void tankDrive(double leftSpeed, double rightSpeed) {
    	
    _drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void tankDrive(Joystick jLeft, Joystick jRight){
    tankDrive(jLeft.getY(), jRight.getZ());
  }*/
  public void _arcadeDrive(double j, double r){
		_drive.arcadeDrive(j, r, false);
		
    }

  public void _arcadeDrive(Joystick joy){
    _arcadeDrive(joy);
  }
    
  public void stopDrive() {
    _drive.stopMotor();
    }
}
