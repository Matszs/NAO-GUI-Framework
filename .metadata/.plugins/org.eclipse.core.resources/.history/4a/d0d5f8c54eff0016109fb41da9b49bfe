package edu.sru.marijana.nao.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import edu.sru.brian.tictactoegame.TicTacInteraction;
import edu.sru.thangiah.nao.connection.SynchronizedConnectDemo;
import edu.sru.thangiah.nao.demo.AutomatedMotion;
import edu.sru.thangiah.nao.demo.BarcodeScanner;
import edu.sru.thangiah.nao.demo.Demo;
import edu.sru.thangiah.nao.demo.DemoEnum;
import edu.sru.thangiah.nao.demo.FistBumpDemo;
import edu.sru.thangiah.nao.demo.HelloEarth;
import edu.sru.thangiah.nao.demo.HelloWorldTutorial;
import edu.sru.thangiah.nao.demo.Posture;
import edu.sru.thangiah.nao.demo.SynchronizedDance;
import edu.sru.thangiah.nao.demo.Track;
import edu.sru.thangiah.nao.demo.gui.CameraDemoFrame;
import edu.sru.thangiah.nao.demo.gui.DiagnosticGUI;
import edu.sru.thangiah.nao.demo.gui.SynchronizedConnectDialog;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Window.Type;

public class NAONewGui extends JFrame {

	private JPanel contentPanel;
	private NewRobotPanel newRobotPanel;
	private ArrayList<Demo> demos;				//for loading all the demos
	private SynchronizedConnectDemo connect;

	
	private RibbonMenu ribbonMenu;
	RobotTabbedPane robotTabbedPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NAONewGui frame = new NAONewGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NAONewGui() {
		//demos = new ArrayList<Demo>();
		connect = null;
		setTitle("Nao New Gui");

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(60, 0, 1280, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout(0, 0));

		/* Add new robot panel - for robot nodes to be listed once robots are connected */
		newRobotPanel = new NewRobotPanel();
		newRobotPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.add(newRobotPanel, BorderLayout.WEST);
		
		/* Add ribbon menu */
		ribbonMenu = new RibbonMenu(newRobotPanel);
		ribbonMenu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		contentPanel.add(ribbonMenu, BorderLayout.NORTH);
		
		/* Robots tabbed panel - with tabs for each robot */
		robotTabbedPane = new RobotTabbedPane();
		robotTabbedPane.AddRobotPanel("Robots");
		
		contentPanel.add(robotTabbedPane);
		
		WindowListener exitListener = new WindowAdapter() {

		    @Override
		    public void windowClosing(WindowEvent e) {
		    	exit();
		    }

			private void exit() {
				// TODO Auto-generated method stub
				
			}
		};
		addWindowListener(exitListener);
	}
	
	
	
	/*private void connect(){
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
				newRobotPanel.createNodes(connect);
				new Update().start();
			}
			else{
				if(!connect.isRunning()){
					for(String ip : ips){
						connect.addRobot(ip);
					}
					newRobotPanel.createNodes(connect);
					new Update().start();
				}
				else{
					for(String ip : ips){
						connect.addRobot(ip);
					}
					newRobotPanel.createNodes(connect);
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
		newRobotPanel.update();
	}*/
	
	/* Execution of the demos 
	 * 
	 * The case values for the Demos have to match the order in which the
	 * demo programs are listed on DemoEnum.java
	 * 
	 */
	private void runDemo(DemoEnum enumVal){
		Demo demo = null;
		if(connect.getAllInactiveNames().size() <=0 ){
			JOptionPane.showMessageDialog(this, "There are no inactive robots.");
		}
		else{
		switch(enumVal){
		case HelloWorld:
			try{
				demo = new HelloWorldTutorial(connect);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			break;
		case HelloEarth:
			try{
				demo = new HelloEarth(connect);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			break;
		case FistBump:
			try{
				demo = new FistBumpDemo(connect);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			break;
		case BarcodeScanner:
			try {
				demo = new BarcodeScanner(connect);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case Track:
			try {
				demo = new Track(connect);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case AutomatedMotion:
			try {
				demo = new AutomatedMotion(connect);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case Posture:
			try {
				demo = new Posture(connect);
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
				demo = new TicTacInteraction(connect);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		default:
			break;	
		}
		addDemo(demo);
	
	}
	}
	private void addDemo(Demo demo) {
		// TODO Auto-generated method stub
		
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
