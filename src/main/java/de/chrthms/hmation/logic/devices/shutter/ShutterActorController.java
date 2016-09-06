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
import de.chrthms.hmatic4j.core.commands.impl.set.value.SetValueLevel;
import de.chrthms.hmatic4j.event.client.enums.ValueKey;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.chrthms.hmation.logic.devices.ProcessFinals.*;
import java.util.Optional;
import org.camunda.bpm.BpmPlatform;

/**
 *
 * @author christian
 */
public class ShutterActorController implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(ShutterActorController.class);
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        final String processInstanceId = execution.getProcessInstanceId();
        
        final String hmRpcAddress = (String) execution.getVariable(VAR_HM_RPC_SERVER_ADDRESS);
        final String deviceAddress = (String) execution.getVariable(VAR_DEVICE_ADDRESS);
        final String deviceChannel = (String) execution.getVariable(VAR_DEVICE_CHANNEL);
        final Double controlValue = Double.valueOf( (String) execution.getVariable(VAR_CONTROL_VALUE) ); // TODO IN FIRST TEST A STRING IS GIVEN!!!
                        
        LOG.info("About to controll shutter-actor with following values: hmRpcAddress = {}, devicesAddress = {}, deviceChannel = {}, "
                + "controllValue = {}.",
                new Object[]{hmRpcAddress, deviceAddress, deviceChannel, controlValue});
                
        LOG.info("About to observe the homematic shutter-actor for upcoming execution.");
        
        Optional<String> eventRegistryId = HMaticAPI.getInstance()
                .observe()
                .deviceAddress(deviceAddress)
                .deviceChannel(deviceChannel)
                .valueKey(ValueKey.WORKING)
                .start((event) -> {
                    LOG.info("Received expected Homematic Event = {}.", event);
                    
                    if ((Boolean) event.getValue() == false) {
                        LOG.info("The WORKING flag is set to false. About to send message back to process.");
                        
                        BpmPlatform.getDefaultProcessEngine()
                                .getRuntimeService()
                                .createMessageCorrelation(MSG_SHUTTER_INCOMING_EVENT)
                                .processInstanceId(processInstanceId)
                                .correlate();
                        
                    } else {
                        LOG.info("The WORKING flag is set to true. Actually nothing to do. False is expeded.");                        
                    }
                    
                });

        LOG.info("Hold given eventRegistryId = {} as process variable.", eventRegistryId);
        execution.setVariable(VAR_HM_EVENT_REGISTRY_ID, eventRegistryId.orElse(null));
        
        LOG.info("Now it is time to perform the execution to controll the shutter-actor.");
        
        HMaticAPI.getInstance()
                .rpcServerAddress(hmRpcAddress)
                .wireless()
                .command(new SetValueLevel()
                    .value(controlValue)
                    .deviceAddress(deviceAddress)
                    .deviceChannel(deviceChannel))
                .execute();
        
        
    }

}
