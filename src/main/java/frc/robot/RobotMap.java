/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.oi.limelightvision.limelight.frc.LimeLight;
//import frc.robot.subsystems.Encoders;
import frc.robot.oi.limelightvision.limelight.frc.LimeLight;

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

  // public static SpeedController Shoot_motor = new Spark(7);

  public static SpeedController motor2 = new Spark(4);
  public static SpeedController motor1 = new Spark(5);
  public static SpeedControllerGroup motors = new SpeedControllerGroup(motor1, motor2);

  // public static DoubleSolenoid dsolenoid = new DoubleSolenoid(6,7);

  public static SpeedController r_motor = new Spark(6);
  public static SpeedController r_motor2 = new Spark(7);
  public static SpeedControllerGroup romotor = new SpeedControllerGroup(r_motor, r_motor2);

  public static Counter R_AmMag = new Counter(8);
  public static Counter R_AmIndex = new Counter(9);

  public static AHRS ahrs = new AHRS(SPI.Port.kMXP);

  public static DoubleSolenoid dSolenoid1 = new DoubleSolenoid(7, 3); // Hatch Panel1
  public static DoubleSolenoid dSolenoid2 = new DoubleSolenoid(2, 1); // Hatched Mech
  public static DoubleSolenoid dSolenoid3 = new DoubleSolenoid(0, 6); // Push Cargo
  public static DoubleSolenoid dSolenoid4 = new DoubleSolenoid(4, 5); // Hatch Panel2

  public static Compressor com = new Compressor();
  
  public void log() {

    // SmartDashboard.putNumber("getLeftEncDistance",
    // Encoders.getLeftEncDistance());
    // SmartDashboard.putNumber("getLeftEncRate", Encoders.getLeftEncRate());

  SmartDashboard.putNumber("CompressorCurrent", com.getCompressorCurrent());

  SmartDashboard.putNumber("NavX Pitch", ahrs.getPitch());
	SmartDashboard.putNumber("NavX Roll", ahrs.getRoll());
  SmartDashboard.putNumber("NavX Yaw", ahrs.getYaw());
  SmartDashboard.putBoolean("NavX isConnected", ahrs.isConnected());
  SmartDashboard.putBoolean("NavX isCalibrating", ahrs.isCalibrating());

  SmartDashboard.putNumber("Encoder getRate", R_AmIndex.getRate());
  SmartDashboard.putNumber("Encoder getPeriod", R_AmIndex.getPeriod());
  SmartDashboard.putNumber("Encoder getDistance", R_AmIndex.getDistance());
  SmartDashboard.putNumber("Encoder Counts", R_AmMag.get());

  /*
  SmartDashboard.putBoolean("LimeLightConnected", Robot.drivetrain.gLimeLight().isConnected());
  SmartDashboard.putBoolean("LimeLight IsTargetFound", Robot.drivetrain.gLimeLight().tv_getIsTargetFound());
  SmartDashboard.putNumber("LimelightX",  Robot.drivetrain.gLimeLight().tx_getdegRotationToTarget());
	SmartDashboard.putNumber("LimelightY",  Robot.drivetrain.gLimeLight().ty_getdegVerticalToTarget());
	SmartDashboard.putNumber("LimelightArea",  Robot.drivetrain.gLimeLight().ta_getTargetArea());

  */
  }

  public static DifferentialDrive drive;

  public static SpeedController leftMotor1;
  public static SpeedController leftMotor2;
  public static SpeedControllerGroup left;

  public static SpeedController rightMotor1;
  public static SpeedController rightMotor2;
  public static SpeedControllerGroup right;

  public static void init(){     
    leftMotor1 = new Spark(0);
    leftMotor2 = new Spark(1);
    left = new SpeedControllerGroup(leftMotor1, leftMotor2);
  

    rightMotor1 = new Spark(2);
    rightMotor2 = new Spark(3);
    right = new SpeedControllerGroup(rightMotor1, rightMotor2);

   drive = new DifferentialDrive(leftMotor1, rightMotor1);
   // drive.setExpiration(.5);
   //drive.setSafetyEnabled(false);
    
  }

  

}
