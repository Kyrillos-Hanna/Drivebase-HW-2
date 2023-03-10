// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//imports CANSparkMax class
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//imports the MotorControllerGroup class
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//imports AHRS class
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort.Port;
//imports differential drive class
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.IllegalArgumentException;

public class Drivebase extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Drivebase() {
    m_rightMaster.setInverted(true);
  }
  //creates motor objects
  public static final CANSparkMaxLowLevel.MotorType kbrush = CANSparkMaxLowLevel.MotorType.kBrushed;
  CANSparkMax m_leftMaster  = new CANSparkMax(1, kbrush);
  CANSparkMax m_leftSlave   = new CANSparkMax(2, kbrush);
  CANSparkMax m_leftSlave2  = new CANSparkMax(3, kbrush);
  CANSparkMax m_rightMaster = new CANSparkMax(4, kbrush);
  CANSparkMax m_rightSlave  = new CANSparkMax(5, kbrush);
  CANSparkMax m_rightSlave2 = new CANSparkMax(6, kbrush);
  
  //creates MotorControllerGroup objects
  MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightMaster, m_rightSlave, m_rightSlave2);
  MotorControllerGroup m_leftGroup  = new MotorControllerGroup(m_leftMaster,  m_leftSlave,  m_leftSlave2 );

  //makes an object of the DifferentialDive class
  DifferentialDrive m_differentialDrive = new DifferentialDrive(m_leftGroup, m_rightGroup);

  //makes a method that calls arcadeDrive
  public void arcadeDrive(double speed, double rotation) {
    m_differentialDrive.arcadeDrive(speed, rotation);
  }
  //makes an object of the AHRS class
  private final AHRS m_AHRS = new AHRS(Port.kMXP);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Angle", m_AHRS.getAngle());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
