package com.laudoecia.api.worklistes;


public enum StorageVerificationPolicy {
    DB_RECORD_EXISTS,
    OBJECT_EXISTS,
    OBJECT_SIZE,
    OBJECT_FETCH,
    OBJECT_CHECKSUM,
    S3_MD5SUM
}
