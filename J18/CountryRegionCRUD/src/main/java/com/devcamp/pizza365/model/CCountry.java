package com.devcamp.pizza365.model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class CCountry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code", unique = true)
    private String countryCode;

    @Column(name = "country_name")
    private String countryName;

    @OneToMany(targetEntity = CRegion.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private List<CRegion> regions;


    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public List<CRegion> getRegions() {
		return regions;
	}

	public void setRegions(List<CRegion> regions) {
		this.regions = regions;
	}
}

