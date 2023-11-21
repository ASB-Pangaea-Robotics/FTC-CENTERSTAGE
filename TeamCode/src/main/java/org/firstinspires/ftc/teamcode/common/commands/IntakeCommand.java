package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcontroller.external.samples.RobotHardware;
import org.firstinspires.ftc.teamcode.common.subsystems.ActiveIntakeSubsystem;

public class IntakeCommand extends CommandBase {

    ActiveIntakeSubsystem activeIntake;

    double power;

    public IntakeCommand(RobotHardware robotHardware, ActiveIntakeSubsystem activeIntake, double power) {
        this.activeIntake = activeIntake;
        this.power = power;
    }

    @Override
    public void execute() {
        activeIntake.setMotorPower(power);
    }
}
