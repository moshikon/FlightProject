package demo.jxmapviewerdemo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CheckBoxError extends JFrame{
		
	public CheckBoxError(){
		super("Days");
		setLayout(new FlowLayout());
        JLabel patternLabel1 = new JLabel("Choose again");
        add(patternLabel1);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(280, 150);
        setVisible(true);
	}
}
