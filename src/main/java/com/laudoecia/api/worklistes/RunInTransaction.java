package com.laudoecia.api.worklistes;


public class RunInTransaction {
	public void execute(Runnable command) {
		command.run();
	}
}
