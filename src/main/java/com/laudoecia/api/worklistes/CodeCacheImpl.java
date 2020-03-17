package com.laudoecia.api.worklistes;

import java.util.concurrent.ConcurrentHashMap;

import org.dcm4che3.data.Code;

import com.laudoecia.api.domain.CodeEntity;

public class CodeCacheImpl implements CodeCache {
	private CodeService service;
	private ConcurrentHashMap<Code.Key, CodeEntity> cache = new ConcurrentHashMap<>();

	@Override
	public CodeEntity findOrCreate(Code code) {
		CodeEntity entry = cache.get(code.key());
		if (entry == null) {
			entry = service.findOrCreate(code);
			cache.put(code.key(), entry);
		}
		return entry;
	}

	@Override
	public CodeEntity[] findOrCreateEntities(Code... codes) {
		CodeEntity[] entities = new CodeEntity[codes.length];
		for (int i = 0; i < entities.length; i++) {
			entities[i] = findOrCreate(codes[i]);
		}
		return entities;
	}
}
