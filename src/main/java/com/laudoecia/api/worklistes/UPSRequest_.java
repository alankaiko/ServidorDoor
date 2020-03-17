package com.laudoecia.api.worklistes;

import com.laudoecia.api.domain.IssuerEntity;
import com.laudoecia.api.domain.PersonName;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UPSRequest.class)
public abstract class UPSRequest_ {

	public static volatile SingularAttribute<UPSRequest, String> requestedProcedureID;
	public static volatile SingularAttribute<UPSRequest, PersonName> requestingPhysician;
	public static volatile SingularAttribute<UPSRequest, String> requestingService;
	public static volatile SingularAttribute<UPSRequest, String> studyInstanceUID;
	public static volatile SingularAttribute<UPSRequest, IssuerEntity> issuerOfAccessionNumber;
	public static volatile SingularAttribute<UPSRequest, Long> pk;
	public static volatile SingularAttribute<UPSRequest, String> accessionNumber;

	public static final String REQUESTED_PROCEDURE_ID = "requestedProcedureID";
	public static final String REQUESTING_PHYSICIAN = "requestingPhysician";
	public static final String REQUESTING_SERVICE = "requestingService";
	public static final String STUDY_INSTANCE_UI_D = "studyInstanceUID";
	public static final String ISSUER_OF_ACCESSION_NUMBER = "issuerOfAccessionNumber";
	public static final String PK = "pk";
	public static final String ACCESSION_NUMBER = "accessionNumber";

}

