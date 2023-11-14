package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;

import java.io.File;
import java.io.IOException;

public class Swerve extends SubsystemBase {
    public static final double kMaxVelocity = 1.5; // m/s
    public static final double kMaxAngularVelocity = 1.5 * Math.PI; // rad/s
    private SwerveDrive swerveDrive = null; // Instance of SwerveDrive

    public static final double kMaxAcceleration = 1.5; // m/s^2
    public static final double kMaxAngularAcceleration = 1.5 * Math.PI; // rads/s^2

    private boolean fieldRelative = true; // Field relative driving

    /**
     * Constructor of swerve
     */
    public Swerve() {
        try {
            // Load swerve JSON files from deploy/swerve
            File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
            swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive();
        } catch (IOException e) {
            // Print error if directory does not open properly
            System.out.println("ERROR: could not open deply/swerve");
        }
    }

    /**
     * Wrapper method for SwerveDrive.drive
     *
     * @param vx Velocity in x direction
     * @param vy Velocity in the y direction
     * @param omega Angular velocity
     */
    public void drive(double vx, double vy, double omega) {
        swerveDrive.drive(
          new Translation2d(vx, vy),
          omega,
          false,
          false
        );
    }

    public void toggleFieldRelative() {
        fieldRelative = !fieldRelative;
    }
}
