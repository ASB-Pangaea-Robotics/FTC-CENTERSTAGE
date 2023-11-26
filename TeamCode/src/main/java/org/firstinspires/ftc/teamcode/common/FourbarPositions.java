package org.firstinspires.ftc.teamcode.common;

public enum FourbarPositions {

    GRAB_PIXEL(0),
    PLACE_GROUND(100),
    PLACE_BACKBOARD(300);

    private final int position;

    FourbarPositions(int position) {this.position = position;}

    public double getPosition(FourbarPositions position) {return position.position;}
}
