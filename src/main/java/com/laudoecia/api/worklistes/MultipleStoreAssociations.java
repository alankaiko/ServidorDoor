package com.laudoecia.api.worklistes;

import java.util.Optional;
import java.util.stream.Stream;

public class MultipleStoreAssociations {
	final String value;
	final int index;
	final int max;

	MultipleStoreAssociations(String value) {
		try {
			this.value = value;
			index = value.indexOf(':');
			max = Integer.parseInt(value.substring(index + 1));
			if (index != 0 && max > 0)
				return;
		} catch (RuntimeException e) {
		}
		throw new IllegalArgumentException(value);
	}

	@Override
	public String toString() {
		return value;
	}

	static MultipleStoreAssociations[] of(String... ss) {
		return Stream.of(ss).map(MultipleStoreAssociations::new)
				.sorted((a, b) -> a.index < 0 ? (b.index < 0 ? 0 : 1) : (b.index < 0 ? -1 : 0))
				.toArray(MultipleStoreAssociations[]::new);
	}

	static int maxTo(String aet, MultipleStoreAssociations[] a, MultipleStoreAssociations[] b) {
		Optional<MultipleStoreAssociations> match = Stream.of(a, b).flatMap(Stream::of).filter(x -> x.match(aet))
				.findFirst();
		return match.isPresent() ? match.get().max : 1;
	}

	boolean match(String aet) {
		return this.index < 0 || this.value.startsWith(aet) && aet.length() == index;
	}
}
