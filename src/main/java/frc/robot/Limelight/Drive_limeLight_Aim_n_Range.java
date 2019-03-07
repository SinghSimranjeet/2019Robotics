package frc.robot.Limelight;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class Drive_limeLight_Aim_n_Range extends Command {

    private double kpAim = 0.05;
    private double kpDistance = 0.05;
    private double m_moveValue;
    private double m_rotateValue;
  
    public Drive_limeLight_Aim_n_Range() {
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

    double tx = Robot.limelightAPI.tx_getdegRotationToTarget();
    double ty = Robot.limelightAPI.ty_getdegVerticalToTarget();
    boolean targetFound = Robot.limelightAPI.tv_getIsTargetFound();

    if (targetFound) {
      m_moveValue = ty * kpDistance;
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
    Robot.drivetrain._arcadeDrive(m_moveValue, m_rotateValue);
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      end();
    }
  
    private double Estimated_Distance(double a2){
      double h1 = 6.0;
      double h2 = 36.0;
      double a1 = 0.0;
      return (h2-h1)/Math.tan(a1+a2);
      
    }
  }
  