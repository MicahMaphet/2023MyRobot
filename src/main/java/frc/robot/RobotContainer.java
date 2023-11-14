// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.swerve.DriveCommand;
import frc.robot.subsystems.Swerve;

public class RobotContainer {
  private final Swerve swerve = new Swerve();
  private final CommandXboxController controller = new CommandXboxController(0);
  public RobotContainer() {
    configureBindings();
    configureDefaultCommands();
  }

  private void configureBindings() {}

  private void configureDefaultCommands() {
    swerve.setDefaultCommand(new DriveCommand(swerve, () -> -controller.getLeftY(), () -> -controller.getLeftX(), () -> -controller.getRightX()));
  }
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
