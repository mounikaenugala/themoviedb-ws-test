package constants;

/**
 * Mapping for all the status codes to valid enum values.
 * 
 * Reference Doc: https://www.themoviedb.org/documentation/api/status-codes
 * 
 * @author mounikaenugala
 * 
 *
 */
public enum APIStatusCodes {
	SUCCESS(1, 200, "Success."), 
	INVALID_SERVICE(2, 501, "Invalid service: this service does not exist."), 
	AUTHENTICATION_FAILED_PERMISSION(3, 401, "Authentication failed: You do not have permissions to access the service."), 
	INVALID_FORMAT(4, 405, "Invalid format: This service doesn't exist in that format."), 
	INVALID_PARAMETERS(5, 422, "Invalid parameters: Your request parameters are incorrect."), 
	INVALID_ID(6, 404, "Invalid id: The pre-requisite id is invalid or not found."), 
	INVALID_API_KEY(7, 401, "Invalid API key: You must be granted a valid key."), 
	DUPLICATE_ENTRY(8, 403, "Duplicate entry: The data you tried to submit already exists."), 
	SERVICE_OFFLINE(9, 503, "Service offline: This service is temporarily offline, try again later."), 
	SUSPEND_API_KEY(10, 401, "Suspended API key: Access to your account has been suspended, contact TMDb."), 
	INTERNAL_ERROR(11, 500, "Internal error: Something went wrong, contact TMDb."),
	ITEM_UPDATED_SUCCESSFULLY(12, 201, "The item/record was updated successfully."), 
	ITEM_DELETED_SUCCESSFULLY(13, 200, "The item/record was deleted successfully."), 
	AUTHENTICATION_FAILED(14, 401, "Authentication failed."), 
	FAILED(15, 500, "Failed."), 
	DEVICE_DENIED(16,401,"Device denied."), 
    SESSION_DENIED(17,401,"Session denied."), 
    VALIDATION_FAILED(18,400,"Validation failed."), 
    INVALID_ACCEPT_HEADER(19,406,"Invalid accept header."), 
    INVALID_DATE_RANGE(20, 422, "Invalid date range: Should be a range no longer than 14 days."), 
    ENTRY_NOT_FOUND(21, 200, "Entry not found: The item you are trying to edit cannot be found."), 
    INVALID_PAGE(22, 400, "Invalid page: Pages start at 1 and max at 1000. They are expected to be an integer."), 
    INVALID_DATE(23, 400, "Invalid date: Format needs to be YYYY-MM-DD."), 
    TIMED_OUT(24, 504, "Your request to the backend server timed out. Try again."), 
    REQUEST_COUNT_OVER(25, 429, "Your request count (#) is over the allowed limit of (40)."), 
    PROVIDE_USERNAME_PSWD(26, 400, "You must provide a username and password."), 
    TOO_MANY_APPEND(27, 400, "Too many append to response objects: The maximum number of remote calls is 20."), 
    INVALID_TIMEZONE(28, 400, "Invalid timezone: Please consult the documentation for a valid timezone."), 
    CONFIRM_THIS_ACTION(29, 400, "You must confirm this action: Please provide a confirm=true parameter."), 
    INVALID_USERNAME_PSWD(30, 401, "Invalid username and/or password: You did not provide a valid login."), 
    ACCOUNT_DISABLED(31, 401, "Account disabled: Your account is no longer active. Contact TMDb if this is an error."),
    EMAIL_NOT_VERIFIED(32 , 401, "Email not verified: Your email address has not been verified."),
    INVALID_REQUEST_TOKEN(33 , 401, "Invalid request token: The request token is either expired or invalid."),
    RESOURCE_NOT_FOUND(34, 401 ,"The resource you requested could not be found.");
    
    private final int code;
	private final int httpStatus;
	private final String message;

	APIStatusCodes(int code, int httpStatus, String message) {
		this.code = code;
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}
	
    public static String getAPIStatusCodes(int code){
    	APIStatusCodes[] values = APIStatusCodes.values();
        for(APIStatusCodes apiStatusCodes : values) {
        	if(apiStatusCodes.code == code) {
        		return apiStatusCodes.message;
        	}
        }
        return null;
    }
}
