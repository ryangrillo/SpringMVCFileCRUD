package data;

import java.util.List;

public interface CelebDao {
	public Celebrity getCelebrityByName(String name);
	public Celebrity getCelebrityByJobTitle(String jobTitle );
	public void addCelebrity(Celebrity celeb);
	public Celebrity getNextCelebrity(Celebrity celeb);
	public Celebrity getPreviousCelebrity(Celebrity celeb);
	List<Celebrity> getCeleb();
	void setCeleb(List<Celebrity> celeb);
	public void removeCelebrity(String n);
	void persistCelebrityList();
	public Celebrity editCelebrity(String oldName, Celebrity celeb);
	Celebrity randomCeleb();
	String randomColor();
	
}
