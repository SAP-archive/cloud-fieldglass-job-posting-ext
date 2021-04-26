![](https://img.shields.io/badge/STATUS-NOT%20CURRENTLY%20MAINTAINED-red.svg?longCache=true&style=flat)

# Important Notice
This public repository is read-only and no longer maintained.

# Fieldglass Job Posting Sample

Prerequisites:
--------------

1.    Eclipse installed with SAP HANA Cloud Platform Tools plugins
2.    JDK 1.7 is available as an Installed JRE in *Windows->Preferences->Java->Installed JREs*
3.    SAP HANA Cloud Java Web Tomcat 7, SAP HANA Cloud Java EE 6 Web Profile or SAP HANA Cloud Platform as a runtime environment.
4.    [Configured destinations for connecting to the Fieldglass API](#configuring-destinations)

##What is it?

This is a sample showing how you can connect to the Fieldglass SOAP Service and publish job postings.

## How to run it?

Step 1: Clone the Git repository

Step 2: Import the project as a Maven project into your eclipse workspace. 

Step 3: Run Maven goal clean install 

Step 4: If you are deploying locally then see [Creating and Deleting Destinations Locally](https://help.hana.ondemand.com/help/frameset.htm?7fa92ffa007346f58491999361928303.html).<br>
If you are deploying on the Cloud see [Creating and Deleting Destinations on the Cloud](https://help.hana.ondemand.com/help/frameset.htm?94dddf7d9e56401ba1719b7e836d8ee9.html).

Step 5: Build and deploy your application. **Make sure you selected the SAP HANA Cloud Java Web Tomcat 7, SAP HANA Cloud Java EE 6 Web Profile or SAP HANA Cloud Platform as the runtime environment**


## <a name="configuring-destinations"></a> Configuring Destinations
Prior to running the project you must have the destination configured as described in the [SAP HANA Cloud Platform Destinations Documentation] (https://help.hana.ondemand.com/help/frameset.htm?e4f1d97cbb571014a247d10f9f9a685d.html)

The HTTP API Destination should look like this:


>Name=fieldglass-jobposting-api<br>
Type=HTTP<br>
URL=https\://train1.fgvms.com/ws2/services/Connector<br>
ProxyType=Internet<br>
TrustAll=true<br>
CloudConnectorVersion=2<br>
Authentication=BasicAuthentication<br>
User=<b>your-user-name</b><br>
Password=<b>your-password</b><br>
WebNamespaceURL=http\://webservice2.partner.insite

## Accessing the application
After deployment the application is accessible on the following URL:
`http(s)://host:port/fieldglass.jobposting`

You should see an application for creating job postings.

## Resources

* SAP HANA Cloud Documentation - https://help.hana.ondemand.com/

## Copyright and license

Copyright 2015 SAP AG

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
