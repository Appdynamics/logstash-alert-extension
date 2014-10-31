package com.appdynamics.extensions.logstash;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

public class EventArgs {

	public String[] getHealthRuleViolationEventWithOneEvalEntityAndTriggerNoBaseline() {
		List<String> strings = Lists.newArrayList();
		generateLeadingArgs(strings);

		strings.add("\"1\""); // number of eval entities
		strings.add("\"APPLICATION_COMPONENT_NODE\""); // eval entity type
		strings.add("\"MyMacMachineAgentNode1\""); // eval entity name
		strings.add("\"8\""); // eval entity id
		strings.add("\"1\""); // number of triggered cond per eval entity
		strings.add("\"APPLICATION_COMPONENT_NODE\""); // scope type 1
		strings.add("\"MyMacMachineAgentNode1\""); // scope name 1
		strings.add("\"8\""); // scope id 1
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition
																	// name 1
		strings.add("\"113\""); // condition id 1
		strings.add("\"GREATER_THAN\""); // operator 1
		strings.add("\"ABSOLUTE\""); // condition unit type 1
		strings.add("\"4\""); // threshhold value 1
		strings.add("\"40.0\""); // observed value 1

		generateTrailingArg(strings);

		return Iterables.toArray(strings, String.class);
	}

	public String[] getHealthRuleViolationEventWithMultipleEvalEntityAndATriggerNoBaseline() {
		List<String> strings = Lists.newArrayList();
		generateLeadingArgs(strings);
		strings.add("\"2\""); // number of eval entities
		strings.add("\"APPLICATION_COMPONENT_NODE\""); // eval entity type
		strings.add("\"MyMacMachineAgentNode1\""); // eval entity name
		strings.add("\"8\""); // eval entity id
		strings.add("\"1\""); // number of triggered cond per eval entity
		strings.add("\"APPLICATION_COMPONENT_NODE\""); // scope type 1
		strings.add("\"MyMacMachineAgentNode1\""); // scope name 1
		strings.add("\"8\""); // scope id 1
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition
																	// name 1
		strings.add("\"113\""); // condition id 1
		strings.add("\"GREATER_THAN\""); // operator 1
		strings.add("\"ABSOLUTE\""); // condition unit type 1
		strings.add("\"4\""); // threshhold value 1
		strings.add("\"40.0\""); // observed value 1

		strings.add("\"APPLICATION\""); // eval entity type
		strings.add("\"MyMacMachineAgentNode2\""); // eval entity name
		strings.add("\"9\""); // eval entity id
		strings.add("\"1\""); // number of triggered cond per eval entity
		strings.add("\"APPLICATION\""); // scope type 2
		strings.add("\"MyMacMachineAgentNode2\""); // scope name 2
		strings.add("\"10\""); // scope id 2
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition
																	// name 2
		strings.add("\"114\""); // condition id 2
		strings.add("\"LESS_THAN\""); // operator 2
		strings.add("\"ABSOLUTE\""); // condition unit type 2
		strings.add("\"5\""); // threshhold value 2
		strings.add("\"20.0\""); // observed value 2

		// summary message
		generateTrailingArg(strings);
		return Iterables.toArray(strings, String.class);
	}

	public String[] getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerNoBaseline() {
		List<String> strings = Lists.newArrayList();
		generateLeadingArgs(strings);
		strings.add("\"2\""); // number of eval entities

		strings.add("\"APPLICATION_COMPONENT_NODE\""); // eval entity type
		strings.add("\"MyMacMachineAgentNode1\""); // eval entity name
		strings.add("\"8\""); // eval entity id
		strings.add("\"2\""); // number of triggered cond per eval entity

		strings.add("\"APPLICATION_COMPONENT_NODE\""); // scope type 1
		strings.add("\"MyMacMachineAgentNode1\""); // scope name 1
		strings.add("\"8\""); // scope id 1
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition
																	// name 1
		strings.add("\"113\""); // condition id 1
		strings.add("\"GREATER_THAN\""); // operator 1
		strings.add("\"ABSOLUTE\""); // condition unit type 1
		strings.add("\"4\""); // threshhold value 1
		strings.add("\"40.0\""); // observed value 1

		strings.add("\"APPLICATION_COMPONENT\""); // scope type 2
		strings.add("\"MyMacMachineAgentNode1\""); // scope name 2
		strings.add("\"9\""); // scope id 2
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition
																	// name 2
		strings.add("\"114\""); // condition id 2
		strings.add("\"GREATER_THAN_EQUALS\""); // operator 2
		strings.add("\"ABSOLUTE\""); // condition unit type 2
		strings.add("\"2\""); // threshhold value 2
		strings.add("\"50.0\""); // observed value 2

		strings.add("\"APPLICATION\""); // eval entity type
		strings.add("\"MyMacMachineAgentNode2\""); // eval entity name
		strings.add("\"9\""); // eval entity id
		strings.add("\"1\""); // number of triggered cond per eval entity

		strings.add("\"APPLICATION\""); // scope type 1
		strings.add("\"MyMacMachineAgentNode2\""); // scope name 1
		strings.add("\"10\""); // scope id 1
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition
																	// name 1
		strings.add("\"114\""); // condition id 1
		strings.add("\"LESS_THAN\""); // operator 1
		strings.add("\"ABSOLUTE\""); // condition unit type 1
		strings.add("\"5\""); // threshhold value 1
		strings.add("\"20.0\""); // observed value 1

		// summary message
		generateTrailingArg(strings);
		return Iterables.toArray(strings, String.class);

	}

