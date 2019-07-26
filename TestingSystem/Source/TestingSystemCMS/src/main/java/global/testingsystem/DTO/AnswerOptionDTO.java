package global.testingsystem.DTO;
public class AnswerOptionDTO {

	public AnswerOptionDTO(int id, String content, String media,boolean correct) {
		super();
		this.id = id;
		this.content = content;
		this.media = media;
		this.correct=correct;
	}

	private int id;
	
	private String content;
	
	private  boolean correct;
	
	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	private String media;

}
