package web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.CelebDao;
import data.Celebrity;

@Controller
public class CelebrityController {
	
	@Autowired
	CelebDao dao;
	
	@RequestMapping(path="getFirstName.do", method = RequestMethod.GET)
	public ModelAndView getFirstName(@RequestParam("firstName") String fn) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		mv.addObject("randColor", dao.randomColor());
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		mv.addObject("celebObject", dao.getCelebrityByName(fn));
		return mv;
	}
	
	@RequestMapping(path="getJobTitle.do", method = RequestMethod.GET)
	public ModelAndView getJobTitle(@RequestParam("jobTitle") String jt) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("celebObject", dao.getCelebrityByJobTitle(jt));
		mv.addObject("randColor", dao.randomColor());
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		mv.setViewName("WEB-INF/views/results.jsp");
		return mv;
	
	}
	
	@RequestMapping(path="getAllCelebs.do", method = RequestMethod.GET)
	public ModelAndView getAllCelebs() {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		List<Celebrity> list = dao.getAllCelebrities();
		mv.addObject("celebList", list);
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		System.out.println(list);
		return mv;
	}
	
//	@RequestMapping(path="addCelebrity.do", method = RequestMethod.POST)
//	public ModelAndView addCelebrity(Celebrity celeb) {
//		ModelAndView mv = new ModelAndView("index.html");
//		dao.addCelebrity(celeb);
//		return mv;
//	}
	@RequestMapping(path="addCelebrity.do", method = RequestMethod.POST)
	public ModelAndView addCelebrity(Celebrity celeb) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		Celebrity c = dao.addCelebrity(celeb);
		mv.addObject("randColor", dao.randomColor());
		mv.addObject("celebObject", c);
		return mv;
	}
	@RequestMapping(path="removeCelebrity.do", method = RequestMethod.POST)
	public ModelAndView RemoveCelebrity(Celebrity c) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		dao.removeCelebrity(c);
		mv.addObject("celebObject", dao.randomCeleb(c));
		mv.addObject("randColor", dao.randomColor());
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		return mv;
	}
	
	@RequestMapping(path = "goToEditCeleb.do", method=RequestMethod.POST)
	public ModelAndView sendToEditPage(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/edit.jsp");
		mv.addObject("celeb", dao.getCelebrityById(id));	
		return mv;
	}
	
	@RequestMapping(path = "updateCelebrity.do", method=RequestMethod.POST)
	public ModelAndView updateCelebrity(Celebrity celeb) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		System.out.println(celeb.getQuote());
		mv.addObject("celebObject", dao.editCelebrity(celeb));
		mv.addObject("randColor", dao.randomColor());
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		return mv;
	}
	@RequestMapping(path="randomCeleb.do", method = RequestMethod.GET)
	public ModelAndView getrandomCeleb(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		Celebrity oldCeleb = dao.getCelebrityById(id);
		Celebrity randomCeleb = dao.randomCeleb(oldCeleb);
		mv.addObject("celebObject", randomCeleb);
		mv.addObject("randColor", dao.randomColor());
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		return mv;
	}
	@RequestMapping(path="firstRandomCeleb.do", method = RequestMethod.GET)
	public ModelAndView getFirstRandomCeleb() {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		Celebrity oldCeleb = dao.getCelebrityById(1);
		Celebrity randomCeleb = dao.randomCeleb(oldCeleb);
		mv.addObject("celebObject", randomCeleb);
		mv.addObject("randColor", dao.randomColor());
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		return mv;
	}
	@RequestMapping(path="randomColor.do", method = RequestMethod.GET)
	public ModelAndView getrandomColor() {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		mv.addObject("celebObject", dao.randomColor());
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		return mv;
	}
	@RequestMapping(path="goHome.do", method = RequestMethod.GET)
	public ModelAndView goHome() {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		mv.addObject("celebObject", dao.getCelebrityById(1));
		mv.addObject("randColor", dao.randomColor());
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		return mv;
	}
	@RequestMapping(path="addForm.do", method = RequestMethod.GET)
	public ModelAndView addForm() {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		mv.addObject("add", "add");
		mv.addObject("home", "home");
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		mv.addObject("randColor", dao.randomColor());
		
		return mv;
	}
	@RequestMapping(path="updateForm.do", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam("id") int id) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		mv.addObject("update", "update");
		mv.addObject("home", "home");
		mv.addObject("celeb", dao.getCelebrityById(id));
		mv.addObject("jobTitleList", dao.getJobTitles());
		mv.addObject("celebNameList", dao.getCelebNames());
		mv.addObject("randColor", dao.randomColor());
		
		return mv;
	}
}
