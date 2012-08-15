package cz.cvut.fit.culkajac.dp.services.amazons3.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import org.switchyard.transform.TransformerFactoryClass;

@XmlRegistry
public class ObjectFactory {

	private static class Util {

		static <T> JAXBElement<T> createGeneric(T value, Class<T> clazz) {
			return new JAXBElement<T>(new QName(AMZN_NS, clazz.getSimpleName()), clazz, null, value);
		}

	}

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * cz.cvut.fit.culkajac.dp.services.amazons3.dto
	 * 
	 */

	private static final String AMZN_NS = "http://s3.amazonaws.com/doc/2006-03-01/";

	public ObjectFactory() {
	}

	@XmlElementDecl(namespace = "http://schemas.xmlsoap.org/soap/envelope/", name = "Fault")
	public JAXBElement<Fault> createFault(Fault r) {
//		return Util.createGeneric(r, Fault.class);
		return new JAXBElement<Fault>(new QName("http://schemas.xmlsoap.org/soap/envelope/", Fault.class.getSimpleName()), Fault.class, null, r);
	}

	public Fault createFault() {
		return new Fault();
	}

	/**
	 * Create an instance of {@link CopyObjectResult }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "CopyObjectResult")
	public JAXBElement<CopyObjectResult> createCopyObjectResult(CopyObjectResult r) {
		return Util.createGeneric(r, CopyObjectResult.class);
	}

	public CopyObjectResult createCopyObjectResult() {
		return new CopyObjectResult();
	}

	/**
	 * Create an instance of {@link MetadataEntry }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "MetadataEntry")
	public JAXBElement<MetadataEntry> createMetadataEntry(MetadataEntry r) {
		return Util.createGeneric(r, MetadataEntry.class);
	}

	public MetadataEntry createMetadataEntry() {
		return new MetadataEntry();
	}

	/**
	 * Create an instance of {@link CanonicalUser }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "CanonicalUser")
	public JAXBElement<CanonicalUser> createCanonicalUser(CanonicalUser r) {
		return Util.createGeneric(r, CanonicalUser.class);
	}

	public CanonicalUser createCanonicalUser() {
		return new CanonicalUser();
	}

	/**
	 * Create an instance of {@link Group }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "Group")
	public JAXBElement<Group> createGroup(Group r) {
		return Util.createGeneric(r, Group.class);
	}

	public Group createGroup() {
		return new Group();
	}

	/**
	 * Create an instance of {@link GetObjectExtendedResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetObjectExtendedResponse")
	public JAXBElement<GetObjectExtendedResponse> createGetObjectExtendedResponse(GetObjectExtendedResponse r) {
		return Util.createGeneric(r, GetObjectExtendedResponse.class);
	}

	public GetObjectExtendedResponse createGetObjectExtendedResponse() {
		return new GetObjectExtendedResponse();
	}

	/**
	 * Create an instance of {@link PutObjectInline }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "PutObjectInline")
	public JAXBElement<PutObjectInline> createPutObjectInline(PutObjectInline r) {
		return Util.createGeneric(r, PutObjectInline.class);
	}

	public PutObjectInline createPutObjectInline() {
		return new PutObjectInline();
	}

	/**
	 * Create an instance of {@link PutObjectResult }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "PutObjectResult")
	public JAXBElement<PutObjectResult> createPutObjectResult(PutObjectResult r) {
		return Util.createGeneric(r, PutObjectResult.class);
	}

	public PutObjectResult createPutObjectResult() {
		return new PutObjectResult();
	}

	/**
	 * Create an instance of {@link PutObject }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "PutObject")
	public JAXBElement<PutObject> createPutObject(PutObject r) {
		return Util.createGeneric(r, PutObject.class);
	}

	public PutObject createPutObject() {
		return new PutObject();
	}

	/**
	 * Create an instance of {@link PutObjectInlineResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "PutObjectInlineResponse")
	public JAXBElement<PutObjectInlineResponse> createPutObjectInlineResponse(PutObjectInlineResponse r) {
		return Util.createGeneric(r, PutObjectInlineResponse.class);
	}

	public PutObjectInlineResponse createPutObjectInlineResponse() {
		return new PutObjectInlineResponse();
	}

	/**
	 * Create an instance of {@link VersionEntry }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "VersionEntry")
	public JAXBElement<VersionEntry> createVersionEntry(VersionEntry r) {
		return Util.createGeneric(r, VersionEntry.class);
	}

	public VersionEntry createVersionEntry() {
		return new VersionEntry();
	}

	/**
	 * Create an instance of {@link GetObjectResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetObjectResponse")
	public JAXBElement<GetObjectResponse> createGetObjectResponse(GetObjectResponse r) {
		return Util.createGeneric(r, GetObjectResponse.class);
	}

	public GetObjectResponse createGetObjectResponse() {
		return new GetObjectResponse();
	}

	/**
	 * Create an instance of {@link TopicConfiguration }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "TopicConfiguration")
	public JAXBElement<TopicConfiguration> createTopicConfiguration(TopicConfiguration r) {
		return Util.createGeneric(r, TopicConfiguration.class);
	}

	public TopicConfiguration createTopicConfiguration() {
		return new TopicConfiguration();
	}

	/**
	 * Create an instance of {@link DeleteBucketResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "DeleteBucketResponse")
	public JAXBElement<DeleteBucketResponse> createDeleteBucketResponse(DeleteBucketResponse r) {
		return Util.createGeneric(r, DeleteBucketResponse.class);
	}

	public DeleteBucketResponse createDeleteBucketResponse() {
		return new DeleteBucketResponse();
	}

	/**
	 * Create an instance of {@link ListAllMyBuckets }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListAllMyBuckets")
	public JAXBElement<ListAllMyBuckets> createListAllMyBuckets(ListAllMyBuckets r) {
		return Util.createGeneric(r, ListAllMyBuckets.class);
	}

	public ListAllMyBuckets createListAllMyBuckets() {
		return new ListAllMyBuckets();
	}

	/**
	 * Create an instance of {@link ListBucket }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListBucket")
	public JAXBElement<ListBucket> createListBucket(ListBucket r) {
		return Util.createGeneric(r, ListBucket.class);
	}

	public ListBucket createListBucket() {
		return new ListBucket();
	}

	/**
	 * Create an instance of {@link ListVersionsResult }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListVersionsResult")
	public JAXBElement<ListVersionsResult> createListVersionsResult(ListVersionsResult r) {
		return Util.createGeneric(r, ListVersionsResult.class);
	}

	public ListVersionsResult createListVersionsResult() {
		return new ListVersionsResult();
	}

	/**
	 * Create an instance of {@link ListAllMyBucketsList }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListAllMyBucketsList")
	public JAXBElement<ListAllMyBucketsList> createListAllMyBucketsList(ListAllMyBucketsList r) {
		return Util.createGeneric(r, ListAllMyBucketsList.class);
	}

	public ListAllMyBucketsList createListAllMyBucketsList() {
		return new ListAllMyBucketsList();
	}

	/**
	 * Create an instance of {@link LoggingSettings }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "LoggingSettings")
	public JAXBElement<LoggingSettings> createLoggingSettings(LoggingSettings r) {
		return Util.createGeneric(r, LoggingSettings.class);
	}

	public LoggingSettings createLoggingSettings() {
		return new LoggingSettings();
	}

	/**
	 * Create an instance of {@link NotificationConfiguration }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "NotificationConfiguration")
	public JAXBElement<NotificationConfiguration> createNotificationConfiguration(NotificationConfiguration r) {
		return Util.createGeneric(r, NotificationConfiguration.class);
	}

	public NotificationConfiguration createNotificationConfiguration() {
		return new NotificationConfiguration();
	}

	/**
	 * Create an instance of {@link ListAllMyBucketsEntry }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListAllMyBucketsEntry")
	public JAXBElement<ListAllMyBucketsEntry> createListAllMyBucketsEntry(ListAllMyBucketsEntry r) {
		return Util.createGeneric(r, ListAllMyBucketsEntry.class);
	}

	public ListAllMyBucketsEntry createListAllMyBucketsEntry() {
		return new ListAllMyBucketsEntry();
	}

	/**
	 * Create an instance of {@link GetBucketLoggingStatusResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetBucketLoggingStatusResponse")
	public JAXBElement<GetBucketLoggingStatusResponse> createGetBucketLoggingStatusResponse(GetBucketLoggingStatusResponse r) {
		return Util.createGeneric(r, GetBucketLoggingStatusResponse.class);
	}

	public GetBucketLoggingStatusResponse createGetBucketLoggingStatusResponse() {
		return new GetBucketLoggingStatusResponse();
	}

	/**
	 * Create an instance of {@link VersioningConfiguration }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "VersioningConfiguration")
	public JAXBElement<VersioningConfiguration> createVersioningConfiguration(VersioningConfiguration r) {
		return Util.createGeneric(r, VersioningConfiguration.class);
	}

	public VersioningConfiguration createVersioningConfiguration() {
		return new VersioningConfiguration();
	}

	/**
	 * Create an instance of {@link ListVersionsResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListVersionsResponse")
	public JAXBElement<ListVersionsResponse> createListVersionsResponse(ListVersionsResponse r) {
		return Util.createGeneric(r, ListVersionsResponse.class);
	}

	public ListVersionsResponse createListVersionsResponse() {
		return new ListVersionsResponse();
	}

	/**
	 * Create an instance of {@link PostResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "PostResponse")
	public JAXBElement<PostResponse> createPostResponse(PostResponse r) {
		return Util.createGeneric(r, PostResponse.class);
	}

	public PostResponse createPostResponse() {
		return new PostResponse();
	}

	/**
	 * Create an instance of {@link ListBucketResult }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListBucketResult")
	public JAXBElement<ListBucketResult> createListBucketResult(ListBucketResult r) {
		return Util.createGeneric(r, ListBucketResult.class);
	}

	public ListBucketResult createListBucketResult() {
		return new ListBucketResult();
	}

	/**
	 * Create an instance of {@link GetBucketAccessControlPolicyResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetBucketAccessControlPolicyResponse")
	public JAXBElement<GetBucketAccessControlPolicyResponse> createGetBucketAccessControlPolicyResponse(
			GetBucketAccessControlPolicyResponse r) {
		return Util.createGeneric(r, GetBucketAccessControlPolicyResponse.class);
	}

	public GetBucketAccessControlPolicyResponse createGetBucketAccessControlPolicyResponse() {
		return new GetBucketAccessControlPolicyResponse();
	}

	/**
	 * Create an instance of {@link GetObjectAccessControlPolicyResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetObjectAccessControlPolicyResponse")
	public JAXBElement<GetObjectAccessControlPolicyResponse> createGetObjectAccessControlPolicyResponse(
			GetObjectAccessControlPolicyResponse r) {
		return Util.createGeneric(r, GetObjectAccessControlPolicyResponse.class);
	}

	public GetObjectAccessControlPolicyResponse createGetObjectAccessControlPolicyResponse() {
		return new GetObjectAccessControlPolicyResponse();
	}

	/**
	 * Create an instance of {@link ListAllMyBucketsResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListAllMyBucketsResponse")
	public JAXBElement<ListAllMyBucketsResponse> createListAllMyBucketsResponse(ListAllMyBucketsResponse r) {
		return Util.createGeneric(r, ListAllMyBucketsResponse.class);
	}

	public ListAllMyBucketsResponse createListAllMyBucketsResponse() {
		return new ListAllMyBucketsResponse();
	}

	/**
	 * Create an instance of {@link SetObjectAccessControlPolicy }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "SetObjectAccessControlPolicy")
	public JAXBElement<SetObjectAccessControlPolicy> createSetObjectAccessControlPolicy(SetObjectAccessControlPolicy r) {
		return Util.createGeneric(r, SetObjectAccessControlPolicy.class);
	}

	public SetObjectAccessControlPolicy createSetObjectAccessControlPolicy() {
		return new SetObjectAccessControlPolicy();
	}

	/**
	 * Create an instance of {@link DeleteMarkerEntry }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "DeleteMarkerEntry")
	public JAXBElement<DeleteMarkerEntry> createDeleteMarkerEntry(DeleteMarkerEntry r) {
		return Util.createGeneric(r, DeleteMarkerEntry.class);
	}

	public DeleteMarkerEntry createDeleteMarkerEntry() {
		return new DeleteMarkerEntry();
	}

	/**
	 * Create an instance of {@link AmazonCustomerByEmail }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "AmazonCustomerByEmail")
	public JAXBElement<AmazonCustomerByEmail> createAmazonCustomerByEmail(AmazonCustomerByEmail r) {
		return Util.createGeneric(r, AmazonCustomerByEmail.class);
	}

	public AmazonCustomerByEmail createAmazonCustomerByEmail() {
		return new AmazonCustomerByEmail();
	}

	/**
	 * Create an instance of {@link Grant }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "Grant")
	public JAXBElement<Grant> createGrant(Grant r) {
		return Util.createGeneric(r, Grant.class);
	}

	public Grant createGrant() {
		return new Grant();
	}

	/**
	 * Create an instance of {@link PrefixEntry }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "PrefixEntry")
	public JAXBElement<PrefixEntry> createPrefixEntry(PrefixEntry r) {
		return Util.createGeneric(r, PrefixEntry.class);
	}

	public PrefixEntry createPrefixEntry() {
		return new PrefixEntry();
	}

	/**
	 * Create an instance of {@link GetObjectAccessControlPolicy }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetObjectAccessControlPolicy")
	public JAXBElement<GetObjectAccessControlPolicy> createGetObjectAccessControlPolicy(GetObjectAccessControlPolicy r) {
		return Util.createGeneric(r, GetObjectAccessControlPolicy.class);
	}

	public GetObjectAccessControlPolicy createGetObjectAccessControlPolicy() {
		return new GetObjectAccessControlPolicy();
	}

	/**
	 * Create an instance of {@link SetBucketAccessControlPolicyResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "SetBucketAccessControlPolicyResponse")
	public JAXBElement<SetBucketAccessControlPolicyResponse> createSetBucketAccessControlPolicyResponse(
			SetBucketAccessControlPolicyResponse r) {
		return Util.createGeneric(r, SetBucketAccessControlPolicyResponse.class);
	}

	public SetBucketAccessControlPolicyResponse createSetBucketAccessControlPolicyResponse() {
		return new SetBucketAccessControlPolicyResponse();
	}

	/**
	 * Create an instance of {@link Result }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "Result")
	public JAXBElement<Result> createResult(Result r) {
		return Util.createGeneric(r, Result.class);
	}

	public Result createResult() {
		return new Result();
	}

	/**
	 * Create an instance of {@link SetBucketLoggingStatusResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "SetBucketLoggingStatusResponse")
	public JAXBElement<SetBucketLoggingStatusResponse> createSetBucketLoggingStatusResponse(SetBucketLoggingStatusResponse r) {
		return Util.createGeneric(r, SetBucketLoggingStatusResponse.class);
	}

	public SetBucketLoggingStatusResponse createSetBucketLoggingStatusResponse() {
		return new SetBucketLoggingStatusResponse();
	}

	/**
	 * Create an instance of {@link CreateBucketResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "CreateBucketResponse")
	public JAXBElement<CreateBucketResponse> createCreateBucketResponse(CreateBucketResponse r) {
		return Util.createGeneric(r, CreateBucketResponse.class);
	}

	public CreateBucketResponse createCreateBucketResponse() {
		return new CreateBucketResponse();
	}

	/**
	 * Create an instance of {@link SetObjectAccessControlPolicyResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "SetObjectAccessControlPolicyResponse")
	public JAXBElement<SetObjectAccessControlPolicyResponse> createSetObjectAccessControlPolicyResponse(
			SetObjectAccessControlPolicyResponse r) {
		return Util.createGeneric(r, SetObjectAccessControlPolicyResponse.class);
	}

	public SetObjectAccessControlPolicyResponse createSetObjectAccessControlPolicyResponse() {
		return new SetObjectAccessControlPolicyResponse();
	}

	/**
	 * Create an instance of {@link DeleteObject }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "DeleteObject")
	public JAXBElement<DeleteObject> createDeleteObject(DeleteObject r) {
		return Util.createGeneric(r, DeleteObject.class);
	}

	public DeleteObject createDeleteObject() {
		return new DeleteObject();
	}

	/**
	 * Create an instance of {@link AccessControlPolicy }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "AccessControlPolicy")
	public JAXBElement<AccessControlPolicy> createAccessControlPolicy(AccessControlPolicy r) {
		return Util.createGeneric(r, AccessControlPolicy.class);
	}

	public AccessControlPolicy createAccessControlPolicy() {
		return new AccessControlPolicy();
	}

	/**
	 * Create an instance of {@link CreateBucket }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "CreateBucket")
	public JAXBElement<CreateBucket> createCreateBucket(CreateBucket r) {
		return Util.createGeneric(r, CreateBucket.class);
	}

	public CreateBucket createCreateBucket() {
		return new CreateBucket();
	}

	/**
	 * Create an instance of {@link CreateBucketConfiguration }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "CreateBucketConfiguration")
	public JAXBElement<CreateBucketConfiguration> createCreateBucketConfiguration(CreateBucketConfiguration r) {
		return Util.createGeneric(r, CreateBucketConfiguration.class);
	}

	public CreateBucketConfiguration createCreateBucketConfiguration() {
		return new CreateBucketConfiguration();
	}

	/**
	 * Create an instance of {@link CreateBucketResult }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "CreateBucketResult")
	public JAXBElement<CreateBucketResult> createCreateBucketResult(CreateBucketResult r) {
		return Util.createGeneric(r, CreateBucketResult.class);
	}

	public CreateBucketResult createCreateBucketResult() {
		return new CreateBucketResult();
	}

	/**
	 * Create an instance of {@link BucketLoggingStatus }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "BucketLoggingStatus")
	public JAXBElement<BucketLoggingStatus> createBucketLoggingStatus(BucketLoggingStatus r) {
		return Util.createGeneric(r, BucketLoggingStatus.class);
	}

	public BucketLoggingStatus createBucketLoggingStatus() {
		return new BucketLoggingStatus();
	}

	/**
	 * Create an instance of {@link DeleteBucket }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "DeleteBucket")
	public JAXBElement<DeleteBucket> createDeleteBucket(DeleteBucket r) {
		return Util.createGeneric(r, DeleteBucket.class);
	}

	public DeleteBucket createDeleteBucket() {
		return new DeleteBucket();
	}

	/**
	 * Create an instance of {@link LocationConstraint }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "LocationConstraint")
	public JAXBElement<LocationConstraint> createLocationConstraint(LocationConstraint r) {
		return Util.createGeneric(r, LocationConstraint.class);
	}

	public LocationConstraint createLocationConstraint() {
		return new LocationConstraint();
	}

	/**
	 * Create an instance of {@link DeleteObjectResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "DeleteObjectResponse")
	public JAXBElement<DeleteObjectResponse> createDeleteObjectResponse(DeleteObjectResponse r) {
		return Util.createGeneric(r, DeleteObjectResponse.class);
	}

	public DeleteObjectResponse createDeleteObjectResponse() {
		return new DeleteObjectResponse();
	}

	/**
	 * Create an instance of {@link SetBucketAccessControlPolicy }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "SetBucketAccessControlPolicy")
	public JAXBElement<SetBucketAccessControlPolicy> createSetBucketAccessControlPolicy(SetBucketAccessControlPolicy r) {
		return Util.createGeneric(r, SetBucketAccessControlPolicy.class);
	}

	public SetBucketAccessControlPolicy createSetBucketAccessControlPolicy() {
		return new SetBucketAccessControlPolicy();
	}

	/**
	 * Create an instance of {@link PutObjectResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "PutObjectResponse")
	public JAXBElement<PutObjectResponse> createPutObjectResponse(PutObjectResponse r) {
		return Util.createGeneric(r, PutObjectResponse.class);
	}

	public PutObjectResponse createPutObjectResponse() {
		return new PutObjectResponse();
	}

	/**
	 * Create an instance of {@link ListEntry }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListEntry")
	public JAXBElement<ListEntry> createListEntry(ListEntry r) {
		return Util.createGeneric(r, ListEntry.class);
	}

	public ListEntry createListEntry() {
		return new ListEntry();
	}

	/**
	 * Create an instance of {@link RequestPaymentConfiguration }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "RequestPaymentConfiguration")
	public JAXBElement<RequestPaymentConfiguration> createRequestPaymentConfiguration(RequestPaymentConfiguration r) {
		return Util.createGeneric(r, RequestPaymentConfiguration.class);
	}

	public RequestPaymentConfiguration createRequestPaymentConfiguration() {
		return new RequestPaymentConfiguration();
	}

	/**
	 * Create an instance of {@link CopyObjectResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "CopyObjectResponse")
	public JAXBElement<CopyObjectResponse> createCopyObjectResponse(CopyObjectResponse r) {
		return Util.createGeneric(r, CopyObjectResponse.class);
	}

	public CopyObjectResponse createCopyObjectResponse() {
		return new CopyObjectResponse();
	}

	/**
	 * Create an instance of {@link ListAllMyBucketsResult }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListAllMyBucketsResult")
	public JAXBElement<ListAllMyBucketsResult> createListAllMyBucketsResult(ListAllMyBucketsResult r) {
		return Util.createGeneric(r, ListAllMyBucketsResult.class);
	}

	public ListAllMyBucketsResult createListAllMyBucketsResult() {
		return new ListAllMyBucketsResult();
	}

	/**
	 * Create an instance of {@link ListBucketResponse }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "ListBucketResponse")
	public JAXBElement<ListBucketResponse> createListBucketResponse(ListBucketResponse r) {
		return Util.createGeneric(r, ListBucketResponse.class);
	}

	public ListBucketResponse createListBucketResponse() {
		return new ListBucketResponse();
	}

	/**
	 * Create an instance of {@link GetBucketAccessControlPolicy }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetBucketAccessControlPolicy")
	public JAXBElement<GetBucketAccessControlPolicy> createGetBucketAccessControlPolicy(GetBucketAccessControlPolicy r) {
		return Util.createGeneric(r, GetBucketAccessControlPolicy.class);
	}

	public GetBucketAccessControlPolicy createGetBucketAccessControlPolicy() {
		return new GetBucketAccessControlPolicy();
	}

	/**
	 * Create an instance of {@link GetObjectExtended }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetObjectExtended")
	public JAXBElement<GetObjectExtended> createGetObjectExtended(GetObjectExtended r) {
		return Util.createGeneric(r, GetObjectExtended.class);
	}

	public GetObjectExtended createGetObjectExtended() {
		return new GetObjectExtended();
	}

	/**
	 * Create an instance of {@link Status }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "Status")
	public JAXBElement<Status> createStatus(Status r) {
		return Util.createGeneric(r, Status.class);
	}

	public Status createStatus() {
		return new Status();
	}

	/**
	 * Create an instance of {@link SetBucketLoggingStatus }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "SetBucketLoggingStatus")
	public JAXBElement<SetBucketLoggingStatus> createSetBucketLoggingStatus(SetBucketLoggingStatus r) {
		return Util.createGeneric(r, SetBucketLoggingStatus.class);
	}

	public SetBucketLoggingStatus createSetBucketLoggingStatus() {
		return new SetBucketLoggingStatus();
	}

	/**
	 * Create an instance of {@link AccessControlList }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "AccessControlList")
	public JAXBElement<AccessControlList> createAccessControlList(AccessControlList r) {
		return Util.createGeneric(r, AccessControlList.class);
	}

	public AccessControlList createAccessControlList() {
		return new AccessControlList();
	}

	/**
	 * Create an instance of {@link GetBucketLoggingStatus }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetBucketLoggingStatus")
	public JAXBElement<GetBucketLoggingStatus> createGetBucketLoggingStatus(GetBucketLoggingStatus r) {
		return Util.createGeneric(r, GetBucketLoggingStatus.class);
	}

	public GetBucketLoggingStatus createGetBucketLoggingStatus() {
		return new GetBucketLoggingStatus();
	}

	/**
	 * Create an instance of {@link GetObjectResult }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetObjectResult")
	public JAXBElement<GetObjectResult> createGetObjectResult(GetObjectResult r) {
		return Util.createGeneric(r, GetObjectResult.class);
	}

	public GetObjectResult createGetObjectResult() {
		return new GetObjectResult();
	}

	/**
	 * Create an instance of {@link GetObject }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "GetObject")
	public JAXBElement<GetObject> createGetObject(GetObject r) {
		return Util.createGeneric(r, GetObject.class);
	}

	public GetObject createGetObject() {
		return new GetObject();
	}

	/**
	 * Create an instance of {@link CopyObject }
	 * 
	 */
	@XmlElementDecl(namespace = AMZN_NS, name = "CopyObject")
	public JAXBElement<CopyObject> createCopyObject(CopyObject r) {
		return Util.createGeneric(r, CopyObject.class);
	}

	public CopyObject createCopyObject() {
		return new CopyObject();
	}

}
