package edu.sru.marijana.nao.demo.gui;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

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

import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class ApplicationPanel extends JPanel {

	private Demo demo;
	private DemoEnum demoType;
	private ArrayList<String> robotNames;
	private String title;
	private JPanel robotsPanel;
	private JPanel demoPanel;
	private JPanel robotOptionDialog;
	
	ApplicationsOptionDialog applicationsOptionDialog;
	SynchronizedConnectDemo connect;
	
	public ApplicationPanel(DemoEnum d, SynchronizedConnectDemo connect) {
		
		this.connect = connect;
		this.demoType = d;
		
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		applicationsOptionDialog = new ApplicationsOptionDialog(connect, this);
		add(applicationsOptionDialog);
		
		robotOptionDialog = new JPanel();
		robotOptionDialog.add(applicationsOptionDialog);
		
		add(robotOptionDialog);
		
		robotsPanel = new JPanel();
		robotsPanel.setPreferredSize(new Dimension(200, 80));
		robotsPanel.setMinimumSize(new Dimension(100, 50));
		robotsPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "ConnectedRobots", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		demoPanel = new JPanel();
		demoPanel.add(robotsPanel);
		
		JButton btnStopApplication = new JButton("Stop Application");
		btnStopApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(isActive())
						demo.exit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		demoPanel.add(btnStopApplication);
		
		add(demoPanel);
		demoPanel.setVisible(false);
		
		setPreferredSize(new Dimension(600, 100));
		new CheckThread().start();
	}
	
	public void startDemo(String robotName)
	{
		demo = createDemo(demoType, robotName);
		
		for(String str : demo.getRobotNames()){
			JTextPane robot = new JTextPane();
			robot.setPreferredSize(new Dimension(50, 50));
			robot.setBackground(SystemColor.menu);
			robot.setText(str);
			robot.setEditable(false);
			robotsPanel.add(robot);
		}
		robotsPanel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), demo.getDemoName(), TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		try {
			demo.start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		demoPanel.setVisible(true);
		robotOptionDialog.setVisible(false);
	}
	
	private Demo createDemo(DemoEnum enumVal, String robotName){
		Demo demo = null;
		if(connect.getAllInactiveNames().size() <=0 ){
			JOptionPane.showMessageDialog(this, "There are no inactive robots.");
		}
		else{
			
			switch(enumVal){
				case HelloWorld:
					try{
						demo = new HelloWorldTutorial(connect, robotName);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					break;
				case HelloEarth:
					try{
						demo = new HelloEarth(connect, robotName);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					break;
				case FistBump:
					try{
						demo = new FistBumpDemo(connect, robotName);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					break;
				case BarcodeScanner:
					try {
						demo = new BarcodeScanner(connect, robotName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case Track:
					try {
						demo = new Track(connect, robotName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case AutomatedMotion:
					try {
						demo = new AutomatedMotion(connect, robotName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
		
				case Posture:
					try {
						demo = new Posture(connect, robotName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case SynchronizedDance:
					try {
						demo = new SynchronizedDance(connect);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case TicTacToeNAO:
					try {
						demo = new TicTacInteraction(connect, robotName);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
		
				default:
					break;	
			}
		}
		
		return demo;
	}
	
	
	public void stopDemo(){
		try {
			demo.exit();
			exit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(isActive()){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		exit();
	}
	private void exit(){
		getParent().remove(this);
	}
	
	private class CheckThread extends Thread{
		public void run(){
			while(demo == null || demo.isRunning()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			exit();
		}
	}
	public boolean isActive(){
		return demo != null && demo.isRunning();
	}
}
