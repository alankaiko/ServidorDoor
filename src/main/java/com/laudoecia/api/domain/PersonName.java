package com.laudoecia.api.domain;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.dcm4che3.data.PersonName.Component;
import org.dcm4che3.data.PersonName.Group;

import com.laudoecia.api.utilities.FuzzyStr;

@Entity
@Table//(name = "personname")
public class PersonName {
    private Long codigo;
    private String familyname;
    private String givenname;
    private String middlename;
    private String nameprefix;
    private String namesuffix;
    private String ideographicfamilyname;
    private String ideographicgivenname;
    private String ideographicmiddlename;
    private String ideographicnameprefix;
    private String ideographicnamesuffix;
    private String phoneticfamilyname;
    private String phoneticgivenname;
    private String phoneticmiddlename;
    private String phoneticnameprefix;
    private String phoneticnamesuffix;
    private Collection<SoundexCode> soundexcodes;

    public PersonName() {}
    
    public PersonName(org.dcm4che3.data.PersonName pn, FuzzyStr fuzzyStr) {
        fromDicom(pn, fuzzyStr);
    }

   

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getNameprefix() {
		return nameprefix;
	}

	public void setNameprefix(String nameprefix) {
		this.nameprefix = nameprefix;
	}

	public String getNamesuffix() {
		return namesuffix;
	}

	public void setNamesuffix(String namesuffix) {
		this.namesuffix = namesuffix;
	}

	public String getIdeographicfamilyname() {
		return ideographicfamilyname;
	}

	public void setIdeographicfamilyname(String ideographicfamilyname) {
		this.ideographicfamilyname = ideographicfamilyname;
	}

	public String getIdeographicgivenname() {
		return ideographicgivenname;
	}

	public void setIdeographicgivenname(String ideographicgivenname) {
		this.ideographicgivenname = ideographicgivenname;
	}

	public String getIdeographicmiddlename() {
		return ideographicmiddlename;
	}

	public void setIdeographicmiddlename(String ideographicmiddlename) {
		this.ideographicmiddlename = ideographicmiddlename;
	}

	public String getIdeographicnameprefix() {
		return ideographicnameprefix;
	}

	public void setIdeographicnameprefix(String ideographicnameprefix) {
		this.ideographicnameprefix = ideographicnameprefix;
	}

	public String getIdeographicnamesuffix() {
		return ideographicnamesuffix;
	}

	public void setIdeographicnamesuffix(String ideographicnamesuffix) {
		this.ideographicnamesuffix = ideographicnamesuffix;
	}

	public String getPhoneticfamilyname() {
		return phoneticfamilyname;
	}

	public void setPhoneticfamilyname(String phoneticfamilyname) {
		this.phoneticfamilyname = phoneticfamilyname;
	}

	public String getPhoneticgivenname() {
		return phoneticgivenname;
	}

	public void setPhoneticgivenname(String phoneticgivenname) {
		this.phoneticgivenname = phoneticgivenname;
	}

	public String getPhoneticmiddlename() {
		return phoneticmiddlename;
	}

	public void setPhoneticmiddlename(String phoneticmiddlename) {
		this.phoneticmiddlename = phoneticmiddlename;
	}

	public String getPhoneticnameprefix() {
		return phoneticnameprefix;
	}

	public void setPhoneticnameprefix(String phoneticnameprefix) {
		this.phoneticnameprefix = phoneticnameprefix;
	}

	public String getPhoneticnamesuffix() {
		return phoneticnamesuffix;
	}

	public void setPhoneticnamesuffix(String phoneticnamesuffix) {
		this.phoneticnamesuffix = phoneticnamesuffix;
	}

	@OneToMany(mappedBy = "personname", cascade = CascadeType.ALL, orphanRemoval = true)
	public Collection<SoundexCode> getSoundexcodes() {
		return soundexcodes;
	}
	
	public void setSoundexcodes(Collection<SoundexCode> soundexcodes) {
		this.soundexcodes = soundexcodes;
	}
	
