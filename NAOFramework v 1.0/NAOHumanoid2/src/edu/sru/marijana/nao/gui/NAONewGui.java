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

public class NAONewGui extends JFrame {

	private JPanel contentPanel;
	private NewRobotPanel robotPanel;
	private ArrayList<Demo> demos;				//for loading all the demos
	private SynchronizedConnectDemo connect;
	private JMenu mnRun;
	private JMenu mnOptions;
	private JMenuItem mntmShutdownAll;
	private JMenuItem mntmRebootAll;
	private JMenuItem mntmDisconnectAll;
	private JMenuItem mntmEndAllApplications;
	private JMenuItem mntmRunCamera;
	private JMenuItem mntmRunDiagnostics;
	//private NewRobotPanel robotPanelNew;
	private Object tabbedPane;
	private JTextField txtCAM;
	private JTextField txtCAM_1;
	private JTextField txtAppWindow;
	
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
		demos = new ArrayList<Demo>();
		connect = null;
		setTitle("Nao New Gui");

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(60, 0,1280,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		RibbonMenu ribbonMenu = new RibbonMenu();
		FlowLayout flowLayout = (FlowLayout) ribbonMenu.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		ribbonMenu.setSize(600, 120);
		ribbonMenu.setLocation(10, 10);
		//setJMenuBar(menuBar);
		//add(ribbonMenu);
		//Button b = new JButton();
		//b.setText("TEST");
		/*b.setLayout(new GridLayout(2,1));
		try
		{
		BufferedImage iconImage = ImageIO.read(new File("resources/kv-NAO.png"));
		ImageIcon imageIcon = new ImageIcon(iconImage);
		JLabel imageLabel = new JLabel();
		imageLabel.setIcon(imageIcon);
		b.add(imageLabel);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		JLabel textLabel = new JLabel("Test");
		b.add(textLabel);*/
		
		//menuBar.add(b);
		
		/*JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		//Connect and find all the robots
		JMenuItem mntmConnect = new JMenuItem("Connect");
		mntmConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connect();
			}
		});
		mnFile.add(mntmConnect);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}

			private void exit() {
				// TODO Auto-generated method stub
				
			}
		});
		mnFile.add(mntmExit);
		
		mnRun = new JMenu("Run");
		mnRun.setEnabled(false);
		menuBar.add(mnRun);
		
		mnOptions = new JMenu("Options");
		mnOptions.setEnabled(false);
		menuBar.add(mnOptions);
		
		//menu option - Shutdown all
		mntmShutdownAll = new JMenuItem("Shutdown All");
		mntmShutdownAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.shutdownAll();
				robotPanel.createNodes(connect);
			}
		});
		
		//menu option - Run Camera
		mntmRunCamera = new JMenuItem("Run Camera");
		mntmRunCamera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new CameraDemoFrame(connect).setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//menu option - Run Diagnostics
		mntmRunDiagnostics = new JMenuItem("Run Diagnostics");
		mntmRunDiagnostics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new DiagnosticGUI(connect);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		//menu option - Reboot all
		mntmRebootAll = new JMenuItem("Reboot All");
		mntmRebootAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.rebootAll();
				robotPanel.createNodes(connect);
			}
		});

		//menu option - Disconnect All
		mntmDisconnectAll = new JMenuItem("Disconnect All");
		mntmDisconnectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.stopAll();
				robotPanel.createNodes(connect);
			}
		});
		
		//menu option -End All Applications
		mntmEndAllApplications = new JMenuItem("End All Applications");
		mntmEndAllApplications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.stopAllDemos();
			}
		});

		//Add to menu options
		mnOptions.add(mntmRunDiagnostics);
		mnOptions.add(mntmRunCamera);
		mnOptions.add(mntmShutdownAll);
		mnOptions.add(mntmRebootAll);
		mnOptions.add(mntmDisconnectAll);
		mnOptions.add(mntmEndAllApplications);
		
		//Options for the Run are added
		
		for(DemoEnum enumVal : DemoEnum.values()){
			JMenuItem mntmNewMenuItem = new JMenuItem(enumVal.name());
			mnRun.add(mntmNewMenuItem);
			
			mntmNewMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					runDemo(enumVal);
			}});
		}*/
	
		contentPanel = new JPanel();
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		contentPanel.add(ribbonMenu);
		
		robotPanel = new NewRobotPanel();
		robotPanel.setBounds(10, 130, 425, 720);
		robotPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPanel.add(robotPanel);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane_1.setBounds(445, 130, 805, 415);
		contentPanel.add(tabbedPane_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabbedPane_1.addTab("Robots", null, panel, null);
		panel.setLayout(null);
		
		JPanel camera_1 = new JPanel();
		camera_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		camera_1.setBounds(10, 10, 365, 365);
		panel.add(camera_1);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		camera_1.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		txtCAM = new JTextField();
		camera_1.add(txtCAM);
		txtCAM.setHorizontalAlignment(SwingConstants.CENTER);
		txtCAM.setText("C A M E R A   1");
		txtCAM.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Disconnect");
		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
		camera_1.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		JPanel camera_2 = new JPanel();
		camera_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		camera_2.setBounds(421, 11, 365, 365);
		panel.add(camera_2);
		
		JButton button_2 = new JButton("Connect");
		button_2.setVerticalAlignment(SwingConstants.BOTTOM);
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		camera_2.add(button_2);
		
		txtCAM_1 = new JTextField();
		txtCAM_1.setText("C A M E R A   2");
		txtCAM_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtCAM_1.setColumns(10);
		camera_2.add(txtCAM_1);
		
		JButton button_3 = new JButton("Disconnect");
		button_3.setVerticalAlignment(SwingConstants.BOTTOM);
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		camera_2.add(button_3);
		
		JPanel appPanel = new JPanel();
		appPanel.setLayout(null);
		appPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		appPanel.setBounds(445, 555, 805, 296);
		contentPanel.add(appPanel);
		
		JButton btnNewButton_2 = new JButton("Pop Out");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton_2.setBounds(706, 11, 89, 23);
		appPanel.add(btnNewButton_2);
		
		txtAppWindow = new JTextField();
		txtAppWindow.setHorizontalAlignment(SwingConstants.CENTER);
		txtAppWindow.setFont(new Font("Tahoma", Font.PLAIN, 48));
		txtAppWindow.setText("APP WINDOW");
		txtAppWindow.setBounds(218, 108, 366, 88);
		appPanel.add(txtAppWindow);
		txtAppWindow.setColumns(10);
		
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
				mnRun.setEnabled(true);
				mnOptions.setEnabled(true);
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
