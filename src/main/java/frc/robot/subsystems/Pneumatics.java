/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Pneumatics extends Subsystem {
  private final DoubleSolenoid solenoid1 = RobotMap.dsolenoid;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void startSolenoid () {
		solenoid1.set(DoubleSolenoid.Value.kForward);
		
  }
  
  public void reverseSolenoid() {
    solenoid1.set(DoubleSolenoid.Value.kReverse);
  }

  public void stopSolenoid(){
    solenoid1.set(DoubleSolenoid.Value.kOff);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
