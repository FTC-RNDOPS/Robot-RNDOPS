package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "LeftCorner", group = "Robot")

public class AutoRun_Left_Corner extends LinearOpMode{
    private DcMotor frontRight = hardwareMap.get(DcMotor.class, "dtFR");
    private DcMotor frontLeft = hardwareMap.get(DcMotor.class, "dtFL");
    private DcMotor backRight = hardwareMap.get(DcMotor.class, "dtBR");
    private DcMotor backLeft = hardwareMap.get(DcMotor.class, "dtBL");

    private ElapsedTime runTime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1440;
    static final double DRIVE_GEAR_REDUCTION = 1.0;
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = ((COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                            (WHEEL_DIAMETER_INCHES * Math.PI));
    static final double DRIVE_SPEED = 0.25;
    static final double TURN_SPEED = 0.5;

    @Override
    public void runOpMode(){

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);

        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        driveCommand(DRIVE_SPEED, 20, 20, 5);
        driveCommand(TURN_SPEED, 10, 0, 5);
    }

    public void driveCommand(double driveSpeed, double leftInches, double rightInches, int timeOutSec) {
        int newFlTarget;
        int newBlTarget;
        int newFrTarget;
        int newBrTarget;

        if (opModeIsActive()){
            //left wheels
            newFlTarget = frontLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newBlTarget = backLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
           //right wheels
            newFrTarget = frontRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newBrTarget = backRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);

            frontLeft.setTargetPosition(newFlTarget);
            backLeft.setTargetPosition(newBlTarget);
            frontRight.setTargetPosition(newFrTarget);
            backRight.setTargetPosition(newBrTarget);

            runTime.reset();
            frontLeft.setPower(Math.abs(driveSpeed));
            backLeft.setPower(Math.abs(driveSpeed));
            frontRight.setPower(Math.abs(driveSpeed));
            backRight.setPower(Math.abs(driveSpeed));

            while (opModeIsActive() && (runTime.seconds() < timeOutSec) &&
            (frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy())){
                telemetry.addData("running to ", "%7d :%7d :%7d :%7d", newBlTarget, newBrTarget, newFlTarget, newFrTarget);
                telemetry.update();
            }
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }
    }

}
