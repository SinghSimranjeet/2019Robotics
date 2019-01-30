/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
  
  //public static SpeedController Shoot_motor = new  Spark(7);

  public static SpeedController motor2 = new Spark(4);
  public static SpeedController motor1 = new Spark(5);
  public static SpeedControllerGroup motors = new SpeedControllerGroup(motor1, motor2);

  public static DoubleSolenoid dsolenoid = new DoubleSolenoid(6,7);

  public static SpeedController r_motor = new Spark(2);
  public static SpeedController r_motor2 = new Spark(3);
  public static SpeedControllerGroup romotor = new SpeedControllerGroup(r_motor, r_motor2);
  
  public static DifferentialDrive drive;

  public static SpeedController leftMotor1;
  public static SpeedController leftMotor2;
  public static SpeedControllerGroup left;

  public static SpeedController rightMotor1;
  public static SpeedController rightMotor2;
  public static SpeedControllerGroup right;

  public static void init(){     
    leftMotor1 = new Spark(6);
    leftMotor2 = new Spark(8);
    left = new SpeedControllerGroup(leftMotor1, leftMotor2);

    rightMotor1 = new Spark(0);
    rightMotor2 = new Spark(1);
    right = new SpeedControllerGroup(rightMotor1, rightMotor2);

    drive = new DifferentialDrive(left, right);
  }

}
