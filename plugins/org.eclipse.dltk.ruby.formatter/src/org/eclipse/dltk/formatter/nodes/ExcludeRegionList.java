/*******************************************************************************
 * Copyright (c) 2008 xored software, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     xored software, Inc. - initial API and Implementation (Alex Panchenko)
 *******************************************************************************/
package org.eclipse.dltk.formatter.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Region;

public class ExcludeRegionList {

	private final List excludes = new ArrayList();

	public boolean isExcluded(int start, int end) {
		if (!excludes.isEmpty()) {
			for (Iterator i = excludes.iterator(); i.hasNext();) {
				final IRegion region = (IRegion) i.next();
				final int regionEnd = region.getOffset() + region.getLength();
				if (start <= regionEnd && region.getOffset() <= end) {
					return true;
				}
			}
		}
		return false;
	}

	public IRegion[] selectValidRanges(int start, int end) {
		final List result = new ArrayList();
		for (Iterator i = excludes.iterator(); i.hasNext();) {
			final IRegion region = (IRegion) i.next();
			final int regionEnd = region.getOffset() + region.getLength();
			if (start <= regionEnd && region.getOffset() <= end) {
				if (start < region.getOffset()) {
					int validEnd = Math.min(end, region.getOffset());
					result.add(new Region(start, validEnd - start));
				}
				start = regionEnd;
				if (start > end) {
					break;
				}
			}
		}
		if (start < end) {
			result.add(new Region(start, end - start));
		}
		return (IRegion[]) result.toArray(new IRegion[result.size()]);
	}

	public List getExcludes() {
		return Collections.unmodifiableList(excludes);
	}

	public void excludeRegion(IRegion region) {
		int start = region.getOffset();
		int end = region.getOffset() + region.getLength();
		if (!excludes.isEmpty()) {
			for (Iterator i = excludes.iterator(); i.hasNext();) {
				final IRegion r = (IRegion) i.next();
				final int rEnd = r.getOffset() + r.getLength();
				if (r.getOffset() <= end && start <= rEnd) {
					if (region.getOffset() >= r.getOffset()
							&& region.getOffset() + region.getLength() <= rEnd) {
						// new region is inside one of the old regions
						return;
					}
					// calculate the surrounding bounds
					if (r.getOffset() < start) {
						start = r.getOffset();
					}
					if (rEnd > end) {
						end = rEnd;
					}
					i.remove();
				}
			}
		}
		// use input region or create the new one
		if (start == region.getOffset()
				&& end == region.getOffset() + region.getLength()) {
			excludes.add(region);
		} else {
			excludes.add(new Region(start, end - start));
		}
		Collections.sort(excludes, REGION_COMPARATOR);
	}

	private static final Comparator REGION_COMPARATOR = new Comparator() {

		public int compare(Object o1, Object o2) {
			return ((IRegion) o1).getOffset() - ((IRegion) o2).getOffset();
		}

	};

}
