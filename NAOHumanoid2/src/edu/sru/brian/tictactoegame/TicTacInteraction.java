package edu.sru.brian.tictactoegame;

import java.awt.EventQueue;

import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

import edu.sru.thangiah.nao.connection.SynchronizedConnectDemo;
import edu.sru.thangiah.nao.demo.Demo;
import edu.sru.thangiah.nao.demo.DemoRobot;
import edu.sru.thangiah.nao.demo.gui.ApplicationsOptionDialog;
import edu.sru.brian.tictactoegame.*;
import edu.sru.brian.tictactoegame.decisiontree.DefaultGenerateDescisionTree;

/**
 * A class that will manage the interaction between the NAO Robot and the progress of the 
 * Tic Tac Toe game built by Brian Atwell.
 * TicTacInteraction connection with robot modeled after HelloEarth.java written by
 * Dr. Sam Thangiah
 * @author Emily Day
 * Date created: 10/25/16
 * Last edited: 11/29/16
 */

public class TicTacInteraction extends Demo {

/*	public static void main(String[] args) {
*	// TODO Auto-generated method stub
*	}
*/
	private TicTacToeBoardGUI ticTacGUI;
	/**
	 * Connects NAO Robot into the program.
	 * @param connection
	 * @throws Exception
	 */
	public TicTacInteraction(SynchronizedConnectDemo connection) throws Exception {
		super(connection);
		demoName = "TicTacToeNAO";
		
		ApplicationsOptionDialog dialog = new ApplicationsOptionDialog(connection);
		dialog.setVisible(true);
		
		robotNames.add(dialog.getSelectedName());
	}
	/**
	 * Creates a new tic tac toe NAO interaction instance
	 */
	protected void run() throws Exception {
		robots.add(new PlayAGame(robotNames.get(0), demoName, connection));
	}
	

	/**
	 * Private class used by TicTacInteraction start a TicTacToe Game and play it
	 * with a NAO Robot
	 * 
	 * @author Emily Day
	 *
	 */
	private class PlayAGame extends DemoRobot {
		private ALTextToSpeech speech;
		
		/**
		 * Creates instance of robot demo
		 * @param name
		 * @param appName
		 * @param connect
		 * @throws Exception
		 */
		
		public PlayAGame(String name, String appName, SynchronizedConnectDemo connect) throws Exception {
			super(name, appName, connect);

		}

		@Override
		protected void safeClose() throws Exception {
			// TODO Auto-generated method stub
		}

		@Override
		protected void init() throws Exception {
			// TODO Auto-generated method stub
			speech = new ALTextToSpeech(connect.getSession(name));
		}
		
		/**
		 * Starts program. Robot speaks, Tic Tac Toe GUI generated here. Connection
		 * information is passed into it.
		 */
		
		@Override
		public void execute() throws Exception {
			// TODO Auto-generated method stub
			speech.say("Let's play Tic Tac Toe!");
			//NAOResponse naoResponse = new NAOResponse (name, connect);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ticTacGUI = new TicTacToeBoardGUI(true,true,true,true, name, connect);
						//TicTacToeBoardGUI frame = new TicTacToeBoardGUI(false,false,false,false);
						ticTacGUI.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		/**
		 * Uses the front head button to run the game.
		 */
		@Override
		public void frontTactil() {
			try {
				executeAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Uses the back head button to exit the game.
		 */
		@Override
		public void rearTactil() {
			try {
				exit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
