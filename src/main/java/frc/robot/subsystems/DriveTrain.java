// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.CANids;

public class DriveTrain extends SubsystemBase {

  private DifferentialDrive RobotDrive;
  private final CANSparkMax leftPrimary = new CANSparkMax(CANids.leftPrimary.value, MotorType.kBrushless);
  private final CANSparkMax leftFollower = new CANSparkMax(CANids.leftFollower.value, MotorType.kBrushless);

  private final CANSparkMax rightPrimary = new CANSparkMax(CANids.rightPrimary.value, MotorType.kBrushless);
  private final CANSparkMax rightFollower = new CANSparkMax(CANids.rightFollower.value, MotorType.kBrushless);

  public IdleMode idleMode;


  /** Creates a new DriveTrain. */
  public DriveTrain() {
    RobotDrive = new DifferentialDrive(leftPrimary, rightPrimary);

    setFollowers();
    setInverted();
    setNeutralMode(IdleMode.kBrake);
  }

  /**
   * Calls ArcadeDrive with xSpeed and zRotation
   * @param xSpeed the speed value
   * @param zRotation the rotation value
   */
  public void drive(double xSpeed, double zRotation) {
    RobotDrive.arcadeDrive(-xSpeed, -zRotation, true);
  }

  public void setFollowers() {
    leftFollower.follow(leftPrimary);
    rightFollower.follow(rightPrimary);
  }

  public double[] getEncoderRotations() {
    return new double[] {
      leftPrimary.getEncoder().getPosition(),
      rightPrimary.getEncoder().getPosition()
    };
  }
  
  public void setNeutralMode(IdleMode mode) {
    leftPrimary.setIdleMode(mode);
    leftFollower.setIdleMode(mode);
    rightPrimary.setIdleMode(mode);
    rightFollower.setIdleMode(mode);
    idleMode = mode;
  }

  public void stop() {
    RobotDrive.stopMotor();
  }

  private void setInverted() {
    rightPrimary.setInverted(true);
    leftPrimary.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("encoderRotations", getEncoderRotations()[1]);
  }
}
