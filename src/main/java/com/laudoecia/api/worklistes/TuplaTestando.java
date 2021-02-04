package com.laudoecia.api.worklistes;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;

public class TuplaTestando implements Tuple{

	@Override
	public <X> X get(TupleElement<X> tupleElement) {
		return null;
	}

	@Override
	public <X> X get(String alias, Class<X> type) {
		return null;
	}

	@Override
	public Object get(String alias) {
		return null;
	}

	@Override
	public <X> X get(int i, Class<X> type) {
		return null;
	}

	@Override
	public Object get(int i) {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public List<TupleElement<?>> getElements() {
		return null;
	}

}
