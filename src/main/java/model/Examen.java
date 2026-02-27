package model;

public class Examen {

    private Long id;
    private String titre;
    private int tempsTotal;
	private Long adminId;
    public Examen() {}

    public Examen(Long id, String titre, int tempsTotal) {
        this.id = id;
        this.titre = titre;
        this.tempsTotal = tempsTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getTempsTotal() {
        return tempsTotal;
    }

    public void setTempsTotal(int temps) {
        this.tempsTotal = temps;
    }

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}


}