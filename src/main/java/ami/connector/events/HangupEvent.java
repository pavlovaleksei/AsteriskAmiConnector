/*
 * Copyright (c) 2020. Alfatell
 * Developer Pavlov Aleksey alekseypavlov1998@gmail.com
 */

package ami.connector.events;

import org.asteriskjava.manager.event.ManagerEvent;

/**
 * @Author Pavlov Aleksey
 * @Date 22.01.2020
 * Override event
 */
public class HangupEvent extends ManagerEvent {
    private static final long serialVersionUID = 2L;
    private String linkedid;
    private String calleridname;
    private String channel;
    private String cause;
    private String language;
    private String privilege;
    private String exten;
    private String calleridnum;
    private String context;
    private String event;
    private String connectedlinenum;
    private String uniqueid;
    private String channelstatedesc;
    private String causetxt;
    private String connectedlinename;
    private Integer priority;
    private String channelstate;
    private String accountcode;

    public HangupEvent(Object source) {
        super(source);
    }

    public String getLinkedid() {
        return linkedid;
    }

    public void setLinkedid(String linkedid) {
        this.linkedid = linkedid;
    }

    public String getCalleridname() {
        return calleridname;
    }

    public void setCalleridname(String calleridname) {
        this.calleridname = calleridname;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String getPrivilege() {
        return privilege;
    }

    @Override
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    @Override
    public String getExten() {
        return exten;
    }

    @Override
    public void setExten(String exten) {
        this.exten = exten;
    }

    public String getCalleridnum() {
        return calleridnum;
    }

    public void setCalleridnum(String calleridnum) {
        this.calleridnum = calleridnum;
    }

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public void setContext(String context) {
        this.context = context;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getConnectedlinenum() {
        return connectedlinenum;
    }

    public void setConnectedlinenum(String connectedlinenum) {
        this.connectedlinenum = connectedlinenum;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getChannelstatedesc() {
        return channelstatedesc;
    }

    public void setChannelstatedesc(String channelstatedesc) {
        this.channelstatedesc = channelstatedesc;
    }

    public String getCausetxt() {
        return causetxt;
    }

    public void setCausetxt(String causetxt) {
        this.causetxt = causetxt;
    }

    public String getConnectedlinename() {
        return connectedlinename;
    }

    public void setConnectedlinename(String connectedlinename) {
        this.connectedlinename = connectedlinename;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getChannelstate() {
        return channelstate;
    }

    public void setChannelstate(String channelstate) {
        this.channelstate = channelstate;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }
}

