package edu.sru.marijana.nao.gui;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;

public class RobotCameraPanel extends JPanel{
	
	public RobotCameraPanel(String labelText)
	{
		super();
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		//setBounds(10, 10, 365, 365);
		setPreferredSize(new Dimension(450, 365));
		
		JTextField txtCAM = new JTextField();
		txtCAM.setHorizontalAlignment(SwingConstants.CENTER);
		txtCAM.setText(labelText);
		txtCAM.setColumns(10);
		add(txtCAM);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JButton btnNewButton_1 = new JButton("Disconnect");
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		add(btnNewButton_1);
		
		
	}
}
