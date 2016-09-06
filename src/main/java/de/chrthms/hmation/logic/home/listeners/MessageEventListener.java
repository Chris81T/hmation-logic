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

package de.chrthms.hmation.logic.home.listeners;

import de.chrthms.hmatic4j.HMaticAPI;
import de.chrthms.hmatic4j.event.client.enums.ValueKey;
import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class MessageEventListener implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(MessageEventListener.class);
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        LOG.info("\n\nMESSAGE EVENT - About to registry event using event filter....");

        final String LEFT_ONE = "NEQ0048901";
        final String RIGHT_ONE = "NEQ0048896";

        HMaticAPI.getInstance()
                .observe()
                .deviceAddress(LEFT_ONE)
                .deviceChannel("1")
                .valueKey(ValueKey.WORKING)
                .onceOnly(true)
                .start((event) -> {
                    LOG.info("INCOMING 'WORKING' EVENT! Remember onceOnly : ) event = {}", event);

                    if ((Boolean) event.getValue() == false) {
                    
                        // TODO test, if the execution is active here....
                        BpmPlatform.getDefaultProcessEngine()
                                .getRuntimeService()
                                .createMessageCorrelation("observerTestMessage")
                                .correlate();

                        LOG.info("INCOMING 'WORKING' EVENT! Successfully sent message!!! : )\n\n");
                        
                    }
                                        
                });
        
        
        
    }

}
