/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.sql9.jmetl;

import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import com.sql9.db.CommonDB;
import com.sql9.util.StringUtil;

public class TransferDataProgressThread extends Thread {
	JProgressBar _$5;
	CommonDB _$4;
	CommonDB _$3;
	List<String> _$2;
	MainFrame _$1;

	public TransferDataProgressThread(JProgressBar bar, CommonDB srcDB, CommonDB desDB, List<String> tables,
			MainFrame frame) {
		this._$5 = bar;
		this._$4 = srcDB;
		this._$3 = desDB;
		this._$2 = tables;
		this._$1 = frame;
	}

	public void run() {
		SwingUtilities.invokeLater(new R1(this));
	}
}

class R1 implements Runnable {
    final /* synthetic */ TransferDataProgressThread _$1;

    R1(TransferDataProgressThread transferDataProgressThread) {
        this._$1 = transferDataProgressThread;
    }

    public void run() {
        int total = this._$1._$2.size();
        int i = 0;
        this._$1._$5.setValue(0);
        for (String o : this._$1._$2) {
            try {
                this._$1._$4.importDataToTargetDB(this._$1._$3, o, this._$1._$1);
            } catch (Exception ex) {
                try {
                    this._$1._$1.println(StringUtil.getStackInfoFromException(ex));
                } catch (Exception e) {
                }
            }
            i++;
            int progress = (i * 100) / total;
            this._$1._$5.setValue(progress);
            this._$1._$5.setString("Transfering table: " + o + ", [Total progress: " + progress + "%]");
        }
        if (this._$1._$2.isEmpty()) {
            try {
                Thread.sleep(100);
                this._$1._$5.setValue(100);
            } catch (InterruptedException e2) {
            }
        }
    }
}