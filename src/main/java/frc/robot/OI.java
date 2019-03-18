/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Limelight.Drive_limeLight_Aim;
import frc.robot.Limelight.Drive_limeLight_Aim_n_Range;
import frc.robot.Limelight.Drive_limeLight_Range;
import frc.robot.Limelight.Drive_limeLight_Reset;
import frc.robot.PistonsCommands.AutoGrabHatched;
import frc.robot.PistonsCommands.AutoReleaseHatched;
import frc.robot.commands.Flag;
import frc.robot.commands.PushCargo;
import frc.robot.commands.RotateMotorsStop;
import frc.robot.commands.TestDriveRuns;
import frc.robot.commands.TestDriveStop;
import frc.robot.commands.TimedRotation;
import frc.robot.commands.backCargo;
import frc.robot.commands.grab_sequence;
import frc.robot.commands.intake;
import frc.robot.commands.invert_lift;
import frc.robot.commands.kill_pistons;
import frc.robot.commands.ledMode;
import frc.robot.commands.ledOn;
import frc.robot.commands.liftUp;
import frc.robot.commands.mStop;
import frc.robot.commands.pistionOff;
import frc.robot.commands.reverse_grab_sequence;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  //public Joystick joyjoystick2 = new Joystick(0);
  public Joystick buttonBoard = new Joystick(1);
  public Joystick joystick2 = new Joystick(0);


  public JoystickButton one;
  public JoystickButton two;
  public JoystickButton three;
  public JoystickButton four;
  public JoystickButton five;
  public JoystickButton six;
  public JoystickButton seven;
  public JoystickButton eight;
  public JoystickButton nine;
  public JoystickButton ten;

  public JoystickButton bumper_j1;
  public JoystickButton button2_j1;
  public JoystickButton random_but;
  public JoystickButton fiveJoy;
  public JoystickButton button3_j1;
  public JoystickButton button6_j1;



  public OI()
{
  
  bumper_j1 = new JoystickButton(joystick2, 1);
  bumper_j1.whileHeld(new TestDriveRuns());
  bumper_j1.whenReleased(new TestDriveStop());
  //bumper_j1.whenPressed(new DownP_push());
  //bumper_j1.whenReleased(new pistionOff());
  

  button2_j1 = new JoystickButton(joystick2, 2);
  button2_j1.whenPressed(new ledOn());
  //button2_j1.whenReleased(new TestDriveRuns());

  button3_j1 = new JoystickButton(joystick2, 3);
  button3_j1.whenPressed(new ledMode());
  //button3_j1.whenReleased(new Flag());

  fiveJoy = new JoystickButton(joystick2,5);
  fiveJoy.whenPressed(new PushCargo());
  fiveJoy.whenReleased(new backCargo());

/*
  random_but = new JoystickButton(joystick2, 4);
  random_but.whenPressed(new Drive_limeLight_Aim());
  random_but.whenReleased(new Drive_limeLight_Reset());

  button6_j1 = new JoystickButton(joystick2, 6);
  button6_j1.whenPressed(new AutoReleaseHatched());
  button6_j1.whenReleased(new pistionOff());
  */

/*
  random_but = new JoystickButtojoystick2, 5);
  random_but.whenPressed(new DownP_back());
  random_but.whenReleased(new pistionOff());
*/


  one = new JoystickButton(buttonBoard, 1);
  one.whenPressed(new liftUp());
  //one.whenPressed(new frc.robot.PistonsCommands.AutoGrabHatched());
  one.whenReleased(new pistionOff());

  two = new JoystickButton(buttonBoard, 2);
  two.whenPressed(new invert_lift());
  two.whenReleased(new pistionOff());

  three = new JoystickButton(buttonBoard, 3);
  three.whenPressed(new TimedRotation(1));
  three.whenReleased(new RotateMotorsStop());

  four = new JoystickButton(buttonBoard, 4);
  four.whenPressed(new intake());
  four.whenReleased(new mStop());

  seven = new JoystickButton(buttonBoard, 7);
  seven.whenPressed(new grab_sequence());
  seven.whenReleased(new pistionOff());

  eight = new JoystickButton(buttonBoard, 8);
  eight.whenPressed(new reverse_grab_sequence());
  eight.whenReleased(new pistionOff());

  ten = new JoystickButton(buttonBoard, 10);
  ten.whenPressed(new kill_pistons());
  ten.whenReleased(new pistionOff());
       
}


  public double getDriveLeftSpeed() {
    return joystick2.getRawAxis(2);
  }
  public double getDriveRightSpeed() {
    return joystick2.getRawAxis(1);
  }
  public Joystick getjoyjoystick2() {
    return joystick2;
}
}
