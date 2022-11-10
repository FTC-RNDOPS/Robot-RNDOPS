package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class FirstClass extends LinearOpMode {
    private DcMotor dtFR = null;
    private DcMotor dtFL = null;
    private DcMotor dtBR = null;
    private DcMotor dtBL = null;
    double tgtPower = 0;

    @Override

    public void runOpMode(){

        dtFR = hardwareMap.get(DcMotor.class, "dtFR");
        dtFL = hardwareMap.get(DcMotor.class, "dtFL");
        dtBR = hardwareMap.get(DcMotor.class, "dtBR");
        dtBL = hardwareMap.get(DcMotor.class, "dtBL");

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
// connect to robot wifi,
// cd Desktop/platform-tools
// ./adb connect 192.168.42.1
// adb devices.