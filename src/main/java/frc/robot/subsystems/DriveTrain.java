/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final SpeedControllerGroup rightSC;
  private final SpeedControllerGroup leftSC;
  private final DifferentialDrive _drive;

  private Joystick tM_Joystick = new Joystick(0);

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;

  AHRS ahrs;
  boolean first_iteration, onTarget, direction;

  public PIDController turnController;
  private double degrees;

  static final double kP = 0.03;
  static final double kI = 0.00;
  static final double kD = 0.00;
  static final double kF = 0.00;

  static final double kToleranceDegrees = 2.0f;

  public DriveTrain() {

    rightSC = RobotMap.right;
    leftSC = RobotMap.left;

    _drive = RobotMap.drive;
    _drive.setExpiration(.5);
    _drive.setSafetyEnabled(false);

    // Code for connection of nav gyro.
    try {
      ahrs = RobotMap.ahrs;
    } catch (RuntimeException ex) {
      DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
    }

    turnController = new PIDController(kP, kI, kD, kF, ahrs, new TurnOutput());
    turnController.setInputRange(-180.0f, 180.0f);
    turnController.setOutputRange(-0.7, 0.7);
    turnController.setAbsoluteTolerance(kToleranceDegrees);
    turnController.setContinuous(true);
    turnController.disable();

    LiveWindow.add(turnController);
    turnController.setName("DriveSystem", "RotateController");

    // next line does not do anything, probably because ahrs does not implement
    // Sendable
    LiveWindow.add(ahrs);
    ahrs.setName("Navigation", "NavX");

    // calibration takes about 20 secs.
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

  public void tankDrive(Joystick jLeft, Joystick jRight) {
    tankDrive(jLeft.getY(), jRight.getZ());
  }

  public void _arcadeDrive(double j, double r) {
    _drive.arcadeDrive(j, r);
  }

  public void _arcadeDrive(Joystick joy) {
    _arcadeDrive(joy.getY(), joy.getZ());
  }

  public void stopDrive() {
    _drive.stopMotor();
    leftSC.set(0);
    rightSC.set(0);
  }

  public void setSpeed(double speed_L, double speed_R) {// two input speed control
    leftSC.set(speed_L);
    rightSC.set(speed_R);

  }

  public void setSpeed(double speed) {// one input speed control
    setSpeed(speed, speed);
  }

  public void drive(double left, double right) {
    leftSC.set(left);
    rightSC.set(right);
  }

  /**
   * 
   * @return Returns the current yaw value (in degrees, from -180 to 180) reported
   *         by the sensor. Yaw is a measure of rotation around the Z Axis (which
   *         is perpendicular to the earth).
   * 
   *         Note that the returned yaw value will be offset by a user-specified
   *         offset value; this user-specified offset value is set by invoking the
   *         zeroYaw() method.
   * 
   *         Returns: The current yaw value in degrees (-180 to 180).
   * 
   */
  public double obtainYaw() {
    return ahrs.getYaw();
  }

  /**
   * set the direction and degrees for rotation
   * 
   * @param deg angles for rotation
   * @param dir direction for rotation: true - clockwise, false - counterClockwise
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
      double inverted = -degrees;
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
    /*
     * if (obtainYaw() > 0) { if (obtainYaw() < 1.5 && obtainYaw() > 0) {
     * _drive.tankDrive(-0.23, -0.23); } else if (obtainYaw() > 1.5 && obtainYaw() <
     * 4) { _drive.tankDrive(0.15, -0.15); } else if (obtainYaw() > 4) {
     * _drive.tankDrive(0.23, -0.23); } } else if (obtainYaw() < 0) { if
     * (obtainYaw() > -1.5 && obtainYaw() < 0) { _drive.tankDrive(-0.23, -0.23); }
     * else if (obtainYaw() < -1.5 && obtainYaw() > -4) { _drive.tankDrive(-0.15,
     * 0.15); } else if (obtainYaw() < -4) { _drive.tankDrive(-0.23, 0.23); } }
     */
    int tempYaw = 0;
    double rotatetoangle = 0;

    if (obtainYaw() != rotatetoangle) {

      while (obtainYaw() > rotatetoangle) {
        tempYaw = (int) (obtainYaw() / 2);

        turnController.setSetpoint(rotatetoangle);

        if (tempYaw == 0) {
          _drive.tankDrive(0.3, 0.3);
        }
      }

      while (obtainYaw() < rotatetoangle) {
        tempYaw = (int) (obtainYaw() / 2);
        turnController.setSetpoint(rotatetoangle);

        if (tempYaw == 0) {
          _drive.tankDrive(0.3, 0.3);
        }
      }
    }
    /*
     * while(obtainYaw() != rotatetoangle){
     * 
     * while(obtainYaw() > rotatetoangle){ tempYaw = (int) (obtainYaw() / 2);
     * 
     * turnController.setSetpoint(rotatetoangle);
     * 
     * if (tempYaw == 0) { _drive.tankDrive(0.3, 0.3); } }
     * 
     * while(obtainYaw() < rotatetoangle){ tempYaw = (int) (obtainYaw() / 2);
     * turnController.setSetpoint(rotatetoangle);
     * 
     * if (tempYaw == 0) { _drive.tankDrive(0.3, 0.3); } }
     * 
     * }
     */
  }

  int rotateToAngleRate;

  public void roatewiththejoystick() {
    /*
     * While this button is held down, the robot is in "drive straight" mode.
     * Whatever direction the robot was heading when "drive straight" mode was
     * entered will be maintained. The average speed of both joysticks is the
     * magnitude of motion.
     */
    if (!turnController.isEnabled()) {
      // Acquire current yaw angle, using this as the target angle.
      turnController.setSetpoint(ahrs.getYaw());
      rotateToAngleRate = 0; // This value will be updated in the pidWrite() method.
      turnController.enable();
    }
      double magnitude = (tM_Joystick.getY() + tM_Joystick.getY()) / 2;
      double leftStickValue = magnitude + rotateToAngleRate;
      double rightStickValue = magnitude - rotateToAngleRate;
      _drive.tankDrive(leftStickValue, rightStickValue);
  }

	public void resetGyro() {
		ahrs.zeroYaw();
		onTarget = false;
		ahrs.resetDisplacement();
  }


  public void Update_Limelight_Tracking()
  {

        // These numbers must be tuned for your Robot!  Be careful!
        final double STEER_K = 0.03;                    // how hard to turn toward the target
        final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast


        double toBoolean_tv_getIsTargetFound = Robot.limelightAPI.tv_getIsTargetFound() ? 1 : 0;

        if (toBoolean_tv_getIsTargetFound < 1.0)
        {
          m_LimelightHasValidTarget = false;
          m_LimelightDriveCommand = 0.0;
          m_LimelightSteerCommand = 0.0;
          return;
        }

        m_LimelightHasValidTarget = true;

        // Start with proportional steering
        double steer_cmd = Robot.limelightAPI.tx_getdegRotationToTarget() * STEER_K;
        m_LimelightSteerCommand = steer_cmd;

        // try to drive forward until the target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA - Robot.limelightAPI.ta_getTargetArea()) * DRIVE_K;

        // don't let the robot drive too fast into the goal
        if (drive_cmd > MAX_DRIVE)
        {
          drive_cmd = MAX_DRIVE;
        }
        m_LimelightDriveCommand = drive_cmd;
  }

  public void limelightAutoFind_Target(){

    Update_Limelight_Tracking();

    double steer = tM_Joystick.getX(Hand.kRight);
    double drive = -tM_Joystick.getY(Hand.kLeft);
    boolean auto = tM_Joystick.getRawButton(2);

    steer *= 0.70;
    drive *= 0.70;

    if (auto)
    {
      if (m_LimelightHasValidTarget)
      {
        _drive.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);
      }
      else
      {
        _drive.arcadeDrive(0.0,0.0);
      }
    }
    else
    {
      _drive.arcadeDrive(drive,steer);
    }
    
  }

  private class TurnOutput implements PIDOutput {
    @Override
    public void pidWrite(double output) {
      drive(output, output);
      }
    }
  
    
}

