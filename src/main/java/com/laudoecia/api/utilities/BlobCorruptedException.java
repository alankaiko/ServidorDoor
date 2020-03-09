package com.laudoecia.api.utilities;

import java.io.IOException;

public class BlobCorruptedException extends RuntimeException {

	private static final long serialVersionUID = 6928826487883256011L;

	public BlobCorruptedException(IOException e) {
		super(e);
	}

}
