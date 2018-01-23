package com.sql9.jmetl;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sql9.db.CommonDB;
import com.sql9.db.TextWriter;
import com.sql9.util.StringUtil;

public class MainFrame extends JFrame implements TextWriter, ChangeListener {
	private static final long serialVersionUID = 1L;
	CommonDB _$33;
	CommonDB _$32;
	private FileWriter _$31;
	private JButton _$30;
	private JButton _$29;
	private JButton _$28;
	private JButton _$27;
	private JButton _$26;
	private JButton _$25;
	private JButton _$24;
	private JButton _$23;
	private JComboBox _$22;
	private JComboBox _$21;
	private JPasswordField _$20;
	private JLabel _$19;
	private JLabel _$18;
	private JLabel _$17;
	private JList _$16;
	private JMenu _$15;
	private JMenu _$14;
	private JMenuBar _$13;
	private JMenuItem _$12;
	private JMenuItem _$11;
	private JMenuItem _$10;
	private JProgressBar _$9;
	private JScrollPane _$8;
	private JScrollPane _$7;
	private JScrollPane _$6;
	private JPasswordField _$5;
	private JTextArea _$4;
	private JTextArea _$3;
	private JTextField _$2;
	private JTextField _$1;

	public MainFrame() {
		this._$1();

		try {
			this._$31 = new FileWriter("error.log");
		} catch (IOException arg1) {
			throw new RuntimeException(arg1);
		}
	}

	public void println(String s) {
		this._$3.append(s + "\n");

		try {
			this._$31.write(StringUtil.getTimeStamp() + " " + s + "\n");
			this._$31.flush();
		} catch (IOException arg2) {
			throw new RuntimeException(arg2);
		}
	}

