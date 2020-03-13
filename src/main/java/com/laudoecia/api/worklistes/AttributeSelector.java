package com.laudoecia.api.worklistes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.ItemPointer;
import org.dcm4che3.util.TagUtils;

public class AttributeSelector implements Serializable {

	private static final long serialVersionUID = 9122372717582373730L;

	private static final int MIN_ITEM_POINTER_STR_LEN = 30;

	private final int tag;
	private final String privateCreator;
	private final List<ItemPointer> itemPointers;
	private String str;

	public AttributeSelector(int tag) {
		this(tag, null, Collections.EMPTY_LIST);
	}

	public AttributeSelector(int tag, String privateCreator) {
		this(tag, privateCreator, Collections.EMPTY_LIST);
	}

	public AttributeSelector(int tag, String privateCreator, ItemPointer... itemPointers) {
		this(tag, privateCreator, Arrays.asList(itemPointers));
	}

	public AttributeSelector(int tag, String privateCreator, List<ItemPointer> itemPointers) {
		this.tag = tag;
		this.privateCreator = privateCreator;
		this.itemPointers = itemPointers;
	}

	public int tag() {
		return tag;
	}

	public String privateCreator() {
		return privateCreator;
	}

	public int level() {
		return itemPointers.size();
	}

	public ItemPointer itemPointer(int index) {
		return itemPointers.get(index);
	}

	public String selectStringValue(Attributes attrs, int valueIndex, String defVal) {
		Attributes item = attrs.getNestedDataset(itemPointers);
		return item != null ? item.getString(privateCreator, tag, valueIndex, defVal) : defVal;
	}

	@Override
	public String toString() {
		if (str == null)
			str = toStringBuilder().toString();
		return str;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AttributeSelector that = (AttributeSelector) o;
		return tag == that.tag && Objects.equals(privateCreator, that.privateCreator)
				&& itemPointers.equals(that.itemPointers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tag, privateCreator, itemPointers);
	}

	StringBuilder toStringBuilder() {
		StringBuilder sb = new StringBuilder(32);
		for (ItemPointer ip : itemPointers) {
			appendTo(ip.sequenceTag, ip.privateCreator, "\"]/Item", sb);
			if (ip.itemIndex >= 0)
				sb.append("[@number=\"").append(ip.itemIndex + 1).append("\"]");
			sb.append('/');
		}
		return appendTo(tag, privateCreator, "\"]", sb);
	}

	private StringBuilder appendTo(int tag, String privateCreator, String suffix, StringBuilder sb) {
		sb.append("DicomAttribute[@tag=\"").append(TagUtils.toHexString(tag));
		if (privateCreator != null)
			sb.append("\" and @privateCreator=\"").append(privateCreator);
		return sb.append(suffix);
	}

	public static AttributeSelector valueOf(String s) {
		int fromIndex = s.lastIndexOf("DicomAttribute");
		try {
			return new AttributeSelector(selectTag(s, fromIndex), selectPrivateCreator(s, fromIndex),
					itemPointersOf(s, fromIndex));
		} catch (Exception e) {
			throw new IllegalArgumentException(s);
		}
	}

	static int selectTag(String s, int fromIndex) {
		String tagStr = select("@tag=", s, fromIndex);
		return Integer.parseInt(tagStr, 16);
	}

	static String selectPrivateCreator(String s, int fromIndex) {
		return select("@privateCreator=", s, fromIndex);
	}

	static int selectNumber(String s, int fromIndex) {
		String no = select("@number=", s, fromIndex);
		return no != null ? Integer.parseInt(no) : 0;
	}

	private static List<ItemPointer> itemPointersOf(String s, int endIndex) {
		if (endIndex == 0)
			return Collections.emptyList();

		ArrayList<ItemPointer> list = new ArrayList<>();
		int fromIndex = 0;
		while (fromIndex < endIndex) {
			list.add(new ItemPointer(selectTag(s, fromIndex), selectPrivateCreator(s, fromIndex),
					selectNumber(s, fromIndex) - 1));
			fromIndex = s.indexOf("DicomAttribute", fromIndex + MIN_ITEM_POINTER_STR_LEN);
		}
		list.trimToSize();
		return list;
	}

	private static String select(String key, String s, int fromIndex) {
		int pos = s.indexOf(key, fromIndex);
		if (pos < 0)
			return null;

		int quotePos = pos + key.length();
		int beginIndex = quotePos + 1;
		return s.substring(beginIndex, s.indexOf(s.charAt(quotePos), beginIndex));
	}

	public boolean matches(List<ItemPointer> itemPointers, String privateCreator, int tag) {
		int level;
		if (tag != this.tag || !Objects.equals(privateCreator, this.privateCreator)
				|| (itemPointers.size() != (level = level()))) {
			return false;
		}
		for (int i = 0; i < level; i++) {
			ItemPointer itemPointer = itemPointers.get(i);
			ItemPointer other = itemPointer(i);
			if (!(itemPointer.itemIndex < 0 || other.itemIndex < 0 ? itemPointer.equalsIgnoreItemIndex(other)
					: itemPointer.equals(other))) {
				return false;
			}
		}
		return true;
	}
}
