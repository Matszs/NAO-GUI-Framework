package edu.sru.marijana.nao.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Color;

public class RibbonMenuButton extends JButton {
	
	Border buttonBorder;
	
	// Konstruktor za ribbon dugme
	// Informacije koje su nam potrebne:  path - putanja do slike koju postavljamo za icon
	
	public RibbonMenuButton(String iconPath, Dimension dimension)
	{
		super();
		setBackground(Color.GRAY);
		//setFont(new Font("Tahoma", Font.PLAIN, 8));
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		setMaximumSize(new Dimension(300, 120));
		setSize(new Dimension(116, 55));
		setPreferredSize(dimension);
		
		// Postavljamo layout na BoxLayout da bi nam se componente unutar dugmeta (label za tekst i slika) prikazale jedna ispod druge
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		try
		{
			// Kreiramo sliku od fajla na zadatoj putanji iconPath
			BufferedImage iconImage = ImageIO.read(new File(iconPath));
			
			
			JLabel imageLabel = new JLabel();
			imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			imageLabel.setBackground(Color.RED);
			imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			imageLabel.setPreferredSize(new Dimension(dimension.height - 10, dimension.height - 10));
			imageLabel.setMaximumSize(new Dimension(400, 400));
			imageLabel.setSize(new Dimension(dimension.height - 10, dimension.height - 10));
			
			ImageIcon imageIcon = new ImageIcon(iconImage.getScaledInstance(imageLabel.getWidth() - 5, imageLabel.getHeight() - 5, Image.SCALE_SMOOTH));
			
			imageLabel.setIcon(imageIcon);
		
			this.add(imageLabel);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		// Krieramo labelu za prikaz zadatog teksta text
		//JLabel textLabel = new JLabel(text);
		//textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		//textLabel.setBackground(Color.WHITE);
		//textLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		//textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		//textLabel.setSize((int)dimension.getWidth(), (int)textLabel.getPreferredSize().getHeight());
		
		setMaximumSize(new Dimension(200, 120));
		
		setSize(dimension);
		setPreferredSize(dimension);
		
		//this.add(textLabel);
		
		CreateBorder();
		
		setBorderPainted(false);
		
		addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	if(isEnabled())
		    	{
		    		setBorderPainted(true);
		    		setBorder(buttonBorder);
		    	}
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	setBorder(null);
				setBorderPainted(false);
		    }
		});
	}
	
	public void CreateBorder()
	{
		buttonBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
	}

	@Override
	public void paint(Graphics g) {
		setOpaque(false);
		//setContentAreaFilled(false);
		//setBorderPainted(false);
	    super.paint(g);
	}
	
	

}
