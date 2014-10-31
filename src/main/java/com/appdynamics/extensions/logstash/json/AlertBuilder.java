package com.appdynamics.extensions.logstash.json;

import com.appdynamics.extensions.alerts.customevents.EvaluationEntity;
import com.appdynamics.extensions.alerts.customevents.Event;
import com.appdynamics.extensions.alerts.customevents.EventSummary;
import com.appdynamics.extensions.alerts.customevents.EventType;
import com.appdynamics.extensions.alerts.customevents.HealthRuleViolationEvent;
import com.appdynamics.extensions.alerts.customevents.OtherEvent;
import com.appdynamics.extensions.alerts.customevents.TriggerCondition;
import com.appdynamics.extensions.logstash.exception.NoEventReceivedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlertBuilder {

	public Alert buildAlert(Event event, boolean showEvaluationDetails) {
		if (event != null) {
			Alert alert = new Alert();
			setSeverity(event.getSeverity(), event);

			if (event instanceof HealthRuleViolationEvent) {
				alert.setDetails(getSummary((HealthRuleViolationEvent) event, showEvaluationDetails));

			} else {
				alert.setDetails(getSummary((OtherEvent) event, showEvaluationDetails));
			}

			return alert;
		}
		
		throw new NoEventReceivedException("No event to process");

	}

	private AlertDetails getSummary(HealthRuleViolationEvent violationEvent, boolean showEvaluationDetails) {
		AlertHeatlhRuleVioEventDetails details = new AlertHeatlhRuleVioEventDetails();
		details.setAffectedEntityID(violationEvent.getAffectedEntityID());
		details.setAffectedEntityName(violationEvent.getAffectedEntityName());
		details.setAffectedEntityType(violationEvent.getAffectedEntityType());
		details.setApplicationId(violationEvent.getAppID());
		details.setApplicationName(violationEvent.getAppName());
		details.setEventType(violationEvent.getEventType());
		details.setHealthRuleID(violationEvent.getHealthRuleID());
		details.setHealthRuleName(violationEvent.getHealthRuleName());
		details.setIncidentId(violationEvent.getIncidentID());
		details.setIncidentUrl(violationEvent.getIncidentUrl());
		details.setPolicyViolationAlertTime(violationEvent.getPvnAlertTime());
		details.setPolicyViolationTimePeriodInMinutes(violationEvent
				.getPvnTimePeriodInMinutes());
		details.setPriority(violationEvent.getPriority());
		details.setSeverity(violationEvent.getSeverity());
		details.setSummaryMessage(violationEvent.getSummaryMessage());
		details.setTag(violationEvent.getTag());

		if (showEvaluationDetails) {
			for (EvaluationEntity eval : violationEvent.getEvaluationEntity()) {
				AlertEvaluationEntity alertEval = buildAlertEvalutionEntity(eval);
				details.getEvaluationEntities().add(alertEval);
			}
		}

		return details;
	}

	private AlertDetails getSummary(OtherEvent otherEvent, boolean showEvaluationDetails) {
		AlertOtherEventDetails details = new AlertOtherEventDetails();
		details.setApplicationId(otherEvent.getAppID());
		details.setApplicationName(otherEvent.getAppName());
		details.setEventNotificationId(otherEvent.getEventNotificationId());
		details.setEventNotificationIntervalInMins(otherEvent
				.getEventNotificationIntervalInMin());
		details.setEventNotificationName(otherEvent.getEventNotificationName());
		details.setEventNotificationTime(otherEvent.getEventNotificationName());
		details.setPriority(otherEvent.getPriority());
		details.setSeverity(otherEvent.getSeverity());
		details.setTag(otherEvent.getTag());

		for (EventType eventType : otherEvent.getEventTypes()) {
			AlertEventType alertEventType = new AlertEventType();
			alertEventType.setEventType(eventType.getEventType());
			alertEventType.setEventTypeNum(eventType.getEventTypeNum());
			details.getEventTypes().add(alertEventType);
		}

		if (showEvaluationDetails) {
			for (EventSummary eventSummary : otherEvent.getEventSummaries()) {
				AlertEventSummary alertSummary = new AlertEventSummary();
				alertSummary.setEventSummaryDeepLinkUrl(otherEvent.getDeepLinkUrl() + alertSummary.getEventSummaryId());
				alertSummary.setEventSummaryId(eventSummary.getEventSummaryId());
				alertSummary.setEventSummarySeverity(eventSummary.getEventSummarySeverity());
				alertSummary.setEventSummaryString(eventSummary.getEventSummaryString());
				alertSummary.setEventSummaryTime(eventSummary.getEventSummaryTime());
				alertSummary.setEventSummaryType(eventSummary.getEventSummaryType());
				details.getEventSummaries().add(alertSummary);
			}
		}

		return details;
	}

	private AlertEvaluationEntity buildAlertEvalutionEntity(EvaluationEntity eval) {
		AlertEvaluationEntity alertEval = new AlertEvaluationEntity();
		alertEval.setId(eval.getId());
		alertEval.setName(eval.getName());
		alertEval.setNumberOfTriggeredConditions(eval
				.getNumberOfTriggeredConditions());
		alertEval.setType(eval.getType());

		for (TriggerCondition tc : eval.getTriggeredConditions()) {
			AlertTriggeredCondition alertTrigger = buildAlertTriggeredConditions(tc);
			alertEval.getTriggeredConditions().add(alertTrigger);
		}

		return alertEval;
	}

	private AlertTriggeredCondition buildAlertTriggeredConditions(TriggerCondition tc) {
		AlertTriggeredCondition alertTrigger = new AlertTriggeredCondition();
		alertTrigger.setBaselineId(tc.getBaselineId());
		alertTrigger.setBaselineName(tc.getBaselineName());
		alertTrigger.setConditionId(tc.getConditionId());
		alertTrigger.setConditionName(tc.getConditionName());
		alertTrigger.setConditionUnitType(tc.getConditionUnitType());
		alertTrigger.setObservedValue(tc.getObservedValue());
		alertTrigger.setOperator(tc.getOperator());
		alertTrigger.setScopeId(tc.getScopeId());
		alertTrigger.setScopeName(tc.getScopeName());
		alertTrigger.setScopeType(tc.getScopeType());
		alertTrigger.setThresholdValue(tc.getThresholdValue());
		alertTrigger.setUseDefaultBaseline(tc.isUseDefaultBaseline());
		return alertTrigger;
	}

	private void setSeverity(String severity, Event event) {
		if (severity.equalsIgnoreCase("WARN")) {
			event.setSeverity("WARNING");
		} else if (severity.equalsIgnoreCase("INFO")) {
			event.setSeverity("INFORMATION");
		}
	}

	public String convertIntoJsonString(Alert alert)
			throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(alert);
	}

}
