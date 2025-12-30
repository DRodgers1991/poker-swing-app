package com.poker.swing.utilityclasses.gui;

import java.sql.Time;

public class AdminCommandObject {
	private Time timeMessageArrived;
	private ActionOptions adminCommand;
	private String source;


	/**
	 * 
	 * Creates an object representation of an admin message 
	 * @param timeMessageArrived
	 * @param source
	 * @param adminCommand
	 */
	public AdminCommandObject(Time timeMessageArrived,String source, ActionOptions adminCommand) {
		this.timeMessageArrived = timeMessageArrived;
		this.adminCommand = adminCommand;
		this.source = source;
	}

	/**
	 * Retrieve the action associated with this message
	 * @return
	 */
	public ActionOptions getAdminCommand() {
		return adminCommand;
	}

	/**
	 * Get the origin of the message
	 * @return
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Time Stamp on the Message being sent
	 * @return
	 */
	public Time getTimeMessageArrived() {
		return timeMessageArrived;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Command: "+adminCommand);
		sb.append(" Time Received: "+timeMessageArrived);
		sb.append(" Source: "+source);
		return sb.toString();
	}
}
