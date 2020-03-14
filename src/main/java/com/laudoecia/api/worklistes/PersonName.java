package com.laudoecia.api.worklistes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.dcm4che3.data.PersonName.Component;
import org.dcm4che3.data.PersonName.Group;

import com.laudoecia.api.utilities.FuzzyStr;

public class PersonName {

	private long pk;
	private String familyName;
	private String givenName;
	private String middleName;
	private String namePrefix;
	private String nameSuffix;
	private String ideographicFamilyName;
	private String ideographicGivenName;
	private String ideographicMiddleName;
	private String ideographicNamePrefix;
	private String ideographicNameSuffix;
	private String phoneticFamilyName;
	private String phoneticGivenName;
	private String phoneticMiddleName;
	private String phoneticNamePrefix;
	private String phoneticNameSuffix;
	private Collection<SoundexCode> soundexCodes;

	public PersonName() {
	}

	public PersonName(org.dcm4che3.data.PersonName pn, FuzzyStr fuzzyStr) {
		fromDicom(pn, fuzzyStr);
	}

	private void fromDicom(org.dcm4che3.data.PersonName pn, FuzzyStr fuzzyStr) {
		familyName = pn.get(Group.Alphabetic, Component.FamilyName);
		givenName = pn.get(Group.Alphabetic, Component.GivenName);
		middleName = pn.get(Group.Alphabetic, Component.MiddleName);
		namePrefix = pn.get(Group.Alphabetic, Component.NamePrefix);
		nameSuffix = pn.get(Group.Alphabetic, Component.NameSuffix);
		ideographicFamilyName = pn.get(Group.Ideographic, Component.FamilyName);
		ideographicGivenName = pn.get(Group.Ideographic, Component.GivenName);
		ideographicMiddleName = pn.get(Group.Ideographic, Component.MiddleName);
		ideographicNamePrefix = pn.get(Group.Ideographic, Component.NamePrefix);
		ideographicNameSuffix = pn.get(Group.Ideographic, Component.NameSuffix);
		phoneticFamilyName = pn.get(Group.Phonetic, Component.FamilyName);
		phoneticGivenName = pn.get(Group.Phonetic, Component.GivenName);
		phoneticMiddleName = pn.get(Group.Phonetic, Component.MiddleName);
		phoneticNamePrefix = pn.get(Group.Phonetic, Component.NamePrefix);
		phoneticNameSuffix = pn.get(Group.Phonetic, Component.NameSuffix);
		createOrUpdateSoundexCodes(familyName, givenName, ideographicMiddleName, fuzzyStr);
	}

	private void createOrUpdateSoundexCodes(String familyName, String givenName, String middleName, FuzzyStr fuzzyStr) {

		if (soundexCodes == null)
			soundexCodes = new ArrayList<SoundexCode>();
		else
			for (Iterator<SoundexCode> iterator = soundexCodes.iterator(); iterator.hasNext();) {
				iterator.next();
				iterator.remove();
			}

		addSoundexCodesTo(Component.FamilyName, familyName, fuzzyStr, soundexCodes);
		addSoundexCodesTo(Component.GivenName, givenName, fuzzyStr, soundexCodes);
		addSoundexCodesTo(Component.MiddleName, middleName, fuzzyStr, soundexCodes);
	}

	private void addSoundexCodesTo(Component component, String name, FuzzyStr fuzzyStr, Collection<SoundexCode> codes) {
		if (name == null)
			return;

		Iterator<String> parts = SoundexCode.tokenizePersonNameComponent(name);
		for (int i = 0; parts.hasNext(); i++) {
			SoundexCode soundexCode = new SoundexCode(component, i, fuzzyStr.toFuzzy(parts.next()));
			soundexCode.setPersonName(this);
			codes.add(soundexCode);
		}
	}

