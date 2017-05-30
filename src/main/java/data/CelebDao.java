package data;

import java.util.List;

public interface CelebDao {
	public Celebrity getCelebrityByName(String name);
	public Celebrity getCelebrityByJobTitle(String jobTitle );
	public Celebrity addCelebrity(Celebrity celeb);
	public Celebrity getNextCelebrity(Celebrity celeb);
	public Celebrity getPreviousCelebrity(Celebrity celeb);
	public void removeCelebrity(Celebrity c);
	void persistCelebrityList();
	public Celebrity editCelebrity(Celebrity celeb);
	Celebrity randomCeleb(Celebrity oldCeleb);
	String randomColor();
	public List<Celebrity> getAllCelebrities();
	Celebrity getCelebrityById(int id);
	List<String> getCelebNames();
	List<String> getJobTitles();

	
}
