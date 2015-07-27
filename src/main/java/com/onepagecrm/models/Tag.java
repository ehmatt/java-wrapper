package com.onepagecrm.models;

public class Tag {

	private String name;
	private int counts;
	private int totalCounts;
	private int actionStreamCount;
	
	public Tag() {}

	public Tag(String name, int counts, int totalCounts, int actionStreamCount) {
		super();
		this.name = name;
		this.counts = counts;
		this.totalCounts = totalCounts;
		this.actionStreamCount = actionStreamCount;
	}

	public String getName() {
		return name;
	}

	public Tag setName(String name) {
		this.name = name;
		return this;
	}

	public int getCounts() {
		return counts;
	}

	public Tag setCounts(int counts) {
		this.counts = counts;
		return this;
	}

	public int getTotalCounts() {
		return totalCounts;
	}

	public Tag setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
		return this;
	}

	public int getActionStreamCount() {
		return actionStreamCount;
	}

	public Tag setActionStreamCount(int actionStreamCount) {
		this.actionStreamCount = actionStreamCount;
		return this;
	}
}