package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Code;

import com.laudoecia.api.domain.CodeEntity;

public interface CodeCache {
    CodeEntity findOrCreate(Code code);
    CodeEntity[] findOrCreateEntities(Code... codes);
}
