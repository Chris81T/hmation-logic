/*
 * Copyright 2016 Christian Thomas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.chrthms.hmation.logic.devices.shutter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class ShutterActorMessageInterpreter implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(ShutterActorMessageInterpreter.class);
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOG.info("About to interprete given message");
        
        // TODO
        
        LOG.info("Finally leave the observer delegate");
        LeaveObserverDelegate leaveObserverDelegate = new LeaveObserverDelegate();
        leaveObserverDelegate.execute(execution);        
    }

}
