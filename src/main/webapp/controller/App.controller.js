sap.ui.define([
	"jquery.sap.global",
	"sap/ui/core/mvc/Controller",
	"sap/ui/model/json/JSONModel",
	"sap/m/MessageToast",
	"sap/m/MessageBox",
	"sap/ui/core/ValueState"
], function($, Controller, JSONModel, MessageToast, MessageBox, ValueState) {
	"use strict";
 
	return Controller.extend("sap.ui.fieldglass.jobposting.controller.App", {
		
		onInit: function () {
			sap.ui.getCore().getConfiguration().setLanguage("en_US");
			
			this._wizard = this.getView().byId("CreateJobPostingWizard");
			this._oNavContainer = this.getView().byId("wizardNavContainer");
			this._oWizardContentPage = this.getView().byId("wizardContentPage");
			this._oWizardReviewPage = sap.ui.xmlfragment("sap.ui.fieldglass.jobposting.view.ReviewPage", this);
			this._oSuccessPage = sap.ui.xmlfragment("sap.ui.fieldglass.jobposting.view.SuccessPage", this);
			this._oNavContainer.addPage(this._oWizardReviewPage);
			this._oNavContainer.addPage(this._oSuccessPage);
			
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
						startDate : "2016-06-12",
						endDate : "2016-06-20",
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
			}
			
			this.sample = oJobPostingSample;
			this.model = new JSONModel();
			this.model.setData($.extend(true, {}, this.sample));
			this.getView().setModel(this.model);
			
		},
		
		_submitForm : function (oEvent){
			var jobPostingJson = this.model.getJSON();
			
			var that = this;
			$.post(
	  				"post",
	  				jobPostingJson
	  			)
	  			.success(function(a,b,c){
	  				that.successHandler();
	  			})
	  			.fail(function(oResponse){
	  				var bCompact = !!that.getView().$().closest(".sapUiSizeCompact").length;
	  				var errorMessage = oResponse.responseText;
	  				MessageBox.error(
		  					errorMessage,
		  					{
		  						styleClass: bCompact? "sapUiSizeCompact" : ""
		  					}
		  				);
	  			});
		},
		
		reloadAll : function () {
			this.model = new JSONModel(this.sample);
			this.getView().setModel(this.model);
			this._handleNavigationToStep(0);
			this._wizard.discardProgress(this._wizard.getSteps()[0]);
		},
		
		wizardCompleteHandler : function () {
			this._oNavContainer.to(this._oWizardReviewPage);
		}, 
		
		backToWizardContent : function () {
			this._oNavContainer.backToPage(this._oWizardContentPage.getId());
		},
		
		successHandler : function () {
			this._oNavContainer.to(this._oSuccessPage);
		},
		
		editStepOne : function () {
			this._handleNavigationToStep(0);
		},
		editStepTwo : function () {
			this._handleNavigationToStep(1);
		},
		editStepThree : function () {
			this._handleNavigationToStep(2);
		},
		editStepFour : function () {
			this._handleNavigationToStep(3);
		},
		
		handleWizardCancel : function () {
			this._handleCancelMessageBoxOpen("Are you sure you want to cancel your job posting?", "warning");
		},
		handleWizardSubmit : function () {
			this._handleSubmitMessageBoxOpen("Are you sure you want to submit your job posting?", "confirm");
		},
		
		_handleSubmitMessageBoxOpen : function(sMessage, sMessageBoxType) {
			var that = this;
			MessageBox[sMessageBoxType](sMessage, {
				actions: [MessageBox.Action.YES, MessageBox.Action.NO],
				initialFocus: MessageBox.Action.YES,
				onClose: function (oAction) {
					if (oAction === MessageBox.Action.YES) {
						var invalidStep = that.findInvalidStep();
						if(isNaN(invalidStep)){
							that._submitForm();
						} else {
							that._handleNavigationToStep(invalidStep);
						}
						
					}
				},
			});
		},
		
		_handleCancelMessageBoxOpen : function (sMessage, sMessageBoxType) {
			var that = this;
			MessageBox[sMessageBoxType](sMessage, {
				actions: [MessageBox.Action.YES, MessageBox.Action.NO],
				initialFocus: MessageBox.Action.NO,
				onClose: function (oAction) {
					if (oAction === MessageBox.Action.YES) {
						that.reloadAll();
					}
				},
			});
		},
		
		_handleNavigationToStep : function (iStepNumber) {
			var that = this;
			function fnAfterNavigate () {
				that._wizard.goToStep(that._wizard.getSteps()[iStepNumber]);
				that._oNavContainer.detachAfterNavigate(fnAfterNavigate);
			}
 
			this._oNavContainer.attachAfterNavigate(fnAfterNavigate);
			this.backToWizardContent();
		},
		
		findInvalidStep : function() {
			var steps = this._wizard.getSteps();
			for (var i=0; i < steps.length; i++){
				if(!steps[i].getValidated()){
					return i;
				}
			}
		},
		
		//Triger validation of fields in first step
		firstStepActivate : function () {
			this.validateLanguageInput();
			this.validateNumberFormat();
		},
		
		updateFirstStepState : function () {
			var step = this.getView().byId("jobPostingFirstStep");
			var bLanguageIsValid = this.getView().byId("language").getValueState() != ValueState.Error;
			var bNumberFormatIsValid = this.getView().byId("numberFormat").getValueState() != ValueState.Error;
			
			bLanguageIsValid && bNumberFormatIsValid ? this._wizard.validateStep(step) :
				this._wizard.invalidateStep(step);
		},
		
		validateLanguageInput : function () {
			var oLanguageInput = this.getView().byId("language");
			var sLanguage = oLanguageInput.getValue();
			/^[a-zA-Z\s\(\)]+$/.test(sLanguage) ? oLanguageInput.setValueState(ValueState.None) :
				oLanguageInput.setValueState(ValueState.Error);
			this.updateFirstStepState();
		},
		
		validateNumberFormat : function (){
			var oNumberFormatInput = this.getView().byId("numberFormat");
			var sNumberFormat = oNumberFormatInput.getValue();
			sNumberFormat.indexOf("Example") > -1 ? oNumberFormatInput.setValueState(ValueState.None) :
				oNumberFormatInput.setValueState(ValueState.Error);
			this.updateFirstStepState();
		},
		
		//Trigger validation of mendatory fields in third step
		thirdStepActivate : function() {
			
			var oTitle = this.getView().byId("title");
			oTitle.setValue(oTitle.getValue());
			
			var oNumberOfPositions = this.getView().byId("numberOfPositions");
			oNumberOfPositions.setValue(oNumberOfPositions.getValue());
			
			var oHoursPerWeek = this.getView().byId("hoursPerWeek");
			oHoursPerWeek.setValue(oHoursPerWeek.getValue());
			
			var oHoursPerDay = this.getView().byId("hoursPerDay");
			oHoursPerDay.setValue(oHoursPerDay.getValue());
		},
		
		updateThirdStepState : function(){
			var step = this.getView().byId("jobPostingThirdStep");
			var bTitleIsValid= this.getView().byId("title").getValueState() != ValueState.Error;
			var bNumberOfPositionIsValid = this.getView().byId("numberOfPositions").getValueState() != ValueState.Error;
			var bHoursPerWeekIsValid = this.getView().byId("hoursPerWeek").getValueState() != ValueState.Error;
			var bHoursPerDayIsValid = this.getView().byId("hoursPerDay").getValueState() != ValueState.Error;
			var bPeriodIsValid = this.getView().byId("startDate").getValueState() != ValueState.Error;
			
			bTitleIsValid && bNumberOfPositionIsValid && bHoursPerWeekIsValid && bHoursPerDayIsValid && bPeriodIsValid ? 
				this._wizard.validateStep(step) : this._wizard.invalidateStep(step);
					
		},
		
		validateThirdStepNumber : function(oEvent) {
			this.validateNumber(oEvent);
			this.updateThirdStepState();
		},
		
		validateNumber : function(oEvent){
			var oField = oEvent.getSource();
			var value = oField.getValue();
			isNaN(value) || value.length == 0 ? oField.setValueState(ValueState.Error) : oField.setValueState(ValueState.None);
		},
		
		validateTitle : function(oEvent){
			var oField = oEvent.getSource();
			var value = oField.getValue();
			/^[a-zA-Z]+$/.test(value) ? oField.setValueState(ValueState.None) : oField.setValueState(ValueState.Error);
			
			this.updateThirdStepState();
		},
		
		validatePeriod : function () {
			var oStartDateInput = this.getView().byId("startDate");
			var sStartDate = oStartDateInput.getValue();
			
			var oEndDateInput = this.getView().byId("endDate");
			var sEndDate = oEndDateInput.getValue();
			if (sStartDate <= sEndDate){
				oStartDateInput.setValueState(ValueState.None);
				oEndDateInput.setValueState(ValueState.None);
			} else {
				oStartDateInput.setValueState(ValueState.Error);
				oEndDateInput.setValueState(ValueState.Error);
			}
			this.updateThirdStepState();
		},
		
		updateLastStepState : function(){
			var step = this.getView().byId("jobPostingLastStep");
			var bMinStHrIsValid = this.getView().byId("minStHr").getValueState() != ValueState.Error;
			var bMaxStHrIsValid = this.getView().byId("maxStHr").getValueState() != ValueState.Error;
			
			bMinStHrIsValid && bMaxStHrIsValid ? this._wizard.validateStep(step) : this._wizard.invalidateStep(step);
					
		},
		
		validateStHr : function () {
			var oMinStHr = this.getView().byId("minStHr");
			var iMinStHr = parseInt(oMinStHr.getValue());
			
			var oMaxStHr = this.getView().byId("maxStHr");
			var iMaxStHr = parseInt(oMaxStHr.getValue());
			
			if (iMinStHr <= iMaxStHr){
				oMinStHr.setValueState(ValueState.None);
				oMaxStHr.setValueState(ValueState.None);
			} else {
				oMinStHr.setValueState(ValueState.Error);
				oMaxStHr.setValueState(ValueState.Error);
			}
			
			this.updateLastStepState();
		}
	});
});