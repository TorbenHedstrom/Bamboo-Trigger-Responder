<script type="text/javascript">
    var selectedTriggerType = '${Scheduled}Block';
    function triggerTypeChanged(newTriggerType) {
        newTriggerType = newTriggerType + 'Block';
        jQuery('#' + selectedTriggerType).hide();
        selectedTriggerType = newTriggerType;
        jQuery('#' + selectedTriggerType).show();
    }
    ;
</script>
[@ui.bambooSection titleKey='org.hedstroem.trigger.extractor.choose.configuration.label.title' ]

<input id="${Scheduled}" type="radio" name="triggerType" onclick="triggerTypeChanged('${Scheduled}')" checked/>
<label for="${Scheduled}">${Scheduled}</label>

<input id="${Commit}" type="radio" name="triggerType" onclick="triggerTypeChanged('${Commit}')"/>
<label for="${Commit}">${Commit}</label>

<input id="${Manual}" type="radio" name="triggerType" onclick="triggerTypeChanged('${Manual}')"/>
<label for="${Manual}">${Manual}</label>
[/@ui.bambooSection]

[@ui.bambooSection titleKey='org.hedstroem.trigger.extractor.configure.label.title' ]
<div id="${Scheduled}Block">
    <table>
        <tr>
            <td>
                [@ww.textfield labelKey="org.hedstroem.trigger.extractor.scheduled.configuration.label.param.name" name="scheduledTriggerParamName" /]
            [@ww.textfield labelKey="org.hedstroem.trigger.extractor.scheduled.configuration.label.profiles" name="scheduledTriggerProfiles" /]
            </td>
        </tr>
    </table>
</div>
<div id="${Commit}Block" style="display: none">
    <table>
        <tr>
            <td>
                [@ww.textfield labelKey="org.hedstroem.trigger.extractor.commit.configuration.label.param.name" name="commitTriggerParamName" /]
            [@ww.textfield labelKey="org.hedstroem.trigger.extractor.commit.configuration.label.profiles" name="commitTriggerProfiles" /]
            </td>
        </tr>
    </table>
</div>
<div id="${Manual}Block" style="display: none">
    <table>
        <tr>
            <td>
                [@ww.textfield labelKey="org.hedstroem.trigger.extractor.manual.configuration.label.param.name" name="manualTriggerParamName" /]
            [@ww.textfield labelKey="org.hedstroem.trigger.extractor.manual.configuration.label.profiles" name="manualTriggerProfiles" /]
            </td>
        </tr>
    </table>
</div>
[/@ui.bambooSection ]