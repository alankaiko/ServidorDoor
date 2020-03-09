package com.laudoecia.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.SpecificCharacterSet;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.UID;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.util.StringUtils;
import org.dcm4che3.util.TagUtils;

import com.laudoecia.api.utilities.BlobCorruptedException;



public class UtilsWorklist {


    public static String upper (String value) {
        if (value == null)
            return null;

        return value.toUpperCase();
    }

    public static byte[] encodeAttributes(Attributes attrs) {
        ByteArrayOutputStream out = new ByteArrayOutputStream(512);
        try {
            @SuppressWarnings("resource")
            DicomOutputStream dos = new DicomOutputStream(out,
                    UID.ExplicitVRLittleEndian);
            dos.writeDataset(null, attrs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }

    /**
     * 
     */
    public static String digestAttributes(Attributes attrs, MessageDigest digest) throws IOException {
        
        OutputStream nulloutputstream = new OutputStream() {
            /** Discards the specified byte. */
            @Override public void write(int b) {
            }
            /** Discards the specified byte array. */
            @Override public void write(byte[] b, int off, int len) {
            }};
        
        if (digest != null) {
            digest.reset();
            nulloutputstream = new DigestOutputStream(nulloutputstream, digest);
        }
        
        DicomOutputStream dout = new DicomOutputStream(nulloutputstream,
                UID.ExplicitVRLittleEndian);
        dout.writeDataset(null, attrs);
        dout.flush();
        dout.close();
        
        return TagUtils.toHexString(digest.digest());
    }

    public static Attributes decodeAttributes(byte[] b) {
        if (b == null || b.length == 0)
            return new Attributes(0);
        ByteArrayInputStream is = new ByteArrayInputStream(b);
        try {
            @SuppressWarnings("resource")
            DicomInputStream dis = new DicomInputStream(is);
            return dis.readDataset(-1, -1);
        } catch (IOException e) {
            throw new BlobCorruptedException(e);
        }
    }

    public static void decodeAttributes(Attributes attrs, byte[] b) {
        if (b == null || b.length == 0)
            return;
        ByteArrayInputStream is = new ByteArrayInputStream(b);
        try {
            @SuppressWarnings("resource")
            DicomInputStream dis = new DicomInputStream(is);
            dis.readFileMetaInformation();
            dis.readAttributes(attrs, -1, -1);
        } catch (IOException e) {
            throw new BlobCorruptedException(e);
        }
    }

//    public static void setStudyQueryAttributes(Attributes attrs,
//            int numberOfStudyRelatedSeries, int numberOfStudyRelatedInstances,
//            String modalitiesInStudy, String sopClassesInStudy,
//            int numberVisibleInstances, PrivateTag numberVisibleInstancesTag,
//            Date lastUpdateTime, PrivateTag lastUpdateTimeTag) {
//
//        attrs.setInt(Tag.NumberOfStudyRelatedSeries, VR.IS,
//                numberOfStudyRelatedSeries);
//        attrs.setInt(Tag.NumberOfStudyRelatedInstances, VR.IS,
//                numberOfStudyRelatedInstances);
//        attrs.setString(Tag.ModalitiesInStudy, VR.CS,
//                StringUtils.split(modalitiesInStudy, '\\'));
//        attrs.setString(Tag.SOPClassesInStudy, VR.CS,
//                StringUtils.split(sopClassesInStudy, '\\'));
//        if (lastUpdateTimeTag!=null && lastUpdateTime!=null)
//            attrs.setDate(lastUpdateTimeTag.getCreator(),
//            lastUpdateTimeTag.getIntTag(),VR.DT,lastUpdateTime);
//        if (numberVisibleInstancesTag!=null)
//            attrs.setInt(numberVisibleInstancesTag.getCreator(),
//                    numberVisibleInstancesTag.getIntTag(), VR.IS,
//                    numberVisibleInstances);
//    }

//    public static void setSeriesQueryAttributes(Attributes attrs,
//            int numberOfSeriesRelatedInstances, int numberVisibleInstances,
//            PrivateTag numberVisibleInstancesTag, Date lastUpdateTime,
//            PrivateTag lastUpdateTimeTag) {
//        attrs.setInt(Tag.NumberOfSeriesRelatedInstances, VR.IS,
//                numberOfSeriesRelatedInstances);
//        if (numberVisibleInstancesTag!=null)
//            attrs.setInt(numberVisibleInstancesTag.getCreator(),
//                    numberVisibleInstancesTag.getIntTag(), VR.IS,
//                    numberVisibleInstances);
//        if (lastUpdateTimeTag!=null && lastUpdateTime!=null)
//            attrs.setDate(lastUpdateTimeTag.getCreator(),
//                    lastUpdateTimeTag.getIntTag(), VR.DT, lastUpdateTime);
//    }

    public static String[] decodeAETs(String aetsSeparated) {
        return StringUtils.split(aetsSeparated, '\\');
    }

    public static void setRetrieveAET(Attributes attrs, String retrieveAETs) {
        if (retrieveAETs != null)
                attrs.setString(Tag.RetrieveAETitle, VR.AE,
                        StringUtils.split(retrieveAETs, '\\'));
    }

    public static void setRetrieveAET(Attributes attrs, String[] retrieveAETs) {
        if (retrieveAETs != null)
                attrs.setString(Tag.RetrieveAETitle, VR.AE, retrieveAETs);
    }

//    public static void setAvailability(Attributes attrs,
//            Availability availability) {
//        attrs.setString(Tag.InstanceAvailability, VR.CS,
//                availability.toString());
//    }

    public static String[] intersection(String[] ss1, String[] ss2) {
        int l = 0;
        for (int i = 0; i < ss1.length; i++)
            if (contains(ss2, ss1[i]))
                ss1[l++] = ss1[i];
        return l == ss1.length ? ss1 : Arrays.copyOf(ss1, l);
    }

    public static boolean contains(String[] ss, String s0) {
        for (String s : ss)
            if (s0.equals(s))
                return true;
        return false;
    }

    public static Attributes mergeAndNormalize(Attributes... attrsList) {

        SpecificCharacterSet globalCs = null;

        for (Attributes attrs : attrsList) {

            SpecificCharacterSet cs = attrs.getSpecificCharacterSet();

            if (globalCs == null) {
                globalCs = cs; // first
            } else {

                if (!cs.equals(globalCs)) {
                    if (!(globalCs.containsASCII() && cs.isASCII())) {
                        if (globalCs.isUTF8()) {
                            // convert to UTF8
                            convertToUTF8(attrsList);
                            break;
                        } else if (cs.isUTF8()) {
                            globalCs = cs;
                            convertToUTF8(attrsList);
                            break;
                        } else if (globalCs.isASCII() && cs.containsASCII()) {
                            // do not decode
                            globalCs = cs;
                        } else {
                            // incompatible codes, set all to UTF-8
                            globalCs = SpecificCharacterSet
                                    .valueOf("ISO_IR 192"); // UTF-8
                            convertToUTF8(attrsList);
                            break;
                        }
                    }
                }
            }
        }

        // merge
        Attributes mergedAttrs = new Attributes();
        for (Attributes attrs : attrsList)
            mergedAttrs.addAll(attrs);

        mergedAttrs.setString(Tag.SpecificCharacterSet, VR.CS,
                globalCs.toCodes());

        return mergedAttrs;
    }

    private static void convertToUTF8(Attributes... attrsList) {
        for (Attributes attrs : attrsList) {
            if (!attrs.getSpecificCharacterSet().isUTF8())
                attrs.setSpecificCharacterSet("ISO_IR 192"); // UTF-8
        }
    }

    /**
     * Updates an attributes set (the current one) with a set of incoming
     * attributes, according to the configured strategy. Eventually stores
     * in a modified set all the current modified attributes. A default
     * value for the update strategy is also passed to the method
     *
     * @return <tt>true</tt> if one ore more attribute of the "current" set
     *         were added or overwritten with a different value
     */
//    public static boolean updateAttributes (Attributes current, Attributes incoming,
//            Attributes modified, AttributeFilter filter, MetadataUpdateStrategy defaultStrategy) {
//
//        MetadataUpdateStrategy strategy = filter.getMetadataUpdateStrategy() != null ? filter
//                .getMetadataUpdateStrategy() : defaultStrategy;
//
//        switch (strategy) {
//            case COERCE:
//                return false;
//            case COERCE_MERGE:
//                return current.mergeSelected(incoming, filter.getCompleteSelection(incoming));
//            case OVERWRITE:
//                return current.addSelected(incoming, filter.getCompleteSelection(incoming));
//            case OVERWRITE_MERGE:
//                return current.updateSelected(incoming, modified, filter.getCompleteSelection(incoming));
//        }
//
//        return false;
//    }
}
