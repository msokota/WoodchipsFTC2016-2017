/*
    Team 10603's Hardware Testing OpMode
        Written by John Gaidimas
 */
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.RobotLog;
import org.firstinspires.ftc.teamcode.Hardware10603;

@TeleOp(name="10603: TeleOp", group="10603")

public class TeleOp10603 extends LinearOpMode {

    /* Declare OpMode members. */
    Hardware10603 robot           = new Hardware10603();   // Use a Pushbot's hardware

    @Override
    public void runOpMode() throws InterruptedException {
        double left;
        double right;
        double max;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Would you like to play a game?");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            right = gamepad1.right_stick_y;
            left = gamepad1.left_stick_y;

            max = Math.max(Math.abs(left), Math.abs(right));
            if (max > 1.0)
            {
                left /= max;
                right /= max;
            }

            robot.rightPower.setPower(right);
            robot.leftPower.setPower(left);

            //While the A button is pressed, the launch motors will spin
            if(gamepad1.a){
                robot.leftLaunch.setPower(1.0);
                robot.rightLaunch.setPower(-1.0);
                telemetry.addData("Particle Launcher","Engaged");
                telemetry.update();
            }
            if(gamepad1.a != true){
                robot.leftLaunch.setPower(0);
                robot.rightLaunch.setPower(0);
                telemetry.addData("Particle Launcher","Disengaged");
                telemetry.update();
            }

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}

