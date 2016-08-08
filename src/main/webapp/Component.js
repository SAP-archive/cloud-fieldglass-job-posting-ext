sap.ui.define([
    "sap/ui/core/UIComponent",
    "sap/ui/model/json/JSONModel",
    "sap/ui/model/resource/ResourceModel"
], function (UIComponent, JSONModel, ResourceModel) {
	"use strict";
	return UIComponent.extend("sap.ui.fieldglass.jobposting.Component", {
		metadata : {
			rootView: "sap.ui.fieldglass.jobposting.view.App"
		},
		init : function () {
			UIComponent.prototype.init.apply(this, arguments);
			
			//set sample job posting model
			var oJobPostingSample = {
					transaction : true,
					approvalRequired : false,
					sendNotification : true,
					language : "English (United States)",
					numberFormat : "#,##9.99 (Example: 1,234,567.99)",
					dateFormat : "YYYY-MM-DD",
					comments : {
						creatorUsername : "HTCP@fg_help",
						templateName : "Accountant",
						buyerReference : null,
						title : "Accountant",
						numberOfPositions : 1,
						jobDescription : null,
						currency : "USD",
						bussinessUnitCode : 1000,
						siteCode : 200,
						locationCode : 200,
						startDate : "2016-06-04",
						endDate : "2016-06-10",
						ownerUsername : "HiringManager1",
						coordinatorUsername : "HiringManager1",
						distributorUsername : "HiringManager1",
						costCenterCode : 1000,
						estimatedExpensePercent : null,
						estimateAdditionalSpendPercent : null,
						jobSeekerRateCanBeIncreased : true,
						respondByDate : null,
						hoursPerWeek : 40,
						hoursPerDay : 8,
						travelTime : null,
						maximumSubmission : null,
						flatAdjustments : null,
						comments : null,
						address1 : null,
						address2 : null,
						city : null,
						stateOrProvince : null,
						country : null,
						postalCode : null,
						siteTax : null,
						suppliers : null,
						timeSheetFrequency : "Weekly",
						startDayOfWeek : null,
						minStHr : 1,
						maxStHr : 100
					}
			};
			var oModel = new JSONModel (oJobPostingSample);
			this.setModel(oModel);
		}
		});
});