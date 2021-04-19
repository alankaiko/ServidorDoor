package com.laudoecia.api.sistemdicom.worklist;


public class RunInTransaction {
	public void execute(Runnable command) {
		command.run();
	}
}