	private void fromDicom(org.dcm4che3.data.PersonName pn, FuzzyStr fuzzyStr) {
		familyname = pn.get(Group.Alphabetic, Component.FamilyName);
	    givenname = pn.get(Group.Alphabetic, Component.GivenName);
	    middlename = pn.get(Group.Alphabetic, Component.MiddleName);
	    nameprefix = pn.get(Group.Alphabetic, Component.NamePrefix);
	    namesuffix = pn.get(Group.Alphabetic, Component.NameSuffix);
	    ideographicfamilyname = pn.get(Group.Ideographic, Component.FamilyName);
	    ideographicgivenname = pn.get(Group.Ideographic, Component.GivenName);
	    ideographicmiddlename = pn.get(Group.Ideographic, Component.MiddleName);
	    ideographicnameprefix = pn.get(Group.Ideographic, Component.NamePrefix);
	    ideographicnamesuffix = pn.get(Group.Ideographic, Component.NameSuffix);
	    phoneticfamilyname = pn.get(Group.Phonetic, Component.FamilyName);
	    phoneticgivenname = pn.get(Group.Phonetic, Component.GivenName);
	    phoneticmiddlename = pn.get(Group.Phonetic, Component.MiddleName);
	    phoneticnameprefix = pn.get(Group.Phonetic, Component.NamePrefix);
	    phoneticnamesuffix = pn.get(Group.Phonetic, Component.NameSuffix);
	    createOrUpdateSoundexCodes(familyname, givenname, ideographicmiddlename, fuzzyStr);
	}

	private void createOrUpdateSoundexCodes(String familyName, String givenName, String middleName, FuzzyStr fuzzyStr) { 
	    if (soundexcodes == null)
	    	soundexcodes = new ArrayList<SoundexCode>();
	    else
	        for (Iterator<SoundexCode> iterator = soundexcodes.iterator(); iterator.hasNext();) {
	            iterator.next();
	            iterator.remove();
	        }
	
	    addSoundexCodesTo(Component.FamilyName, familyName, fuzzyStr, soundexcodes);
	    addSoundexCodesTo(Component.GivenName, givenName, fuzzyStr, soundexcodes);
	    addSoundexCodesTo(Component.MiddleName, middleName, fuzzyStr, soundexcodes);
	}

	private void addSoundexCodesTo(Component component, String name, FuzzyStr fuzzyStr, Collection<SoundexCode> codes) {
	    if (name == null)
	        return;
	
	    Iterator<String> parts = SoundexCode.tokenizePersonNameComponent(name);
	    for (int i = 0; parts.hasNext(); i++) {
	        SoundexCode soundexCode = new SoundexCode(component, i, fuzzyStr.toFuzzy(parts.next()));
	        soundexCode.setPersonname(this);
	        codes.add(soundexCode);
	    }
	}

	public org.dcm4che3.data.PersonName toPersonName() {
	    org.dcm4che3.data.PersonName pn = new org.dcm4che3.data.PersonName();
	    pn.set(Group.Alphabetic, Component.FamilyName, familyname);
	    pn.set(Group.Alphabetic, Component.GivenName, givenname);
	    pn.set(Group.Alphabetic, Component.MiddleName, middlename);
	    pn.set(Group.Alphabetic, Component.NamePrefix, nameprefix);
	    pn.set(Group.Alphabetic, Component.NameSuffix, namesuffix);
	    pn.set(Group.Ideographic, Component.FamilyName, ideographicfamilyname);
	    pn.set(Group.Ideographic, Component.GivenName, ideographicgivenname);
	    pn.set(Group.Ideographic, Component.MiddleName, ideographicmiddlename);
	    pn.set(Group.Ideographic, Component.NamePrefix, ideographicnameprefix);
	    pn.set(Group.Ideographic, Component.NameSuffix, ideographicnamesuffix);
	    pn.set(Group.Phonetic, Component.FamilyName, phoneticfamilyname);
	    pn.set(Group.Phonetic, Component.GivenName, phoneticgivenname);
	    pn.set(Group.Phonetic, Component.MiddleName, phoneticmiddlename);
	    pn.set(Group.Phonetic, Component.NamePrefix, phoneticnameprefix);
	    pn.set(Group.Phonetic, Component.NameSuffix, phoneticnamesuffix);
	    return pn;
	}

	public static PersonName valueOf(String s, FuzzyStr fuzzyStr, PersonName prev) {
	    if (s == null)
	        return null;
	
	    org.dcm4che3.data.PersonName pn = new org.dcm4che3.data.PersonName(s,true);
	    if (pn.isEmpty())
	        return null;
	
	    if (prev != null) {
	        if (!pn.equals(prev.toPersonName()))
	            prev.fromDicom(pn, fuzzyStr); //update values
	    return prev;
	} else
	    return new PersonName(pn, fuzzyStr); //create new
	}
	    
	@Override
	public String toString() {
	    return toPersonName().toString();
	}
}
