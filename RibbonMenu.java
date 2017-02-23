package edu.sru.marijana.nao.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;

public class RibbonMenu extends JPanel {
	
	public RibbonMenu() {
		super();
		setSize(new Dimension(600,120));
		InitializeButtons();
		
	}
	
	public void InitializeButtons()
	{
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(tabbedPane);
		
		JPanel connect = new JPanel();
		FlowLayout fl_connect = (FlowLayout) connect.getLayout();
		fl_connect.setAlignment(FlowLayout.LEFT);
		tabbedPane.addTab("Connection", null, connect, null);
		RibbonMenuButton connectButton = new RibbonMenuButton("CONNECT", "resources/connect.png", new Dimension(80, 80));
		connectButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		connect.add(connectButton);
		
		RibbonMenuButton disconnectButton = new RibbonMenuButton("DISCONNECT", "resources/disconnect.png", new Dimension(100, 80));
		disconnectButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		connect.add(disconnectButton);
		
		JPanel application = new JPanel();
		FlowLayout fl_application = (FlowLayout) application.getLayout();
		fl_application.setAlignment(FlowLayout.LEFT);
		tabbedPane.addTab("Application", null, application, null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Hello Word", "Tic Tac Toe", "Red Ball"}));
		application.add(comboBox);
		
		RibbonMenuButton playButton = new RibbonMenuButton("PLAY", "resources/play.png", new Dimension(80, 80));
		application.add(playButton);
		
		RibbonMenuButton stopButton = new RibbonMenuButton("STOP", "resources/stop.png", new Dimension(80, 80));
		application.add(stopButton);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		tabbedPane.addTab("???", null, panel_1, null);
		
		RibbonMenuButton menuButton5 = new RibbonMenuButton("???", "resources/playapp.png", new Dimension(80, 80));
		panel_1.add(menuButton5);
		
		RibbonMenuButton menuButton6 = new RibbonMenuButton("???", "resources/playapp.png", new Dimension(80, 80));
		panel_1.add(menuButton6);
		
		RibbonMenuButton menuButton7 = new RibbonMenuButton("???", "resources/playapp.png", new Dimension(80, 80));
		panel_1.add(menuButton7);
	}

}
