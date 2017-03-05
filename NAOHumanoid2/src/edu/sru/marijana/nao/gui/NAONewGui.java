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

import edu.sru.marijana.nao.connection.Robot;
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
import edu.sru.marijana.nao.demo.gui.CameraDemoFrame;
import edu.sru.marijana.nao.demo.gui.DiagnosticGUI;

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
		ribbonMenu = new RibbonMenu(newRobotPanel, this);
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
	
	public void addRobotTabs(SynchronizedConnectDemo connect)
	{
		clearTabbedPane();
		for(Robot r : connect.getAllRobots()){
			robotTabbedPane.AddRobotPanel(r.name());
		}
	}
	
	public void clearTabbedPane()
	{
		robotTabbedPane.removeAll();
	}
	
	public void setDefaultTabbedPane()
	{
		robotTabbedPane.removeAll();
		robotTabbedPane.AddRobotPanel("Robots");
	}
	
}
