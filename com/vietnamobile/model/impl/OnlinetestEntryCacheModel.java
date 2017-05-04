/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.vietnamobile.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.vietnamobile.model.OnlinetestEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OnlinetestEntry in entity cache.
 *
 * @author duymb
 * @see OnlinetestEntry
 * @generated
 */
public class OnlinetestEntryCacheModel implements CacheModel<OnlinetestEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{onlinetestEntryId=");
		sb.append(onlinetestEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", questionId=");
		sb.append(questionId);
		sb.append(", testStartTime=");
		sb.append(testStartTime);
		sb.append(", testEndTime=");
		sb.append(testEndTime);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", numberOfQuestions=");
		sb.append(numberOfQuestions);
		sb.append(", title=");
		sb.append(title);
		sb.append(", point=");
		sb.append(point);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OnlinetestEntry toEntityModel() {
		OnlinetestEntryImpl onlinetestEntryImpl = new OnlinetestEntryImpl();

		onlinetestEntryImpl.setOnlinetestEntryId(onlinetestEntryId);
		onlinetestEntryImpl.setGroupId(groupId);
		onlinetestEntryImpl.setCompanyId(companyId);
		onlinetestEntryImpl.setUserId(userId);

		if (userName == null) {
			onlinetestEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			onlinetestEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			onlinetestEntryImpl.setCreateDate(null);
		}
		else {
			onlinetestEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			onlinetestEntryImpl.setModifiedDate(null);
		}
		else {
			onlinetestEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (questionId == null) {
			onlinetestEntryImpl.setQuestionId(StringPool.BLANK);
		}
		else {
			onlinetestEntryImpl.setQuestionId(questionId);
		}

		if (testStartTime == Long.MIN_VALUE) {
			onlinetestEntryImpl.setTestStartTime(null);
		}
		else {
			onlinetestEntryImpl.setTestStartTime(new Date(testStartTime));
		}

		if (testEndTime == Long.MIN_VALUE) {
			onlinetestEntryImpl.setTestEndTime(null);
		}
		else {
			onlinetestEntryImpl.setTestEndTime(new Date(testEndTime));
		}

		onlinetestEntryImpl.setDuration(duration);
		onlinetestEntryImpl.setNumberOfQuestions(numberOfQuestions);

		if (title == null) {
			onlinetestEntryImpl.setTitle(StringPool.BLANK);
		}
		else {
			onlinetestEntryImpl.setTitle(title);
		}

		onlinetestEntryImpl.setPoint(point);
		onlinetestEntryImpl.setStatus(status);

		onlinetestEntryImpl.resetOriginalValues();

		return onlinetestEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		onlinetestEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		questionId = objectInput.readUTF();
		testStartTime = objectInput.readLong();
		testEndTime = objectInput.readLong();
		duration = objectInput.readInt();
		numberOfQuestions = objectInput.readInt();
		title = objectInput.readUTF();
		point = objectInput.readInt();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(onlinetestEntryId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (questionId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(questionId);
		}

		objectOutput.writeLong(testStartTime);
		objectOutput.writeLong(testEndTime);
		objectOutput.writeInt(duration);
		objectOutput.writeInt(numberOfQuestions);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		objectOutput.writeInt(point);
		objectOutput.writeInt(status);
	}

	public long onlinetestEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String questionId;
	public long testStartTime;
	public long testEndTime;
	public int duration;
	public int numberOfQuestions;
	public String title;
	public int point;
	public int status;
}