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
        private DcMotor Elevator = null;
        // Define "Regular" Motor Speeds
        double dtFLPwr = 0;
        double dtBRPwr = 0;
        double dtBLPwr = 0;
        double dtFRPwr = 0;
        // Define "Sprint Mode" Motor Speeds
        double dtFLPwrSprint = 0;
        double dtBRPwrSprint = 0;
        double dtBLPwrSprint = 0;
        double dtFRPwrSprint = 0;
        // Define Movement Axies
        double x = 0;
        double y = 0;
        double rotX = 0;
        // Sprint Mode Var
        boolean SprintMode = false;
        // End Of Arm Up And Down
        boolean ElevatorDown = false;
        boolean ElevatorUp = false;
        int ElevatorPos = 0;
        int MaxElevatorPos = 10;

        @SuppressLint("SuspiciousIndentation")
        @Override

        public void runOpMode() {
            dtFR = hardwareMap.get(DcMotor.class, "dtFR");
            dtFL = hardwareMap.get(DcMotor.class, "dtFL");
            dtBR = hardwareMap.get(DcMotor.class, "dtBR");
            dtBL = hardwareMap.get(DcMotor.class, "dtBL");
            Elevator = hardwareMap.get(DcMotor.class, "Elevator");

            dtBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            dtBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            dtFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            dtFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            dtBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dtBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dtFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            dtFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            // Not running with encoder because we want to give driver control
            // Elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            dtBL.setDirection(DcMotorSimple.Direction.REVERSE);
            dtFL.setDirection(DcMotorSimple.Direction.REVERSE);

            telemetry.addData("Status", "Initialized");
            telemetry.update();

            waitForStart();

            while(opModeIsActive()){

                // ============== Elevator Code ============== //

                ElevatorDown = false;
                ElevatorUp = false;
                ElevatorPos = Elevator.getCurrentPosition();

                if ((gamepad1.dpad_up) & !(gamepad1.dpad_down) & !(ElevatorPos >= MaxElevatorPos)) {
                    ElevatorUp = true;
                }
                if (gamepad1.dpad_down & !gamepad1.dpad_up) {
                    ElevatorDown = true;
                }
                if (ElevatorUp == true) {
                    Elevator.setPower(0.1);
                } else if (ElevatorDown == true) {
                    Elevator.setPower(-0.1);
                } else {
                    Elevator.setPower(0);
                }

                // ============== Drive Train Code ============== //

                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rotX), 1);
                y = -gamepad1.left_stick_y;
                x = gamepad1.right_stick_x * 1.1;
                rotX = gamepad1.left_stick_x;

                if (gamepad1.right_bumper) {
                    SprintMode = true
                } 
                if (gamepad1.left_bumper) {
                    SprintMode = false
                }

                dtBLPwr = (y + x - rotX) / denominator;
                dtBRPwr = (y - x + rotX) / denominator;
                dtFLPwr = (y + x + rotX) / denominator;
                dtFRPwr = (y - x - rotX) / denominator;

                dtBLPwrSprint = ((y + x - rotX) / denominator)*2;
                dtBRPwrSprint = ((y + x - rotX) / denominator)*2;
                dtFLPwrSprint = ((y + x - rotX) / denominator)*2;
                dtBRPwrSprint = ((y + x - rotX) / denominator)*2;

                if (SprintMode == true) {
                    dtBL.setPower(dtBLPwrSprint)
                    dtBR.setPower(dtBRPwrSprint);
                    dtFL.setPower(dtFLPwrSprint);
                    dtFR.setPower(dtFRPwrSprint);
                    telemetry.addData("dtBL Current Pwr:", dtBLPwrSprint);
                    telemetry.addData("dtBR Current Pwr:", dtBRPwrSprint);
                    telemetry.addData("dtFL Current Pwr:", dtFLPwrSprint);
                    telemetry.addData("dtFR Current Pwr:", dtFRPwrSprint);
                } else {
                    dtBL.setPower(dtBLPwr);
                    dtBR.setPower(dtBRPwr);
                    dtFL.setPower(dtFLPwr);
                    dtFR.setPower(dtFRPwr);
                    telemetry.addData("dtBL Current Pwr:", dtBLPwr);
                    telemetry.addData("dtBR Current Pwr:", dtBRPwr);
                    telemetry.addData("dtFL Current Pwr:", dtFLPwr);
                    telemetry.addData("dtFR Current Pwr:", dtFRPwr);
                }
                telemetry.addDate("Sprint Mode Status",SprintMode;);
                telemetry.update();
            }
        }
    }
