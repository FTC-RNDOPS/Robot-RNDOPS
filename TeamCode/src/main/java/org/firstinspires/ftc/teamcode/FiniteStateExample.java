package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Disabled //this will leave it off the robot. Don't worry about this.



public class FiniteStateExample extends LinearOpMode {
    private final DcMotor lifterMotor = null;
    enum lifterHeight {
        floor, lowPole, mediumPole, highPole,
        eoatGrab, eoatRelease
    }

    public void runOpMode() {
        DcMotor lifterMotor = hardwareMap.get(DcMotor.class, "lifterMotor");


    }


}
