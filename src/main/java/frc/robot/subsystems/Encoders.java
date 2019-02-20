/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
/*
public class Encoders extends Subsystem {

  public static Encoder rightEnc = new Encoder(1, 2);
  public static Encoder leftEnc = new Encoder(3, 4);

 
  public static Counter R_AmMag = RobotMap.R_AmMag; 
  public static Counter R_AmIndex = RobotMap.R_AmIndex;

 
  public static Counter L_AmMag = RobotMap.L_AmMag; 
  public static Counter L_AmIndex = RobotMap.L_AmIndex;
    
  // Put methods for controlling this subsystem
  // here. Call these from Commands.



  public static double getLeftEncDistance(){
    return L_AmMag.getDistance();
  }

  public static double getLeftEncRate(){
    return L_AmIndex.getRate();
  }

/*
  public static double getRightEncDistance(){
    return R_AmMag.getDistance();
  }

  public static double getRightEncRate(){
    return R_AmMag.getRate();
  }

  public static double EncAverage(){
    return Math.abs((getLeftEncDistance()+getRightEncDistance()))/2;
  }

  public static double EncRateAverage(){
    return Math.abs((getLeftEncRate()+getRightEncRate()))/2;
  }

  public static void resetEnc(){
   // L_AmMag.setDistancePerPulse(0);
    R_AmMag.setDistancePerPulse(0);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
*/

