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
	private JButton _$3;
	private JLabel _$2;
	private JLabel _$1;

	public AboutFrame() {
		this._$1();
	}

	private void _$1() {
		this._$2 = new JLabel();
		this._$3 = new JButton();
		this._$1 = new JLabel();
		this.setTitle("About");
		this._$2.setHorizontalAlignment(2);
		this._$2.setText(
				"<html><body><p> <b>Welcome!</b><br> This is JMyETL.[ Version 1.0.8]<br> Designed by Xiong HE. (iihero@CSDN) <br> mail: hexiong@gmail.com<br> MSN: iiihero@hotmail.com<br>   </body></html> ");
		this._$2.setVerticalAlignment(1);
		this._$3.setText("OK");
		this._$3.addActionListener(new lllIIIlllIIllIII(this));
		this._$1.setIcon(new ImageIcon(this.getClass().getResource("/JMyETL_middle.png")));
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(1)
				.add(2, layout.createSequentialGroup().addContainerGap(166, 32767).add(this._$3, -2, 73, -2).add(171,
						171, 171))
				.add(2, layout.createSequentialGroup().add(45, 45, 45).add(
						layout.createParallelGroup(2).add(1, this._$2, -1, 355, 32767).add(1, this._$1, -1, 355, 32767))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(1).add(2,
				layout.createSequentialGroup().addContainerGap().add(this._$2, -2, 127, -2).add(18, 18, 18)
						.add(this._$1, -1, 109, 32767).addPreferredGap(0).add(this._$3).addContainerGap()));
		this.pack();
	}

	private void _$1(ActionEvent evt) {
		this.dispose();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Ra());
	}
	
	class lllIIIlllIIllIII implements ActionListener {
	    final /* synthetic */ AboutFrame _$1;

	    lllIIIlllIIllIII(AboutFrame aboutFrame) {
	        this._$1 = aboutFrame;
	    }

	    public void actionPerformed(ActionEvent evt) {
	        this._$1._$1(evt);
	    }
	}
}

class Ra implements Runnable {
	Ra() {
    }

    public void run() {
        new AboutFrame().setVisible(true);
    }
}

