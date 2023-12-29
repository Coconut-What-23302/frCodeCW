package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Helper.RobotHardware;

@TeleOp
public class scoreDebug extends LinearOpMode {
    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            double frontLeftPower = y - x + rx;
            double backLeftPower = y + x + rx;
            double frontRightPower = y + x - rx;
            double backRightPower = y - x - rx;

            robot.frontLeft.setPower(frontLeftPower);
            robot.backLeft.setPower(backLeftPower);
            robot.frontRight.setPower(frontRightPower);
            robot.backRight.setPower(backRightPower);

            int armPosition = 0;

            if (gamepad1.left_bumper || gamepad1.right_bumper) {
                if (gamepad1.left_bumper) {
                    armPosition += 1;
                } else if (gamepad1.right_bumper) {
                    armPosition -= 1;
                }

                robot.clawArm.setTargetPosition(armPosition);
                robot.clawArm.setPower(0.2);
                robot.clawArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }



            if(gamepad1.right_trigger > .5) {
                robot.clawWrist.setPosition(robot.clawWrist.getPosition() + .01);
            }

            if(gamepad1.left_trigger > .5) {
                robot.clawWrist.setPosition(robot.clawWrist.getPosition() - .01);
            }

            if(gamepad2.right_trigger > .5) {
                robot.leftClaw.setPosition(robot.leftClaw.getPosition() + .01);
            }
            if (gamepad2.left_trigger > .5) {
                robot.leftClaw.setPosition(robot.leftClaw.getPosition() - .01);
            }
            if (gamepad2.right_bumper) {
                robot.rightClaw.setPosition(robot.rightClaw.getPosition() + .01);
            }
            if (gamepad2.left_bumper) {
                robot.rightClaw.setPosition(robot.rightClaw.getPosition() - .01);
            }

            telemetry.addData("Arm Position", robot.clawArm.getCurrentPosition());
            telemetry.addData("wrist position", robot.clawWrist.getPosition());
            telemetry.addData("left claw position", robot.leftClaw.getPosition());
            telemetry.addData("right claw position", robot.rightClaw.getPosition());
            telemetry.update();
        }
    }
}
