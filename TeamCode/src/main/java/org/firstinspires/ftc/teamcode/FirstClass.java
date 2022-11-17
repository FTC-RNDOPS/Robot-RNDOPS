package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp

public class FirstClass<motor, frPos> extends LinearOpMode {
    private DcMotor dtFR = null;
    private DcMotor dtFL = null;
    private DcMotor dtBR = null;
    private DcMotor dtBL = null;
    double tgtPower = 0;
    double turnPower = 0;

    @SuppressLint("SuspiciousIndentation")
    @Override

    public void runOpMode(){

        dtFR = hardwareMap.get(DcMotor.class, "dtFR");
        dtFL = hardwareMap.get(DcMotor.class, "dtFL");
        dtBR = hardwareMap.get(DcMotor.class, "dtBR");
        dtBL = hardwareMap.get(DcMotor.class, "dtBL");

        dtBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dtBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dtFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dtFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        dtBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dtBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dtFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dtFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        dtFR.setDirection(DcMotorSimple.Direction.REVERSE);
        dtBR.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //Nothing after this line happens until you press play on the driver station.
        waitForStart();

        while(opModeIsActive()){
            turnPower = this.gamepad1.right_stick_x;
            tgtPower = -this.gamepad1.left_stick_y;
            //totPower =
            dtFR.setPower(tgtPower-turnPower);
            dtFL.setPower(tgtPower+turnPower);
            dtBR.setPower(tgtPower-turnPower);
            dtBL.setPower(tgtPower+turnPower);
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