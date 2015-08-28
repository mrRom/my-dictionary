package com.mr.text.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "text", catalog = "dictionarydb")
public class Text implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer textId;
	private String title;
	private String text;
	private String userName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "text_id", unique = true, nullable = false)
	public Integer getTextId() {
		return textId;
	}
	public void setTextId(Integer textId) {
		this.textId = textId;
	}
	
	@Column(name = "title", nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "text", nullable = false)
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Column(name = "user_name", nullable = false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