	private void _$1() {
		this._$19 = new JLabel();
		this._$18 = new JLabel();
		this._$22 = new JComboBox();
		this._$21 = new JComboBox();
		this._$1 = new JTextField();
		this._$5 = new JPasswordField();
		this._$2 = new JTextField();
		this._$20 = new JPasswordField();
		this._$8 = new JScrollPane();
		this._$16 = new JList();
		this._$28 = new JButton();
		this._$29 = new JButton();
		this._$30 = new JButton();
		this._$7 = new JScrollPane();
		this._$3 = new JTextArea();
		this._$17 = new JLabel();
		this._$27 = new JButton();
		this._$26 = new JButton();
		this._$25 = new JButton();
		this._$24 = new JButton();
		this._$6 = new JScrollPane();
		this._$4 = new JTextArea();
		this._$23 = new JButton();
		this._$9 = new JProgressBar();
		this._$13 = new JMenuBar();
		this._$15 = new JMenu();
		this._$10 = new JMenuItem();
		this._$12 = new JMenuItem();
		this._$14 = new JMenu();
		this._$11 = new JMenuItem();
		this.setDefaultCloseOperation(3);
		ResourceBundle bundle = ResourceBundle.getBundle("com/sql9/jmetl/resources");
		this.setTitle(bundle.getString("JMyETL_data_import_tool"));
		this.setBackground(new Color(0, 204, 204));
		this.setBounds(new Rectangle(0, 0, 1024, 768));
		this.setCursor(new Cursor(0));
		this.setFont(new Font("宋体", 0, 12));
		this.setForeground(Color.black);
		this.setLocationByPlatform(true);
		this.setName("mainframe");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
		        _$1(evt);
		    }
		});
		this._$19.setText(bundle.getString("sourcedb"));
		this._$19.setToolTipText(bundle.getString("sourcedb.tooltip"));
		this._$18.setText(bundle.getString("TargetDB"));
		this._$18.setToolTipText(bundle.getString("The_target_database_connection_to_import_data."));
		this._$22.setEditable(true);
		this._$22.setFont(new Font("宋体", 0, 12));
		this._$22.setMaximumRowCount(10);
		this._$22.setModel(new DefaultComboBoxModel(new String[]{"jdbc:postgresql://{host}:{port}/{database}",
				"jdbc:sybase:Tds:{host}:{port}/{database}", "jdbc:oracle:thin:@{host}:{port}:{database}",
				"jdbc:mysql://{host}:{port}/{database}", "jdbc:db2://{host}:{port}/{database}",
				"jdbc:sqlserver://{host}:{port};DatabaseName={database}", "jdbc:cubrid:{host}:{port}:{database}:::",
				"jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ={database}", "jdbc:sqlite:{database}"}));
		this._$22.setToolTipText(
				bundle.getString("The_source_connection_string,_please_replace_the_{}_with_proper_value"));
		this._$21.setEditable(true);
		this._$21.setFont(new Font("宋体", 0, 12));
		this._$21.setMaximumRowCount(10);
		this._$21.setModel(new DefaultComboBoxModel(new String[]{"jdbc:postgresql://{host}:{port}/{database}",
				"jdbc:sybase:Tds:{host}:{port}/{database}", "jdbc:oracle:thin:@{host}:{port}:{database}",
				"jdbc:mysql://{host}:{port}/{database}", "jdbc:db2://{host}:{port}/{database}",
				"jdbc:sqlserver://{host}:{port};DatabaseName={database}", "jdbc:cubrid:{host}:{port}:{database}:::",
				"jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ={database}", "jdbc:sqlite:{database}"}));
		this._$21.setToolTipText(
				bundle.getString("The_target_connection_string,_please_replace_the_{}_with_proper_value"));
		this._$1.setText(bundle.getString("{UserName}"));
		this._$1.setToolTipText(bundle.getString("Please_Input_source_usrename,_to_replace_{UserName}"));
		this._$1.addMouseListener(new IIIllllIIlIllIII(this));
		this._$5.setText(bundle.getString("password"));
		this._$5.setToolTipText(bundle.getString("source_connection_password"));
		this._$5.setName("srcPassword");
		this._$5.addMouseListener(new lIIllllIIlIllIII(this));
		this._$2.setText(bundle.getString("{UserName}"));
		this._$2.setToolTipText(bundle.getString("Please_input_target_connection_user_name,_replace_{UserName}"));
		this._$2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		       _$3(evt);
		    }
		});
		this._$20.setText(bundle.getString("password"));
		this._$20.setToolTipText(bundle.getString("target_connection_password"));
		this._$20.setName("desPassword");
		this._$20.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
		        _$1(evt);
		    }
		});
		this._$16.setToolTipText(bundle.getString("table_list_in_the_source_connection"));
		this._$8.setViewportView(this._$16);
		this._$28.setText(bundle.getString("Start_Import"));
		this._$28.setToolTipText(bundle.getString("Start_import_data_from_source_db_to_the_target_db."));
		this._$28.setName("btnStartImport");
		this._$28.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_$7(e);
			}
		});
		this._$29.setText(bundle.getString("Test_Connect"));
		this._$29.setToolTipText(bundle.getString("Test_connecting_to_the_source_DB"));
		this._$29.setName("srcTestConnBtn");
		this._$29.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_$8(e);
			}
		});
		this._$30.setText(bundle.getString("Test_Connect"));
		this._$30.setToolTipText(bundle.getString("Test_connecting_to_the_target_DB"));
		this._$30.setName("desTestConnBtn");
		this._$30.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_$6(e);
				
			}
		});
		this._$3.setColumns(20);
		this._$3.setLineWrap(true);
		this._$3.setRows(5);
		this._$3.setText(
				"Readme:\nThis is the official release version of JMyETL, the  copyright belongs to iihero (hexiong@gmail.com) ~2012.\nCurrently, it supports Sybase ASE/ASA/Access/MySQL/hisql[PostgreSQL]/CUBRID/Oracle/DB2/SQLite/SQLServer  import/export.\nAny issues please email to me. Thanks.\n\nUsage:\nUpdate the template item of {host}, {port}, {database} with the real value of your source database and target database. Test connect --> import.\n\nIf the target database is Access MDB, after you specify the full path of MDB, if it doesn\'t exist, JMyETL will create it for you.\n\n使用很简单，直接将源连接的{host}, {port}, {database}替换为真实的值，然后Test Connect，接接成功之后，即可点击start import，进行传输到目标数据库.\n\n如果目标数据库是Access，指定完路径以后，如果Access文件不存在，系统会为你自动创建Access文件\nAny issues, please contact the author of JMyETL：hexiong@gmail.com (He Xiong)");
		this._$3.setToolTipText(bundle.getString("output_information_window"));
		this._$3.setBorder(BorderFactory.createTitledBorder("output"));
		this._$3.setDoubleBuffered(true);
		this._$7.setViewportView(this._$3);
		this._$17.setText(
				"<html>Any issues please contact with us: <a href=\"mailto:hexiong@gmail.com\">hexiong@gmail.com</a></html>");
		this._$17.setBorder(BorderFactory.createTitledBorder("Contact"));
		this._$27.setText(bundle.getString("Rollback"));
		this._$27.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_$5(e);
			}
		});
		this._$26.setText(bundle.getString("SrcDBStudio"));
		this._$26.setToolTipText(bundle.getString("Studio_to_manage_the_source_DB"));
		this._$26.setEnabled(false);
		this._$26.setName("jbtnSrcDBStudio");
		this._$25.setText(bundle.getString("DesDBStudio"));
		this._$25.setToolTipText(bundle.getString("Studio_to_manage_the_target_DB"));
		this._$25.setEnabled(false);
		this._$25.setName("jbtnDesDBStudio");
		this._$25.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_$4(e);
			}
		});
		this._$24.setText(bundle.getString("srcSQL!"));
		this._$24.setToolTipText(bundle.getString("execute_sql_on_source_DB"));
		this._$24.setEnabled(false);
		this._$4.setColumns(20);
		this._$4.setRows(5);
		this._$4.setBorder(BorderFactory.createTitledBorder("SQL to be executed, srcSQL||desSQL"));
		this._$6.setViewportView(this._$4);
		this._$23.setText(bundle.getString("desSQL!"));
		this._$23.setToolTipText(bundle.getString("execute_SQL_on_target_DB"));
		this._$23.setEnabled(false);
		this._$9.setForeground(new Color(0, 92, 255));
		this._$9.setBorder(BorderFactory.createLineBorder(new Color(51, 0, 255)));
		this._$9.setName("jProgressBarTransfer");
		this._$9.setStringPainted(true);
		this._$15.setText(bundle.getString("File"));
		this._$10.setText(bundle.getString("EnableImport"));
		this._$10.setToolTipText(bundle.getString("EnableTransferForImport"));
		this._$10.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_$2(e);
			}
		});
		this._$15.add(this._$10);
		this._$12.setAccelerator(KeyStroke.getKeyStroke(87, 2));
		this._$12.setText(bundle.getString("Clear_Output"));
		this._$12.setToolTipText(bundle.getString("Clear_the_output_window"));
		this._$12.setName("jmenu_clearoutput");
		this._$12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_$3(e);
			}
		});
		this._$15.add(this._$12);
		this._$13.add(this._$15);
		this._$14.setText(bundle.getString("Help(H)"));
		this._$14.setToolTipText(bundle.getString("Help"));
		this._$11.setAccelerator(KeyStroke.getKeyStroke(112, 0));
		this._$11.setText(bundle.getString("About"));
		this._$11.setToolTipText(bundle.getString("About_the_jmyetl_tool"));
		this._$11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_$1(e);
			}
		});
		this._$14.add(this._$11);
		this._$13.add(this._$14);
		this.setJMenuBar(this._$13);
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(30, 30, 30)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(this._$19, -1, -1, 32767).addComponent(this._$18, -1, 62, 32767))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(this._$21, Alignment.LEADING, 0, 0, 32767).addComponent(this._$22,
												Alignment.LEADING, -2, 398, -2))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this._$1)
										.addComponent(this._$2, -1, 73, 32767))
								.addGap(14, 14, 14)
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false).addComponent(this._$20)
										.addComponent(this._$5, -1, 64, 32767))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this._$29)
										.addComponent(this._$30))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(this._$25, 0, 0, 32767).addComponent(this._$26, -1, -1, 32767)))
								.addGroup(Alignment.TRAILING,
										layout.createParallelGroup(Alignment.TRAILING).addGroup(
												Alignment.LEADING, layout.createSequentialGroup()
														.addComponent(this._$17, -2, 513, -2)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(this._$9, -1, 578, 32767))
												.addGroup(layout.createSequentialGroup().addGroup(layout
														.createParallelGroup(Alignment.TRAILING, false)
														.addGroup(Alignment.LEADING,
																layout.createSequentialGroup().addGroup(layout
																		.createParallelGroup(Alignment.LEADING, false)
																		.addComponent(this._$27, -1, -1, 32767)
																		.addComponent(this._$28, -1, 135, 32767))
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addGroup(layout
																				.createParallelGroup(Alignment.TRAILING)
																				.addComponent(this._$24, -1, -1, 32767)
																				.addComponent(this._$23)))
														.addComponent(this._$8, 0, 0, 32767))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(Alignment.TRAILING)
																.addComponent(this._$6, -1, 873, 32767)
																.addComponent(this._$7, -1, 873, 32767)))))
						.addGap(0, 0, 0)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this._$22, -2, -1, -2)
								.addComponent(this._$19).addComponent(this._$1, -2, -1, -2))
						.addGap(13, 13, 13)
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this._$21, -2, -1, -2)
								.addComponent(this._$18).addComponent(this._$2, -2, -1, -2)))
						.addGroup(layout.createSequentialGroup().addComponent(this._$5, -2, -1, -2).addGap(15, 15, 15)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(this._$20, -2, -1, -2).addComponent(this._$30, -2, 20,
												-2)
										.addComponent(this._$25, -2, 20, -2)))
						.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this._$29, -2, 21, -2)
								.addComponent(this._$26, -2, 20, -2)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this._$7, -1, 425, 32767)
						.addComponent(this._$8, -1, 425, 32767))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(this._$24, -2, 23, -2).addComponent(this._$28, -2, 23, -2))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this._$23)
										.addComponent(this._$27)))
						.addComponent(this._$6, -2, 64, -2))
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(this._$17).addComponent(this._$9, -2, 40, -2))
				.addContainerGap()));
		this.pack();
	}

	private void _$4(MouseEvent evt) {
		this._$1.setText("");
	}

	private void _$3(MouseEvent evt) {
		this._$2.setText("");
	}

	private void _$2(MouseEvent evt) {
		this._$5.setText("");
	}

	private void _$1(MouseEvent evt) {
		this._$20.setText("");
	}

	private void _$8(ActionEvent evt) {
		try {
			if (this._$33 != null) {
				this._$33.close();
			}

			this._$33 = new CommonDB(this._$22.getSelectedItem().toString(), this._$1.getText(),
					String.valueOf(this._$5.getPassword()), (Properties) null);
			List<String> ex = this._$33.getTables();
			ListModel model = new ListModel(ex);
			this._$16.setModel(model);
			this._$29.setBackground(Color.GREEN);
			this._$3.setText("");
			this.println(ResourceBundle.getBundle("com/sql9/jmetl/resources").getString("Product_name:_")
					+ this._$33.getDetails());
			this.println(ResourceBundle.getBundle("com/sql9/jmetl/resources").getString("DB_type:_")
					+ this._$33.getDbType());
		} catch (Exception arg3) {
			this.println("caused by: ---------->");
			this.println(StringUtil.getStackInfoFromException(arg3));
		}

	}

	private void _$1(WindowEvent evt) {
		try {
			if (this._$33 != null) {
				this._$33.close();
			}

			if (this._$32 != null) {
				this._$32.close();
			}

			if (this._$31 != null) {
				this._$31.close();
			}
		} catch (Exception arg2) {
			;
		}

	}

	private void _$7(ActionEvent evt) {
		try {
			Object[] ex = this._$16.getSelectedValues();
			ArrayList tables = new ArrayList();
			String tmp = null;
			Object[] arr$ = ex;
			int len$ = ex.length;

			for (int i$ = 0; i$ < len$; ++i$) {
				Object o = arr$[i$];
				tmp = (String) o;
				tables.add(tmp);
			}

			(new TransferDataProgressThread(this._$9, this._$33, this._$32, tables, this)).start();
			this._$28.setEnabled(false);
		} catch (Exception arg8) {
			this.println(StringUtil.getStackInfoFromException(arg8));
		}

	}

	private void _$6(ActionEvent evt) {
		try {
			if (this._$32 != null) {
				this._$32.close();
			}

			this._$32 = new CommonDB(this._$21.getSelectedItem().toString(), this._$2.getText(),
					String.valueOf(this._$20.getPassword()), (Properties) null);
			this.println(ResourceBundle.getBundle("com/sql9/jmetl/resources").getString("Target_DB_product_name:_")
					+ this._$32.getDetails());
			this.println(ResourceBundle.getBundle("com/sql9/jmetl/resources").getString("Target_DB_type:_")
					+ this._$32.getDbType());
			this._$30.setBackground(Color.GREEN);
		} catch (Exception arg2) {
			this.println("caused by: ---------->");
			this.println(StringUtil.getStackInfoFromException(arg2));
		}

	}

	private void _$5(ActionEvent evt) {
		this.println(ResourceBundle.getBundle("com/sql9/jmetl/resources").getString("To_be_implemented....."));
	}

	private void _$4(ActionEvent evt) {
	}

	private void _$3(ActionEvent evt) {
		this._$3.setText("");
	}

	private void _$2(ActionEvent evt) {
		this._$28.setEnabled(true);
	}

	private void _$1(ActionEvent evt) {
		AboutFrame af = new AboutFrame();
		af.setVisible(true);
		af.setLocationRelativeTo(this.getOwner());
	}

	public void stateChanged(ChangeEvent e1) {
		if (e1.getSource() == this._$9) {
			int value = this._$9.getValue();
			if (value >= 100 || value <= 0) {
				this._$28.setEnabled(true);
			}
		}

	}

	public void setTransferBtnEnabled(boolean enabled) {
		this._$28.setEnabled(enabled);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ImageIcon icon = new ImageIcon(getClass().getResource("/logo.gif"));
		        MainFrame jf = new MainFrame();
		        jf.setIconImage(icon.getImage());
		        jf.setVisible(true);
		        jf.setExtendedState(6);
		        jf._$9.addChangeListener(jf);
			}
		});
	}
	
	
	class IIIllllIIlIllIII extends MouseAdapter {
	    final /* synthetic */ MainFrame _$1;

	    IIIllllIIlIllIII(MainFrame mainFrame) {
	        this._$1 = mainFrame;
	    }

	    public void mouseClicked(MouseEvent evt) {
	        this._$1._$4(evt);
	    }
	}
	
	class lIIllllIIlIllIII extends MouseAdapter {
	    final /* synthetic */ MainFrame _$1;

	    lIIllllIIlIllIII(MainFrame mainFrame) {
	        this._$1 = mainFrame;
	    }

	    public void mouseClicked(MouseEvent evt) {
	        this._$1._$2(evt);
	    }
	}
}
class ListModel extends DefaultListModel {
	ListModel(List<String> res) {
        for (String s : res) {
            addElement(s);
        }
    }
}
