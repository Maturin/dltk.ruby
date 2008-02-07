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
package org.eclipse.dltk.ruby.internal.core.codeassist;

import java.util.ArrayList;
import java.util.List;

public class RubyKeyword {
	private static String[] sKeywords = new String[] {"if", "else", "elsif", "unless", "while", "until", "in"
		,"case", "when", "begin", "ensure", "module", "for", "then", "do", "and", "or", "not"
		,"rescue", "return", "break", "next", "yield", "defined?", "super", "def", "undef", "alias", "class"
		,"end", "self", "false", "true", "retry", "nil", "redo", "BEGIN", "END", "__LINE__", "__FILE__",
		"sub", "sub!", "gsub", "gsub!", "scan", "index", "match", "require" };
	
	public static String[] findByPrefix (String prefix) {
		List result = new ArrayList ();
		for (int i = 0; i < sKeywords.length; i++) {
			if (sKeywords[i].startsWith(prefix))
				result.add(sKeywords[i]);
		}		
		return (String[]) result.toArray(new String[result.size()]);
	}
}
