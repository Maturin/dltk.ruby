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
package org.eclipse.dltk.ruby.formatter.internal.nodes;

import org.eclipse.dltk.formatter.IFormatterCommentableNode;
import org.eclipse.dltk.formatter.IFormatterContext;
import org.eclipse.dltk.formatter.IFormatterDocument;
import org.eclipse.dltk.ruby.formatter.RubyFormatterConstants;

public class FormatterMethodNode extends FormatterBlockWithBeginEndNode
		implements IFormatterCommentableNode {

	/**
	 * @param document
	 */
	public FormatterMethodNode(IFormatterDocument document) {
		super(document);
	}

	protected boolean isIndenting() {
		return getDocument().getBoolean(RubyFormatterConstants.INDENT_METHOD);
	}

	protected int getBlankLinesBefore(IFormatterContext context) {
		if (context.getParent() == null) {
			return getInt(RubyFormatterConstants.LINES_FILE_BETWEEN_METHOD);
		} else if (context.getChildIndex() == 0) {
			return getInt(RubyFormatterConstants.LINES_BEFORE_FIRST);
		} else {
			return getInt(RubyFormatterConstants.LINES_BEFORE_METHOD);
		}
	}

	protected int getBlankLinesAfter(IFormatterContext context) {
		if (context.getParent() == null) {
			return getInt(RubyFormatterConstants.LINES_FILE_BETWEEN_METHOD);
		} else {
			return super.getBlankLinesAfter(context);
		}
	}

}
