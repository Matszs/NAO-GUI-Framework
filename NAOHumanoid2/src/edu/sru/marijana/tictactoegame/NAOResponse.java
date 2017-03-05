package edu.sru.marijana.tictactoegame;

import java.awt.EventQueue;

import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

import edu.sru.marijana.nao.connection.SynchronizedConnectDemo;
import edu.sru.thangiah.nao.events.EventPair;
import edu.sru.thangiah.nao.events.NaoEvents;
import edu.sru.marijana.tictactoegame.*;

/**
 * File: NAOResponse.java
 * @author Emily Day
 * This is the class that runs the NAO responses to the tic tac toe game
 * as it progresses. All methods here can be placed in game event listeners
 * so they occur at the correct times.
 *
 */

public class NAOResponse {

	private ALMemory memory;
	private ALMotion motion;
	private ALRobotPosture posture;
	private ALTextToSpeech speech;
	//private TicTacToeBoardGUI tictacGUI;


	/**
	 * Creates an instance of NAOResponse - allows a NAO to react during
	 * a tic tac toe game
	 * 
	 * @param name
	 * @param appName
	 * @param connect
	 * @throws Exception
	 */
	public NAOResponse(String name, SynchronizedConnectDemo connect)   {
		try {
			this.memory = new ALMemory(connect.getSession(name));
			this.motion = new ALMotion(connect.getSession(name));
			this.posture = new ALRobotPosture(connect.getSession(name));
			this.speech = new ALTextToSpeech(connect.getSession(name));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Switch statement takes in the AI choice for tic tac toe
	 * and verbalizes it.
	 * @param position
	 */
	public void speakAIChoose(int position){
		try {
			switch (position) {
			case 1:
				speech.say("I choose square one!");
				break;
			case 2:
				speech.say("I choose square two!");
				break;
			case 3:
				speech.say("I choose square three!");
				break;
			case 4:
				speech.say("I choose square four!");
				break;
			case 5:
				speech.say("I choose square five!");
				break;
			case 6:
				speech.say("I choose square six!");
				break;
			case 7:
				speech.say("I choose square seven!");
				break;
			case 8:
				speech.say("I choose square eight!");
				break;
			case 0:
				speech.say("I choose square zero!");
				break;
			default:
				break;		
			}						
		}
		catch (Exception ex) 
		{
			System.out.println("Error: speakAIChoose");
			ex.printStackTrace();
		}
	}
	/**
	 * Can be called at the end of an AI/NAO's turn
	 */
	public void yourTurn() {
		try {
			speech.say("Your turn!");
		} catch (CallError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Can be called at the end of a human player's turn
	 */
	public void myTurn() {
		try {
			speech.say("My turn!");
		} catch (CallError e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Called in the event of the NAO winning the game
	 */
	public void naoWins() {
		try {
			speech.say("I win! Let's play again.");
		} catch (CallError e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Called in the event of the NAO losing the game
	 */
	public void userWins() {
		try {
			speech.say("Oh no! You win.");
		} catch (CallError e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Called in the event of a tie.
	 */
	public void tieGame() {
		try {
			speech.say("It's a tie!");
		} catch (CallError e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
