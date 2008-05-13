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
package org.eclipse.dltk.ruby.internal.ui.preferences;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.dltk.ruby.core.RubyPlugin;
import org.eclipse.dltk.ui.preferences.TodoTaskAbstractPreferencePage;

public class RubyTodoTaskPreferencePage extends TodoTaskAbstractPreferencePage {

	protected String getHelpId() {
		return null;
	}

	protected void setDescription() {
		setDescription(RubyPreferencesMessages.TodoTaskDescription);
	}

	protected Preferences getPluginPreferences() {
		return RubyPlugin.getDefault().getPluginPreferences();
	}

}