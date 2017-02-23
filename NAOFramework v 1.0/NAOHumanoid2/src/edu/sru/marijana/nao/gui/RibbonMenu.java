package edu.sru.marijana.nao.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class RibbonMenu extends JPanel {
	
	public RibbonMenu() {
		super();
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		this.setLayout(flowLayout);
		setSize(new Dimension(600,120));
		InitializeButtons();
		
	}
	
	public void InitializeButtons()
	{
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		RibbonMenuButton menuButton = new RibbonMenuButton("CONNECT", "resources/connect.png", new Dimension(80, 80));
		panel.add(menuButton);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		RibbonMenuButton menuButton5 = new RibbonMenuButton("???", "resources/playapp.png", new Dimension(80, 80));
		panel_2.add(menuButton5);
		
		RibbonMenuButton menuButton6 = new RibbonMenuButton("???", "resources/playapp.png", new Dimension(80, 80));
		panel_2.add(menuButton6);
		
		RibbonMenuButton menuButton7 = new RibbonMenuButton("???", "resources/playapp.png", new Dimension(80, 80));
		panel_2.add(menuButton7);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		RibbonMenuButton menuButton2 = new RibbonMenuButton("DISCONNECT", "resources/disconnect.png", new Dimension(80, 80));
		panel_1.add(menuButton2);
		
		RibbonMenuButton menuButton3 = new RibbonMenuButton("PLAY APP", "resources/playapp.png", new Dimension(80, 80));
		panel_1.add(menuButton3);
		
		RibbonMenuButton menuButton4 = new RibbonMenuButton("???", "resources/playapp.png", new Dimension(80, 80));
		panel_1.add(menuButton4);
	}

}
