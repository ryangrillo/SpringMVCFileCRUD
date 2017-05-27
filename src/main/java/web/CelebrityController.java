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
		mv.addObject("celebObject", dao.getCelebrityByName(fn));
		return mv;
	}
	
	@RequestMapping(path="getJobTitle.do", method = RequestMethod.GET)
	public ModelAndView getJobTitle(@RequestParam("jobTitle") String jt) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("celebObject", dao.getCelebrityByJobTitle(jt));
		mv.setViewName("WEB-INF/views/results.jsp");
		return mv;
	
	}
	
	@RequestMapping(path="getAllCelebs.do", method = RequestMethod.GET)
	public ModelAndView getAllCelebs() {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		List<Celebrity> list = dao.getAllCelebrities();
		mv.addObject("celebList", list);
		System.out.println(list);
		return mv;
	}
	
	@RequestMapping(path="addCelebrity.do", method = RequestMethod.POST)
	public ModelAndView addCelebrity(Celebrity celeb) {
		ModelAndView mv = new ModelAndView("index.html");
		dao.addCelebrity(celeb);
		return mv;
	}
	@RequestMapping(path="removeCelebrity.do", method = RequestMethod.POST)
	public ModelAndView RemoveCelebrity(Celebrity c) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		dao.removeCelebrity(c);
		dao.removeCelebrity(c);
		mv.addObject("celebObject", c);
		return mv;
	}
	
	@RequestMapping(path = "updateCelebrity.do", params="goToEdit", method=RequestMethod.POST)
	public ModelAndView sendToEditPage(@RequestParam("goToEdit") String thingToDo, Celebrity celeb) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/edit.jsp");
		mv.addObject("celeb", celeb);	
		return mv;
	}
	
	@RequestMapping(path = "updateCelebrity.do", method=RequestMethod.POST)
	public ModelAndView updateCelebrity(Celebrity celeb) {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		mv.addObject("celebObject", dao.editCelebrity(celeb));
		return mv;
	}
	@RequestMapping(path="randomCeleb.do", method = RequestMethod.GET)
	public ModelAndView getrandomCeleb() {
		System.out.println("in random celeb");
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		mv.addObject("celebObject", dao.randomCeleb());
		mv.addObject("randColor", dao.randomColor());
		return mv;
	}
	@RequestMapping(path="randomColor.do", method = RequestMethod.GET)
	public ModelAndView getrandomColor() {
		ModelAndView mv = new ModelAndView("WEB-INF/views/results.jsp");
		mv.addObject("celebObject", dao.randomColor());
		return mv;
	}
}
