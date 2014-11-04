# AppDynamics Logstash - Alerting Extension

This alerting extension is only meant for on-premise controllers. 

##Use Case

The Logstash Alerting Extension enables AppDynamics to post custom notifications to Logstash in a JSON format. Logstash is an open source tool which is part of the ElasticSearch family, used for managing events and logs. It comes with different plugins for collecting, parsing and outputting logs. It also comes with a web interface for searching and drilling into your logs. For more info, see [http://logstash.net/](http://logstash.net/). 

### Prerequisites

 1. You have a running instance of logstash.
 
 2. You have configured your logstash to use the tcp input plugin in **server** mode, see [http://logstash.net/docs/1.4.2/inputs/tcp](http://logstash.net/docs/1.4.2/inputs/tcp). For examples:
 
	**Non-SSL TCP config:**
 
	```
	input {                                                                                                                                           
	 tcp {
	  host => "localhost"
	  mode => "server"
	  port => 3333
	  type => "AppDynamics Alert"
	 }
	}

	```
 
	**SSL TCP config with Self Signed Certificate:**
 
	```
	input {                                                                                                                                         
	 tcp {
	  host => "192.168.57.1"
	  mode => "server"
	  port => 3334
	  ssl_enable => true
	  ssl_key => "/my/path/to/ssl-key/privkey.pem"
	  ssl_cert => "/my/path/to/ssl-cert/newcert.pem"
	  ssl_key_passphrase => "nopassphrase"
	  type => "AppDynamics Alert"
	 }
	}

	```

##Installation Steps

 1. Run "mvn clean install".

 2. Find the zip file at 'target/logstash-alert-extension-\<version\>.zip'.

 3. Unzip the zip file into <CONTROLLER_HOME_DIR>/custom/actions/. You should have  <CONTROLLER_HOME_DIR>/custom/actions/logstash-alert created.

 4. Check if you have custom.xml file in <CONTROLLER_HOME_DIR>/custom/actions/ directory. If yes, add the following xml to the <custom-actions> element.
 
	```
      <action>
    		  <type>logstash-alert</type>
          <!-- For Linux/Unix *.sh -->
     		  <executable>logstash-alert.sh</executable>
          <!-- For windows *.bat -->
     		  <!--<executable>logstash-alert.bat</executable>-->
      </action>

	```
     
	If you don't have custom.xml already, create one with the below xml content
    

	```
      <custom-actions>
          <action>
      		  <type>logstash-alert</type>
            <!-- For Linux/Unix *.sh -->
       		  <executable>logstash-alert.sh</executable>
            <!-- For windows *.bat -->
       		  <!--<executable>logstash-alert.bat</executable>-->
     	    </action>
        </custom-actions>
        
	```

	Uncomment the appropriate executable tag based on windows or linux/unix machine.
    
5. Update the config.yaml file in <CONTROLLER_HOME_DIR>/custom/actions/logstash-alert/conf/.

###Configuration

####1. config.yml
**Note: Please make sure to not use tab (\t) while editing yaml files. You may want to validate the yaml file using a [yaml validator](http://yamllint.com/)**.

| Param | Description |
| ----- | ----- |
| host | The TCP hostname, i.e. host value specified in logstash's tcp config|
| port | The TCP port number, i.e. port value specified in logstash's tcp config|
| showEvaluationDetails | If set to true, the message posted will contain the evaluation details that triggered the alert|
| ssl | This section includes all SSL related config|
| enable | If set to true, SSL connection is used - must provide all other SSL info below |
| allowSelfSignedCert | If set to true, Self Signed Certificate used by Logstash is accepted without the need to import the certificate in your truststore (no need to provide any details about keystore and truststore). |
| keystorePath | The keystore's path (absolute path and relative path from logstash-alert extension install dir are accepted) |
| keystorePassword | The keystore password |
| keystorePath | The truststore's path (absolute path and relative path from logstash-alert extension install dir are accepted) |
| truststorePassword | The truststore password |

Below is an example config using SSL:

```                                                                                                       
host: localhost

port: 3334

showEvaluationDetails: true

ssl:
  enable: true
  allowSelfSignedCert: false
  keystorePath: "conf/client_cert/keystore.jks" #example of relative path
  keystorePassword: "password"
  truststorePath: "/home/fsarmiento/AppDynamics/Controller/custom/actions/logstash-alert/conf/client_cert/keystore.jks" #example of absolute path
  truststorePassword: "password"

```

####2. Creating Custom Actions:

1. To create a Custom Action, first refer to the the following topics (requires login):

	* [Creating custom action](http://docs.appdynamics.com/display/PRO14S/Custom+Actions)
	
    * [Build an Alerting Extension](http://docs.appdynamics.com/display/PRO14S/Build+an+Alerting+Extension)
    
2. Now you are ready to use this extension as a custom action. In the AppDynamics UI, go to Alert & Respond -> Actions. Click Create Action. Select Custom Action and click OK. In the drop-down menu you can find the action called 'logstash-alert'.

3. You can now check the newly created event in Logstash web interface for example.

##Contributing

Find out more in the [AppDynamics Exchange](http://community.appdynamics.com/t5/AppDynamics-eXchange/idb-p/extensions)

##Support

For any questions or feature request, please contact [AppDynamics Center of Excellence](mailto:ace-request@appdynamics.com).

**Version:** 1.0.0
**Controller Compatibility:** 3.7+
