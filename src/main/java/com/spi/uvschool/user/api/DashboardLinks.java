/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.user.api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spi.uvschool.domain.Role;

/**
 *
 * @author:
 */
@XmlRootElement
public class DashboardLinks {

	private static final Logger LOG = LoggerFactory.getLogger(DashboardLinks.class);

	private List<String> iconsList;

	private List<String> linksList;

	private List<String> linksNameList;

	private List<String> linksClassList;

	public DashboardLinks() {
	}

	public DashboardLinks(Role role, boolean deviceUserAgent) {

		if (role.equals(Role.authenticated)) {
			buildUserAccessList();
		} else if (role.equals(Role.teacher)) {
			buildTeacherAccessList();
		} else if (role.equals(Role.administrator)) {
			buildAdministratorAccessList(deviceUserAgent);
		} else if (role.equals(Role.individual)) {
			buildIndividualAccessList();
		} else if (role.equals(Role.transporter)) {
			buildTransporterAccessList();
		} else if (role.equals(Role.superAdmin)) {
			buildSuperAdminAccessList();
		}

	}

	public DashboardLinks(String role, boolean deviceUserAgent) {

		if (role.equals("authenticated")) {
			buildUserAccessList();
		} else if (role.equals("teacher")) {
			buildTeacherAccessList();
		} else if (role.equals("administrator")) {
			buildAdministratorAccessList(deviceUserAgent);
		} else if (role.equals("individual")) {
			buildIndividualAccessList();
		} else if (role.equals("superAdmin")) {
			buildSuperAdminAccessList();
		} else if (role.equals("transporter")) {
			buildTransporterAccessList();
		}

	}

	private void buildUserAccessList() {

		iconsList = new ArrayList<String>();
		linksList = new ArrayList<String>();
		linksNameList = new ArrayList<String>();
		linksClassList = new ArrayList<String>();

		iconsList.add("img/avatar5.png");
		linksList.add("#student.html");
		linksNameList.add("Student Details");
		linksClassList.add("ion-android-contacts");
		
		iconsList.add("icons/bus.png");
		linksList.add("#busLocation.html");
		linksNameList.add("Bus Location");
		linksClassList.add("ion-android-bus");
		
		iconsList.add("icons/holiday.jpg");
		linksList.add("#schoolHolidaysNew.html");
		linksNameList.add("School Holidays");
		linksClassList.add("ion-university");
		
		iconsList.add("icons/School_Icon_128.png");
	    linksList.add("#schoolInfo.html");
	    linksNameList.add("School Details");
	    linksClassList.add("ion-university");
	    
	    iconsList.add("icons/SchoolBusInformation.jpg");
	    linksList.add("#busInformation.html");
	    linksNameList.add("Bus Details");
	    linksClassList.add("ion-university");
	    
	    iconsList.add("icons/Setting_Icon_128.png");
		linksList.add("#settings.html");
		linksNameList.add("Notification Details");
		linksClassList.add("ion-gear-a");
		
	    iconsList.add("icons/makeRequest_icon.png");
		linksList.add("#makeRequest.html");
		linksNameList.add("Make Request");
		linksClassList.add("ion-university");
		
		iconsList.add("icons/busstop.png");
		linksList.add("#busStop.html");
		linksNameList.add("Bus Stop");
		linksClassList.add("ion-university");
		
		iconsList.add("icons/feedback.png");
		linksList.add("#complaintMessage.html");
		linksNameList.add("Feedback");
		linksClassList.add("ion-compose");
	    
		iconsList.add("icons/spi_change_password.png");
		linksList.add("#changePassword.html");
		linksNameList.add("Change Password");
		linksClassList.add("ion-android-bus");
	
		iconsList.add("icons/aboutUs.png");
		linksList.add("#aboutUs.html");
		linksNameList.add("About Us");
		linksClassList.add("ion-university");

	}

	private void buildTeacherAccessList() {
		LOG.debug("************Building Teacher Dashboard***********");
		iconsList = new ArrayList<String>();
		linksList = new ArrayList<String>();
		linksNameList = new ArrayList<String>();
		linksClassList = new ArrayList<String>();

		iconsList.add("img/avatar5.png");
		linksList.add("#teacherView.html");
		linksNameList.add("Attendance");
		linksClassList.add("ion-android-contacts");

		iconsList.add("icons/att_report_icon.png");
		linksList.add("#studentReport.html");
		linksNameList.add("Attendance Report");
		linksClassList.add("ion-ios-monitor-outline");

		// iconsList.add("icons/bus.png");
		// linksList.add("#busLocation.html");
		// linksNameList.add("Bus Location");
		// linksClassList.add("ion-university");

		iconsList.add("icons/School_Icon_128.png");
		linksList.add("#schoolInfo.html");
		linksNameList.add("School Information");
		linksClassList.add("ion-gear-a");

		// iconsList.add("icons/Setting_Icon_128.png");
		// linksList.add("#settings.html");
		// linksNameList.add("Settings");
		// linksClassList.add("ion-compose");

		// iconsList.add("icons/feedback.png");
		// linksList.add("#broadcastMessage.html");
		// linksNameList.add("Broadcast Message");
		// linksClassList.add("ion-compose");

		iconsList.add("icons/spi_change_password.png");
		linksList.add("#changePassword.html");
		linksNameList.add("Change Password");
		linksClassList.add("ion-university");

		iconsList.add("icons/aboutUs.png");
		linksList.add("#aboutUs.html");
		linksNameList.add("About Us");
		linksClassList.add("ion-university");
	}

