package com.laudoecia.api.utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.ValueSelector;

public class AttributeFilter implements Serializable {
	private static final long serialVersionUID = -2417549681350544302L;
	private int[] selection;
	private Map<Integer, String> privateSelection = new TreeMap<>();
	private ValueSelector customAttribute1;
	private ValueSelector customAttribute2;
	private ValueSelector customAttribute3;
	private MetadataUpdateStrategy metadataUpdateStrategy;

	private int[] completeselection = null;

	public AttributeFilter() {}

	public AttributeFilter(int... selection) {
		Arrays.sort(this.selection = selection);
	}

	public AttributeFilter(int[] selection, Map<Integer, String> privateSelection) {
		Arrays.sort(this.selection = selection);
		setPrivateSelection(privateSelection);
	}

	public AttributeFilter(int[] selection, Map<Integer, String> privateSelection, MetadataUpdateStrategy strategy) {
		Arrays.sort(this.selection = selection);
		setPrivateSelection(privateSelection);
		setMetadataUpdateStrategy(strategy);
	}

	public int[] getCompleteSelection(Attributes attrs) {
		if (completeselection != null)
			return completeselection;

		if (privateSelection == null || privateSelection.size() == 0)
			return selection;

		ArrayList<Integer> privateTags = new ArrayList<>();

		// add private tags to the filter
		for (int privateTag : privateSelection.keySet()) {
			String configuredCreator = privateSelection.get(privateTag);
			if (attrs.contains(configuredCreator, privateTag))
				privateTags.add(attrs.tagOf(configuredCreator, privateTag));
		}

		// merge non-private and private tags
		completeselection = new int[selection.length + privateTags.size()];
		System.arraycopy(selection, 0, completeselection, 0, selection.length);
		for (int i = 0; i < privateTags.size(); i++)
			completeselection[selection.length + i] = privateTags.get(i);

		Arrays.sort(completeselection);

		return completeselection;
	}

	public int[] getSelection() {
		return selection;
	}

	public static String selectStringValue(Attributes attrs, ValueSelector selector, String defVal) {
		return selector != null ? selector.selectStringValue(attrs, defVal) : defVal;
	}

	public void setCustomAttribute1(ValueSelector customAttribute1) {
		this.customAttribute1 = customAttribute1;
	}

	public ValueSelector getCustomAttribute1() {
		return customAttribute1;
	}

	public void setCustomAttribute2(ValueSelector customAttribute2) {
		this.customAttribute2 = customAttribute2;
	}

	public ValueSelector getCustomAttribute2() {
		return customAttribute2;
	}

	public void setCustomAttribute3(ValueSelector customAttribute3) {
		this.customAttribute3 = customAttribute3;
	}

	public ValueSelector getCustomAttribute3() {
		return customAttribute3;
	}

	public void setSelection(int[] selection) {
		this.completeselection = null; // invalidate
		this.selection = selection;
	}

	public Map<Integer, String> getPrivateSelection() {
		return privateSelection;
	}

	public void setPrivateSelection(Map<Integer, String> privateSelection) {
		this.completeselection = null; // invalidate
		this.privateSelection = privateSelection;
	}

	public Map<Integer, String> addPrivate(Integer tag, String creator) {
		this.completeselection = null; // invalidate
		this.privateSelection.put(tag, creator);
		return this.privateSelection;
	}

	public MetadataUpdateStrategy getMetadataUpdateStrategy() {
		return metadataUpdateStrategy;
	}

	public void setMetadataUpdateStrategy(MetadataUpdateStrategy metadataUpdateStrategy) {
		this.metadataUpdateStrategy = metadataUpdateStrategy;
	}
}
