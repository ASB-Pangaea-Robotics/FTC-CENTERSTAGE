package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.common.Hardware;

public class ActiveIntakeSubsystem extends SubsystemBase {

    private final Hardware robotHardware;

    private double power;

    ActiveIntakeSubsystem(Hardware robotHardware) {
        this.robotHardware = robotHardware;
    }

    public void setMotorPower(double power) {
        this.power = power;
    }

    public void read() {

    }

    public void loop() {

    }

    public void write() {
        robotHardware.intakeMotor.set(power);
    }

}
