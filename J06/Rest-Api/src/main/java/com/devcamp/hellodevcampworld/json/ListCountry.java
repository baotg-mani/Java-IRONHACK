package com.devcamp.hellodevcampworld.json;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class ListCountry {

	private Map<String, String> languagesMap = new TreeMap<String, String>();

	public ListCountry() {
		initLanguageMap();
	}

	public static void main(String[] args) {

		ListCountry obj = new ListCountry();
		obj.getListOfCountries();

	}

	public void getListOfCountries() {

		String[] countries = Locale.getISOCountries();

		int supportedLocale = 0, nonSupportedLocale = 0;

		for (String countryCode : countries) {

			Locale obj = null;
			if (languagesMap.get(countryCode) == null) {

				obj = new Locale("", countryCode);
				nonSupportedLocale++;

			} else {

				// create a Locale with own country's languages
				obj = new Locale(languagesMap.get(countryCode), countryCode);
				supportedLocale++;

			}

			System.out.println("Languages " + languagesMap.get(countryCode) +" Country Code = " + obj.getCountry() + ", Country Name = " + obj.getDisplayCountry(obj)
					+ ", Languages = " + obj.getDisplayLanguage());

		}

		System.out.println("nonSupportedLocale : " + nonSupportedLocale);
		System.out.println("supportedLocale : " + supportedLocale);

	}

	// create Map with country code and languages
	public void initLanguageMap() {

		Locale[] locales = Locale.getAvailableLocales();

		for (Locale obj : locales) {

			if ((obj.getDisplayCountry() != null) && (!"".equals(obj.getDisplayCountry()))) {
				languagesMap.put(obj.getCountry(), obj.getLanguage());
			}

		}

	}

}
