package demo.jxmapviewerdemo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class CheckBox extends JFrame{

	public static int days = 0; // 1=weekend , 2==week, 3= all
	public static int start = 00; //starting hour
	public static int oldStart; //keep from error
	public static int end = 23; //ending hour
	public static int oldEnd; //keep from error
	Object oldObj;

	private JCheckBox week;
	private JCheckBox weekend;
	
	private JComboBox<?> start_hour;
	private JComboBox<?> end_hour;
	private static String[] hours = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	
	public CheckBox(){
		super("Days");
		setLayout(new FlowLayout());
		
		//checkbox - week or weekend?
		week = new JCheckBox("Week");
		weekend = new JCheckBox("Weekend");
        JLabel patternLabel = new JLabel("select the type of data:");
        add(patternLabel);
        add(week);
		add(weekend);
		HandlerClass handler = new HandlerClass();
		week.addItemListener(handler);
		weekend.addItemListener(handler);
		
		//drop list - hours at the day
		start_hour = new JComboBox<Object>(hours);
		end_hour = new JComboBox<Object>(hours);
        start_hour.setSelectedItem("00");
        end_hour.setSelectedItem("23");
		start_hour.addItemListener(
			new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
					if(event.getStateChange()==ItemEvent.SELECTED){
						start = Integer.parseInt((String) start_hour.getSelectedItem());
						if((int)start>(int)end){
					        CheckBoxError frame = new CheckBoxError();
					        start = 00;
					        start_hour.setSelectedItem("00");
						}
						SelectionAdapter.someAaction(); // -- calculating
					}
				}
			}	
		);
		end_hour.addItemListener(
				new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						if(event.getStateChange()==ItemEvent.SELECTED){
							end = Integer.parseInt((String) end_hour.getSelectedItem());
							if((int)start>(int)end){
						        CheckBoxError frame = new CheckBoxError();
						        end = 23;
						        end_hour.setSelectedItem("23");
							}
							SelectionAdapter.someAaction();
						}
					}
				}					
		);
        JLabel patternLabel1 = new JLabel("select starting time:");
        add(patternLabel1);
		add(start_hour);
        JLabel patternLabel2 = new JLabel("select ending time:");
        add(patternLabel2);
        add(end_hour);
	}
	
	private class HandlerClass implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
		
			if(week.isSelected() && weekend.isSelected()){
				days = 3;
				SelectionAdapter.someAaction();
				}
			else if (week.isSelected()){
				days = 2;
				SelectionAdapter.someAaction();
				}
			else if (weekend.isSelected()){
				days = 1;
				SelectionAdapter.someAaction();
				}
			else{
				days = 7;
				SelectionAdapter.someAaction();	
			}
		}
		
	}
}
