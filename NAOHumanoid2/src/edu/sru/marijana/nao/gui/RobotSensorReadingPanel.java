package edu.sru.marijana.nao.gui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;

public class RobotSensorReadingPanel extends JPanel {

	public RobotSensorReadingPanel(String labelText)
	{
		super();
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		//setBounds(10, 385, 365, 205);
		setPreferredSize(new Dimension(365, 205));
		setLayout(null);
		
		JTextField txtArmsSensorReading = new JTextField();
		txtArmsSensorReading.setHorizontalAlignment(SwingConstants.CENTER);
		txtArmsSensorReading.setBounds(105, 85, 150, 20);
		txtArmsSensorReading.setText(labelText);
		txtArmsSensorReading.setColumns(10);
		
		add(txtArmsSensorReading);
		
	}
}
