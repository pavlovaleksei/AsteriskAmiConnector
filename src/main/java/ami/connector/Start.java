/*
 * Copyright (c) 2020. Alfatell
 * Developer Pavlov Aleksey alekseypavlov1998@gmail.com
 */

package ami.connector;

import ami.connector.events.DialStateEvent;
import ami.connector.events.HangupEvent;
import ami.connector.events.NewExtenEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.log4j.Logger;
import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.*;
import com.fasterxml.jackson.core.*;

import java.io.IOException;

/**
 * @Author Pavlov Aleksey
 * @Date 22.01.2020
 */
public class Start implements ManagerEventListener {
    private ManagerConnection managerConnection;
    private static String asteriskHost;
    private static String asteriskUsername;
    private static String asteriskPassword;
    private static String ccHost;
    private static DataTransfer dt;

    // PARAMS
    // 1 - asterisk host
    // 2 - asterisk user
    // 3 - asterisk password
    // 4 - ContectCenter Alfatell host (http://hostname:port)
    // Example - 192.168.0.105 cc-manager ef18d7cef8234542a7a9bae2da8495b4 http://localhost:8082/server/ami
    //
    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new Exception("Incorrect arguments in JVM");
        }
        asteriskHost = args[0];
        asteriskUsername = args[1];
        asteriskPassword = args[2];
        ccHost = args[3];
        Logger.getRootLogger().info("Run program with params: \n" +
                        "asteriskHost: " + asteriskHost + "\n" +
                        "asteriskUsername: " + asteriskUsername + "\n" +
                        "asteriskPassword: " + asteriskPassword + "\n" +
                        "ccHost: " + ccHost + "\n"
        );

        Start start = new Start();
        dt = new DataTransfer();
        // Start listening events all time (i don`t know why start without while(true))
        while (true) {
            start.readAsteriskAmiMessages();
            Logger.getRootLogger().info("reconnect to ami after timeout");
        }
    }

    /**
     * Run program
     */
    private Start() {
//        ManagerConnectionFactory factory = new ManagerConnectionFactory("192.168.0.105", "cc-manager", "ef18d7cef8234542a7a9bae2da8495b4");
        ManagerConnectionFactory factory = new ManagerConnectionFactory(asteriskHost, asteriskUsername, asteriskPassword);
        this.managerConnection = factory.createManagerConnection();
    }

    /**
     * Read all messages from AMI interface
     * @throws IOException
     * @throws AuthenticationFailedException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    private void readAsteriskAmiMessages() throws IOException, AuthenticationFailedException, TimeoutException, InterruptedException {
        // Register custom events models
        managerConnection.registerUserEventClass(DialStateEvent.class);
        managerConnection.deregisterEventClass(org.asteriskjava.manager.event.HangupEvent.class);
        managerConnection.registerUserEventClass(HangupEvent.class);
        managerConnection.deregisterEventClass(org.asteriskjava.manager.event.NewExtenEvent.class);
        managerConnection.registerUserEventClass(ami.connector.events.NewExtenEvent.class);
        managerConnection.addEventListener(this);
        // Login in AsteriskAMI
        managerConnection.login();
        // Start listening AMI with all events
        managerConnection.sendAction(new StatusAction());
        // Sleep for listening max time (max Long value)
        Thread.sleep(0x7fffffffffffffffL);
        // After sleep logout from AMI interface
        managerConnection.logoff();
    }

    /**
     * Convert object to json with Jackson library
     * @param o Object to convert
     * @return JSON String
     * @throws JsonProcessingException
     */
    private String objectToJson(Object o) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return om.writeValueAsString(o);
    }

    /**
     * Event handler
     * @param event
     */
    public void onManagerEvent(ManagerEvent event) {

        // Входящие "ЗВОНОК"
        if(event.getClass().equals(AgentCalledEvent.class)){
            try {
                String json = objectToJson(event);
                Logger.getRootLogger().info("IN RINGING with data: [" + json + "]");
                dt.sendHttpPost(ccHost, "in-call-ringing", json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // Входящие "ОТВЕТ"
        if(event.getClass().equals(AgentConnectEvent.class)){
            try {
                String json = objectToJson(event);
                Logger.getRootLogger().info("IN ANSWER with data: [" + json + "]");
                dt.sendHttpPost(ccHost, "in-call-answer", json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // Входящие "ПОЛОЖИЛИ ТРУБКУ"
        if(event.getClass().equals(AgentCompleteEvent.class)){
            try {
                String json = objectToJson(event);
                Logger.getRootLogger().info("IN HANGUP with data: [" + json + "]");
                dt.sendHttpPost(ccHost, "in-call-hangup", json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // Исходящие "ЗВОНОК"
        if (event.getClass().equals(NewExtenEvent.class)) {
            NewExtenEvent ev = (NewExtenEvent)event;
            if (ev.getAppData().contains("ORG_OUTBOUND") && ev.getAppData().split("=").length == 2) {
                try {
                    ev.setCallerIDName(ev.getCallerIdName());
                    ev.setCallerIDNum(ev.getCallerIdNum());
                    String json = objectToJson(ev);
                    Logger.getRootLogger().info("OUT RINGING with data: [" + json + "]");
                    dt.sendHttpPost(ccHost, "out-call-ringing", json);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Исходящие "ОТВЕТ"
        if (event.getClass().equals(BridgeEnterEvent.class)) {
            try {
                String json = objectToJson(event);
                Logger.getRootLogger().info("OUT ANSWER with data: [" + json + "]");
                dt.sendHttpPost(ccHost, "out-call-answer", json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // Исходящие "ПОЛОЖИЛИ ТРУБКУ"
        if (event.getClass().equals(HangupEvent.class)) {
            try {
                String json = objectToJson(event);
                Logger.getRootLogger().info("OUT HANGUP with data: [" + json + "]");
                dt.sendHttpPost(ccHost, "out-call-hangup", json);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}
