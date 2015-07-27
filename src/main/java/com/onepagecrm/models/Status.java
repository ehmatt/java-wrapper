package com.onepagecrm.models;

import java.util.List;

import com.onepagecrm.models.internal.TeamCounts;
import com.onepagecrm.net.ApiResource;

public class Status extends ApiResource {

	private String id;
	private String color;
	private String status;
	private String text;
	private String description;
	private int counts;
	private int totalCounts;
	private int actionStreamCount;
	private List<TeamCounts> teamCounts;

	public Status() {

	}

	public Status(String id, String color, String status, String text, String description, 
			int counts, int totalCounts, int actionStreamCount, List<TeamCounts> teamCounts) {
		this.id = id;
		this.color = color;
		this.status = status;
		this.text = text;
		this.description = description;
		this.counts = counts;
		this.totalCounts = totalCounts;
		this.actionStreamCount = actionStreamCount;
		this.teamCounts = teamCounts;
	}
	
	@Override
	public String toString() {
		String retString;
		
		retString = "Status{" +
				"id=\'" + id + "\', " + 
				"color=\'" + color + "\', " + 
				"status=\'" + status + "\', " + 
				"text=\'" + text + "\', " + 
				"description=\'" + description + "\', " + 
				"counts=\'" + counts + "\', " + 
				"totalCounts=\'" + totalCounts + "\', " +
				"actionStreamCount=\'" + actionStreamCount + "\', ";

		if (teamCounts != null && !teamCounts.isEmpty()) {
            retString += ", TeamCounts{";
            for (int i = 0; i < teamCounts.size(); i++) {
                retString += teamCounts.get(i).toString();
                if (i == teamCounts.size() - 1) 
                	retString += ", ";
            }
            retString += "}";
        }
        		
		retString += "}";
		return retString;
	}

	public String getId() {
		return id;
	}

	public Status setId(String id) {
		this.id = id;
		return this;
	}

	public String getColor() {
		return color;
	}

	public Status setColor(String color) {
		this.color = color;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Status setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getText() {
		return text;
	}

	public Status setText(String text) {
		this.text = text;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Status setDescription(String description) {
		this.description = description;
		return this;
	}

	public int getCounts() {
		return counts;
	}

	public Status setCounts(int counts) {
		this.counts = counts;
		return this;
	}

	public int getTotalCounts() {
		return totalCounts;
	}

	public Status setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
		return this;
	}

	public int getActionStreamCount() {
		return actionStreamCount;
	}

	public Status setActionStreamCount(int actionStreamCount) {
		this.actionStreamCount = actionStreamCount;
		return this;
	}

	public List<TeamCounts> getTeamCounts() {
		return teamCounts;
	}

	public Status setTeamCounts(List<TeamCounts> teamCounts) {
		this.teamCounts = teamCounts;
		return this;
	}
}