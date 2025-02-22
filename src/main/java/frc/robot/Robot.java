/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Limelight.Drive_limeLight_Aim_n_Range;
import frc.robot.PistonsCommands.AutoGrabHatched;
import frc.robot.commands.Flag;
import frc.robot.commands.TimedRotation;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimelightAPI;
//import frc.robot.subsystems.Encoders;
import frc.robot.subsystems.Motor2;
import frc.robot.subsystems.Pistons;
import frc.robot.subsystems.RotateShooter;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveTrain drivetrain;
  public static RotateShooter rotateShooter;
 // public static Encoders encoders;
  public static RobotMap robotMap;
  public static OI m_oi;
  public static Motor2 Motor2;
  public static Pistons piston;
  public static LimelightAPI limelightAPI;
  public static Drive_limeLight_Aim_n_Range limelight_range;
  public static TimedRotation time_rotate;
  public static Flag flag;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser;

  @Override
  public void robotInit() {
      RobotMap.init();// super duper important!!!

      UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
      camera.setVideoMode(PixelFormat.kMJPEG, 640, 360, 30);

    
      
    // Intilized subsystem classes before OI class.
      drivetrain = new DriveTrain();
      rotateShooter = new RotateShooter();
      robotMap = new RobotMap();
      Motor2 = new Motor2();
      piston = new Pistons();
      limelightAPI = new LimelightAPI();
      limelight_range = new Drive_limeLight_Aim_n_Range();
      time_rotate = new TimedRotation(0);
      flag = new Flag();

     // encoders = new Encoders();

      m_oi = new OI();

     m_chooser = new SendableChooser<>();
    m_chooser.addDefault("Piston", new AutoGrabHatched());
    SmartDashboard.putData("Auto mode", m_chooser);

}


    
  

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    robotMap.log();
    
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {

    m_autonomousCommand = (Command) m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    robotMap.R_AmIndex.reset();
    robotMap.R_AmMag.reset();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}
