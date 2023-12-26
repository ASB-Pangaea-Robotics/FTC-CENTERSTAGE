package org.firstinspires.ftc.teamcode.common.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;

import org.firstinspires.ftc.teamcode.common.Hardware;

public class ActiveIntakeSubsystem extends SubsystemBase {

    private final Hardware robotHardware;

    private double power;
    private double currentVel;

    public ActiveIntakeSubsystem(Hardware robotHardware) {
        this.robotHardware = robotHardware;
    }

    public void setMotorPower(double power) {
        this.power = power;
    }

    public void read() {
        currentVel = robotHardware.intakeMotor.getCorrectedVelocity();
    }

    public void loop() {

    }

    public void write() {
        robotHardware.intakeMotor.set(power);
    }

}
