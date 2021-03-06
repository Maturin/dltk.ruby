/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.dltk.ruby.core.model;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.ModelException;
import org.eclipse.dltk.internal.core.ModelElement;
import org.eclipse.dltk.internal.core.SourceField;

public class FakeField extends SourceField implements ISourceRange {
	private final int offset;
	private final int length;
	private final int flags;
	private final boolean hasFlags;

	public FakeField(ISourceModule parent, String name, int offset, int length) {
		super((ModelElement) parent, name);
		this.offset = offset;
		this.length = length;
		this.flags = 0;
		this.hasFlags = false;
	}

	public FakeField(ISourceModule parent, String name, int offset, int length,
			int flags) {
		super((ModelElement) parent, name);
		this.offset = offset;
		this.length = length;
		this.flags = flags;
		this.hasFlags = true;
	}

	public ISourceRange getNameRange() throws ModelException {
		return this;
	}

	public ISourceRange getSourceRange() throws ModelException {
		return this;
	}

	public boolean exists() {
		return true;
	}

	public int getFlags() throws ModelException {
		return hasFlags ? flags : super.getFlags();
	}

	public int getLength() {
		return length;
	}

	public int getOffset() {
		return offset;
	}
}
