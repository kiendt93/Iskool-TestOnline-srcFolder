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

import com.vietnamobile.model.OnlinetestResultEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing OnlinetestResultEntry in entity cache.
 *
 * @author duymb
 * @see OnlinetestResultEntry
 * @generated
 */
public class OnlinetestResultEntryCacheModel implements CacheModel<OnlinetestResultEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{onlinetestResultEntryId=");
		sb.append(onlinetestResultEntryId);
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
		sb.append(", onlineTestEntryId=");
		sb.append(onlineTestEntryId);
		sb.append(", score=");
		sb.append(score);
		sb.append(", answerSelected=");
		sb.append(answerSelected);
		sb.append(", numberOfWrongAnswer=");
		sb.append(numberOfWrongAnswer);
		sb.append(", timeRemaining=");
		sb.append(timeRemaining);
		sb.append(", examType=");
		sb.append(examType);
		sb.append(", questionIds=");
		sb.append(questionIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OnlinetestResultEntry toEntityModel() {
		OnlinetestResultEntryImpl onlinetestResultEntryImpl = new OnlinetestResultEntryImpl();

		onlinetestResultEntryImpl.setOnlinetestResultEntryId(onlinetestResultEntryId);
		onlinetestResultEntryImpl.setGroupId(groupId);
		onlinetestResultEntryImpl.setCompanyId(companyId);
		onlinetestResultEntryImpl.setUserId(userId);

		if (userName == null) {
			onlinetestResultEntryImpl.setUserName(StringPool.BLANK);
		}
		else {
			onlinetestResultEntryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			onlinetestResultEntryImpl.setCreateDate(null);
		}
		else {
			onlinetestResultEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			onlinetestResultEntryImpl.setModifiedDate(null);
		}
		else {
			onlinetestResultEntryImpl.setModifiedDate(new Date(modifiedDate));
		}

		onlinetestResultEntryImpl.setOnlineTestEntryId(onlineTestEntryId);
		onlinetestResultEntryImpl.setScore(score);

		if (answerSelected == null) {
			onlinetestResultEntryImpl.setAnswerSelected(StringPool.BLANK);
		}
		else {
			onlinetestResultEntryImpl.setAnswerSelected(answerSelected);
		}

		onlinetestResultEntryImpl.setNumberOfWrongAnswer(numberOfWrongAnswer);
		onlinetestResultEntryImpl.setTimeRemaining(timeRemaining);

		if (examType == null) {
			onlinetestResultEntryImpl.setExamType(StringPool.BLANK);
		}
		else {
			onlinetestResultEntryImpl.setExamType(examType);
		}

		if (questionIds == null) {
			onlinetestResultEntryImpl.setQuestionIds(StringPool.BLANK);
		}
		else {
			onlinetestResultEntryImpl.setQuestionIds(questionIds);
		}

		onlinetestResultEntryImpl.resetOriginalValues();

		return onlinetestResultEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		onlinetestResultEntryId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		onlineTestEntryId = objectInput.readLong();
		score = objectInput.readDouble();
		answerSelected = objectInput.readUTF();
		numberOfWrongAnswer = objectInput.readInt();
		timeRemaining = objectInput.readInt();
		examType = objectInput.readUTF();
		questionIds = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(onlinetestResultEntryId);
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
		objectOutput.writeLong(onlineTestEntryId);
		objectOutput.writeDouble(score);

		if (answerSelected == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(answerSelected);
		}

		objectOutput.writeInt(numberOfWrongAnswer);
		objectOutput.writeInt(timeRemaining);

		if (examType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(examType);
		}

		if (questionIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(questionIds);
		}
	}

	public long onlinetestResultEntryId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long onlineTestEntryId;
	public double score;
	public String answerSelected;
	public int numberOfWrongAnswer;
	public int timeRemaining;
	public String examType;
	public String questionIds;
}