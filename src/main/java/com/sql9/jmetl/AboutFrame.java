/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.sql9.jmetl;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.jdesktop.layout.GroupLayout;

public class AboutFrame extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton button;
	private JLabel lab1;
	private JLabel lab2;

	public AboutFrame() {
		this.about();
	}

	private void about() {
		this.lab1 = new JLabel();
		this.button = new JButton();
		this.lab2 = new JLabel();
		this.setTitle("About");
		this.lab1.setHorizontalAlignment(2);
		this.lab1.setText( "<html><body><p> <b>Welcome!</b><br> This is JMyETL.[ Version 1.0.8]<br> Designed by Xiong HE. (iihero@CSDN) <br> mail: hexiong@gmail.com<br> MSN: iiihero@hotmail.com<br>   </body></html> ");
		this.lab1.setVerticalAlignment(1);
		this.button.setText("OK");
		this.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dis(e);
			}
		});
		this.lab2.setIcon(new ImageIcon(this.getClass().getResource("/JMyETL_middle.png")));
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(1)
				.add(2, layout.createSequentialGroup().addContainerGap(166, 32767).add(this.button, -2, 73, -2).add(171, 171, 171))
				.add(2, layout.createSequentialGroup().add(45, 45, 45).add(
						layout.createParallelGroup(2).add(1, this.lab1, -1, 355, 32767).add(1, this.lab2, -1, 355, 32767)) .addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(1).add(2,
				layout.createSequentialGroup().addContainerGap().add(this.lab1, -2, 127, -2).add(18, 18, 18)
						.add(this.lab2, -1, 109, 32767).addPreferredGap(0).add(this.button).addContainerGap()));
		this.pack();
	}

	private void dis(ActionEvent evt) {
		this.dispose();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AboutFrame().setVisible(true);
			}
		});
	}
}

