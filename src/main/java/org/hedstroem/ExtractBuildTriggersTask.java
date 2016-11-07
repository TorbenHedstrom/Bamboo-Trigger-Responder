package org.hedstroem;

import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.configuration.ConfigurationMap;
import com.atlassian.bamboo.task.*;
import com.atlassian.bamboo.v2.build.trigger.CodeChangedTriggerReason;
import com.atlassian.bamboo.v2.build.trigger.ManualBuildTriggerReason;
import com.atlassian.bamboo.v2.build.trigger.ScheduledTriggerReason;
import com.atlassian.bamboo.v2.build.trigger.TriggerReason;
import com.atlassian.bamboo.variable.CustomVariableContext;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;


/**
 * Created by torben on 07/11/15.
 */
@ExportAsService({ExtractBuildTriggersTask.class})
public class ExtractBuildTriggersTask implements TaskType {
    @ComponentImport
    private final CustomVariableContext customVariableContext;

    @Inject
    public ExtractBuildTriggersTask(CustomVariableContext customVariableContext) {
        this.customVariableContext = customVariableContext;
    }

    /**
     * @param taskContext The TaskContext
     * @return The TaskResult
     * @throws TaskException
     */
    @NotNull
    @Override
    public TaskResult execute(@NotNull TaskContext taskContext) throws TaskException {
        final BuildLogger buildLogger = taskContext.getBuildLogger();
        buildLogger.addBuildLogEntry("Extracting trigger information, and setting parameters accordingly.");

        TriggerReason triggerReason = taskContext.getBuildContext().getTriggerReason();
        buildLogger.addBuildLogEntry(String.format("Build trigger identified as %s", triggerReason.getName()));

        String triggerReasonParameter = triggerReason.getName().replaceAll(" ", "-");

        buildLogger.addBuildLogEntry(String.format("Setting variable %s to %s", ExtractBuildTriggersTaskConfig.BAMBOO_TRIGGER_TYPE_KEY, triggerReasonParameter));
        customVariableContext.addCustomData(ExtractBuildTriggersTaskConfig.BAMBOO_TRIGGER_TYPE_KEY, triggerReasonParameter);
        ConfigurationMap configurationMap = taskContext.getConfigurationMap();

        if (triggerReason instanceof ManualBuildTriggerReason) {
            String manualParamName = configurationMap.get(ExtractBuildTriggersTaskConfig.MANUAL_TRIGGER_PARAM_NAME);
            String manualParamValue = configurationMap.get(ExtractBuildTriggersTaskConfig.MANUAL_TRIGGER_PARAM_VALUE);

            if (manualParamName != null && manualParamName != null) {
                buildLogger.addBuildLogEntry(String.format("Setting build trigger variable with name %s to %s", manualParamName, manualParamValue));
                customVariableContext.addCustomData(manualParamName, manualParamValue);
            }
        } else if (triggerReason instanceof ScheduledTriggerReason) {
            String scheduledParamName = configurationMap.get(ExtractBuildTriggersTaskConfig.SCHEDULE_TRIGGER_PARAM_NAME);
            String scheduledParamValue = configurationMap.get(ExtractBuildTriggersTaskConfig.SCHEDULE_TRIGGER_PARAM_VALUE);

            if (scheduledParamName != null && scheduledParamName != null) {
                buildLogger.addBuildLogEntry(String.format("Setting build trigger variable with name %s to %s", scheduledParamName, scheduledParamValue));
                customVariableContext.addCustomData(scheduledParamName, scheduledParamValue);
            }
        } else if (triggerReason instanceof TriggerReason) {
            System.out.println("JIRA trigger reason");
        } else if (triggerReason instanceof CodeChangedTriggerReason) {
            String commitParamName = configurationMap.get(ExtractBuildTriggersTaskConfig.COMMIT_TRIGGER_PARAM_NAME);
            String commitParamValue = configurationMap.get(ExtractBuildTriggersTaskConfig.COMMIT_TRIGGER_PARAM_VALUE);

            if (commitParamName != null && commitParamValue != null) {
                buildLogger.addBuildLogEntry(String.format("Setting build trigger variable with name %s to %s", commitParamName, commitParamValue));
                customVariableContext.addCustomData(commitParamName, commitParamValue);
            }
        }
        return TaskResultBuilder.newBuilder(taskContext).success().build();


    }
}
