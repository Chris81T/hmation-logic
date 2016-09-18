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

package de.chrthms.hmation.logic.devices.commons;

import de.chrthms.hmatic4j.HMaticAPI;
import de.chrthms.hmatic4j.core.commands.impl.init.LogicLayerInit;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class EventRegisterToCCUDelegate implements JavaDelegate {

    private static final Logger LOG = LoggerFactory.getLogger(EventRegisterToCCUDelegate.class);
    
    public static final String INTERFACE_ID = "CAM-HMATIC-4J-11101981";
    public static final String RPC_SERVER_ADDRESS = "raspberrypi";
    public static final String LOGIC_RPC_SERVER_ADDRESS = "127.0.0.1";
    public static final String LOGIC_RPC_SERVER_PORT = "8080";
    public static final String LOGIC_RPC_SERVER_PATH = "hmation-logic/event";
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        
        LOG.info("\n\nabout to connect to logic layer...");

        HMaticAPI.getInstance()
                .rpcServerAddress(RPC_SERVER_ADDRESS)
                .wireless()
                .command(new LogicLayerInit()
                    .interfaceId(INTERFACE_ID)
                    .logicRpcServerAddress(LOGIC_RPC_SERVER_ADDRESS)
                    .logicRpcServerPort(LOGIC_RPC_SERVER_PORT)
                    .logicRpcServerPath(LOGIC_RPC_SERVER_PATH))
                .execute();

        LOG.info("successfully connected to logic layer!\n\n");
        
    }

}
