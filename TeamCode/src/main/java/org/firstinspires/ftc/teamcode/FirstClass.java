/*
11/01 testing this again :(
things to do:
11/10 Need to add stuff for turning and strafing....
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class FirstClass extends LinearOpMode {
    double tgtPower = 0;

    @Override

    public void runOpMode(){

        DcMotor dtFR = hardwareMap.get(DcMotor.class, "dtFR");
        DcMotor dtFL = hardwareMap.get(DcMotor.class, "dtFL");
        DcMotor dtBR = hardwareMap.get(DcMotor.class, "dtBR");
        DcMotor dtBL = hardwareMap.get(DcMotor.class, "dtBL");

        dtFR.setDirection(DcMotorSimple.Direction.REVERSE);
        dtBR.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();


        //Nothing after this line happens until you press play on the driver station.
        waitForStart();

        while(opModeIsActive()){
            tgtPower = -this.gamepad1.left_stick_y;
            dtFR.setPower(tgtPower);
            dtFL.setPower(tgtPower);
            dtBR.setPower(tgtPower);
            dtBL.setPower(tgtPower);
            telemetry.addData("target power", tgtPower);
            telemetry.addData("motor power", dtFR.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
}
