###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

class Foo
	
	def dining_philosopher_2
		dining_philosopher
	end
	
	def dining_philosopher
		ultimate_answer
	end

	def ultimate_answer
		42
	end

end

## meth Foo.ultimate_answer => Fixnum
## meth Foo.dining_philosopher => Fixnum
## meth Foo.dining_philosopher_2 => Fixnum
