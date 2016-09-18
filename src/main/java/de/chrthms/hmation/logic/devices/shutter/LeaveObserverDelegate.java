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

import de.chrthms.hmatic4j.HMaticAPI;
import de.chrthms.hmation.logic.ProcessFinals;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class LeaveObserverDelegate implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(LeaveObserverDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        final String registryId = (String) execution.getVariable(ProcessFinals.VAR_HM_EVENT_REGISTRY_ID);
        
        LOG.info("About to unobserve with registryId = {}", registryId);
        
        HMaticAPI.getInstance()
                .unobserve(registryId);

    }

}
