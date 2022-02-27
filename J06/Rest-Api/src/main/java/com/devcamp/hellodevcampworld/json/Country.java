package com.devcamp.hellodevcampworld.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "countryName", "countryShortCode", "regions" })
@Generated("jsonschema2pojo")
public class Country {

	@JsonProperty("countryName")
	private String countryName;
	@JsonProperty("countryShortCode")
	private String countryShortCode;
	@JsonProperty("regions")
	private List<Region> regions = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("countryName")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("countryName")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("countryShortCode")
	public String getCountryShortCode() {
		return countryShortCode;
	}

	@JsonProperty("countryShortCode")
	public void setCountryShortCode(String countryShortCode) {
		this.countryShortCode = countryShortCode;
	}

	@JsonProperty("regions")
	public List<Region> getRegions() {
		return regions;
	}

	@JsonProperty("regions")
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}