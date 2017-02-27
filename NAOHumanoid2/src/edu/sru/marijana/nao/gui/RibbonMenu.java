package edu.sru.marijana.nao.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;

import edu.sru.marijana.nao.connection.Robot;
import edu.sru.marijana.nao.connection.SynchronizedConnect;
import edu.sru.marijana.nao.connection.SynchronizedConnectDemo;

public class RibbonMenu extends JPanel {
	
	private SynchronizedConnectDemo connect;
	
	NewRobotPanel robotPanel;
	
	RibbonMenuButton connectButton;
	RibbonMenuButton disconnectButton;
	
	public RibbonMenu(NewRobotPanel robotPanel) {
		super();
		setSize(new Dimension(600,120));
		InitializeButtons();
		this.robotPanel = robotPanel;
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
		connectButton = new RibbonMenuButton("CONNECT", "resources/connect.png", new Dimension(80, 80));
		connectButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		connect.add(connectButton);
		
		disconnectButton = new RibbonMenuButton("DISCONNECT", "resources/disconnect.png", new Dimension(100, 80));
		disconnectButton.setEnabled(false);
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
	
	private void connect(){
		SynchronizedConnectDialog dialog = null;
		try {
			dialog = new SynchronizedConnectDialog();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<String> ips = dialog.getIps();
		if(ips != null){
			if(connect ==null){
				connect = new SynchronizedConnectDemo(ips);
				//mnRun.setEnabled(true);
				//mnOptions.setEnabled(true);
				disconnectButton.setEnabled(true);
				robotPanel.createNodes(connect);
				new Update().start();
			}
			else{
				if(!connect.isRunning()){
					for(String ip : ips){
						connect.addRobot(ip);
					}
					robotPanel.createNodes(connect);
					new Update().start();
				}
				else{
					for(String ip : ips){
						connect.addRobot(ip);
					}
					robotPanel.createNodes(connect);
				}

			}
		}
	}
	
	private class Update extends Thread{
		public void run(){
			while(connect.isRunning()){
				refresh();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private void refresh(){
		robotPanel.update();
	}

}