	public String[] getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerBaseline() {

		List<String> strings = Lists.newArrayList();
		generateLeadingArgs(strings);
		strings.add("\"2\""); // number of eval entities

		strings.add("\"APPLICATION_COMPONENT_NODE\""); // eval entity type
		strings.add("\"MyMacMachineAgentNode1\""); // eval entity name
		strings.add("\"8\""); // eval entity id
		strings.add("\"2\""); // number of triggered cond per eval entity

		strings.add("\"APPLICATION_COMPONENT_NODE\""); // scope type 1
		strings.add("\"MyMacMachineAgentNode1\""); // scope name 1
		strings.add("\"8\""); // scope id 1
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition
																	// name 1
		strings.add("\"113\""); // condition id 1
		strings.add("\"GREATER_THAN\""); // operator 1
		strings.add("\"BASELINE_STANDARD_DEVIATION\""); // condition unit type 1
		strings.add("\"false\""); // use default baseline
		strings.add("\"baseline name\""); // base line name
		strings.add("\"base line id\""); // base line id
		strings.add("\"4\""); // threshhold value 1
		strings.add("\"40.0\""); // observed value 1

		strings.add("\"APPLICATION_COMPONENT\""); // scope type 2
		strings.add("\"MyMacMachineAgentNode1\""); // scope name 2
		strings.add("\"9\""); // scope id 2
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition
																	// name 2
		strings.add("\"114\""); // condition id 2
		strings.add("\"GREATER_THAN_EQUALS\""); // operator 2
		strings.add("\"BASELINE_PERCENTAGE\""); // condition unit type 2
		strings.add("\"true\""); // use default baseline
		strings.add("\"2\""); // threshhold value 2
		strings.add("\"50.0\""); // observed value 2

		strings.add("\"APPLICATION\""); // eval entity type
		strings.add("\"MyMacMachineAgentNode2\""); // eval entity name
		strings.add("\"9\""); // eval entity id
		strings.add("\"1\""); // number of triggered cond per eval entity

		strings.add("\"APPLICATION\""); // scope type 1
		strings.add("\"MyMacMachineAgentNode2\""); // scope name 1
		strings.add("\"10\""); // scope id 1
		strings.add("\"Hardware Resources|CPU|%Busy Condition\""); // condition name 1
		strings.add("\"114\""); // condition id 1
		strings.add("\"LESS_THAN\""); // operator 1
		strings.add("\"ABSOLUTE\""); // condition unit type 1
		strings.add("\"5\""); // threshhold value 1
		strings.add("\"20.0\""); // observed value 1

		// summary message
		generateTrailingArg(strings);
		return Iterables.toArray(strings, String.class);
	}

	public String[] getOtherEvent() {
		List<String> strings = Lists.newArrayList();
		strings.add("\"MyMacMachineAgent\""); // appname
		strings.add("\"4\""); // appID
		strings.add("\"Wed Apr 30 09:42:55 PDT 2014\""); // event notification
															// time
		strings.add("\"1\""); // priority
		strings.add("\"ERROR\""); // severity
		strings.add("\"LogstashAction\""); // tag
		strings.add("\"App Server restart\""); // event notification name
		strings.add("\"24\""); // event notification id
		strings.add("\"2\""); // event notification time period in mins
		strings.add("\"2\""); // number of event types

		strings.add("\"ERROR\""); // event type 1
		strings.add("\"2\""); // event type num 1
		strings.add("\"AGENT_STATUS\""); // event type 1
		strings.add("\"1\""); // event type num 1

		strings.add("\"2\""); // num event summaries

		strings.add("\"I\""); // event summary id 1
		strings.add("\"Wed Apr 30 09:42:55 PDT 2014\""); // event summary time 1
		strings.add("APP_SERVER_RESTART"); // event summary type 1
		strings.add("ERROR"); // event summary severity 1
		strings.add("summary string"); // event summary string 1

		strings.add("\"II\""); // event summary id 2
		strings.add("\"Wed Apr 30 09:42:55 PDT 2014\""); // event summary time 2
		strings.add("DIAGNOSTIC_SESSION"); // event summary type 2
		strings.add("WARN"); // event summary severity 2
		strings.add("summary string"); // event summary string 2

		strings.add("\"http://PRINHYLTPHP0158:8090/controller/#location=APP_EVENT_VIEWER_MODAL&eventSummary=\"");
		return Iterables.toArray(strings, String.class);
	}

	private void generateTrailingArg(List<String> strings) {
		// summary message
		strings.add("\"CPU utilization is too high triggerded at Wed Apr 30 09:42:55 PDT 2014. This policy was violated because the following conditions were met for the MyMacMachineAgentNode1 Node for the last 1 minute(s):   For Evaluation Entity: MyMacMachineAgentNode1 Node - Hardware Resources|CPU|%Busy Condition is greater than 4. Observed value = 40.0\""); // observed
		strings.add("\"3\""); // incident id
		strings.add("\"http://WIN-OAR4D8QEG3K:8090/controller/#location=APP_INCIDENT_DETAIL&incident=\""); // deeplink url
		strings.add("\"POLICY_OPEN_CRITICAL\""); // event type
	}

	private void generateLeadingArgs(List<String> strings) {
		strings.add("\"MyMacMachineAgent\""); // appname
		strings.add("\"4\""); // appID
		strings.add("\"Wed Apr 30 09:42:55 PDT 2014\""); // pvn alert time
		strings.add("\"1\""); // priority
		strings.add("\"ERROR\""); // severity
		strings.add("\"LogstashAction\""); // tag
		strings.add("\"CPU utilization is too high\""); // health rule name
		strings.add("\"24\""); // health rule id
		strings.add("\"1\""); // pvn time period in min
		strings.add("\"APPLICATION_COMPONENT_NODE\""); // affected entity type
		strings.add("\"MyMacMachineAgentNode1\""); // affected entity name
		strings.add("\"8\""); // affected entity id
	}
}
