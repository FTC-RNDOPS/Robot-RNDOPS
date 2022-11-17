package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous

public class Red_Auto extends LinearOpMode  {

    private DcMotor dtFR = null;
    private DcMotor dtFL = null;
    private DcMotor dtBR = null;
    private DcMotor dtBL = null;


    @SuppressLint("SuspiciousIndentation")
    @Override
    public void runOpMode() {
        dtFR = hardwareMap.get(DcMotor.class, "dtFR");
        dtFL = hardwareMap.get(DcMotor.class, "dtFL");
        dtBR = hardwareMap.get(DcMotor.class, "dtBR");
        dtBL = hardwareMap.get(DcMotor.class, "dtBL");

        dtBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dtBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dtFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dtFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        dtBL.setDirection(DcMotorSimple.Direction.REVERSE);
        dtFL.setDirection(DcMotorSimple.Direction.REVERSE);

        dtBL.setTargetPosition(3000);
        dtBR.setTargetPosition(-3000);
        dtFL.setTargetPosition(-3000);
        dtFR.setTargetPosition(3000);

        dtBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dtBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dtFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dtFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            dtFL.setPower(0.5);
            dtFR.setPower(0.5);
            dtBL.setPower(0.5);
            dtBR.setPower(0.5);
        }
    }
}



