package com.laudoecia.api.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.UID;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomOutputStream;

import com.laudoecia.api.utilities.BlobCorruptedException;

@Entity
@Table
public class AttributesBlob {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long pk;
    
    @Basic(optional = false)
    private byte[] encodedattributes;
    
    @Transient
    private Attributes cachedattributes;

    public AttributesBlob(Attributes attrs) {
        setAttributes(attrs);
    }
    
    protected AttributesBlob() {}

    @Override
    public String toString() {
        return "AttributesBlob[pk=" + pk + "]";
    }

    public long getPk() {
        return pk;
    }

    public Attributes getAttributes() throws BlobCorruptedException {
        if (cachedattributes == null)
            cachedattributes = AttributesBlob.decodeAttributes(encodedattributes, null);
        return cachedattributes;
    }

    public void setAttributes(Attributes attrs) {
        cachedattributes = new Attributes(attrs);
        cachedattributes.removeAllBulkData();
        encodedattributes = AttributesBlob.encodeAttributes(cachedattributes);
    }

    public byte[] getEncodedAttributes() {
        return encodedattributes;
    }

    public static byte[] encodeAttributes(Attributes attrs) {
        ByteArrayOutputStream out = new ByteArrayOutputStream(512);
        try {
            DicomOutputStream dos = new DicomOutputStream(out, UID.ExplicitVRLittleEndian);
            dos.writeDataset(null, attrs);
            dos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }

    public static Attributes decodeAttributes(byte[] resultado, Attributes result) {
        if (resultado == null || resultado.length == 0)
            return result != null ? result : new Attributes(0);

        if (result == null)
            result = new Attributes();
        
        ByteArrayInputStream is = new ByteArrayInputStream(resultado);
        
        try {
            DicomInputStream dis = new DicomInputStream(is);
            dis.readFileMetaInformation();
            dis.readAttributes(result, -1, -1);
            dis.close();
            return result;
        } catch (IOException e) {
            throw new BlobCorruptedException(e);
        }
    }
}
