package frc.robot.commands.swerve;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Swerve;

import java.util.function.Supplier;

public class DriveCommand extends CommandBase {
  private static final double kJoystickDeadband = 0.1;

  private final Swerve swerve;

  private Supplier<Double> xInput;
  private Supplier<Double> yInput;
  private Supplier<Double> rotInput;

  private final SlewRateLimiter vxRateLimiter = new SlewRateLimiter(
    Swerve.kMaxAcceleration
  );
  private final SlewRateLimiter vyRateLimiter = new SlewRateLimiter(
    Swerve.kMaxAcceleration
  );
  private final SlewRateLimiter omegaRateLimiter = new SlewRateLimiter(
    Swerve.kMaxAngularVelocity
  );

  public DriveCommand(Swerve swerve, Supplier<Double> xInput, Supplier<Double> yInput, Supplier<Double> rotInput) {
    this.swerve = swerve;

    this.xInput = xInput;
    this.yInput = yInput;
    this.rotInput = rotInput;

    addRequirements(swerve);
  }

  @Override
  public void execute() {
    swerve.drive(
      vxRateLimiter.calculate(deadband(xInput.get()) * Swerve.kMaxVelocity),
      vyRateLimiter.calculate(deadband(yInput.get()) * Swerve.kMaxVelocity),
      omegaRateLimiter.calculate(deadband(rotInput.get()) * Swerve.kMaxAngularVelocity)
    );
  }

  private double deadband(double joystickInput) {
    return Math.abs(joystickInput) >= kJoystickDeadband ? joystickInput : 0.0;
  }
}
