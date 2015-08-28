package com.mr.util.search;

import java.util.List;

public class JQGridFilter {
	
	private String groupOp;
	private List <Rules> rules;
	
	public String getGroupOp() {
		return groupOp;
	}
	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}
	public List <Rules> getRules() {
		return rules;
	}
	public void setRules(List <Rules> rules) {
		this.rules = rules;
	}
	
	@Override
	public String toString() {
		return "JQGridFilter [groupOp=" + groupOp + ", rules=" + rules + "]";
	}
}