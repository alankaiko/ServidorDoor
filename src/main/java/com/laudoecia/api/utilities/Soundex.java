package com.laudoecia.api.utilities;


public class Soundex implements FuzzyStr {

    protected static final java.lang.String MAP_6 =
        // A BCD  E FG  H   I JKLMN  O PQRST  U V  W X  Y Z
        "\000123\00012\001\00022455\00012623\0001\0012\0002";
    protected static final java.lang.String MAP_9 = 
        // A BCD  E FG  H   I JKLMN  O PQRST  U V  W X  Y Z
        "\000136\00024\001\00043788\00015936\0002\0015\0005";

    private final boolean encodeFirst;
    private final int codeLength;
    private final int padLength;
    private final char[] map;

    public Soundex() {
        this(false, 4, 4, MAP_6);
    }

    public Soundex(boolean encodeFirst, int codeLength, int padLength, String map) {
        this.encodeFirst = encodeFirst;
        this.codeLength = codeLength;
        this.padLength = padLength;
        this.map = map.toCharArray();
    }

    @Override
    public String toFuzzy(String s) {
        if (s == null || s.length() == 0)
            return "";

        char[] in = s.toCharArray();
        char[] out = in.length < padLength ? new char[padLength] : in;
        int i = 0;
        int j = 0;
        char prevout = 0;
        if (!encodeFirst) {
            while (!Character.isLetter(in[i]))
                if (++i >= in.length)
                    return "";
            prevout = map(out[j++] = Character.toUpperCase(in[i++]));
        }

        char curout = 0;
        for (; i < in.length && j < codeLength; i++) {
            curout = map(in[i]);
            switch (curout) {
            case '\0':
                prevout = curout;
            case '\1':
                break;
            default:
                if (curout != prevout)
                    out[j++] = prevout = curout;
            }
        }
        while (j < padLength)
            out[j++] = '0';
        return new String(out, 0, j);
    }

    private char map(char c) {
        try {
            return map[c >= 'a' ? c - 'a' : c - 'A'];
        } catch (IndexOutOfBoundsException e) {
            return (c == 'ß' || c == 'Ç' || c == 'ç') ? map['c' - 'a'] : '\u0000';
        }
    }

    public static void main(String[] args) {
        Soundex inst = new Soundex();
        for (String arg : args)
            System.out.println(inst.toFuzzy(arg));
    }

}
