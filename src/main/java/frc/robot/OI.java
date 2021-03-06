// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveToDistance;
import frc.robot.commands.VisionAim;
import static frc.robot.Constants.Ports;

/** Add your docs here. */
public class OI {

    private static final XboxController DriverXbox = new XboxController(Ports.DriverXbox.value);

    public static double getY() {
        return DriverXbox.getLeftY();
    }

    public static double getX() {
        return -(DriverXbox.getRightX());
    }

    public void mapButtons () {
        new JoystickButton(DriverXbox, XboxController.Button.kRightBumper.value).whileHeld(new VisionAim());
        new JoystickButton(DriverXbox, XboxController.Button.kB.value).whenPressed(new DriveToDistance(0.2, 12));
    }
}
