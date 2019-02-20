/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.RotateMotorsInvert;
//import frc.robot.commands.SolenoidBackward;
//import frc.robot.commands.SolenoidForward;
//import frc.robot.commands.SolenoidOff;
import frc.robot.commands.RotateMotorsStart;
import frc.robot.commands.RotateMotorsStop;
import frc.robot.commands.TimedRotation;
import frc.robot.commands.grab;
import frc.robot.commands.invertGrab;
import frc.robot.commands.liftUp;
import frc.robot.commands.pistionOff;
import frc.robot.commands.reverseLift;


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
  public Joystick joystick1 = new Joystick(0);
  public Joystick buttonBoard = new Joystick(1);

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




  public OI()
{
  one = new JoystickButton(buttonBoard, 1);
  one.whileHeld(new RotateMotorsStart());
  one.whenReleased(new RotateMotorsStop());

  two = new JoystickButton(buttonBoard, 2);
  two.whileHeld(new RotateMotorsInvert());
  two.whenReleased(new RotateMotorsStop());

  three = new JoystickButton(buttonBoard, 3);
  three.whenPressed(new TimedRotation(2));
  three.whenReleased(new RotateMotorsStop());

  four = new JoystickButton(buttonBoard, 4);
  four.whenPressed(new TimedRotation(2));
  four.whenReleased(new RotateMotorsStop());
  

  seven = new JoystickButton(buttonBoard, 7);
  seven.whenPressed(new grab());
  seven.whenReleased(new pistionOff());

  eight = new JoystickButton(buttonBoard, 8);
  eight.whenPressed(new invertGrab());
  eight.whenReleased(new pistionOff());

  nine = new JoystickButton(buttonBoard, 9);
  nine.whenPressed(new liftUp());
  nine.whenReleased(new pistionOff());
 
  ten = new JoystickButton(buttonBoard, 10);
  ten.whenPressed(new reverseLift());
  ten.whenReleased(new pistionOff());
}


  public double getDriveLeftSpeed() {
    return joystick1.getRawAxis(2);
  }
  public double getDriveRightSpeed() {
    return joystick1.getRawAxis(1);
  }
  public Joystick getJoystick1() {
    return joystick1;
}
}
