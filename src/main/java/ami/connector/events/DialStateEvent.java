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
public class DialStateEvent extends ManagerEvent {
    private static final long serialVersionUID = 2L;
    private String destchannel;
    private String destchannelstate;
    private String destaccountcode;
    private String linkedid;
    private String destcontext;
    private String calleridname;
    private String destlinkedid;
    private String destconnectedlinename;
    private String channel;
    private String language;
    private String privilege;
    private String destlanguage;
    private String exten;
    private String destexten;
    private String calleridnum;
    private String context;
    private String event;
    private String destuniqueid;
    private String connectedlinenum;
    private String uniqueid;
    private String channelstatedesc;
    private String dialstatus;
    private String destpriority;
    private String connectedlinename;
    private String priority;
    private String destcalleridname;
    private String channelstate;
    private String destconnectedlinenum;
    private String destchannelstatedesc;
    private String destcalleridnum;
    private String accountcode;

    public DialStateEvent(Object source) {
        super(source);
    }

    public String getDestchannel() {
        return destchannel;
    }

    public void setDestchannel(String destchannel) {
        this.destchannel = destchannel;
    }

    public String getDestchannelstate() {
        return destchannelstate;
    }

    public void setDestchannelstate(String destchannelstate) {
        this.destchannelstate = destchannelstate;
    }

    public String getDestaccountcode() {
        return destaccountcode;
    }

    public void setDestaccountcode(String destaccountcode) {
        this.destaccountcode = destaccountcode;
    }

    public String getLinkedid() {
        return linkedid;
    }

    public void setLinkedid(String linkedid) {
        this.linkedid = linkedid;
    }

    public String getDestcontext() {
        return destcontext;
    }

    public void setDestcontext(String destcontext) {
        this.destcontext = destcontext;
    }

    public String getCalleridname() {
        return calleridname;
    }

    public void setCalleridname(String calleridname) {
        this.calleridname = calleridname;
    }

    public String getDestlinkedid() {
        return destlinkedid;
    }

    public void setDestlinkedid(String destlinkedid) {
        this.destlinkedid = destlinkedid;
    }

    public String getDestconnectedlinename() {
        return destconnectedlinename;
    }

    public void setDestconnectedlinename(String destconnectedlinename) {
        this.destconnectedlinename = destconnectedlinename;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
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

    public String getDestlanguage() {
        return destlanguage;
    }

    public void setDestlanguage(String destlanguage) {
        this.destlanguage = destlanguage;
    }

    @Override
    public String getExten() {
        return exten;
    }

    @Override
    public void setExten(String exten) {
        this.exten = exten;
    }

    public String getDestexten() {
        return destexten;
    }

    public void setDestexten(String destexten) {
        this.destexten = destexten;
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

    public String getDestuniqueid() {
        return destuniqueid;
    }

    public void setDestuniqueid(String destuniqueid) {
        this.destuniqueid = destuniqueid;
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

    public String getDialstatus() {
        return dialstatus;
    }

    public void setDialstatus(String dialstatus) {
        this.dialstatus = dialstatus;
    }

    public String getDestpriority() {
        return destpriority;
    }

    public void setDestpriority(String destpriority) {
        this.destpriority = destpriority;
    }

    public String getConnectedlinename() {
        return connectedlinename;
    }

    public void setConnectedlinename(String connectedlinename) {
        this.connectedlinename = connectedlinename;
    }

    @Override
    public Integer getPriority() {
        return 1;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDestcalleridname() {
        return destcalleridname;
    }

    public void setDestcalleridname(String destcalleridname) {
        this.destcalleridname = destcalleridname;
    }

    public String getChannelstate() {
        return channelstate;
    }

    public void setChannelstate(String channelstate) {
        this.channelstate = channelstate;
    }

    public String getDestconnectedlinenum() {
        return destconnectedlinenum;
    }

    public void setDestconnectedlinenum(String destconnectedlinenum) {
        this.destconnectedlinenum = destconnectedlinenum;
    }

    public String getDestchannelstatedesc() {
        return destchannelstatedesc;
    }

    public void setDestchannelstatedesc(String destchannelstatedesc) {
        this.destchannelstatedesc = destchannelstatedesc;
    }

    public String getDestcalleridnum() {
        return destcalleridnum;
    }

    public void setDestcalleridnum(String destcalleridnum) {
        this.destcalleridnum = destcalleridnum;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }
}
