package data;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.swing.colorchooser.ColorSelectionModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;


public class CelebrityDAOImpl implements CelebDao{
	
	private static final String FILE_NAME = "/WEB-INF/data.txt";
	private List<Celebrity> celeb = new ArrayList<>();
	private ArrayList<String> colors = new ArrayList<String>();
	
	

	@Autowired
	private WebApplicationContext wac;

	@PostConstruct
	public void addColors() {
		colors.add("#8E24AA");
		colors.add("#00796B");
		colors.add("#0277BD");
		colors.add("#F9A825");
		colors.add("#E64A19");
		colors.add("#C2185B");
	}
	
	@PostConstruct
	public void init() {
		try (InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line = buf.readLine();
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split("\\|");
				String id = tokens[0];
				String name = tokens[1];
				String jobTitle = tokens[2];
				String image = tokens[3];
				String quote = tokens[4];
				celeb.add(new Celebrity(name, jobTitle, image, quote));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	@Override
	public List<Celebrity> getCeleb() {
		return celeb;
	}
	@Override
	public Celebrity randomCeleb() {
		 Collections.shuffle(celeb);
		 return celeb.get(0);
	}
	@Override
	public String randomColor() {
		Collections.shuffle(colors);
		return colors.get(0);
	}
	


	@Override
	public void setCeleb(List<Celebrity> celeb) {
		this.celeb = celeb;
	}

	@Override
	public Celebrity getCelebrityByName(String name) {
		Celebrity c = null;
		for (Celebrity celebrity : celeb) {
			if (celebrity.getName().equalsIgnoreCase(name)){
				c = celebrity;
				break;
			}
		}
		return c;
	}

	@Override
	public Celebrity getCelebrityByJobTitle(String jobTitle) {
		Celebrity c = null;
		for (Celebrity celebrity : celeb) {
			if (celebrity.getJobTitle().equalsIgnoreCase(jobTitle)) {
				c = celebrity;
				break;
			}
		}
		return c;
	
	}
	

	@Override
	public void addCelebrity(Celebrity c) {
		celeb.add(c);
		persistCelebrityList();
		
	}


	@Override
	public void removeCelebrity(String n) {
		Celebrity c = null;
		for (Celebrity celebrity : celeb) {
			if (celebrity.getName().equalsIgnoreCase(n)){
				c = celebrity;
				break;
			}
			
		}
		celeb.remove(c);
		persistCelebrityList();
		
	}
	
	@Override
    public void persistCelebrityList() {
        String filePath = wac.getServletContext().getRealPath(FILE_NAME);
        System.out.println("DAO: " + filePath);
        
        try {
            PrintWriter out = new PrintWriter(new FileWriter(filePath));
            for (Celebrity c : celeb) {
                out.println(c.getName() + "|" + c.getJobTitle() + "|" + c.getImage() + "|" + c.getQuote());
                
            }
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

	@Override
	public Celebrity getNextCelebrity(Celebrity celeb) {
		
		return null;
	}

	@Override
	public Celebrity getPreviousCelebrity(Celebrity celeb) {
		
		return null;
	}

	@Override
	public Celebrity editCelebrity(String oldName, Celebrity newCeleb) {
		
		Celebrity c = null;
		for (Celebrity celebrity : celeb) {
			if (celebrity.getName().equalsIgnoreCase(oldName)){
				c = celebrity;
				break;
			}
		}
		
		int index = celeb.indexOf(c);
		celeb.remove(c);
		celeb.add(index, newCeleb);
		persistCelebrityList();

		return celeb.get(index);
	}


}
