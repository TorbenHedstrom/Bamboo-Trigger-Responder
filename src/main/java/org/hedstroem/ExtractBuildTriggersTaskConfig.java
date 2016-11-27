package org.hedstroem;

import com.atlassian.bamboo.collections.ActionParametersMap;
import com.atlassian.bamboo.task.AbstractTaskConfigurator;
import com.atlassian.bamboo.task.TaskDefinition;
import com.atlassian.bamboo.utils.error.ErrorCollection;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Created by torben on 09/11/15.
 */
public class ExtractBuildTriggersTaskConfig extends AbstractTaskConfigurator {
    public static final String COMMIT_TRIGGER_PARAM_NAME = "commitTriggerParamName";
    public static final String COMMIT_TRIGGER_PARAM_VALUE = "commitTriggerProfiles";
    public static final String SCHEDULE_TRIGGER_PARAM_NAME = "scheduledTriggerParamName";
    public static final String SCHEDULE_TRIGGER_PARAM_VALUE = "scheduledTriggerProfiles";
    public static final String MANUAL_TRIGGER_PARAM_NAME = "manualTriggerParamName";
    public static final String MANUAL_TRIGGER_PARAM_VALUE = "manualTriggerProfiles";
    public static final String JIRA_TRIGGER_PARAM_NAME = "jiraTriggerParamName";
    public static final String JIRA_TRIGGER_PARAM_VALUE = "jiraTriggerProfiles";
    public static final String OTHER_TRIGGER_PARAM_NAME = "otherTriggerParamName";
    public static final String OTHER_TRIGGER_PARAM_VALUE = "otherTriggerProfiles";

    public static final String BAMBOO_TRIGGER_TYPE_KEY = "trigger.type";
    public static final String SCHEDULE_TRIGGER_NAME = "Scheduled";
    public static final String COMMIT_TRIGGER_NAME = "Commit";
    public static final String MANUAL_TRIGGER_NAME = "Manual";
    public static final String JIRA_TRIGGER_NAME = "Jira";
    public static final String OTHER_TRIGGER_NAME = "Other";

    @Override
    public void populateContextForCreate(Map<String, Object> context) {
        super.populateContextForCreate(context);
        initializeContext(context);
        setTriggernamesInContext(context);
    }

    @Override
    public void populateContextForEdit(Map<String, Object> context, TaskDefinition taskDefinition) {
        super.populateContextForEdit(context, taskDefinition);
        setTriggerContextFromTaskDef(context, taskDefinition);
        setTriggernamesInContext(context);
    }

    @Override
    public void populateContextForView(Map<String, Object> context, TaskDefinition taskDefinition) {
        super.populateContextForView(context, taskDefinition);
        setTriggerContextFromTaskDef(context, taskDefinition);
    }

    @NotNull
    @Override
    public Map<String, String> generateTaskConfigMap(ActionParametersMap params, TaskDefinition previousTaskDefinition) {
        Map<String, String> config = super.generateTaskConfigMap(params, previousTaskDefinition);
        config.put(COMMIT_TRIGGER_PARAM_NAME, params.getString(COMMIT_TRIGGER_PARAM_NAME));
        config.put(COMMIT_TRIGGER_PARAM_VALUE, params.getString(COMMIT_TRIGGER_PARAM_VALUE));
        config.put(SCHEDULE_TRIGGER_PARAM_NAME, params.getString(SCHEDULE_TRIGGER_PARAM_NAME));
        config.put(SCHEDULE_TRIGGER_PARAM_VALUE, params.getString(SCHEDULE_TRIGGER_PARAM_VALUE));
        config.put(MANUAL_TRIGGER_PARAM_NAME, params.getString(MANUAL_TRIGGER_PARAM_NAME));
        config.put(MANUAL_TRIGGER_PARAM_VALUE, params.getString(MANUAL_TRIGGER_PARAM_VALUE));

        config.put(JIRA_TRIGGER_PARAM_NAME, params.getString(JIRA_TRIGGER_PARAM_NAME));
        config.put(JIRA_TRIGGER_PARAM_VALUE, params.getString(JIRA_TRIGGER_PARAM_VALUE));

        config.put(OTHER_TRIGGER_PARAM_NAME, params.getString(OTHER_TRIGGER_PARAM_NAME));
        config.put(OTHER_TRIGGER_PARAM_VALUE, params.getString(OTHER_TRIGGER_PARAM_VALUE));

        return config;
    }

