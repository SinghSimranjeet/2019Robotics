/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.PistonsCommands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class AutoGrabHatched extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoGrabHatched() {

    addSequential(new H_TopPistonPush());
    addSequential(new LimeLightPistonsUp());
    addSequential(new H_TopPistonBack());
    addSequential(new LimeLightPistonsDown());

    requires(Robot.piston);


  }
}
