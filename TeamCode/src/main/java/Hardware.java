import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

 public class Hardware {
    //Create motors for the wheels only.
    public DcMotor motorFrontRightWheel = null;
    public DcMotor motorBackRightWheel = null;
    public DcMotor motorFrontLeftWheel = null;
    public DcMotor motorBackLeftWheel = null;

    //Create aux motors.
    public DcMotor motorElevator = null;

    //Create servos.
    public Servo servoGripper = null;

    //
    HardwareMap hardwareMap = null;
    public ElapsedTime runTime = new ElapsedTime();

    public Hardware(HardwareMap hwMap){
        initialize(hwMap);
    }

    private void initialize(HardwareMap hwMap){
        hardwareMap = hwMap;
        motorFrontRightWheel = hardwareMap.get(DcMotor.class, "motorFRW");
        motorFrontLeftWheel = hardwareMap.get(DcMotor.class, "motorFLW");
        motorBackRightWheel = hardwareMap.get(DcMotor.class, "motorBRW");
        motorBackLeftWheel = hardwareMap.get(DcMotor.class, "motorBLW");
        servoGripper = hardwareMap.get(Servo.class, "servoGripper");

        //setup motor direction
        motorFrontRightWheel.setDirection(DcMotor.Direction.FORWARD);
        motorBackRightWheel.setDirection(DcMotor.Direction.FORWARD);
        motorBackLeftWheel.setDirection(DcMotor.Direction.REVERSE);
        motorFrontLeftWheel.setDirection(DcMotor.Direction.REVERSE);

        //reset encoders
        motorFrontRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFrontLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackRightWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBackLeftWheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //run with encoders
        motorFrontRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorFrontLeftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackRightWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBackLeftWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Zero power behaviour
        motorFrontRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorFrontLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackLeftWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorBackRightWheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //init to 0
        motorFrontRightWheel.setPower(0);
        motorBackLeftWheel.setPower(0);
        motorBackRightWheel.setPower(0);
        motorFrontLeftWheel.setPower(0);
    }

}
