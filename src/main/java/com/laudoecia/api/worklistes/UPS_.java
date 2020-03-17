package com.laudoecia.api.worklistes;

import com.laudoecia.api.domain.AttributesBlob;
import com.laudoecia.api.domain.CodeEntity;
import com.laudoecia.api.domain.IssuerEntity;
import com.laudoecia.api.domain.Patient;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UPS.class)
public abstract class UPS_ {

	public static volatile SingularAttribute<UPS, IssuerEntity> issuerOfAdmissionID;
	public static volatile CollectionAttribute<UPS, Subscription> subscriptions;
	public static volatile SingularAttribute<UPS, AttributesBlob> attributesBlob;
	public static volatile SingularAttribute<UPS, String> scheduledStartDateAndTime;
	public static volatile SingularAttribute<UPS, String> expectedCompletionDateAndTime;
	public static volatile SingularAttribute<UPS, CodeEntity> scheduledStationGeographicLocationCode;
	public static volatile SingularAttribute<UPS, Patient> patient;
	public static volatile SingularAttribute<UPS, String> admissionID;
	public static volatile SingularAttribute<UPS, Date> createdTime;
	public static volatile CollectionAttribute<UPS, UPSRequest> referencedRequests;
	public static volatile SingularAttribute<UPS, String> scheduledProcedureStepExpirationDateTime;
	public static volatile SingularAttribute<UPS, InputReadinessState> inputReadinessState;
	public static volatile SingularAttribute<UPS, String> replacedSOPInstanceUID;
	public static volatile SingularAttribute<UPS, Date> updatedTime;
	public static volatile SingularAttribute<UPS, String> upsLabel;
	public static volatile SingularAttribute<UPS, UPSState> procedureStepState;
	public static volatile SingularAttribute<UPS, CodeEntity> scheduledWorkitemCode;
	public static volatile SingularAttribute<UPS, String> worklistLabel;
	public static volatile SingularAttribute<UPS, Long> version;
	public static volatile SingularAttribute<UPS, String> transactionUID;
	public static volatile SingularAttribute<UPS, CodeEntity> scheduledStationNameCode;
	public static volatile CollectionAttribute<UPS, CodeEntity> humanPerformerCodes;
	public static volatile SingularAttribute<UPS, String> performerAET;
	public static volatile SingularAttribute<UPS, Long> pk;
	public static volatile SingularAttribute<UPS, UPSPriority> upsPriority;
	public static volatile SingularAttribute<UPS, String> upsInstanceUID;
	public static volatile SingularAttribute<UPS, CodeEntity> scheduledStationClassCode;

	public static final String ISSUER_OF_ADMISSION_ID = "issuerOfAdmissionID";
	public static final String SUBSCRIPTIONS = "subscriptions";
	public static final String ATTRIBUTES_BLOB = "attributesBlob";
	public static final String SCHEDULED_START_DATE_AND_TIME = "scheduledStartDateAndTime";
	public static final String EXPECTED_COMPLETION_DATE_AND_TIME = "expectedCompletionDateAndTime";
	public static final String SCHEDULED_STATION_GEOGRAPHIC_LOCATION_CODE = "scheduledStationGeographicLocationCode";
	public static final String PATIENT = "patient";
	public static final String ADMISSION_ID = "admissionID";
	public static final String CREATED_TIME = "createdTime";
	public static final String REFERENCED_REQUESTS = "referencedRequests";
	public static final String SCHEDULED_PROCEDURE_STEP_EXPIRATION_DATE_TIME = "scheduledProcedureStepExpirationDateTime";
	public static final String INPUT_READINESS_STATE = "inputReadinessState";
	public static final String REPLACED_SO_PINSTANCE_UI_D = "replacedSOPInstanceUID";
	public static final String UPDATED_TIME = "updatedTime";
	public static final String UPS_LABEL = "upsLabel";
	public static final String PROCEDURE_STEP_STATE = "procedureStepState";
	public static final String SCHEDULED_WORKITEM_CODE = "scheduledWorkitemCode";
	public static final String WORKLIST_LABEL = "worklistLabel";
	public static final String VERSION = "version";
	public static final String TRANSACTION_UI_D = "transactionUID";
	public static final String SCHEDULED_STATION_NAME_CODE = "scheduledStationNameCode";
	public static final String HUMAN_PERFORMER_CODES = "humanPerformerCodes";
	public static final String PERFORMER_AE_T = "performerAET";
	public static final String PK = "pk";
	public static final String UPS_PRIORITY = "upsPriority";
	public static final String UPS_INSTANCE_UI_D = "upsInstanceUID";
	public static final String SCHEDULED_STATION_CLASS_CODE = "scheduledStationClassCode";

}

