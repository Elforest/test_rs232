package com.qqq.main;

public class SerialBuffer {
	private String Content = "";
	private String CurrentMsg, TempContent;
	private boolean available = false;
	private int LengthNeeded = 1;

	public synchronized String GetMsg(int Length) {
		LengthNeeded = Length;
		notifyAll();
		if (LengthNeeded > Content.length()) {
			available = false;
			while (available == false) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		CurrentMsg = Content.substring(0, LengthNeeded);
		TempContent = Content.substring(LengthNeeded);
		Content = TempContent;
		LengthNeeded = 1;
		notifyAll();
		return CurrentMsg;
	}

	public synchronized void PutChar(int c) {
		Character d = new Character((char) c);
		Content = Content.concat(d.toString());
		if (LengthNeeded <= Content.length()) {
			available = true;
		}
		notifyAll();
	}
}
