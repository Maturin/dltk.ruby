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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dltk.compiler.util.Util;
import org.eclipse.jface.text.IRegion;

public class FormatterWriter implements IFormatterVisitor {

	private final StringBuffer writer = new StringBuffer();
	private final StringBuffer indent = new StringBuffer();
	private final StringBuffer callbackBuffer = new StringBuffer();
	private final StringBuffer emptyLines = new StringBuffer();

	private boolean lineStarted = false;
	private char lastChar = 0;
	private final List newLineCallbacks = new ArrayList();

	private final String lineDelimiter = Util.LINE_SEPARATOR;

	public void preVisit(IFormatterContext context, IFormatterTextNode node)
			throws Exception {
		if (!lineStarted) {
			startLine(context);
		}
	}

	public void visit(IFormatterContext context, IFormatterTextNode node)
			throws Exception {
		if (!excludes.isExcluded(node.getStartOffset(), node.getEndOffset())) {
			write(context, node.getText());
		} else {
			final IRegion[] regions = excludes.selectValidRanges(node
					.getStartOffset(), node.getEndOffset());
			IFormatterDocument document = node.getDocument();
			for (int i = 0; i < regions.length; ++i) {
				write(context, document.get(regions[i]));
			}
		}
	}

	protected void write(IFormatterContext context, String text)
			throws IOException {
		for (int i = 0; i < text.length(); ++i) {
			write(context, text.charAt(i));
		}
	}

	/**
	 * @param context
	 * @param charAt
	 * @throws IOException
	 */
	protected void write(IFormatterContext context, char ch) throws IOException {
		if (ch == '\n' || ch == '\r') {
			if (lineStarted) {
				writer.append(ch);
				lineStarted = false;
				if (!newLineCallbacks.isEmpty()) {
					executeNewLineCallbacks(context);
					assert newLineCallbacks.isEmpty();
				}
			} else if (ch == '\n' && lastChar == '\r') {
				if (emptyLines.length() == 0) {
					writer.append(ch); // windows EOL = "\r\n"
				} else {
					emptyLines.append(ch);
				}
			} else {
				indent.setLength(0);// add option "trim empty lines"
				emptyLines.append(ch);
			}
		} else if (!lineStarted) {
			if (Character.isWhitespace(ch)) {
				indent.append(ch);
			} else {
				startLine(context);
				writer.append(ch);
			}
		} else {
			writer.append(ch);
		}
		lastChar = ch;
	}

	private void executeNewLineCallbacks(IFormatterContext context) {
		final IFormatterWriter callbackWriter = new IFormatterWriter() {

			public void writeIndent(IFormatterContext context) {
				FormatterWriter.writeIndent(context, callbackBuffer);
			}

			public void writeText(IFormatterContext context, String text) {
				callbackBuffer.append(text);
			}

		};
		final List copy = new ArrayList(newLineCallbacks);
		newLineCallbacks.clear();
		for (Iterator i = copy.iterator(); i.hasNext();) {
			IFormatterCallback callback = (IFormatterCallback) i.next();
			callback.call(context, callbackWriter);
		}
	}

	private void startLine(IFormatterContext context) throws IOException {
		if (callbackBuffer.length() != 0) {
			writer.append(callbackBuffer);
			callbackBuffer.setLength(0);
		}
		if (emptyLines.length() != 0) {
			writer.append(emptyLines);
			emptyLines.setLength(0);
		}
		if (context.isIndenting()) {
			writeIndent(context);
		} else {
			writer.append(indent);
		}
		indent.setLength(0);
		lineStarted = true;
	}

	/**
	 * @param context
	 * @throws IOException
	 */
	protected void writeIndent(IFormatterContext context) {
		writeIndent(context, writer);
	}

	protected static void writeIndent(IFormatterContext context,
			StringBuffer buffer) {
		for (int i = 0, indent = context.getIndent(); i < indent; ++i) {
			buffer.append('\t');
		}
	}

	public String getOutput() {
		return writer.toString();
	}

	private final ExcludeRegionList excludes = new ExcludeRegionList();

	public void excludeRegion(IRegion region) {
		excludes.excludeRegion(region);
	}

	public void addNewLineCallback(IFormatterCallback callback) {
		newLineCallbacks.add(callback);
	}

	public void flush(IFormatterContext context) {
		if (!newLineCallbacks.isEmpty()) {
			if (lineStarted) {
				writer.append(lineDelimiter);
				lineStarted = false;
			}
			executeNewLineCallbacks(context);
			assert newLineCallbacks.isEmpty();
		}
		if (callbackBuffer.length() != 0) {
			writer.append(callbackBuffer);
			callbackBuffer.setLength(0);
		}
		if (emptyLines.length() != 0) {
			writer.append(emptyLines);
			emptyLines.setLength(0);
		}
	}
}
