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

package de.chrthms.hmation.logic.home.delegates;

import de.chrthms.hmatic4j.HMaticAPI;
import java.util.Optional;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class EventTestRegistryObserver implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(EventTestRegistryObserver.class);
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOG.info("******************************************** About to register test observer..");

        Optional<String> registryId = HMaticAPI.getInstance()
                .observe()
                .start((address, channel, valueKey, value) -> {
                    
                    LOG.info("\n\n\n\n############## functional method ivokation, while getting homematic event!");
                    LOG.info("############## deviceAddress = {}", address);
                    LOG.info("############## deviceChannel = {}", channel);
                    LOG.info("############## valueKey = {}", valueKey);
                    LOG.info("############## value = {}\n\n\n\n", value);
                    
                });
        
        LOG.info("******************************************** Save registryId = {} as process variable...", registryId);
        execution.setVariable("observerRegistryId", registryId.orElse(null));
        
    }

}
