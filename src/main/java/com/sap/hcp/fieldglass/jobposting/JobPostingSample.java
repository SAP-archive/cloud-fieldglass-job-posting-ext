package com.sap.hcp.fieldglass.jobposting;

/**
 * A sample showing how to create a {@link JobPosting} instance and set its properties
 * 
 * @author i330092
 *
 */
public class JobPostingSample {

	/**
	 * Creates a sample {@link JobPosting} object
	 * 
	 * @return the created object
	 */
	public static JobPosting createJobPosting() {
		JobPosting jobPosting = new JobPosting();
		jobPosting.setTransaction(true);
		jobPosting.setApprovalRequired(false);
		jobPosting.setSendNotification(true);
		jobPosting.setLanguange("English (United States)");
		jobPosting.setNumberFormat("#,##9.99 (Example: 1,234,567.99)");

		jobPosting.setDateFormat("MM/DD/YYYY");

		JobPostingComments jobPostingComments = new JobPostingComments();
		jobPostingComments.setUniqueExternalId();
		jobPostingComments.setCreatorUsername("HTCP@fg_help");
		jobPostingComments.setTemplateName("Accountant");
		jobPostingComments.setTitle("Accountant");
		jobPostingComments.setNumberOfPositions(1);
		jobPostingComments.setCurrency("USD");
		jobPostingComments.setBussinessUnitCode(1000);
		jobPostingComments.setSiteCode(200);
		jobPostingComments.setLocationCode(200);
		jobPostingComments.setStartDate("4/1/2016");
		jobPostingComments.setEndDate("4/30/2016");

		jobPostingComments.setOwnerUsername("HiringManager1");
		jobPostingComments.setCoordinatorUsername("HiringManager1");
		jobPostingComments.setDistributorUsername("HiringManager1");
		jobPostingComments.setCostCenterCode(1000);
		jobPostingComments.setJobSeekerRateCanBeIncreased(true);
		jobPostingComments.setHoursPerWeek(40);
		jobPostingComments.setHoursPerDay(8);
		jobPostingComments.setTimeSheetFrequency("Weekly");
		jobPostingComments.setMinStHr(1);
		jobPostingComments.setMaxStHr(100);

		jobPosting.setComments(jobPostingComments);

		return jobPosting;
	}
}