	private void buildAdministratorAccessList(boolean deviceUserAgent) {
		LOG.debug("************Building Administrator Dashboard***********");
		iconsList = new ArrayList<String>();
		linksList = new ArrayList<String>();
		linksNameList = new ArrayList<String>();
		linksClassList = new ArrayList<String>();

		// iconsList.add("icons/bus-schedule.png");
		// linksList.add("#busSchedule.html");
		// linksNameList.add("Schedule");
		// linksClassList.add("ion-ios-calendar-outline");

		// iconsList.add("icons/tracko.png");
		// linksList.add("http://52.37.103.196:8082");
		// linksNameList.add("Vehicle Track");
		// linksClassList.add("ion-person-stalker");

		iconsList.add("icons/mapDashboard.png");
		linksList.add("#myBus.html");
		linksNameList.add("Map Dashboard");
		linksClassList.add("ion-person-stalker");

		iconsList.add("icons/att_report_icon.png");
		linksList.add("#studentReport.html");
		linksNameList.add("Attendance Report");
		linksClassList.add("ion-ios-monitor-outline");

		iconsList.add("icons/sms-icon.png");
		linksList.add("#SendSms.html");
		linksNameList.add("Send Message");
		linksClassList.add("ion-ios-monitor-outline");

		iconsList.add("icons/email.png");
		linksList.add("#SendEmail.html");
		linksNameList.add("Send Email");
		linksClassList.add("ion-ios-monitor-outline");

		if (!deviceUserAgent) {

			iconsList.add("icons/map.png");
			linksList.add("#manageRoutes.html");
			linksNameList.add("Manage Routes");
			linksClassList.add("ion-map");

			iconsList.add("icons/bus-gps.jpg");
			linksList.add("#manageBus.html");
			linksNameList.add("Manage Bus");
			linksClassList.add("ion-android-bus");

			iconsList.add("icons/School_Icon_128.png");
			linksList.add("#manageSchool.html");
			linksNameList.add("Schools");
			linksClassList.add("ion-university");

			iconsList.add("icons/holiday-icon.png");
			linksList.add("#manageSchoolHolidays.html");
			linksNameList.add("School Holidays");
			linksClassList.add("ion-university");

			iconsList.add("icons/businessman.png");
			linksList.add("#manageStudents.html");
			linksNameList.add("Student/Kids");
			linksClassList.add("ion-android-contacts");

			iconsList.add("icons/Administrator_Icon_128.png");
			linksList.add("#manageUsers.html");
			linksNameList.add("Parent");
			linksClassList.add("ion-person-stalker");

			iconsList.add("icons/report.png");
			linksList.add("#reports.html");
			linksNameList.add("Reports");
			linksClassList.add("ion-ios-speedometer-outline");

			iconsList.add("icons/Teacher-icon.png");
			linksList.add("#manageSchoolTeacher.html");
			linksNameList.add("Teachers");
			linksClassList.add("ion-university");

			iconsList.add("icons/alert.png");
			linksList.add("#alert.html");
			linksNameList.add("Alert");
			linksClassList.add("ion-ios-speedometer-outline");
			
			iconsList.add("icons/incomingrequest.png");
			linksList.add("#incomingRequest.html");
			linksNameList.add("Incoming Request");
			linksClassList.add("ion-university");

		}
//		iconsList.add("icons/feedback.png");
//		linksList.add("#broadcastMessage.html");
//		linksNameList.add("Broadcast Message");
//		linksClassList.add("ion-compose");

		iconsList.add("icons/bus_stop_marker.png");
		linksList.add("#assignment.html");
		linksNameList.add("Bus Stop Marker");
		linksClassList.add("ion-university");

		iconsList.add("icons/fleetSummary.png");
		linksList.add("#fleetSummaryDetails.html");
		linksNameList.add("Fleet Summary details");
		linksClassList.add("ion-university");

		iconsList.add("icons/spi_change_password.png");
		linksList.add("#changePassword.html");
		linksNameList.add("Change Password");
		linksClassList.add("ion-university");

		iconsList.add("icons/aboutUs.png");
		linksList.add("#aboutUs.html");
		linksNameList.add("About Us");
		linksClassList.add("ion-university");
	}

