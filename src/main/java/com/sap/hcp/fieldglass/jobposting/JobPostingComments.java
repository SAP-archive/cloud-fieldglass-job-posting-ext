package com.sap.hcp.fieldglass.jobposting;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import com.google.gson.Gson;

/**
 * This class represents the comments of a job posting
 * 
 * @author i330092
 *
 */
public class JobPostingComments {

	private static final String BOOLEAN_FALSE_DISPLAY_NAME = "No";

	private static final String BOOLEAN_TRUE_DISPLAY_NAME = "Yes";

	private static final String NAME_VALUE_DELIMITER = "\n";

	private static final String UNIQUE_EXTERNAL_ID_PREFIX = "HCP";

	private static final String VALUE_DELIMITER = ",";

	@SuppressWarnings("serial")
	private static final HashMap<String, String> displayNames = new HashMap<String, String>() {
		{
			put("externalJobPostingID", "External Job Posting ID");
			put("creatorUsername", "Creator Username");
			put("templateName", "Template Name");
			put("buyerReference", "Buyer Reference");
			put("title", "Title");
			put("numberOfPositions", "Number of Positions");
			put("jobDescription", "Job Description");
			put("currency", "Currency");
			put("bussinessUnitCode", "Business Unit Code");
			put("siteCode", "Site Code");
			put("locationCode", "Location Code");
			put("startDate", "Start Date");
			put("endDate", "End Date");
			put("ownerUsername", "Owner Username");
			put("coordinatorUsername", "Coordinator Username");
			put("distributorUsername", "Distributor Username");
			put("costCenterCode", "Cost Center Code");
			put("estimatedExpensePercent", "Estimated Expense %");
			put("estimateAdditionalSpendPercent", "Estimated Additional Spend %");
			put("jobSeekerRateCanBeIncreased", "Job Seeker Rate can be increased");
			put("respondByDate", "Respond by Date");
			put("hoursPerWeek", "Hours per Week");
			put("hoursPerDay", "Hours per Day");
			put("travelTime", "Travel Time");
			put("maximumSubmission", "Maximum Submission");
			put("flatAdjustments", "Flat Adjustments");
			put("comments", "Comments");
			put("address1", "Address 1");
			put("address2", "Address 2");
			put("city", "City");
			put("stateOrProvince", "State/Province");
			put("country", "Country");
			put("postalCode", "ZIP/Postal Code");
			put("siteTax", "Site Tax");
			put("suppliers", "Suppliers");
			put("timeSheetFrequency", "Time Sheet Frequency");
			put("startDayOfWeek", "Start Day of Week");
			put("minStHr", "[Min] ST/Hr");
			put("maxStHr", "[Max] ST/Hr");
		}
	};

	private String externalJobPostingID;

	private String creatorUsername;

	private String templateName;

	private String buyerReference;

	private String title;

	private Integer numberOfPositions;

	private String jobDescription;

	private String currency;

	private Integer bussinessUnitCode;

	private Integer siteCode;

	private Integer locationCode;

	private String startDate;

	private String endDate;

	private String ownerUsername;

	private String coordinatorUsername;

	private String distributorUsername;

	private Integer costCenterCode;

	private Integer estimatedExpensePercent;

	private Integer estimateAdditionalSpendPercent;

	private boolean jobSeekerRateCanBeIncreased;

	private String respondByDate;

	private Integer hoursPerWeek;

	private Integer hoursPerDay;

	private Integer travelTime;

	private Integer maximumSubmission;

	private String flatAdjustments;

	private String comments;

	private String address1;

	private String address2;

	private String city;

	private String stateOrProvince;

	private String country;

	private Integer postalCode;

	private Integer siteTax;

	private String suppliers;

	private String timeSheetFrequency;

	private String startDayOfWeek;

	private Integer minStHr;

	private Integer maxStHr;

	public JobPostingComments() {
	}

	public String getExternalJobPostingID() {
		return externalJobPostingID;
	}

	public void setExternalJobPostingID(String externalJobPostingID) {
		this.externalJobPostingID = externalJobPostingID;
	}

