package com.appdynamics.extensions.logstash.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AlertHeatlhRuleVioEventDetails extends AlertDetails {
	
	private static final String HR_EVENT_ALERT_TYPE = "Health Rule Violation Event";

	@JsonProperty("Policy Violation Alert Time")
	private String policyViolationAlertTime;

	@JsonProperty("Violated Health Rule Name")
	private String healthRuleName;

	@JsonProperty("Violated Health Rule ID")
	private String healthRuleID;

	@JsonProperty("Policy Violation Time Period in Mins")
	private String policyViolationTimePeriodInMinutes;

	@JsonProperty("Affected Entity Type")
	private String affectedEntityType;

	@JsonProperty("Affected Entity Name")
	private String affectedEntityName;

	@JsonProperty("Affected Entity ID")
	private String affectedEntityID;

	@JsonProperty("Incident ID")
	private String incidentId;

	@JsonProperty("Event Type")
	private String eventType;

	@JsonProperty("Summary Message")
	private String summaryMessage;

	@JsonProperty("Incident URL")
	private String incidentUrl;

	@JsonProperty("Evaluation Entities")
	private List<AlertEvaluationEntity> evaluationEntities = new ArrayList<AlertEvaluationEntity>();

	public String getPolicyViolationAlertTime() {
		return policyViolationAlertTime;
	}

	public void setPolicyViolationAlertTime(String policyViolationAlertTime) {
		this.policyViolationAlertTime = policyViolationAlertTime;
	}

	public String getHealthRuleName() {
		return healthRuleName;
	}

	public void setHealthRuleName(String healthRuleName) {
		this.healthRuleName = healthRuleName;
	}

	public String getHealthRuleID() {
		return healthRuleID;
	}

	public void setHealthRuleID(String healthRuleID) {
		this.healthRuleID = healthRuleID;
	}

	public String getPolicyViolationTimePeriodInMinutes() {
		return policyViolationTimePeriodInMinutes;
	}

	public void setPolicyViolationTimePeriodInMinutes(
			String policyViolationTimePeriodInMinutes) {
		this.policyViolationTimePeriodInMinutes = policyViolationTimePeriodInMinutes;
	}

	public String getAffectedEntityType() {
		return affectedEntityType;
	}

	public void setAffectedEntityType(String affectedEntityType) {
		this.affectedEntityType = affectedEntityType;
	}

	public String getAffectedEntityName() {
		return affectedEntityName;
	}

	public void setAffectedEntityName(String affectedEntityName) {
		this.affectedEntityName = affectedEntityName;
	}

	public String getAffectedEntityID() {
		return affectedEntityID;
	}

	public void setAffectedEntityID(String affectedEntityID) {
		this.affectedEntityID = affectedEntityID;
	}

	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String incidentId) {
		this.incidentId = incidentId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getSummaryMessage() {
		return summaryMessage;
	}

	public void setSummaryMessage(String summaryMessage) {
		this.summaryMessage = summaryMessage;
	}

	public String getIncidentUrl() {
		return incidentUrl;
	}

	public void setIncidentUrl(String incidentUrl) {
		this.incidentUrl = incidentUrl;
	}

	public List<AlertEvaluationEntity> getEvaluationEntities() {
		return evaluationEntities;
	}

	public void setEvaluationEntities(
			List<AlertEvaluationEntity> evaluationEntities) {
		this.evaluationEntities = evaluationEntities;
	}

	@Override
	public String getAlertType() {
		return HR_EVENT_ALERT_TYPE;
	}

}
