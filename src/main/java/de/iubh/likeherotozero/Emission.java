package de.iubh.likeherotozero;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "countries_co2") 
public class Emission {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_name")
    private String land; 

    @Column(name = "year")
    private int year;

    @Column(name = "total_co2")
    private double wert; 

    @Column(name = "per_capita")
    private double perCapita;

    @Column(name = "growth_prct")
    private double growthPrct;

    @Column(name = "is_approved", nullable = false)
    private boolean isApproved;

    public Emission() {}

    // Getter und Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLand() { return land; }
    public void setLand(String land) { this.land = land; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getWert() { return wert; }
    public void setWert(double wert) { this.wert = wert; }

    public double getPerCapita() { return perCapita; }
    public void setPerCapita(double perCapita) { this.perCapita = perCapita; }

    public double getGrowthPrct() { return growthPrct; }
    public void setGrowthPrct(double growthPrct) { this.growthPrct = growthPrct; }

    public boolean isApproved() { return isApproved; }
    public void setApproved(boolean approved) { this.isApproved = approved; }
}