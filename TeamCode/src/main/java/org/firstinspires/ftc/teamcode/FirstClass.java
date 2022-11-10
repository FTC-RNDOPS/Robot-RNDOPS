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
    //private DcMotor motor = null;
    double tgtPower = 0;
    double turnPower = 0;
    boolean liftPower = false;
    double halfSpeed = 0;
   // private int frPos;
   // private int flPos;
   // private int brPos;
    //private int blPos;
   // private int motorPos;



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
        //motor  = hardwareMap.get(DcMotor.class, "dcMotor");

        dtBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dtBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dtFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dtFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        dtFR.setDirection(DcMotorSimple.Direction.REVERSE);
        dtBR.setDirection(DcMotorSimple.Direction.REVERSE);

        //flPos = 0;
        //frPos = 0;
       // blPos = 0;
       // brPos = 0;
       // motorPos = 0;

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
            //start of code attempt for "lifter Motor"
           // liftPower = this.gamepad1.a;
           // if (liftPower == true) {
               // motor.setPower(1.0);
            //}
            //^^^I hope this works???? Taking a shot in the dark here!
            //start of code attempt for 50% reduced speed.
            halfSpeed = this.gamepad1.right_trigger;
            if (halfSpeed > 0.1) {
                dtFR.setPower(tgtPower/2-turnPower/2);
                dtFL.setPower(tgtPower/2+turnPower/2);
                dtBR.setPower(tgtPower/2-turnPower/2);
                dtBL.setPower(tgtPower/2+turnPower/2);

            }





        }
    }
}
// connect to robot wifi,
// cd Desktop/platform-tools
// ./adb connect 192.168.42.1
