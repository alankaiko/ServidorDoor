package com.laudoecia.api.worklistes;

import java.io.Serializable;
import java.util.Objects;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.ItemPointer;

public class ValueSelector implements Serializable {

    private static final long serialVersionUID = 7043276856762009199L;

    private final AttributeSelector attributeSelector;
    private final int valueIndex;
    private String str;

    public ValueSelector(int tag, String privateCreator, int index, ItemPointer... itemPointers) {
        this(new AttributeSelector(tag, privateCreator, itemPointers), index);
    }

    public ValueSelector(AttributeSelector attributeSelector, int index) {
        this.attributeSelector = Objects.requireNonNull(attributeSelector);
        this.valueIndex = index;
    }

    public int tag() {
        return attributeSelector.tag();
    }

    public String privateCreator() {
        return attributeSelector.privateCreator();
    }

    public int level() {
        return attributeSelector.level();
    }

    public ItemPointer itemPointer(int index) {
        return attributeSelector.itemPointer(index);
    }

    public int valueIndex() {
        return valueIndex;
    }

    public String selectStringValue(Attributes attrs, String defVal) {
        return attributeSelector.selectStringValue(attrs, valueIndex, defVal);
    }

    @Override
    public String toString() {
        if (str == null)
            str = attributeSelector.toStringBuilder()
                    .append("/Value[@number=\"")
                    .append(valueIndex + 1)
                    .append("\"]")
                    .toString();
        return str;
    }

    public static ValueSelector valueOf(String s) {
        int fromIndex = s.lastIndexOf("DicomAttribute");
        try {
            return new ValueSelector(AttributeSelector.valueOf(s),
                    AttributeSelector.selectNumber(s, fromIndex) - 1);
        } catch (Exception e) {
            throw new IllegalArgumentException(s);
        }
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ValueSelector))
            return false;

        return toString().equals(obj.toString());
    }
}
