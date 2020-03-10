package com.laudoecia.api.worklistes;

import java.io.Serializable;
import java.util.Arrays;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.ValueSelector;

public class AttributeFilter implements Serializable {

	private static final long serialVersionUID = -2417549681350544302L;

	private int[] selection;
	private Attributes.UpdatePolicy attributeUpdatePolicy = Attributes.UpdatePolicy.PRESERVE;
	private ValueSelector customAttribute1;
	private ValueSelector customAttribute2;
	private ValueSelector customAttribute3;

	public AttributeFilter() {
	}

	public AttributeFilter(int... selection) {
		setSelection(selection);
	}

	public int[] getSelection() {
		return selection;
	}

	public int[] getSelection(boolean withOriginalAttributesSequence) {
		int index = Arrays.binarySearch(selection, Tag.OriginalAttributesSequence);
		if (withOriginalAttributesSequence) {
			if (index < 0)
				return includeOriginalAttributesSequence(selection, -(index + 1));
		} else {
			if (index >= 0) {
				return removeOriginalAttributesSequence(selection, index);
			}
		}
		return selection;
	}

	private static int[] includeOriginalAttributesSequence(int[] selection, int index) {
		int[] result = new int[selection.length + 1];
		System.arraycopy(selection, 0, result, 0, index);
		result[index] = Tag.OriginalAttributesSequence;
		System.arraycopy(selection, index, result, index + 1, selection.length - index);
		return result;
	}

	private static int[] removeOriginalAttributesSequence(int[] selection, int index) {
		int[] result = new int[selection.length - 1];
		System.arraycopy(selection, 0, result, 0, index);
		System.arraycopy(selection, index + 1, result, index - 1, selection.length - index - 1);
		return result;
	}

	public void setSelection(int[] selection) {
		Arrays.sort(this.selection = selection);
	}

	public Attributes.UpdatePolicy getAttributeUpdatePolicy() {
		return attributeUpdatePolicy;
	}

	public void setAttributeUpdatePolicy(Attributes.UpdatePolicy attributeUpdatePolicy) {
		this.attributeUpdatePolicy = attributeUpdatePolicy;
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

}
