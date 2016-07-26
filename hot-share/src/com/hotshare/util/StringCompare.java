/**
 * Program : HistoryTodayCompare.java Author : misery Create : 2011-12-28
 * 下午10:32:02 Copyright 2006 by Embedded Internet Solutions Inc., All rights
 * reserved. This software is the confidential and proprietary information of
 * Embedded Internet Solutions Inc.("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Embedded
 * Internet Solutions Inc.
 */

package com.hotshare.util;

/**
 * @author misery
 * @version 1.0.0
 * @2011-12-28 下午10:32:02
 */
import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class StringCompare implements Comparator {

	public int compare(Object arg0, Object arg1) {
		String data1 = (String) arg0;
		String data2 = (String) arg1;
		if (data1.length() > data2.length()) {
			return 1;

		} else if (data1.length() < data2.length()) {
			return -1;
		} else {
			if (((String) arg0).compareTo((String) arg1) > 0) {
				return 1;
			} else {
				return -1;
			}

		}

	}

}
