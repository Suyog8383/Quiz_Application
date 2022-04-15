package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.QuestionService.QuestionService;
import com.example.demo.QuestionService.QuizService;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionForm;
import com.example.demo.model.Result;

@Controller
public class MainController {
	
	@Autowired
	Question question;
	@Autowired
	Result result;
	@Autowired
	QuizService qService;
	@Autowired
    private QuestionService questionService;
	Boolean submitted = false;
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}
	
	@RequestMapping("/")
	public String indexPage()
	{
		return "index.html";
	}
	
	@RequestMapping("/loginpage")
	public String loginPage()
	{
		return "login.html";
	}
	
	@PostMapping("/login")
    public String login(@RequestParam String email, String password) {
               if(email.equals("quiz@admin") && password.equals("admin"))
                {
                	return "adminHome.html";
                }
        
		return "login.html";
    }
	
	@RequestMapping("/adminHome")
	public String adminHome()
	{
		return "adminHome.html";
	}
	
	@RequestMapping("/addQuestion")
	public String addQuestion()
	{
		return "addquestion.html";
	}
	
	@PostMapping("/addquestion")
	public String AddQuestion(@ModelAttribute("question") Question question, Model m) {
		questionService.saveQuestion(question);
		
		List<Question> qList = qService.ViewtQuestions();
		m.addAttribute("qList", qList);
		return "viewquestion.html";
	}
	
	@RequestMapping("/viewQuestion")
	public String viewQuestion(Model m)
	{
		List<Question> qList = qService.ViewtQuestions();
		m.addAttribute("qList", qList);
		return "viewquestion.html";
	}
	
	@GetMapping("/scoreBoard")
	public String score(Model m) {
		List<Result> sList = qService.getTopScore();
		m.addAttribute("sList", sList);
		
		return "scoreboard.html";
	}
	
	@PostMapping("/quiz")
	public String quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
		if(username.equals("")) {
			ra.addFlashAttribute("warning", "You must enter your name");
			return "redirect:/";
		}
		
		submitted = false;
		result.setUsername(username);
		
		QuestionForm qForm = qService.getQuestions();
		m.addAttribute("qForm", qForm);
		
		return "quiz.html";
	}
	
	@PostMapping("/submit")
	public String submit(@ModelAttribute QuestionForm qForm, Model m) {
	
		if(!submitted) {
			result.setTotalCorrect(qService.getResult(qForm));
			qService.saveScore(result);
			submitted = true;
		}
		
		return "result.html";
	}
	@GetMapping("/showQuestionForUpdate/{quesId}")
    public String showQuestionForUpdate(@PathVariable(value = "quesId") int quesId, Model model) {

        Question question = questionService.getQuestionById(quesId);

        model.addAttribute("question", question);
        return "update_question.html";
    }

    @GetMapping("/deleteQuestion/{quesId}")
    public String deleteQuestion(@PathVariable(value = "quesId") int quesId, Model m) {

        this.questionService.deleteQuestionById(quesId);
        List<Question> qList = qService.ViewtQuestions();
		m.addAttribute("qList", qList);
        return "viewquestion.html";
    }
    
    @RequestMapping("/register")
	public String registerPage()
	{
		return "register.html";
	}
    
    @RequestMapping("/attainExam")
	public String StudentExamrPage()
	{
		return "attainExam.html";
	}
    @PostMapping("/studentquiz")
	public String studentquiz(@RequestParam String username, Model m, RedirectAttributes ra) {
		if(username.equals("")) {
			ra.addFlashAttribute("warning", "You must enter your name");
			return "redirect:/";
		}
		
		submitted = false;
		result.setUsername(username);
		
		QuestionForm qForm = qService.getQuestions();
		m.addAttribute("qForm", qForm);
		
		return "studentquiz.html";
	}
    @RequestMapping("/studentsubmit")
    public String Studentsubmit(@ModelAttribute QuestionForm qForm, Model m) {
		if(!submitted) {
			result.setTotalCorrect(qService.getResult(qForm));
			qService.saveScore(result);
			submitted = true;
		}
		return "studentResult.html";
	}
    
    @RequestMapping("/studyboard")
	public String studyBoard()
	{
		return "studyboard.html";
	}
    @RequestMapping("/java")
	public String javastudy()
	{
		return "java.html";
	}
    @RequestMapping("/css")
	public String cssstudy()
	{
		return "css.html";
	}
    @RequestMapping("/html")
	public String htmlstudy()
	{
		return "html.html";
	}
    @RequestMapping("/practice")
	public String quizpractice()
	{
		return "practice.html";
	}
    @RequestMapping("/cssnew")
	public String cssTimerQuiz()
	{
		return "cssnew.html";
	}
    @RequestMapping("/javanew")
	public String javaTimerQuiz()
	{
		return "javanew.html";
	}
    @RequestMapping("/htmlnew")
	public String htmlTimerQuiz()
	{
		return "htmlnew.html";
	}
	
}
