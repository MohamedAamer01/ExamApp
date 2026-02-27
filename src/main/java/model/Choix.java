package model;

public class Choix {
	private Long id;
    private Long questionId;
    private String contenu;
    private boolean estCorrect;
    public Choix() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public boolean isEstCorrect() {
		return estCorrect;
	}
	public void setEstCorrect(boolean estCorrect) {
		this.estCorrect = estCorrect;
	}
    
}
