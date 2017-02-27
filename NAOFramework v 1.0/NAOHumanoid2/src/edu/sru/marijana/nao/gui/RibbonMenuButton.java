package edu.sru.marijana.nao.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RibbonMenuButton extends JButton {
	
	// Konstruktor za ribbon dugme
	// Informacije koje su nam potrebne: text - text koji se prikazuje na dugmetu, path - putanja do slike koju postavljamo za icon
	
	public RibbonMenuButton(String text, String iconPath, Dimension dimension)
	{
		super();
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		setMaximumSize(new Dimension(120, 120));
		setSize(dimension);
		setPreferredSize(dimension);
		
		// Postavljamo layout na BoxLayout da bi nam se componente unutar dugmeta (label za tekst i slika) prikazale jedna ispod druge
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		try
		{
			// Kreiramo sliku od fajla na zadatoj putanji iconPath
			BufferedImage iconImage = ImageIO.read(new File(iconPath));
			
			
			JLabel imageLabel = new JLabel();
			imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
			imageLabel.setPreferredSize(new Dimension(dimension.width - 20, dimension.height - 20));
			imageLabel.setMaximumSize(new Dimension(400, 400));
			imageLabel.setSize(new Dimension(dimension.width - 20, dimension.height - 20));
			
			ImageIcon imageIcon = new ImageIcon(iconImage.getScaledInstance(imageLabel.getWidth() - 15, imageLabel.getHeight() - 15, Image.SCALE_SMOOTH));
			
			imageLabel.setIcon(imageIcon);
		
			this.add(imageLabel);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		// Krieramo labelu za prikaz zadatog teksta text
		JLabel textLabel = new JLabel(text);
		textLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(textLabel);
		
		
	}
}
