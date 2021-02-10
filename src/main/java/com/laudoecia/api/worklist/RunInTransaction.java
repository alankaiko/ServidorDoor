package com.laudoecia.api.worklist;


public class RunInTransaction {
	public void execute(Runnable command) {
		command.run();
	}
}