	private void buildIndividualAccessList() {
		LOG.debug("************Building Individual Dashboard***********");
		iconsList = new ArrayList<String>();
		linksList = new ArrayList<String>();
		linksNameList = new ArrayList<String>();
		linksClassList = new ArrayList<String>();

		iconsList.add("icons/bus-gps.jpg");
		iconsList.add("icons/bus.png");
		iconsList.add("icons/report.png");
		iconsList.add("icons/spi_change_password.png");

		linksList.add("#manageBus.html");
		linksList.add("#busLocation.html");
		linksList.add("#reports.html");
		linksList.add("#changePassword.html");

		linksNameList.add("Manage Device");
		linksNameList.add("Bus Location");
		linksNameList.add("Reports");
		linksNameList.add("Change Password");

		linksClassList.add("ion-android-bus");
		linksClassList.add("ion-android-bus");
		linksClassList.add("ion-ios-speedometer-outline");
		linksClassList.add("ion-university");

		iconsList.add("icons/aboutUs.png");
		linksList.add("#aboutUs.html");
		linksNameList.add("About Us");
		linksClassList.add("ion-university");

	}

	private void buildTransporterAccessList() {
		LOG.debug("************Building Individual Dashboard***********");
		iconsList = new ArrayList<String>();
		linksList = new ArrayList<String>();
		linksNameList = new ArrayList<String>();
		linksClassList = new ArrayList<String>();

		iconsList.add("icons/bus-gps.jpg");
		iconsList.add("icons/bus.png");
		iconsList.add("icons/report.png");
		iconsList.add("icons/spi_change_password.png");

		linksList.add("#manageBus.html");
		linksList.add("#busLocation.html");
		linksList.add("#reports.html");
		linksList.add("#changePassword.html");

		linksNameList.add("Manage Device");
		linksNameList.add("Bus Location");
		linksNameList.add("Reports");
		linksNameList.add("Change Password");

		linksClassList.add("ion-android-bus");
		linksClassList.add("ion-android-bus");
		linksClassList.add("ion-ios-speedometer-outline");
		linksClassList.add("ion-university");

		iconsList.add("icons/aboutUs.png");
		linksList.add("#aboutUs.html");
		linksNameList.add("About Us");
		linksClassList.add("ion-university");

	}

	private void buildSuperAdminAccessList() {
		LOG.debug("************Building SuperAdmin Dashboard***********");
		iconsList = new ArrayList<String>();
		linksList = new ArrayList<String>();
		linksNameList = new ArrayList<String>();
		linksClassList = new ArrayList<String>();

		iconsList.add("icons/School_Icon_128.png");
		linksList.add("#manageSchool.html");
		linksNameList.add("Schools");
		linksClassList.add("ion-university");

		iconsList.add("icons/map.png");
		linksList.add("#manageRoutes.html");
		linksNameList.add("Manage Routes");
		linksClassList.add("ion-map");

		iconsList.add("icons/bus-gps.jpg");
		linksList.add("#manageBus.html");
		linksNameList.add("Manage Bus");
		linksClassList.add("ion-android-bus");

		// iconsList.add("icons/Administrator_Icon_128.png");
		// linksList.add("#manageUsers.html");
		// linksNameList.add("Parent");
		// linksClassList.add("ion-person-stalker");

		iconsList.add("icons/report.png");
		linksList.add("#reports.html");
		linksNameList.add("Reports");
		linksClassList.add("ion-ios-speedometer-outline");

		iconsList.add("icons/schooladmin.jpg");
		linksList.add("#manageSchoolAdmin.html");
		linksNameList.add("School Administrator");
		linksClassList.add("ion-university");

		iconsList.add("icons/holiday-icon.png");
		linksList.add("#holidays.html");
		linksNameList.add("Manage Holidays");
		linksClassList.add("ion-person-stalker");

		iconsList.add("icons/alert.png");
		linksList.add("#alert.html");
		linksNameList.add("Alert");
		linksClassList.add("ion-ios-speedometer-outline");
		
		iconsList.add("icons/routeSchedule.png");
		linksList.add("#manageRouteScheduleDetails.html");
		linksNameList.add("Manage Route Schedule");
		linksClassList.add("ion-ios-speedometer-outline");

		iconsList.add("icons/spi_change_password.png");
		linksList.add("#changePassword.html");
		linksNameList.add("Change Password");
		linksClassList.add("ion-university");

		iconsList.add("icons/aboutUs.png");
		linksList.add("#aboutUs.html");
		linksNameList.add("About Us");
		linksClassList.add("ion-university");

	}

	public List<String> getIconsList() {
		return iconsList;
	}

	public void setIconsList(List<String> iconsList) {
		this.iconsList = iconsList;
	}

	public List<String> getLinksList() {
		return linksList;
	}

	public void setLinksList(List<String> linksList) {
		this.linksList = linksList;
	}

	public List<String> getLinksNameList() {
		return linksNameList;
	}

	public void setLinksNameList(List<String> linksNameList) {
		this.linksNameList = linksNameList;
	}

	public List<String> getLinksClassList() {
		return linksClassList;
	}

	public void setLinksClassList(List<String> linksClassList) {
		this.linksClassList = linksClassList;
	}

}
