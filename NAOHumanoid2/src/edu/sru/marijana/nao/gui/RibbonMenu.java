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
import java.awt.Component;

import edu.sru.marijana.nao.connection.SynchronizedConnectDemo;

import edu.sru.marijana.nao.demo.AutomatedMotion;
import edu.sru.marijana.nao.demo.BarcodeScanner;
import edu.sru.marijana.nao.demo.Demo;
import edu.sru.marijana.nao.demo.DemoEnum;
import edu.sru.marijana.nao.demo.FistBumpDemo;
import edu.sru.marijana.nao.demo.HelloEarth;
import edu.sru.marijana.nao.demo.HelloWorldTutorial;
import edu.sru.marijana.nao.demo.Posture;
import edu.sru.marijana.nao.demo.SynchronizedDance;
import edu.sru.marijana.nao.demo.TicTacInteraction;
import edu.sru.marijana.nao.demo.Track;
import edu.sru.marijana.nao.demo.gui.ApplicationPanel;
import edu.sru.marijana.nao.demo.gui.ApplicationsOptionDialog;


public class RibbonMenu extends JPanel {
	
	private SynchronizedConnectDemo connect;
	private NAONewGui mainWindow;
	
	NewRobotPanel robotPanel;
	
	RibbonMenuButton connectButton;
	RibbonMenuButton disconnectButton;
	RibbonMenuButton exitButton;
	RibbonMenuButton playButton;
	RibbonMenuButton stopButton;
	
	JTabbedPane tabbedPane;
	//JTabbedPane applicationsTabbedPane;
	
	
	public RibbonMenu(NewRobotPanel robotPanel, NAONewGui mainWindow) {
		super();
		setSize(new Dimension(600,120));
		InitializeButtons();
		this.robotPanel = robotPanel;
		this.mainWindow = mainWindow;
	}
	
	public void InitializeButtons()
	{
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(tabbedPane);
		
		JPanel connectPanel = new JPanel();
		FlowLayout fl_connect = (FlowLayout) connectPanel.getLayout();
		fl_connect.setAlignment(FlowLayout.LEFT);
		tabbedPane.addTab("Connection", null, connectPanel, null);
		connectButton = new RibbonMenuButton("resources/connect.png", new Dimension(80, 80));
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		connectPanel.add(connectButton);
		
		disconnectButton = new RibbonMenuButton("resources/disconnect.png", new Dimension(80, 80));
		disconnectButton.setSize(new Dimension(80, 80));
		disconnectButton.setPreferredSize(new Dimension(80, 80));
		disconnectButton.setEnabled(false);
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disconnectAll();
			}
		});
		
		connectPanel.add(disconnectButton);
		
		RibbonMenuButton exitButton_1 = new RibbonMenuButton("resources/exit.png", new Dimension(80, 80));
		exitButton_1.setSize(new Dimension(80, 80));
		exitButton_1.setPreferredSize(new Dimension(80, 80));
		exitButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		connectPanel.add(exitButton_1);
		
		JPanel application = new JPanel();
		FlowLayout fl_application = (FlowLayout) application.getLayout();
		fl_application.setAlignment(FlowLayout.LEFT);
		tabbedPane.addTab("Applications", null, application, null);
		
		/**
		 * DropDownMenu added
		 * listing all possible application
		 * visible after robots are connected
		 * @author Marijana Risovic
		 */
		JComboBox comboBox = new JComboBox();
		int applicationsCount = DemoEnum.values().length;
		DemoEnum[] comboBoxOptions = new DemoEnum[applicationsCount];
		
		for(int i = 0; i < applicationsCount; i++) {
			comboBoxOptions[i] = DemoEnum.values()[i];
		}
		
		comboBox.setModel(new DefaultComboBoxModel(comboBoxOptions));
		application.add(comboBox);
		
		
		RibbonMenuButton playButton = new RibbonMenuButton("resources/play.png", new Dimension(80, 80));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDemo((DemoEnum)comboBox.getSelectedItem());
				
			}
		});
		
		application.add(playButton);
		
		RibbonMenuButton stopButton = new RibbonMenuButton("resources/stop.png", new Dimension(80, 80));
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//demo.exit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		application.add(stopButton);
		
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		tabbedPane.addTab("???", null, panel_1, null);
		
		RibbonMenuButton menuButton5 = new RibbonMenuButton("resources/playapp.png", new Dimension(80, 80));
		panel_1.add(menuButton5);
		
		RibbonMenuButton menuButton6 = new RibbonMenuButton("resources/playapp.png", new Dimension(80, 80));
		panel_1.add(menuButton6);
		
		RibbonMenuButton menuButton7 = new RibbonMenuButton("resources/playapp.png", new Dimension(80, 80));
		panel_1.add(menuButton7);
	}
	
	private void connect(){
		SynchronizedConnectDialog dialog = null;
		try {
			dialog = new SynchronizedConnectDialog(this);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tabbedPane.addTab("Connect", dialog);
		tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
		
		//robotConnect(dialog);
	}
	
	public void robotConnect(SynchronizedConnectDialog dialog)
	{
		ArrayList<String> ips = dialog.getIps();
		if(ips != null){
			System.out.println("IPs NOT NULL");
			if(connect == null){
				connect = new SynchronizedConnectDemo(ips);
				
				updateConnectedRobots(connect);
				new Update().start();
			}
			else{
				if(!connect.isRunning()){
					for(String ip : ips){
						connect.addRobot(ip);
					}
					updateConnectedRobots(connect);
					new Update().start();
				}
				else{
					for(String ip : ips){
						connect.addRobot(ip);
					}
					updateConnectedRobots(connect);
				}

			}
			
			disconnectButton.setEnabled(true);
		}
	}
	
	public void updateConnectedRobots(SynchronizedConnectDemo connect)
	{
		robotPanel.createNodes(connect);
		mainWindow.addRobotTabs(connect);
	}
	
	/* Disconnect all robots */
	private void disconnectAll()
	{
		if(connect == null)
		{
			return;
		}
		
		/* Stop all robots - close connection (includes clearing map containing all connected robots) */
		connect.stopAll();
		/* Update robot panel - re-create robot nodes */
		robotPanel.createNodes(connect);
		/* Disable disconnect button */
		disconnectButton.setEnabled(false);
		
		mainWindow.setDefaultTabbedPane();
	}
	
	private void exit(){
		disconnectAll();
		mainWindow.setVisible(false);
		mainWindow.dispose();
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
	
		
	private void addDemo(DemoEnum d){
		try
		{
			//Demo demo = null;
			if(connect.getAllInactiveNames().size() <=0 ){
				JOptionPane.showMessageDialog(this, "There are no inactive robots.");
			}
			else{
				
				tabbedPane.addTab(d.toString(), new ApplicationPanel(d , connect));
				tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
				
			} 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
