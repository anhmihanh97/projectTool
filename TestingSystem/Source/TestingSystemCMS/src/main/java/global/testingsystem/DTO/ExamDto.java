package global.testingsystem.DTO;

import java.util.Date;

public class ExamDto {
	private Integer id;
	private Integer questionNum;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
    private String name;
    private String title;
    private String subject;
    private boolean isTested;
    private int status;
    private int mode;
    private String owner;
    private int duration;
    private Integer creatorId;
	public ExamDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamDto(Integer id, Integer questionNum, Date startDate, Date endDate, Date createdAt, String name,
			String title, String subject, boolean isTested, int status, int mode, String owner, int duration, Integer creatorId) {
		super();
		this.id = id;
		this.questionNum = questionNum;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
		this.name = name;
		this.title = title;
		this.subject = subject;
		this.isTested = isTested;
		this.status = status;
		this.mode = mode;
		this.owner = owner;
		this.duration = duration;
		this.creatorId=creatorId;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isTested() {
		return isTested;
	}

	public void setTested(boolean isTested) {
		this.isTested = isTested;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
