/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.sql9.jmetl;

import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import com.sql9.connect.CommonDB;
import com.sql9.util.StringUtil;

public class TransferDataProgressThread extends Thread {
	JProgressBar bar;
	CommonDB srcDB;
	CommonDB desDB;
	List<String> tables;
	MainFrame frame;

	public TransferDataProgressThread(JProgressBar bar, CommonDB srcDB, CommonDB desDB, List<String> tables, MainFrame frame) {
		this.bar = bar;
		this.srcDB = srcDB;
		this.desDB = desDB;
		this.tables = tables;
		this.frame = frame;
	}

	public void run() {
		SwingUtilities.invokeLater(new R1(this));
	}
}

//
class R1 implements Runnable {
	
    final TransferDataProgressThread thread;

    R1(TransferDataProgressThread transferDataProgressThread) {
        this.thread = transferDataProgressThread;
    }

    public void run() {
        int total = this.thread.tables.size();
        int i = 0;
        this.thread.bar.setValue(0);
        for (String o : this.thread.tables) {
            try {
                this.thread.srcDB.importDataToTargetDB(this.thread.desDB, o, this.thread.frame);
            } catch (Exception ex) {
                try {
                    this.thread.frame.println(StringUtil.getStackInfoFromException(ex));
                } catch (Exception e) {
                }
            }
            i++;
            int progress = (i * 100) / total;
            this.thread.bar.setValue(progress);
            this.thread.bar.setString("Transfering table: " + o + ", [Total progress: " + progress + "%]");
        }
        if (this.thread.tables.isEmpty()) {
            try {
                Thread.sleep(100);
                this.thread.bar.setValue(100);
            } catch (InterruptedException e2) {
            }
        }
    }
}