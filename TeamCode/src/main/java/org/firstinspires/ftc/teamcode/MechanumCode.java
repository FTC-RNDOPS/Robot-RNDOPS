package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp

public class MechanumCode extends LinearOpMode {

        private DcMotor dtFR = null;
        private DcMotor dtFL = null;
        private DcMotor dtBR = null;
        private DcMotor dtBL = null;
        double dtFLPwr = 0;
        double dtBRPwr = 0;
        double dtBLPwr = 0;
        double dtFRPwr = 0;
        double x = 0;
        double y = 0;
        double rotX = 0;

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

            dtBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dtBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dtFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dtFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            dtBL.setDirection(DcMotorSimple.Direction.REVERSE);
            dtFL.setDirection(DcMotorSimple.Direction.REVERSE);

            telemetry.addData("Status", "Initialized");
            telemetry.update();

            waitForStart();

            while(opModeIsActive()){
                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rotX), 1);
                y = -gamepad1.left_stick_y;
                x = gamepad1.right_stick_x * 1.1;
                rotX = gamepad1.left_stick_x;

                dtBLPwr = (y + x - rotX) / denominator;
                dtBRPwr = (y - x + rotX) / denominator;
                dtFLPwr = (y + x + rotX) / denominator;
                dtFRPwr = (y - x - rotX) / denominator;

                dtBL.setPower(dtBLPwr);
                dtBR.setPower(dtBRPwr);
                dtFL.setPower(dtFLPwr);
                dtFR.setPower(dtFRPwr);
            }
        }
    }

