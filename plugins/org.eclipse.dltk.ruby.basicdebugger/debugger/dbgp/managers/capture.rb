###############################################################################
# Copyright (c) 2005, 2007 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#

###############################################################################

require 'thread'

module XoredDebugger   
    class Capturer
        def initialize(stream, state)
            @stream = stream

            state ? enable : disable
        
            @mutex = Mutex.new

            @output = ''
        end

        def write(s)
            #@mutex.synchronize do
                @output += s
            #end
        end
                 
        def get
            #@mutex.synchronize do
                out = @output
                @output = ''
                out
            #end
        end

        def saved_stream
            @stream
        end
        protected :saved_stream
    end # class Capturer

    class StdoutCapturer < Capturer
        def initialize(state = false)
            super($stdout, state)
        end

        def enable
            $stdout = self
        end

        def disable
            $stdout = saved_stream
        end
    end # class StdoutCapturer

    class StderrCapturer < Capturer
        def initialize(state = false)
            super($stderr, state)
        end

        def enable
            $stderr = self
        end

        def disable
            $stderr = saved_stream
        end
    end # class StderrCapturer
end # module XoredDebugger