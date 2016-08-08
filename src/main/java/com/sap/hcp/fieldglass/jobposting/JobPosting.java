package com.sap.hcp.fieldglass.jobposting;

import java.io.Serializable;

import com.google.gson.Gson;

/**
 * This class represents a jop posting
 * 
 * @author i330092
 *
 */
public class JobPosting implements Serializable {

	private static final String TYPE_JOB_POSTING_DISPLAY_NAME = "Type=Job Posting";

	private static final String TRANSACTION_DISLAY_NAME = "Transaction=";

	private static final String APPROVAL_REQUIRED_DISPLAY_NAME = "Approval Required=";

	private static final String SEND_NOTIFICATION_DISPLAY_NAME = "Send Notification?=";

	private static final String LANGUAGE_DISPLAY_NAME = "Language=";

	private static final String NUMBER_FORMAT_DISPLAY_NAME = "Number Format=";

	private static final String DATE_FORMAT_DISPLAY_NAME = "Date Format=";

	private static final String COMMENTS_DISPLAY_NAME = "Comments=";

	private static final char DISPLAY_NAME_DELIMITER = '\n';

	private static final long serialVersionUID = 1L;

	private boolean transaction;

	private boolean approvalRequired;

	private boolean sendNotification;

	private String languange;

	private String numberFormat;

	private String dateFormat;

	private JobPostingComments comments;

	public JobPosting() {

	}

	public JobPosting(boolean isTransaction, boolean approvalRequired, boolean sendNotification, String languange,
			String numberFormat, String dateFormat, JobPostingComments comments) {
		setTransaction(isTransaction);
		setApprovalRequired(approvalRequired);
		setSendNotification(sendNotification);
		setLanguange(languange);
		setNumberFormat(numberFormat);
		setDateFormat(dateFormat);
		setComments(comments);
	}

	/**
	 * Gets the transaction property of this job posting
	 * 
	 * @return true if the job posting is transaction
	 */
	public boolean isTransaction() {
		return transaction;
	}

	/**
	 * Sets the transaction property of the job posting
	 * 
	 * @param isTransaction
	 *            new value for transaction property
	 */
	public void setTransaction(boolean isTransaction) {
		this.transaction = isTransaction;
	}

	/**
	 * Gets the approvalRequired property of this job posting
	 * 
	 * @return true if the job posting requires an approval
	 */
	public boolean isApprovalRequired() {
		return approvalRequired;
	}

	/**
	 * Sets the a approvalRequired property of the job posting
	 * 
	 * @param approvalRequired
	 *            new value for approvalRequired property
	 */
	public void setApprovalRequired(boolean approvalRequired) {
		this.approvalRequired = approvalRequired;
	}

	/**
	 * Gets the sendNotification property of this job posting
	 * 
	 * @return the sendNotification property of this job posting
	 */
	public boolean getSendNotification() {
		return sendNotification;
	}

	/**
	 * Sets the sendNotification property of this job posting
	 * 
	 * @param sendNotification
	 *            new value for sendNotification property
	 */
	public void setSendNotification(boolean sendNotification) {
		this.sendNotification = sendNotification;
	}

	/**
	 * Gets the language of this job posting
	 * 
	 * @return the language of this job posting
	 */
	public String getLanguange() {
		return languange;
	}

	/**
	 * Sets the language of this job posting
	 * 
	 * @param languange
	 *            new value for the language of this job posting
	 */
	public void setLanguange(String languange) {
		this.languange = languange;
	}

	/**
	 * Gets the number format of this job posting
	 * 
	 * @return the number format of this job posting
	 */
	public String getNumberFormat() {
		return numberFormat;
	}

	/**
	 * Sets the number format of this job posting
	 * 
	 * @param numberFormat
	 *            new value for the number format of this job posting
	 */
	public void setNumberFormat(String numberFormat) {
		this.numberFormat = numberFormat;
	}

	/**
	 * Gets the date format of this job posting
	 * 
	 * @return the date format of this job posting
	 */
	public String getDateFormat() {
		return dateFormat;
	}

	/**
	 * Sets the date format of this job posting
	 * 
	 * @param dateFormat
	 *            new value for the number format of this job posting
	 */
	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	/**
	 * Gets the comments of this job posting
	 * 
	 * @return the comments of this job posting
	 */
	public JobPostingComments getComments() {
		return comments;
	}

	/**
	 * Sets the comments of this job posting
	 * 
	 * @param comments
	 *            new value for the comments of this job posting
	 */
	public void setComments(JobPostingComments comments) {
		this.comments = comments;
	}

	/**
	 * Returns a string representation of this job posting with appropriate
	 * format for the Fieldglass service.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(TYPE_JOB_POSTING_DISPLAY_NAME);
		result.append(DISPLAY_NAME_DELIMITER);

		result.append(TRANSACTION_DISLAY_NAME);
		result.append(this.isTransaction());
		result.append(DISPLAY_NAME_DELIMITER);

		result.append(APPROVAL_REQUIRED_DISPLAY_NAME);
		result.append(this.isApprovalRequired());
		result.append(DISPLAY_NAME_DELIMITER);

		result.append(SEND_NOTIFICATION_DISPLAY_NAME);
		result.append(this.getSendNotification());
		result.append(DISPLAY_NAME_DELIMITER);

		result.append(LANGUAGE_DISPLAY_NAME);
		result.append(this.getLanguange());
		result.append(DISPLAY_NAME_DELIMITER);

		final char quotes = '\"';
		result.append(quotes);
		result.append(NUMBER_FORMAT_DISPLAY_NAME);
		result.append(this.getNumberFormat());
		result.append(quotes);
		result.append(DISPLAY_NAME_DELIMITER);

		result.append(DATE_FORMAT_DISPLAY_NAME);
		result.append(this.getDateFormat());
		result.append(DISPLAY_NAME_DELIMITER);

		result.append(COMMENTS_DISPLAY_NAME);
		result.append(DISPLAY_NAME_DELIMITER);
		result.append(DISPLAY_NAME_DELIMITER);

		result.append(this.getComments().toString());

		return result.toString();
	}

	/**
	 * Creates a JobPosting object from the given JSON
	 * 
	 * @param json
	 *            A json object, containing job posting properties
	 * @return
	 */
	public static JobPosting parseFromJson(String json) {
		Gson gson = new Gson();
		JobPosting result = gson.fromJson(json, JobPosting.class);

		return result;
	}

	/**
	 * Creates JSON object from the JobPosting object
	 * 
	 * @return The created JSON object
	 */
	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	/**
	 * Sets a unique external ID comment for this job posting
	 */
	public void setUniqueExternalId() {
		this.comments.setUniqueExternalId();
	}
}
