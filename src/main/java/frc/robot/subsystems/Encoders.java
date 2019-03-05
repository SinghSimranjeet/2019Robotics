/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */

public class Encoders extends Subsystem {

  private final SpeedControllerGroup romotors = RobotMap.romotor;

  public static Counter R_AmMag = RobotMap.R_AmMag; 
  public static Counter R_AmIndex = RobotMap.R_AmIndex;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  public static int getRightEncCount(){
    return R_AmMag.get();
  }

  public static double getRightEncDistance(){
    return R_AmMag.getDistance();
  }

  public static double getRightEncRate(){
    return R_AmMag.getRate();
  }

  public static void resetEnc(){
   // L_AmMag.setDistancePerPulse(0);
    R_AmMag.setDistancePerPulse(0);
  }
   
  public static void rotatemotortotopal(){
    if(getRightEncCount() == 100)
    {
      

    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    
  }

}


