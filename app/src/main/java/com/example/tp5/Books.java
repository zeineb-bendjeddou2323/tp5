package com.example.tp5;

public class Books {

    private String titre;
    private String auteur;
    private String motCles;
    private String rusumé;
    private int id;

    public Books()
    {
           titre = "";
           auteur = "";
           motCles = "";
           rusumé = "";
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getTitre()
    {
        return titre;
    }
    public String getAuteur()
    {
        return auteur;
    }public String getMotCles()
    {
        return motCles;
    }
    public String getRusumé()
    {
        return rusumé;
    }
    public void setTitre(String titre)
    {
       this.titre = titre;
    }
    public void setAuteur(String auteur)
    {
        this.auteur = auteur;
    }public void setMotCles(String motCles)
    {
        this.motCles = motCles;
    }
    public void setRusumé(String rusumé)
    {
        this.rusumé = rusumé;
    }

}
