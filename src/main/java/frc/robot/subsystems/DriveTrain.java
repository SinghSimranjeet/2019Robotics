/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;
import frc.robot.oi.limelightvision.limelight.frc.LimeLight;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public final SpeedControllerGroup rightSC;
  public final SpeedControllerGroup leftSC;
  public final DifferentialDrive _drive = RobotMap.drive;

  AHRS ahrs;
  public LimeLight _limelight;
  boolean first_iteration, onTarget, direction;

 // private PIDController turnController;
  private double degrees;

  static final double kP = 0.03;
  static final double kI = 0.00;
  static final double kD = 0.00;
  static final double kF = 0.00;

  static final double kToleranceDegrees = 2.0f;

  public DriveTrain() {
    rightSC = RobotMap.right;
    leftSC = RobotMap.left;
    _drive.setExpiration(.5);
    _drive.setSafetyEnabled(false);

    // Code for connection of nav gyro.
    try {
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }
/*
    turnController = new PIDController(kP, kI, kD, kF, ahrs, (PIDOutput) this);
      turnController.setInputRange(-180.0f, 180.0f);
      turnController.setOutputRange(-0.7, 0.7);
      turnController.setAbsoluteTolerance(kToleranceDegrees);
      turnController.setContinuous(true);
*/
    //LiveWindow.add(turnController);
		//turnController.setName("DriveSystem", "RotateController");
		
		//next line does not do anything, probably because ahrs does not implement Sendable
		LiveWindow.add(ahrs);  
		ahrs.setName("Navigation", "NavX");	
  
      //calibration takes about 20 secs.
      boolean is_calibrating = ahrs.isCalibrating();
      if (first_iteration && !is_calibrating) {
        Timer.delay(0.3);
        ahrs.zeroYaw();
        first_iteration = false;
      }
    }

  @Override
  public void initDefaultCommand() {

   setDefaultCommand(new TankDrive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  public void tankDrive(double leftSpeed, double rightSpeed) {
    	
    _drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void tankDrive(Joystick jLeft, Joystick jRight){
    tankDrive(jLeft.getY(), jRight.getZ());
  }

  public void _arcadeDrive(double j, double r){
		_drive.arcadeDrive(j, r, false);
    }
  public void _arcadeDrive(Joystick joy){
    _arcadeDrive(joy.getY(), joy.getZ());
  }
    
  public void stopDrive() {
    _drive.stopMotor();
    }

    public LimeLight gLimeLight(){
      return _limelight;
    }

  /**
   * 
   * @return Returns the current yaw value (in degrees, from -180 to 180) reported by the sensor. Yaw is a measure of rotation around the Z Axis (which is perpendicular to the earth).

            Note that the returned yaw value will be offset by a user-specified offset value; this user-specified offset value is set by invoking the zeroYaw() method.

            Returns:
            The current yaw value in degrees (-180 to 180).
   * 
   */
   public double obtainYaw() {
    return ahrs.getYaw();
    }

    /**
	 * set the direction and degrees for rotation
	 * 
	 * @param deg
	 *            angles for rotation
	 * @param dir
	 *            direction for rotation: true - clockwise, false -
	 *            counterClockwise
	 */

    public void setRotation(double deg, boolean dir) {
      ahrs.zeroYaw();
      this.degrees = deg;
      this.direction = dir;
    }

    /**
	 * rotate to a certain angle
	 */
	public void AngularRotation() {
		onTarget = false;
		if (direction) {// turn to the right
			if (obtainYaw() < degrees + 1.5 && obtainYaw() > degrees - 1.5) {
				_drive.tankDrive(0, 0);
				onTarget = true;
			} else {
				_drive.tankDrive(-0.23, 0.23);
			}
		} else if (!direction) {// turn to the left
			double inverted = - degrees;
			if (obtainYaw() < inverted + 1.5 && obtainYaw() > inverted - 1.5) {
				_drive.tankDrive(0, 0);
				onTarget = true;
			} else {
				_drive.tankDrive(0.23, -0.23);
			}
		}
	}

	/**
	 * Drive while maintaining the correct direction with the gyro on the NavX
	 */
	public void correctWhileDriving() {
		if (obtainYaw() > 0) {
			if (obtainYaw() < 1.5 && obtainYaw() > 0) {
				_drive.tankDrive(-0.23, -0.23);
			} else if (obtainYaw() > 1.5 && obtainYaw() < 4) {
				_drive.tankDrive(0.15, -0.15);
			} else if (obtainYaw() > 4) {
				_drive.tankDrive(0.23, -0.23);
			}
		} else if (obtainYaw() < 0) {
			if (obtainYaw() > -1.5 && obtainYaw() < 0) {
				_drive.tankDrive(-0.23, -0.23);
			} else if (obtainYaw() < -1.5 && obtainYaw() > -4) {
				_drive.tankDrive(-0.15, 0.15);
			} else if (obtainYaw() < -4) {
				_drive.tankDrive(-0.23, 0.23);
			}
		}
	}
	public void resetGyro() {
		ahrs.zeroYaw();
		onTarget = false;
		ahrs.resetDisplacement();
	}


    
}
