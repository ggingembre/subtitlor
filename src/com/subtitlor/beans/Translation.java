package com.subtitlor.beans;

public class Translation {
	
	private long Id;
	private String fileName;
	private String timeStamp;
	private String translatedLineOne;
	private String translatedLineTwo;
	private Language language;
	private boolean original;
	
	public boolean isOriginal() {
		return original;
	}

	public void setOriginal(boolean original) {
		this.original = original;
	}

	public Translation() {}
	
	public Translation(long id, String fileName, String timeStamp, String translatedLineOne, String translatedLineTwo, Language language) {
		super();
		Id = id;
		this.fileName = fileName;
		this.timeStamp = timeStamp;
		this.translatedLineOne = translatedLineOne;
		this.translatedLineTwo = translatedLineTwo;
		this.language = language;
		this.original = false;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
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
		Translation other = (Translation) obj;
		if (Id != other.Id)
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (language != other.language)
			return false;
		return true;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTranslatedLineOne() {
		return translatedLineOne;
	}

	public void setTranslatedLineOne(String translatedLineOne) {
		this.translatedLineOne = translatedLineOne;
	}

	public String getTranslatedLineTwo() {
		return translatedLineTwo;
	}

	public void setTranslatedLineTwo(String translatedLineTwo) {
		this.translatedLineTwo = translatedLineTwo;
	}
	
	
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Translation [Id=" + Id + ", fileName=" + fileName + ", timeStamp=" + timeStamp + ", translatedLineOne="
				+ translatedLineOne + ", translatedLineTwo=" + translatedLineTwo + ", language=" + language
				+ ", original=" + original + "]";
	}
	
}
