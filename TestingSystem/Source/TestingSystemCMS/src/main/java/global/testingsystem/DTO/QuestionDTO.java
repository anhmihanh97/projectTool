package global.testingsystem.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import global.testingsystem.entity.Answer_Option;
import global.testingsystem.entity.Chapter;
import global.testingsystem.entity.Domain;
import global.testingsystem.entity.Question;
import global.testingsystem.entity.Subject;

public class QuestionDTO {
	
	private int id;

	private String title;

	private int time;
	
	private String code;

	private String content;

	private String media;

	private Date created_at;

	private Date updated_at;

	private int status;


	private Integer page;

	
	private String source;


	private String explantion;


	private int creator_id;
	
	private Subject subject;
	
	private Chapter chapter;

	private Domain domain;

	private List<AnswerOptionDTO> answer_Options;

	public QuestionDTO(Question ques, String typeList) {
		super();
		this.id = ques.getId();
		this.title = ques.getTitle();
		this.time = ques.getTime();
		this.code = ques.getCode();
		this.content = ques.getContent();
		this.media = ques.getMedia();
		this.created_at = ques.getCreated_at();
		this.updated_at = ques.getUpdated_at();
		this.status = ques.getStatus();
		this.page = ques.getPage();
		this.source = ques.getSource();
		this.explantion = ques.getExplantion();
		this.creator_id = ques.getCreator_id();
		
		this.subject = ques.getSubject();
		this.chapter = ques.getChapter();
		this.domain = ques.getDomain();
		this.answer_Options = new ArrayList<AnswerOptionDTO>();
		for(Answer_Option ao : ques.getAnswer_Options()) {
			if("listQuestion".equals(typeList))
			this.answer_Options.add(new AnswerOptionDTO(ao.getId(),ao.getContent(),ao.getMedia(),false));
			else this.answer_Options.add(new AnswerOptionDTO(ao.getId(),ao.getContent(),ao.getMedia(),ao.isCorrect()));
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getExplantion() {
		return explantion;
	}

	public void setExplantion(String explantion) {
		this.explantion = explantion;
	}

	public int getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public List<AnswerOptionDTO> getAnswer_Options() {
		return answer_Options;
	}

	public void setAnswer_Options(List<AnswerOptionDTO> answer_Options) {
		this.answer_Options = answer_Options;
	}


}