	public String getCreatorUsername() {
		return creatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getBuyerReference() {
		return buyerReference;
	}

	public void setBuyerReference(String buyerReference) {
		this.buyerReference = buyerReference;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNumberOfPositions() {
		return numberOfPositions;
	}

	public void setNumberOfPositions(Integer numberOfPositions) {
		this.numberOfPositions = numberOfPositions;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getBussinessUnitCode() {
		return bussinessUnitCode;
	}

	public void setBussinessUnitCode(Integer bussinessUnitCode) {
		this.bussinessUnitCode = bussinessUnitCode;
	}

	public Integer getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(Integer siteCode) {
		this.siteCode = siteCode;
	}

	public Integer getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(Integer locationCode) {
		this.locationCode = locationCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOwnerUsername() {
		return ownerUsername;
	}

	public void setOwnerUsername(String ownerUsername) {
		this.ownerUsername = ownerUsername;
	}

	public String getCoordinatorUsername() {
		return coordinatorUsername;
	}

	public void setCoordinatorUsername(String coordinatorUsername) {
		this.coordinatorUsername = coordinatorUsername;
	}

	public String getDistributorUsername() {
		return distributorUsername;
	}

	public void setDistributorUsername(String distributorUsername) {
		this.distributorUsername = distributorUsername;
	}

	public Integer getCostCenterCode() {
		return costCenterCode;
	}

	public void setCostCenterCode(Integer costCenterCode) {
		this.costCenterCode = costCenterCode;
	}

	public Integer getEstimatedExpensePercent() {
		return estimatedExpensePercent;
	}

	public void setEstimatedExpensePercent(Integer estimatedExpensePercent) {
		this.estimatedExpensePercent = estimatedExpensePercent;
	}

	public Integer getEstimateAdditionalSpendPercent() {
		return estimateAdditionalSpendPercent;
	}

	public void setEstimateAdditionalSpendPercent(Integer estimateAdditionalSpendPercent) {
		this.estimateAdditionalSpendPercent = estimateAdditionalSpendPercent;
	}

	public boolean isJobSeekerRateCanBeIncreased() {
		return jobSeekerRateCanBeIncreased;
	}

	public void setJobSeekerRateCanBeIncreased(boolean jobSeekerRateCanBeIncreased) {
		this.jobSeekerRateCanBeIncreased = jobSeekerRateCanBeIncreased;
	}

	public String getFinalRespondDate() {
		return respondByDate;
	}

	public void setFinalRespondDate(String respondByDate) {
		this.respondByDate = respondByDate;
	}

	public Integer getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(Integer hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public Integer getHoursPerDay() {
		return hoursPerDay;
	}

	public void setHoursPerDay(Integer hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}

	public Integer getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Integer travelTime) {
		this.travelTime = travelTime;
	}

	public Integer getMaximumSubmission() {
		return maximumSubmission;
	}

	public void setMaximumSubmission(Integer maximumSubmission) {
		this.maximumSubmission = maximumSubmission;
	}

	public String getFlatAdjustments() {
		return flatAdjustments;
	}

	public void setFlatAdjustments(String flatAdjustments) {
		this.flatAdjustments = flatAdjustments;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateOrProvince() {
		return stateOrProvince;
	}

	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getSiteTax() {
		return siteTax;
	}

	public void setSiteTax(Integer siteTax) {
		this.siteTax = siteTax;
	}

	public String getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(String suppliers) {
		this.suppliers = suppliers;
	}

	public String getTimeSheetFrequency() {
		return timeSheetFrequency;
	}

	public void setTimeSheetFrequency(String timeSheetFrequency) {
		this.timeSheetFrequency = timeSheetFrequency;
	}

	public String getStartDayOfWeek() {
		return startDayOfWeek;
	}

	public void setStartDayOfWeek(String startDayOfWeek) {
		this.startDayOfWeek = startDayOfWeek;
	}

	public Integer getMinStHr() {
		return minStHr;
	}

	public void setMinStHr(Integer minStHr) {
		this.minStHr = minStHr;
	}

	public Integer getMaxStHr() {
		return maxStHr;
	}

	public void setMaxStHr(Integer maxStHr) {
		this.maxStHr = maxStHr;
	}

	public String getDisplayName(String fieldName) {
		return displayNames.get(fieldName);
	}

	/**
	 * Creates a string, containing the JobPostingComments object as Delimiter
	 * Separated Values
	 */
	@Override
	public String toString() {
		Field[] fields = JobPostingComments.class.getDeclaredFields();
		StringBuilder dsv = new StringBuilder();

		boolean first = true;
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()))
				continue;
			if (first)
				first = false;
			else
				dsv.append(VALUE_DELIMITER);

			try {
				dsv.append(getDisplayName(field.getName()));
			} catch (IllegalArgumentException | NullPointerException e) {
				continue;
			}
		}
		dsv.append(NAME_VALUE_DELIMITER);
		first = true;
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()))
				continue;

			if (first)
				first = false;
			else
				dsv.append(VALUE_DELIMITER);

			try {
				String value;

				// Check if a field is an integer and is equal to zero
				if (Number.class.isAssignableFrom(field.getType()) && (Integer) (field.get(this)) == 0) {
					continue;
				}

				if (field.getType() == boolean.class) {
					value = (((boolean) field.get(this) == true) ? BOOLEAN_TRUE_DISPLAY_NAME
							: BOOLEAN_FALSE_DISPLAY_NAME);
				} else {
					value = field.get(this).toString();
				}

				dsv.append(value);
			} catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
				continue;
			}
		}

		return dsv.toString();
	}

	/**
	 * Sets a unique external ID for this {@link JobPostingComments} object
	 */
	public void setUniqueExternalId() {
		this.setExternalJobPostingID(UNIQUE_EXTERNAL_ID_PREFIX + System.currentTimeMillis());
	}

	/**
	 * Parses the JSON string to {@link JobPostingComments} object
	 * 
	 * @param json
	 *            JSON representation of the {@link JobPostingComments} object
	 * @return the {@link JobPostingComments} object parsed from the JSON string
	 */
	public static JobPostingComments parseFromJson(String json) {
		// json.replaceAll("\"\"", "null");

		Gson gson = new Gson();
		JobPostingComments result = gson.fromJson(json, JobPostingComments.class);
		return result;
	}

	/**
	 * Returns JSON representation of the {@link JobPostingComments} object
	 * 
	 * @return JSON representation of the {@link JobPostingComments} object
	 */
	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}
}
