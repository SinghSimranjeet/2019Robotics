/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.R_motorBackTimed;
import frc.robot.commands.R_motorStartTimed;
import frc.robot.commands.R_motorStop;
import frc.robot.commands.SolenoidBackward;
import frc.robot.commands.SolenoidForward;
import frc.robot.commands.SolenoidOff;
import frc.robot.commands.motor2backward;
import frc.robot.commands.motor2forward;
import frc.robot.commands.motor2stop;

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
  five = new JoystickButton(joystick1, 5);
  five.whileHeld(new motor2forward());
  five.whenReleased(new motor2stop());

  six = new JoystickButton(joystick1, 6);
  six.whileHeld(new motor2backward());
  six.whenReleased(new motor2stop());

  ten = new JoystickButton(joystick1, 9);
  ten.whenPressed(new SolenoidForward());
  ten.whenReleased(new SolenoidOff());

  nine = new JoystickButton(joystick1, 10);
  nine.whenPressed(new SolenoidBackward());
  nine.whenReleased(new SolenoidOff());

  seven = new JoystickButton(joystick1, 7);
  seven.whenPressed(new R_motorStartTimed(1));
  seven.whenReleased(new R_motorStop());

  eight = new JoystickButton(joystick1, 8);
  eight.whenPressed(new R_motorBackTimed(1));
  eight.whenReleased(new R_motorStop());


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
