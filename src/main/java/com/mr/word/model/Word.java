package com.mr.word.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "word", catalog = "dictionarydb")
public class Word implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer wordId;
	private String wordName;
	private String wordTranslation;
	private String usageExample;
	private String userName;
 
	public Word() {
	}
 
	public Word(String wordName, String wordTranslation, String usageExample) {
		this.wordName = wordName;
		this.wordTranslation = wordTranslation;
		this.usageExample = usageExample;
	}
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "word_id", unique = true, nullable = false)
	public Integer getWordId() {
		return wordId;
	}

	public void setWordId(Integer wordId) {
		this.wordId = wordId;
	}

	@Column(name = "word_name", nullable = false)
	public String getWordName() {
		return wordName;
	}

	public void setWordName(String wordName) {
		this.wordName = wordName;
	}

	@Column(name = "word_translation", nullable = false)
	public String getWordTranslation() {
		return wordTranslation;
	}

	public void setWordTranslation(String wordTranslation) {
		this.wordTranslation = wordTranslation;
	}

	@Column(name = "usage_example")
	public String getUsageExample() {
		return usageExample;
	}

	public void setUsageExample(String usageExample) {
		this.usageExample = usageExample;
	}
	
	@Column(name = "d_user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Word [wordName=" + wordName + ", wordTranslation=" + wordTranslation
				+ ", usageExample=" + usageExample + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((usageExample == null) ? 0 : usageExample.hashCode());
		result = prime * result + ((wordId == null) ? 0 : wordId.hashCode());
		result = prime * result
				+ ((wordName == null) ? 0 : wordName.hashCode());
		result = prime * result
				+ ((wordTranslation == null) ? 0 : wordTranslation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (usageExample == null) {
			if (other.usageExample != null)
				return false;
		} else if (!usageExample.equals(other.usageExample))
			return false;
		if (wordId == null) {
			if (other.wordId != null)
				return false;
		} else if (!wordId.equals(other.wordId))
			return false;
		if (wordName == null) {
			if (other.wordName != null)
				return false;
		} else if (!wordName.equals(other.wordName))
			return false;
		if (wordTranslation == null) {
			if (other.wordTranslation != null)
				return false;
		} else if (!wordTranslation.equals(other.wordTranslation))
			return false;
		return true;
	}
}
