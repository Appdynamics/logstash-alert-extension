package com.appdynamics.extensions.logstash;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.any;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.mockito.PowerMockito.doThrow;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.appdynamics.extensions.logstash.config.ConfigUtil;
import com.appdynamics.extensions.logstash.config.Configuration;
import com.appdynamics.extensions.logstash.exception.NoEventReceivedException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LogstashAlertExtension.class})
@PowerMockIgnore({"org.apache.*", "javax.xml.*"})
public class LogstashAlertExtensionTest {
	
	@Mock
	private Socket mockSocket;
	
	@Mock
	private DataOutputStream mockDataOutputStream;
	
	private EventArgs eventArgs = new EventArgs();
    private ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();
	private LogstashAlertExtension classUnderTest;
	
	@Test(expected=NoEventReceivedException.class)
	public void testNoEventToProcessResultsInException() throws Exception {
		Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml").getFile(), Configuration.class);
		classUnderTest = new LogstashAlertExtension(configuration);
		classUnderTest.processAnEvent(new String[] {});
	}
	
	@Test(expected=ConnectException.class)
	public void testTcpConnectionRefusedResultsInException() throws Exception {
		whenNew(Socket.class).withArguments(anyString(), anyInt()).thenThrow(new ConnectException("Connection Refused"));
		Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml").getFile(), Configuration.class);
		classUnderTest = spy(new LogstashAlertExtension(configuration));
		classUnderTest.processAnEvent(eventArgs.getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerBaseline());
	}
	
	@Test(expected=IOException.class)
	public void testIOExceptionOccurredWhenSendingMessage() throws Exception {
		whenNew(Socket.class).withArguments(anyString(), anyInt()).thenReturn(mockSocket);
		whenNew(DataOutputStream.class).withArguments(any(OutputStream.class)).thenReturn(mockDataOutputStream);
		doThrow(new IOException()).when(mockDataOutputStream).writeBytes(anyString());
		
		Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml").getFile(), Configuration.class);
		classUnderTest = spy(new LogstashAlertExtension(configuration));
		classUnderTest.processAnEvent(eventArgs.getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerBaseline());
	}
	
	@Test
	public void testSendingHRViolationEventWithMultipleEntityAndTriggerMultipleBaseline() throws Exception {
		whenNew(Socket.class).withArguments(anyString(), anyInt()).thenReturn(mockSocket);
		whenNew(DataOutputStream.class).withArguments(any(OutputStream.class)).thenReturn(mockDataOutputStream);
		
		Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml").getFile(),Configuration.class);
		classUnderTest = new LogstashAlertExtension(configuration);
		classUnderTest.processAnEvent(eventArgs.getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerBaseline());
	}
	
	@Test
	public void testSendingOtherEvent() throws Exception {
		whenNew(Socket.class).withArguments(anyString(), anyInt()).thenReturn(mockSocket);
		whenNew(DataOutputStream.class).withArguments(any(OutputStream.class)).thenReturn(mockDataOutputStream);
		
		Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml").getFile(),Configuration.class);
		classUnderTest = new LogstashAlertExtension(configuration);
		classUnderTest.processAnEvent(eventArgs.getOtherEvent());
	}
	
	@Test
	public void testSendingHRViolationEventWithMultipleEvalEntityAndTriggerMultipleBaselineNoDetails() throws Exception {
		whenNew(Socket.class).withArguments(anyString(), anyInt()).thenReturn(mockSocket);
		whenNew(DataOutputStream.class).withArguments(any(OutputStream.class)).thenReturn(mockDataOutputStream);
		
		Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml.noEvalDetails").getFile(),Configuration.class);
		classUnderTest = new LogstashAlertExtension(configuration);
		classUnderTest.processAnEvent(eventArgs.getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerBaseline());
	}
	
	@Test
	public void testSendingOtherEventWithNoDetails() throws Exception {
		whenNew(Socket.class).withArguments(anyString(), anyInt()).thenReturn(mockSocket);
		whenNew(DataOutputStream.class).withArguments(any(OutputStream.class)).thenReturn(mockDataOutputStream);
		
		Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.yaml.noEvalDetails").getFile(),Configuration.class);
		classUnderTest = new LogstashAlertExtension(configuration);
		classUnderTest.processAnEvent(eventArgs.getOtherEvent());
	}

}
