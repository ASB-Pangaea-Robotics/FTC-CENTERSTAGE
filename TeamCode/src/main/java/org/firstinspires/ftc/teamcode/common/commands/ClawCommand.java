package org.firstinspires.ftc.teamcode.common.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.common.subsystems.OuttakeSubsystem;

public class ClawCommand extends CommandBase {

    OuttakeSubsystem outtake;
    public ClawCommand(OuttakeSubsystem outtake, double pos) {
        this.outtake = outtake;
    }

    @Override
    public void execute() {

    }
}
