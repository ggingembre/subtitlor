package com.subtitlor.beans;

public class ObjectRequest {
	
	private String fileName;
	private Language language;
	
	public ObjectRequest() {}

	public ObjectRequest(String fileName, Language language) {
		super();
		this.fileName = fileName;
		this.language = language;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
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
		ObjectRequest other = (ObjectRequest) obj;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (language != other.language)
			return false;
		return true;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "ObjectRequest [fileName=" + fileName + ", language=" + language + "]";
	}
	
	

}
