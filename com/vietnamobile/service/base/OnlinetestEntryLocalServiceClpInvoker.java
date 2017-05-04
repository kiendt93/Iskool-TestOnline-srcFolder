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

package com.vietnamobile.service.base;

import com.vietnamobile.service.OnlinetestEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author duymb
 * @generated
 */
public class OnlinetestEntryLocalServiceClpInvoker {
	public OnlinetestEntryLocalServiceClpInvoker() {
		_methodName0 = "addOnlinetestEntry";

		_methodParameterTypes0 = new String[] {
				"com.vietnamobile.model.OnlinetestEntry"
			};

		_methodName1 = "createOnlinetestEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteOnlinetestEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteOnlinetestEntry";

		_methodParameterTypes3 = new String[] {
				"com.vietnamobile.model.OnlinetestEntry"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchOnlinetestEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getOnlinetestEntry";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getOnlinetestEntries";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getOnlinetestEntriesCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateOnlinetestEntry";

		_methodParameterTypes15 = new String[] {
				"com.vietnamobile.model.OnlinetestEntry"
			};

		_methodName64 = "getBeanIdentifier";

		_methodParameterTypes64 = new String[] {  };

		_methodName65 = "setBeanIdentifier";

		_methodParameterTypes65 = new String[] { "java.lang.String" };

		_methodName70 = "createOnlinetestEntry";

		_methodParameterTypes70 = new String[] {
				"long", "int", "java.util.ArrayList", "java.util.ArrayList",
				"java.util.ArrayList", "java.util.ArrayList",
				"java.util.ArrayList", "java.util.Date", "java.util.Date", "int",
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName71 = "createOnlinetestEntry";

		_methodParameterTypes71 = new String[] {
				"long", "int", "java.util.ArrayList", "java.util.ArrayList",
				"java.util.ArrayList", "java.util.ArrayList",
				"java.util.ArrayList", "java.util.Date", "java.util.Date", "int",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName72 = "createOnlinetestEntry";

		_methodParameterTypes72 = new String[] {
				"long", "java.util.ArrayList", "java.util.ArrayList",
				"java.util.Date", "java.util.Date", "int", "java.lang.String",
				"int", "com.liferay.portal.service.ServiceContext"
			};

		_methodName73 = "updateOnlinetestEntry";

		_methodParameterTypes73 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName74 = "softDeleteOnlineTestEntry";

		_methodParameterTypes74 = new String[] {
				"long", "long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName75 = "getAllOnlinetests";

		_methodParameterTypes75 = new String[] {  };

		_methodName76 = "createQuestionListsNew";

		_methodParameterTypes76 = new String[] {
				"int", "java.util.ArrayList", "java.util.ArrayList",
				"java.util.ArrayList", "java.util.ArrayList",
				"java.util.ArrayList"
			};

		_methodName77 = "createQuestionLists";

		_methodParameterTypes77 = new String[] {
				"java.util.ArrayList", "java.util.ArrayList"
			};

		_methodName78 = "createQuestionLists";

		_methodParameterTypes78 = new String[] {
				"int", "java.util.ArrayList", "java.util.ArrayList",
				"java.util.ArrayList", "java.util.ArrayList",
				"java.util.ArrayList"
			};

		_methodName80 = "searchOnlinetestEntry";

		_methodParameterTypes80 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Date",
				"java.util.Date", "boolean"
			};

		_methodName81 = "getNumberQuestionOfSubject";

		_methodParameterTypes81 = new String[] {  };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.addOnlinetestEntry((com.vietnamobile.model.OnlinetestEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.createOnlinetestEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.deleteOnlinetestEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.deleteOnlinetestEntry((com.vietnamobile.model.OnlinetestEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.fetchOnlinetestEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.getOnlinetestEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.getOnlinetestEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.getOnlinetestEntriesCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.updateOnlinetestEntry((com.vietnamobile.model.OnlinetestEntry)arguments[0]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			OnlinetestEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.createOnlinetestEntry(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.util.ArrayList<java.lang.String>)arguments[2],
				(java.util.ArrayList<java.lang.Integer>)arguments[3],
				(java.util.ArrayList<java.lang.Integer>)arguments[4],
				(java.util.ArrayList<java.lang.Integer>)arguments[5],
				(java.util.ArrayList<java.lang.Integer>)arguments[6],
				(java.util.Date)arguments[7], (java.util.Date)arguments[8],
				((Integer)arguments[9]).intValue(),
				(java.lang.String)arguments[10],
				((Integer)arguments[11]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[12]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.createOnlinetestEntry(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.util.ArrayList<java.lang.String>)arguments[2],
				(java.util.ArrayList<java.lang.Integer>)arguments[3],
				(java.util.ArrayList<java.lang.Integer>)arguments[4],
				(java.util.ArrayList<java.lang.Integer>)arguments[5],
				(java.util.ArrayList<java.lang.Integer>)arguments[6],
				(java.util.Date)arguments[7], (java.util.Date)arguments[8],
				((Integer)arguments[9]).intValue(),
				(java.lang.String)arguments[10],
				(com.liferay.portal.service.ServiceContext)arguments[11]);
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.createOnlinetestEntry(((Long)arguments[0]).longValue(),
				(java.util.ArrayList<java.lang.String>)arguments[1],
				(java.util.ArrayList<java.lang.Integer>)arguments[2],
				(java.util.Date)arguments[3], (java.util.Date)arguments[4],
				((Integer)arguments[5]).intValue(),
				(java.lang.String)arguments[6],
				((Integer)arguments[7]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.updateOnlinetestEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.softDeleteOnlineTestEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.getAllOnlinetests();
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.createQuestionListsNew(((Integer)arguments[0]).intValue(),
				(java.util.ArrayList<java.lang.String>)arguments[1],
				(java.util.ArrayList<java.lang.Integer>)arguments[2],
				(java.util.ArrayList<java.lang.Integer>)arguments[3],
				(java.util.ArrayList<java.lang.Integer>)arguments[4],
				(java.util.ArrayList<java.lang.Integer>)arguments[5]);
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.createQuestionLists((java.util.ArrayList<java.lang.String>)arguments[0],
				(java.util.ArrayList<java.lang.Integer>)arguments[1]);
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.createQuestionLists(((Integer)arguments[0]).intValue(),
				(java.util.ArrayList<java.lang.String>)arguments[1],
				(java.util.ArrayList<java.lang.Integer>)arguments[2],
				(java.util.ArrayList<java.lang.Integer>)arguments[3],
				(java.util.ArrayList<java.lang.Integer>)arguments[4],
				(java.util.ArrayList<java.lang.Integer>)arguments[5]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.searchOnlinetestEntry((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Date)arguments[2],
				(java.util.Date)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return OnlinetestEntryLocalServiceUtil.getNumberQuestionOfSubject();
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
}