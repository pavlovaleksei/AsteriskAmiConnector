/*
 * Copyright (c) 2020. Alfatell
 * Developer Pavlov Aleksey alekseypavlov1998@gmail.com
 */

package ami.connector.events;

import org.asteriskjava.manager.event.ManagerEvent;

/**
 * Override event
 */
public class NewExtenEvent extends ManagerEvent {
    private static final long serialVersionUID = 2L;
    private String Event;
    private String Privilege;
    private String Channel;
    private Integer ChannelState;
    private String ChannelStateDesc;
    private String CallerIDNum;
    private String CallerIDName;
    private String ConnectedLineNum;
    private String ConnectedLineName;
    private String Language;
    private String AccountCode;
    private String Context;
    private String Exten;
    private Integer Priority;
    private String Uniqueid;
    private String Linkedid;
    private String Extension;
    private String Application;
    private String AppData;

    public NewExtenEvent(Object source) {
        super(source);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    @Override
    public String getPrivilege() {
        return Privilege;
    }

    @Override
    public void setPrivilege(String privilege) {
        Privilege = privilege;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }

    @Override
    public Integer getChannelState() {
        return ChannelState;
    }

    public void setChannelState(Integer channelState) {
        ChannelState = channelState;
    }

    @Override
    public String getChannelStateDesc() {
        return ChannelStateDesc;
    }

    @Override
    public void setChannelStateDesc(String channelStateDesc) {
        ChannelStateDesc = channelStateDesc;
    }

    public String getCallerIDNum() {
        return CallerIDNum;
    }

    public void setCallerIDNum(String CallerIdNum) {
        CallerIDNum = CallerIdNum;
    }

    public String getCallerIDName() {
        return CallerIDName;
    }

    public void setCallerIDName(String CallerIdName) {
        CallerIDName = CallerIdName;
    }

    @Override
    public String getConnectedLineNum() {
        return ConnectedLineNum;
    }

    @Override
    public void setConnectedLineNum(String connectedLineNum) {
        ConnectedLineNum = connectedLineNum;
    }

    @Override
    public String getConnectedLineName() {
        return ConnectedLineName;
    }

    @Override
    public void setConnectedLineName(String connectedLineName) {
        ConnectedLineName = connectedLineName;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getAccountCode() {
        return AccountCode;
    }

    public void setAccountCode(String accountCode) {
        AccountCode = accountCode;
    }

    @Override
    public String getContext() {
        return Context;
    }

    @Override
    public void setContext(String context) {
        Context = context;
    }

    @Override
    public String getExten() {
        return Exten;
    }

    @Override
    public void setExten(String exten) {
        Exten = exten;
    }

    @Override
    public Integer getPriority() {
        return Priority;
    }

    public void setPriority(Integer priority) {
        Priority = priority;
    }

    public String getUniqueid() {
        return Uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        Uniqueid = uniqueid;
    }

    public String getLinkedid() {
        return Linkedid;
    }

    public void setLinkedid(String linkedid) {
        Linkedid = linkedid;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }

    public String getApplication() {
        return Application;
    }

    public void setApplication(String application) {
        Application = application;
    }

    public String getAppData() {
        return AppData;
    }

    public void setAppData(String appData) {
        AppData = appData;
    }
}
