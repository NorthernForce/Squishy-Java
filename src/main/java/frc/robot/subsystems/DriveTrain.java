// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  private DifferentialDrive RobotDrive;
  private final CANSparkMax leftPrimary = new CANSparkMax(Constants.CANLeftPimrary, MotorType.kBrushless);
  private final CANSparkMax leftFollower = new CANSparkMax(Constants.CANLeftFollower, MotorType.kBrushless);

  private final CANSparkMax rightPrimary = new CANSparkMax(Constants.CANRightPimrary, MotorType.kBrushless);
  private final CANSparkMax rightFollower = new CANSparkMax(Constants.CANRightFollower, MotorType.kBrushless);


  /** Creates a new DriveTrain. */
  public DriveTrain() {
    RobotDrive = new DifferentialDrive(leftPrimary, rightPrimary);

    setFollowers();
    setInverted();
  }

  /**
   * Calls ArcadeDrive with xSpeed and zRotation
   * @param xSpeed the speed value
   * @param zRotation the rotation value
   */
  public void drive(double xSpeed, double zRotation) {
    RobotDrive.arcadeDrive(xSpeed, zRotation, true);
  }

  public void setFollowers() {
    leftFollower.follow(leftPrimary);
    rightFollower.follow(rightPrimary);
  }

  private void setInverted() {
    rightPrimary.setInverted(false);
    leftPrimary.setInverted(true);
  }

  public void stop() {
    RobotDrive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}