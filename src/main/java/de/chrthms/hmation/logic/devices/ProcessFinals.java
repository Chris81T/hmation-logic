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

package de.chrthms.hmation.logic.devices;

/**
 *
 * @author christian
 */
public abstract class ProcessFinals {

    public static String VAR_DEVICE_ADDRESS = "deviceAddress";
    public static String VAR_DEVICE_CHANNEL = "deviceChannel";
    public static String VAR_CONTROL_VALUE = "controlValue";
    public static String VAR_HM_RPC_SERVER_ADDRESS = "homematicRpcServerAddress";
    public static String VAR_HM_EVENT_REGISTRY_ID = "homematicEventRegistryId";
    
    public static String MSG_SHUTTER_INCOMING_EVENT = "Message_RollladenFeedback";
    public static String MSG_SHUTTER_ACTION_SUCCEED = "Message_RollladenErfolgreichGesteuert";
    public static String MSG_SHUTTER_ACTION_ABORTED = "Message_RollladenAktionManuellerAbbruch";
    
    
    
}
