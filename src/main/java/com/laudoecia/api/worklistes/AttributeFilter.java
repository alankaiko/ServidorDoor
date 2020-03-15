package com.laudoecia.api.worklistes;

import java.io.Serializable;
import java.util.Arrays;

import org.dcm4che3.data.Attributes;

public class AttributeFilter implements Serializable {

    private static final long serialVersionUID = -2417549681350544302L;

    private final int[] selection;
    private ValueSelector customAttribute1;
    private ValueSelector customAttribute2;
    private ValueSelector customAttribute3;

    public AttributeFilter(int... selection) {
        Arrays.sort(this.selection = selection);
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

}