    @Override
    public void validate(@NotNull ActionParametersMap params, @NotNull ErrorCollection errorCollection) {
        super.validate(params, errorCollection);
    }

    private void initializeContext(Map<String, Object> context) {
        context.put(COMMIT_TRIGGER_PARAM_NAME, String.format("%s-name", COMMIT_TRIGGER_NAME));
        context.put(COMMIT_TRIGGER_PARAM_VALUE, String.format("%s-value", COMMIT_TRIGGER_NAME));
        context.put(SCHEDULE_TRIGGER_PARAM_NAME, String.format("%s-name", SCHEDULE_TRIGGER_NAME));
        context.put(SCHEDULE_TRIGGER_PARAM_VALUE, String.format("%s-value", SCHEDULE_TRIGGER_NAME));
        context.put(MANUAL_TRIGGER_PARAM_NAME, String.format("%s-name", MANUAL_TRIGGER_NAME));
        context.put(MANUAL_TRIGGER_PARAM_VALUE, String.format("%s-value", MANUAL_TRIGGER_NAME));
        context.put(JIRA_TRIGGER_PARAM_NAME, String.format("%s-name", JIRA_TRIGGER_NAME));
        context.put(JIRA_TRIGGER_PARAM_VALUE, String.format("%s-value", JIRA_TRIGGER_NAME));
        context.put(OTHER_TRIGGER_PARAM_NAME, String.format("%s-name", OTHER_TRIGGER_NAME));
        context.put(OTHER_TRIGGER_PARAM_VALUE, String.format("%s-value", OTHER_TRIGGER_NAME));
    }

    private void setTriggernamesInContext(Map<String, Object> context) {
        context.put(SCHEDULE_TRIGGER_NAME, SCHEDULE_TRIGGER_NAME);
        context.put(COMMIT_TRIGGER_NAME, COMMIT_TRIGGER_NAME);
        context.put(MANUAL_TRIGGER_NAME, MANUAL_TRIGGER_NAME);
        context.put(JIRA_TRIGGER_NAME, JIRA_TRIGGER_NAME);
        context.put(OTHER_TRIGGER_NAME, OTHER_TRIGGER_NAME);
    }

    private void setTriggerContextFromTaskDef(Map<String, Object> context, TaskDefinition taskDefinition) {
        context.put(COMMIT_TRIGGER_PARAM_NAME, taskDefinition.getConfiguration().get(COMMIT_TRIGGER_PARAM_NAME));
        context.put(COMMIT_TRIGGER_PARAM_VALUE, taskDefinition.getConfiguration().get(COMMIT_TRIGGER_PARAM_VALUE));
        context.put(SCHEDULE_TRIGGER_PARAM_NAME, taskDefinition.getConfiguration().get(SCHEDULE_TRIGGER_PARAM_NAME));
        context.put(SCHEDULE_TRIGGER_PARAM_VALUE, taskDefinition.getConfiguration().get(SCHEDULE_TRIGGER_PARAM_VALUE));
        context.put(MANUAL_TRIGGER_PARAM_NAME, taskDefinition.getConfiguration().get(MANUAL_TRIGGER_PARAM_NAME));
        context.put(MANUAL_TRIGGER_PARAM_VALUE, taskDefinition.getConfiguration().get(MANUAL_TRIGGER_PARAM_VALUE));
        context.put(JIRA_TRIGGER_PARAM_NAME, taskDefinition.getConfiguration().get(JIRA_TRIGGER_NAME));
        context.put(JIRA_TRIGGER_PARAM_VALUE, taskDefinition.getConfiguration().get(JIRA_TRIGGER_NAME));
        context.put(OTHER_TRIGGER_PARAM_NAME, taskDefinition.getConfiguration().get(OTHER_TRIGGER_NAME));
        context.put(OTHER_TRIGGER_PARAM_VALUE, taskDefinition.getConfiguration().get(OTHER_TRIGGER_NAME));
    }
}