	public org.dcm4che3.data.PersonName toPersonName() {
		org.dcm4che3.data.PersonName pn = new org.dcm4che3.data.PersonName();
		pn.set(Group.Alphabetic, Component.FamilyName, familyName);
		pn.set(Group.Alphabetic, Component.GivenName, givenName);
		pn.set(Group.Alphabetic, Component.MiddleName, middleName);
		pn.set(Group.Alphabetic, Component.NamePrefix, namePrefix);
		pn.set(Group.Alphabetic, Component.NameSuffix, nameSuffix);
		pn.set(Group.Ideographic, Component.FamilyName, ideographicFamilyName);
		pn.set(Group.Ideographic, Component.GivenName, ideographicGivenName);
		pn.set(Group.Ideographic, Component.MiddleName, ideographicMiddleName);
		pn.set(Group.Ideographic, Component.NamePrefix, ideographicNamePrefix);
		pn.set(Group.Ideographic, Component.NameSuffix, ideographicNameSuffix);
		pn.set(Group.Phonetic, Component.FamilyName, phoneticFamilyName);
		pn.set(Group.Phonetic, Component.GivenName, phoneticGivenName);
		pn.set(Group.Phonetic, Component.MiddleName, phoneticMiddleName);
		pn.set(Group.Phonetic, Component.NamePrefix, phoneticNamePrefix);
		pn.set(Group.Phonetic, Component.NameSuffix, phoneticNameSuffix);
		return pn;
	}

	public static PersonName valueOf(String s, FuzzyStr fuzzyStr, PersonName prev) {
		if (s == null)
			return null;

		org.dcm4che3.data.PersonName pn = new org.dcm4che3.data.PersonName(s, true);
		if (pn.isEmpty())
			return null;

		if (prev != null) {
			if (!pn.equals(prev.toPersonName()))
				prev.fromDicom(pn, fuzzyStr); // update values
			return prev;
		} else
			return new PersonName(pn, fuzzyStr); // create new
	}

	@Override
	public String toString() {
		return toPersonName().toString();
	}

	public long getPk() {
		return pk;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getNameSuffix() {
		return nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public String getIdeographicFamilyName() {
		return ideographicFamilyName;
	}

	public void setIdeographicFamilyName(String ideographicFamilyName) {
		this.ideographicFamilyName = ideographicFamilyName;
	}

	public String getIdeographicGivenName() {
		return ideographicGivenName;
	}

	public void setIdeographicGivenName(String ideographicGivenName) {
		this.ideographicGivenName = ideographicGivenName;
	}

	public String getIdeographicMiddleName() {
		return ideographicMiddleName;
	}

	public void setIdeographicMiddleName(String ideographicMiddleName) {
		this.ideographicMiddleName = ideographicMiddleName;
	}

	public String getIdeographicNamePrefix() {
		return ideographicNamePrefix;
	}

	public void setIdeographicNamePrefix(String ideographicNamePrefix) {
		this.ideographicNamePrefix = ideographicNamePrefix;
	}

	public String getIdeographicNameSuffix() {
		return ideographicNameSuffix;
	}

	public void setIdeographicNameSuffix(String ideographicNameSuffix) {
		this.ideographicNameSuffix = ideographicNameSuffix;
	}

	public String getPhoneticFamilyName() {
		return phoneticFamilyName;
	}

	public void setPhoneticFamilyName(String phoneticFamilyName) {
		this.phoneticFamilyName = phoneticFamilyName;
	}

	public String getPhoneticGivenName() {
		return phoneticGivenName;
	}

	public void setPhoneticGivenName(String phoneticGivenName) {
		this.phoneticGivenName = phoneticGivenName;
	}

	public String getPhoneticMiddleName() {
		return phoneticMiddleName;
	}

	public void setPhoneticMiddleName(String phoneticMiddleName) {
		this.phoneticMiddleName = phoneticMiddleName;
	}

	public String getPhoneticNamePrefix() {
		return phoneticNamePrefix;
	}

	public void setPhoneticNamePrefix(String phoneticNamePrefix) {
		this.phoneticNamePrefix = phoneticNamePrefix;
	}

	public String getPhoneticNameSuffix() {
		return phoneticNameSuffix;
	}

	public void setPhoneticNameSuffix(String phoneticNameSuffix) {
		this.phoneticNameSuffix = phoneticNameSuffix;
	}
}
