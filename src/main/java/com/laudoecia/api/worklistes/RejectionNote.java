package com.laudoecia.api.worklistes;

import org.dcm4che3.data.Code;


public class RejectionNote {

    public enum AcceptPreviousRejectedInstance { REJECT, RESTORE, IGNORE }

    public enum Type {
        REJECTED_FOR_QUALITY_REASONS,
        REJECTED_FOR_PATIENT_SAFETY_REASONS,
        INCORRECT_MODALITY_WORKLIST_ENTRY,
        DATA_RETENTION_POLICY_EXPIRED,
        REVOKE_REJECTION
    }

    private String rejectionNoteLabel;
    private Type rejectionNoteType;
    private Code rejectionNoteCode;
    private int seriesNumber;
    private int instanceNumber;
    private AcceptPreviousRejectedInstance acceptPreviousRejectedInstance =
            AcceptPreviousRejectedInstance.REJECT;
    private Code[] overwritePreviousRejection = {};
    private Duration acceptRejectionBeforeStorage;
    private Duration deleteRejectedInstanceDelay;
    private Duration deleteRejectionNoteDelay;

    public String getRejectionNoteLabel() {
        return rejectionNoteLabel;
    }

    public void setRejectionNoteLabel(String rejectionNoteLabel) {
        this.rejectionNoteLabel = rejectionNoteLabel;
    }

    public Type getRejectionNoteType() {
        return rejectionNoteType;
    }

    public void setRejectionNoteType(Type rejectionNoteType) {
        this.rejectionNoteType = rejectionNoteType;
    }

    public boolean isRevokeRejection() {
        return rejectionNoteType == Type.REVOKE_REJECTION;
    }

    public boolean isDataRetentionPolicyExpired() {
        return rejectionNoteType == Type.DATA_RETENTION_POLICY_EXPIRED;
    }

    public Code getRejectionNoteCode() {
        return rejectionNoteCode;
    }

    public void setRejectionNoteCode(Code rejectionNoteCode) {
        this.rejectionNoteCode = rejectionNoteCode;
    }

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public int getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(int instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    public AcceptPreviousRejectedInstance getAcceptPreviousRejectedInstance() {
        return acceptPreviousRejectedInstance;
    }

    public void setAcceptPreviousRejectedInstance(AcceptPreviousRejectedInstance acceptPreviousRejectedInstance) {
        this.acceptPreviousRejectedInstance = acceptPreviousRejectedInstance;
    }

    public Code[] getOverwritePreviousRejection() {
        return overwritePreviousRejection;
    }

    public boolean canOverwritePreviousRejection(Code code) {
        for (Code code1 : overwritePreviousRejection)
            if (code1.equalsIgnoreMeaning(code))
                return true;
        return false;
    }

    public void setOverwritePreviousRejection(Code[] overwritePreviousRejection) {
        this.overwritePreviousRejection = overwritePreviousRejection;
    }

    public Duration getAcceptRejectionBeforeStorage() {
        return acceptRejectionBeforeStorage;
    }

    public void setAcceptRejectionBeforeStorage(Duration acceptRejectionBeforeStorage) {
        this.acceptRejectionBeforeStorage = acceptRejectionBeforeStorage;
    }

    public Duration getDeleteRejectedInstanceDelay() {
        return deleteRejectedInstanceDelay;
    }

    public void setDeleteRejectedInstanceDelay(Duration deleteRejectedInstanceDelay) {
        this.deleteRejectedInstanceDelay = deleteRejectedInstanceDelay;
    }

    public Duration getDeleteRejectionNoteDelay() {
        return deleteRejectionNoteDelay;
    }

    public void setDeleteRejectionNoteDelay(Duration deleteRejectionNoteDelay) {
        this.deleteRejectionNoteDelay = deleteRejectionNoteDelay;
    }
}
