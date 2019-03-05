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
import frc.robot.commands.grab;

/**
 * Add your docs here.
 */
public class Pistons extends Subsystem {

  private final DoubleSolenoid d1 = RobotMap.dSolenoid1;
  private final DoubleSolenoid d2 = RobotMap.dSolenoid2; 
  private final DoubleSolenoid d3 = RobotMap.dSolenoid3;
  private final DoubleSolenoid d4 = RobotMap.dSolenoid4;


  public void grab(){
    d1.set(DoubleSolenoid.Value.kForward);
  }

  public void reverseGrab(){
    d1.set(DoubleSolenoid.Value.kReverse);
  }

  public void lift(){
    d2.set(DoubleSolenoid.Value.kForward);
    //d3.set(DoubleSolenoid.Value.kForward);
  }

  public void invertLift(){
    d2.set(DoubleSolenoid.Value.kReverse);
    //d3.set(DoubleSolenoid.Value.kReverse);
  }

  public void shootPiston(){
    d3.set(DoubleSolenoid.Value.kForward);
  }

  public void shootPistonInvert(){ 
    d3.set(DoubleSolenoid.Value.kReverse);
  }

  public void shootPiston2(){
    d4.set(DoubleSolenoid.Value.kForward);
  }

  public void shootPistonInvert2(){ 
    d4.set(DoubleSolenoid.Value.kReverse);
  }

  public void stop(){
    d1.set(DoubleSolenoid.Value.kOff);
    d2.set(DoubleSolenoid.Value.kOff);
    d3.set(DoubleSolenoid.Value.kOff);
    d4.set(DoubleSolenoid.Value.kOff);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     //setDefaultCommand(new grab());
  }
}
