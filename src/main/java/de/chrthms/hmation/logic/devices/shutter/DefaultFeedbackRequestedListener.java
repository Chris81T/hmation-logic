package de.chrthms.hmation.logic.devices.shutter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static de.chrthms.hmation.logic.ProcessConstants.VAR_FEEDBACK_REQUESTED;

/**
 * Created by christian on 15.10.16.
 */
public class DefaultFeedbackRequestedListener implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Object feedbackRequested = execution.getVariable(VAR_FEEDBACK_REQUESTED);
        if (feedbackRequested == null) {
            execution.setVariable(VAR_FEEDBACK_REQUESTED, false);
        }
    }

}
