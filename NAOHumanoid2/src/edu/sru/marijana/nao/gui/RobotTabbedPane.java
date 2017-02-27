package edu.sru.marijana.nao.gui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;

public class RobotTabbedPane extends JTabbedPane{
	
	private RobotCameraPanel camera1Panel;
	private RobotCameraPanel camera2Panel;
	
	private RobotSensorReadingPanel legsSensorReadingPanel;
	private RobotSensorReadingPanel armsSensorReadingPanel;
	
	public RobotTabbedPane()
	{
		super(JTabbedPane.TOP);
		setSize(new Dimension(500, 500));
		setPreferredSize(new Dimension(500, 500));
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		//AddRobotPanel("x");
	}
	
	public void AddRobotPanel(String robotName)
	{
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		addTab(robotName, null, panel, null);
		
		camera1Panel = new RobotCameraPanel("C A M E R A   1");
		camera2Panel = new RobotCameraPanel("C A M E R A   2");
		
		JPanel cameraPanels = new JPanel();
		cameraPanels.setLayout(new GridLayout(2, 1, 20, 20));
		
		cameraPanels.add(camera1Panel);
		cameraPanels.add(camera2Panel);
		
		armsSensorReadingPanel = new RobotSensorReadingPanel("ARMS SENSOR READING");
		legsSensorReadingPanel = new RobotSensorReadingPanel("LEGS SENSOR READING");
		
		JPanel sensorReadingPanels = new JPanel();
		sensorReadingPanels.setLayout(new GridLayout(2, 1, 20, 20));
		
		sensorReadingPanels.add(armsSensorReadingPanel);
		sensorReadingPanels.add(legsSensorReadingPanel);
		
		sensorReadingPanels.setPreferredSize(new Dimension(365, 800));
		
		panel.setLayout(new BorderLayout(20, 20));
		
		panel.add(cameraPanels, BorderLayout.CENTER);
		panel.add(sensorReadingPanels, BorderLayout.EAST);
		
	}

}
