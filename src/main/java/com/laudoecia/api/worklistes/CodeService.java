package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Code;

import com.laudoecia.api.domain.CodeEntity;

public interface CodeService {
	CodeEntity findOrCreate(Code code);
}
