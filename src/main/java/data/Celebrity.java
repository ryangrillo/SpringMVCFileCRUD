package data;

public class Celebrity {
	int id;
	String name;
	String jobTitle;
	String image;
	String quote;
	
	public Celebrity(){
		
	}
	public String getName() {
		return name;
	}
	public Celebrity(String name, String jobTitle, String image, String quote) {
		super();
		this.name = name;
		this.jobTitle = jobTitle;
		this.image = image;
		this.quote = quote;
	}
	public Celebrity(int id, String name, String jobTitle, String image, String quote) {
		super();
		this.id = id;
		this.name = name;
		this.jobTitle = jobTitle;
		this.image = image;
		this.quote = quote;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	@Override
	public String toString() {
		return "celebrity [name=" + name + ", jobTitle=" + jobTitle + ", image=" + image + ", quote=" + quote + "]";
	}
	
}
