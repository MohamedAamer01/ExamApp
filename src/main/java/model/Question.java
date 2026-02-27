package model;

public class Question {
	private Long id;
    private String enonce;
    private String type;
    private String reponseCorrecte;
    private Long examenId;
    public Question() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnonce() {
		return enonce;
	}
	public void setEnonce(String enonce) {
		this.enonce = enonce;
	}
	public Long getExamenId() {
		return examenId;
	}
	public void setExamenId(Long examenId) {
		this.examenId = examenId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReponseCorrecte() {
		return reponseCorrecte;
	}
	public void setReponseCorrecte(String reponseCorrecte) {
		this.reponseCorrecte = reponseCorrecte;
	}
	
}
