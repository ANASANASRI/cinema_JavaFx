package com.cinema.cinemafx.JDBC.entities;

public class Cinema {
    private String nom;
    private String emplacement;
    private int capaciteMaximale;
    private boolean estOuvert;
    private int nombreDeSalles;
    private double chiffreAffaireAnnuel;
    private double prixMoyen;
    private String numeroDeTelephone;
    private int id;

    public Cinema(String nom, String emplacement, int capaciteMaximale, boolean estOuvert, int nombreDeSalles, double numeroDeTelephone, double chiffreAffaireAnnuel, String prixMoyen) {
        this.nom = nom;
        this.emplacement = emplacement;
        this.capaciteMaximale = capaciteMaximale;
        this.estOuvert = estOuvert;
        this.nombreDeSalles = nombreDeSalles;
        this.numeroDeTelephone = String.valueOf(numeroDeTelephone);
        this.chiffreAffaireAnnuel = chiffreAffaireAnnuel;
        this.prixMoyen = Double.parseDouble(prixMoyen);
    }

    public Cinema() {
        this.nom = "";
        this.emplacement = "";
        this.capaciteMaximale = 0;
        this.estOuvert = false;
        this.nombreDeSalles = 0;
        this.numeroDeTelephone = "";
        this.chiffreAffaireAnnuel = 0.0;
        this.prixMoyen = 0.0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getCapaciteMaximale() {
        return capaciteMaximale;
    }

    public void setCapaciteMaximale(int capaciteMaximale) {
        this.capaciteMaximale = capaciteMaximale;
    }

    public boolean isEstOuvert() {
        return estOuvert;
    }

    public void setEstOuvert(boolean estOuvert) {
        this.estOuvert = estOuvert;
    }

    public int getNombreDeSalles() {
        return nombreDeSalles;
    }

    public void setNombreDeSalles(int nombreDeSalles) {
        this.nombreDeSalles = nombreDeSalles;
    }

    public String getNumeroDeTelephone() {
        return numeroDeTelephone;
    }

    public void setNumeroDeTelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }

    public double getChiffreAffaireAnnuel() {
        return chiffreAffaireAnnuel;
    }

    public void setChiffreAffaireAnnuel(double chiffreAffaireAnnuel) {
        this.chiffreAffaireAnnuel = chiffreAffaireAnnuel;
    }

    public double getPrixMoyen() {
        return prixMoyen;
    }

    public void setPrixMoyen(double prixMoyen) {
        this.prixMoyen = prixMoyen;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "nom='" + nom + '\'' +
                ", emplacement='" + emplacement + '\'' +
                ", capaciteMaximale=" + capaciteMaximale +
                ", estOuvert=" + estOuvert +
                ", nombreDeSalles=" + nombreDeSalles +
                ", numeroDeTelephone='" + numeroDeTelephone + '\'' +
                ", chiffreAffaireAnnuel=" + chiffreAffaireAnnuel +
                ", prixMoyen=" + prixMoyen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cinema cinema)) return false;
        return Double.compare(cinema.getChiffreAffaireAnnuel(), getChiffreAffaireAnnuel()) == 0 && Double.compare(cinema.getPrixMoyen(), getPrixMoyen()) == 0 && getCapaciteMaximale() == cinema.getCapaciteMaximale() && isEstOuvert() == cinema.isEstOuvert() && getNombreDeSalles() == cinema.getNombreDeSalles() && getNom().equals(cinema.getNom()) && getEmplacement().equals(cinema.getEmplacement()) && getNumeroDeTelephone().equals(cinema.getNumeroDeTelephone());
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
