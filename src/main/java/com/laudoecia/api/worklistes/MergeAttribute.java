
package com.laudoecia.api.worklistes;

import java.util.stream.Stream;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.ElementDictionary;
import org.dcm4che3.data.Sequence;
import org.dcm4che3.util.AttributesFormat;
import org.dcm4che3.util.TagUtils;

public class MergeAttribute {
	private static final ElementDictionary dict = ElementDictionary.getStandardElementDictionary();
	final String value;
	final int[] tagPath;
	final AttributesFormat format;

	public MergeAttribute(String value) {
		try {
			this.value = value;
			int index = value.indexOf('=');
			tagPath = TagUtils.parseTagPath(value.substring(0, index));
			format = new AttributesFormat(value.substring(index + 1));
		} catch (Exception e) {
			throw new IllegalArgumentException(value);
		}
	}

	@Override
	public String toString() {
		return value;
	}

	static MergeAttribute[] of(String... ss) {
		return Stream.of(ss).map(MergeAttribute::new).toArray(MergeAttribute[]::new);
	}

	void merge(Attributes attrs, Attributes modified) {
		int tag = tagPath[tagPath.length - 1];
		Attributes item = ensureItem(attrs);
		String newValue = this.format.format(attrs);
		String oldValue = item.getString(tag);
		if (!newValue.equals(oldValue)) {
			item.setString(tag, dict.vrOf(tag), newValue);
			if (modified != null && oldValue != null) {
				ensureItem(modified).setString(tag, dict.vrOf(tag), oldValue);
			}
		}
	}

	private static Attributes ensureItem(Attributes attrs, int tag) {
		Sequence sq = attrs.ensureSequence(tag, 1);
		Attributes item;
		if (sq.isEmpty()) {
			sq.add(item = new Attributes());
		} else {
			item = sq.get(0);
		}
		return item;
	}

	private Attributes ensureItem(Attributes attrs) {
		Attributes item = attrs;
		int last = tagPath.length - 1;
		for (int i = 0; i < last; i++) {
			item = ensureItem(item, tagPath[i]);
		}
		return item;
	}
}
