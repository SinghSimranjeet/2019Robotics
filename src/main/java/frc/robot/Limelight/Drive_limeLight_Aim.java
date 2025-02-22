package frc.robot.Limelight;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.LimelightAPI;
import frc.robot.subsystems.LimelightAPI.LedMode;

/**
 *
 */
public class Drive_limeLight_Aim extends Command {

    private double kpAim = 0.035;
    private double m_moveValue;
    private double m_rotateValue;
    
    public Drive_limeLight_Aim() {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
      requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.limelightAPI.setLEDMode(LedMode.kforceOn);

    double tx = Robot.limelightAPI.tx_getdegRotationToTarget();
    boolean targetFound = Robot.limelightAPI.tv_getIsTargetFound();

    if (targetFound) {
      m_moveValue = 0;
      m_rotateValue = tx * kpAim;
    } else {
      m_moveValue = 0;
      m_rotateValue = 0;
    }

    Robot.drivetrain._arcadeDrive(m_moveValue, m_rotateValue);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain._arcadeDrive(0, 0);
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      end();
    }
  }
  