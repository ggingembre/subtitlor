package com.subtitlor.beans;

public class Original {

	private long Id;
	private String fileName;
	private String timeStamp;
	private String originalLineOne;
	private String originalLineTwo;
	private Language language;
	private boolean original;
	
	
	public Original () {}


	public Original(long id, String fileName, String timeStamp, String originalLineOne, String originalLineTwo, Language language) {
		super();
		Id = id;
		this.fileName = fileName;
		this.timeStamp = timeStamp;
		this.originalLineOne = originalLineOne;
		this.originalLineTwo = originalLineTwo;
		this.language = language; 
		this.original = true;
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
		Original other = (Original) obj;
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


	public String getOriginalLineOne() {
		return originalLineOne;
	}


	public void setOriginalLineOne(String originalLineOne) {
		this.originalLineOne = originalLineOne;
	}


	public String getOriginalLineTwo() {
		return originalLineTwo;
	}


	public void setOriginalLineTwo(String originalLineTwo) {
		this.originalLineTwo = originalLineTwo;
	}


	
	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public boolean isOriginal() {
		return original;
	}


	public void setOriginal(boolean original) {
		this.original = original;
	}


	@Override
	public String toString() {
		return "Original [Id=" + Id + ", fileName=" + fileName + ", timeStamp=" + timeStamp + ", originalLineOne="
				+ originalLineOne + ", originalLineTwo=" + originalLineTwo + ", language=" + language + ", original="
				+ original + "]";
	}



	
	
	
	

}
