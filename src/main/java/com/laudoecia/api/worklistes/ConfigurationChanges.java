package com.laudoecia.api.worklistes;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationChanges {
	public enum ChangeType {
		C, U, D
	}

	public static class ModifiedAttribute {
		private final String name;
		private final List<Object> addedValues = new ArrayList<>(1);
		private final List<Object> removedValues = new ArrayList<>(1);

		public ModifiedAttribute(String name) {
			this.name = name;
		}

		public ModifiedAttribute(String name, Object prev, Object val) {
			this.name = name;
			removeValue(prev);
			addValue(val);
		}

		public String name() {
			return name;
		}

		public List<Object> addedValues() {
			return addedValues;
		}

		public List<Object> removedValues() {
			return removedValues;
		}

		public void addValue(Object value) {
			if (value != null && !removedValues.remove(value))
				addedValues.add(value);
		}

		public void removeValue(Object value) {
			if (value != null && !addedValues.remove(value))
				removedValues.add(value);
		}

	}

	public static class ModifiedObject {
		private final String dn;
		private final ChangeType changeType;
		private final List<ModifiedAttribute> attributes = new ArrayList<>();

		public ModifiedObject(String dn, ChangeType changeType) {
			this.dn = dn;
			this.changeType = changeType;
		}

		public String dn() {
			return dn;
		}

		public ChangeType changeType() {
			return changeType;
		}

		public boolean isEmpty() {
			return attributes.isEmpty();
		}

		public List<ModifiedAttribute> modifiedAttributes() {
			return attributes;
		}

		public void add(ModifiedAttribute attribute) {
			this.attributes.add(attribute);
		}
	}

	private final List<ModifiedObject> objects = new ArrayList<>();

	private final boolean verbose;

	public ConfigurationChanges(boolean verbose) {
		this.verbose = verbose;
	}

	public static <T> T nullifyIfNotVerbose(ConfigurationChanges diffs, T obj) {
		return diffs != null && diffs.isVerbose() ? obj : null;
	}

	public static ModifiedObject addModifiedObjectIfVerbose(ConfigurationChanges diffs, String dn,
			ChangeType changeType) {
		if (diffs == null || !diffs.isVerbose())
			return null;

		ModifiedObject object = new ModifiedObject(dn, changeType);
		diffs.add(object);
		return object;
	}

	public static ModifiedObject addModifiedObject(ConfigurationChanges diffs, String dn, ChangeType changeType) {
		if (diffs == null)
			return null;

		ModifiedObject object = new ModifiedObject(dn, changeType);
		diffs.add(object);
		return object;
	}

	public static void removeLastIfEmpty(ConfigurationChanges diffs, ModifiedObject obj) {
		if (obj != null && obj.isEmpty())
			diffs.removeLast();
	}

	private void removeLast() {
		objects.remove(objects.size() - 1);
	}

	public List<ModifiedObject> modifiedObjects() {
		return objects;
	}

	public void add(ModifiedObject object) {
		objects.add(object);
	}

	public boolean isEmpty() {
		return objects.isEmpty();
	}

	public boolean isVerbose() {
		return verbose;
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "[]";

		StringBuilder sb = new StringBuilder(objects.size() * 64);
		for (ModifiedObject obj : objects) {
			sb.append(obj.changeType).append(' ').append(obj.dn).append('\n');
			if (obj.attributes != null) {
				for (ModifiedAttribute attr : obj.attributes) {
					sb.append("  ").append(attr.name).append(": ").append(attr.removedValues).append("=>")
							.append(attr.addedValues).append('\n');
				}
			}
		}
		return sb.toString();
	}
}
