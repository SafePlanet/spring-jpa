/* Copyright (C) SafePlanet Innovations, LLP - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by SafePlanet Innovations <spinnovations.india@gmail.com>, October 2015
 */
package com.spi.uvschool.domain;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Type;
import org.springframework.util.StringUtils;

import com.spi.uvschool.model.BaseEntity;
import com.spi.uvschool.user.api.ExternalUser;
import com.spi.uvschool.user.api.UpdateUserRequest;
import com.spi.uvschool.util.HashUtil;

/**
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5440359109053186886L;

	/**
	 * Add additional salt to password hashing
	 */
	private static final String HASH_SALT = "a8a8s885-ecce-93fb-8832-894h20f0d8td";

	private static final int HASH_ITERATIONS = 1000;

	private String firstName;
	private String lastName;
	private String fcmtoken;
	private String emailAddress;
	private String hashedPassword;
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean verified;
	private String mobileNumber;
	private char gender = 'M';
	private char usageConsent;
	private Date dateOfBirth;
	private String userImage;
	private long routeId;

	// @OneToOne(mappedBy = "user", targetEntity = UserGroupSchool.class,
	// cascade = CascadeType.ALL)
	// private UserGroupSchool userGroupSchool;

	@Column(columnDefinition = "tinyint(1) default 1")
	private int isEnable;

	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "user", fetch = FetchType.EAGER)
	private Set<SocialUser> socialUsers = new HashSet<SocialUser>();

	@OneToMany(mappedBy = "user", targetEntity = VerificationToken.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<VerificationToken> verificationTokens = new ArrayList<VerificationToken>();

	@OneToMany(mappedBy = "user", targetEntity = Address.class, cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(FetchMode.JOIN)
	private List<Address> addresses = new ArrayList<Address>();

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private AuthorizationToken authorizationToken;

	// @OneToMany(mappedBy = "user", targetEntity = AlertConfig.class, cascade =
	// CascadeType.ALL)
	// @LazyCollection(LazyCollectionOption.TRUE)
	// private List<AlertConfig> alertConfigs = new ArrayList<AlertConfig>();

	public User() {
		this(UUID.randomUUID());
	}

	public User(UUID uuid) {
		super(uuid);
		setRole(Role.anonymous); // all users are anonymous until credentials
									// are proved
	}

	public User(ExternalUser externalUser) {
		this();
		this.firstName = externalUser.getFirstName();
		this.lastName = externalUser.getLastName();
		this.emailAddress = externalUser.getEmailAddress();
	}

	public void setUserDetails(UpdateUserRequest userRequest) {
		this.firstName = userRequest.getFirstName();
		this.lastName = userRequest.getLastName();
		this.emailAddress = userRequest.getEmailAddress();
		this.mobileNumber = userRequest.getMobile();
		this.userImage = userRequest.getUserImage();
		Address address = this.addresses.get(0);
		address.setAddressLine1(userRequest.getHouseNo());
		address.setAddressLine2(userRequest.getAddress());
		address.setCity(userRequest.getCity());
		address.setState(userRequest.getState());
		address.setPinCode(userRequest.getPinCode());

	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getHashedPassword() {
		return this.hashedPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress == null ? "" : emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean hasRole(Role role) {
		return role.equals(this.role);
	}

	public boolean equals(Object otherUser) {
		boolean response = false;

		if (otherUser == null) {
			response = false;
		} else if (!(otherUser instanceof User)) {
			response = false;
		} else {
			if (((User) otherUser).getUuid().equals(this.getUuid())) {
				response = true;
			}
		}

		return response;
	}

	public int hashCode() {
		return getUuid().hashCode();
	}

	public String getName() {
		if (StringUtils.hasText(getFirstName())) {
			return getFirstName() + " " + getLastName();
		}
		return "";
	}

	public Set<SocialUser> getSocialUsers() {
		return socialUsers;
	}

	public void setSocialUsers(Set<SocialUser> socialUsers) {
		this.socialUsers = socialUsers;
	}

	public void addSocialUser(SocialUser socialUser) {
		getSocialUsers().add(socialUser);
	}

	public synchronized void addVerificationToken(VerificationToken token) {
		verificationTokens.add(token);
	}

	public synchronized List<VerificationToken> getVerificationTokens() {
		return Collections.unmodifiableList(this.verificationTokens);
	}

	public synchronized void setAuthorizationToken(AuthorizationToken token) {

		this.authorizationToken = token;
	}

	public synchronized AuthorizationToken getAuthorizationToken() {
		return authorizationToken;
	}

	/**
	 * If the user has a VerificationToken of type
	 * VerificationTokenType.lostPassword that is active return it otherwise return
	 * null
	 *
	 * @return verificationToken
	 */
	public VerificationToken getActiveLostPasswordToken() {
		return getActiveToken(VerificationToken.VerificationTokenType.lostPassword);
	}

	/**
	 * If the user has a VerificationToken of type
	 * VerificationTokenType.emailVerification that is active return it otherwise
	 * return null
	 *
	 * @return verificationToken
	 */
	public VerificationToken getActiveEmailVerificationToken() {
		return getActiveToken(VerificationToken.VerificationTokenType.emailVerification);
	}

	/**
	 * If the user has a VerificationToken of type
	 * VerificationTokenType.emailRegistration that is active return it otherwise
	 * return null
	 *
	 * @return verificationToken
	 */
	public VerificationToken getActiveEmailRegistrationToken() {
		return getActiveToken(VerificationToken.VerificationTokenType.emailRegistration);
	}

	private VerificationToken getActiveToken(VerificationToken.VerificationTokenType tokenType) {
		VerificationToken activeToken = null;
		for (VerificationToken token : getVerificationTokens()) {
			if (token.getTokenType().equals(tokenType) && !token.hasExpired() && !token.isVerified()) {
				activeToken = token;
				break;
			}
		}
		return activeToken;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the gender
	 */
	public char getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(char gender) {
		this.gender = gender;
	}

	/**
	 * @return the usageConsent
	 */
	public char getUsageConsent() {
		return usageConsent;
	}

	/**
	 * @param usageConsent
	 *            the usageConsent to set
	 */
	public void setUsageConsent(char usageConsent) {
		this.usageConsent = usageConsent;
	}

	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Hash the password using salt values See
	 * https://www.owasp.org/index.php/Hashing_Java
	 *
	 * @param passwordToHash
	 * @return hashed password
	 */
	public String hashPassword(String passwordToHash) throws Exception {
		return hashToken(passwordToHash, getUuid().toString() + HASH_SALT);
	}

	private String hashToken(String token, String salt) throws Exception {
		return HashUtil.byteToBase64(getHash(HASH_ITERATIONS, token, salt.getBytes()));
	}

	public byte[] getHash(int numberOfIterations, String password, byte[] salt) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for (int i = 0; i < numberOfIterations; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}

	/**
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public long getRouteId() {
		return routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	public String getFcmtoken() {
		return fcmtoken;
	}

	public void setFcmtoken(String fcmtoken) {
		this.fcmtoken = fcmtoken;
	}
}
