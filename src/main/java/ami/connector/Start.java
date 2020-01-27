package ami.connector;

import ami.connector.events.DialStateEvent;
import ami.connector.events.HangupEvent;
import ami.connector.events.NewExtenEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
    //
    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new Exception("Incorrect arguments in JVM");
        }
        asteriskHost = args[0];
        asteriskUsername = args[1];
        asteriskPassword = args[2];
        ccHost = args[3];
        System.out.println(
                        "asteriskHost: " + asteriskHost + "\n" +
                        "asteriskUsername: " + asteriskUsername + "\n" +
                        "asteriskPassword: " + asteriskPassword + "\n" +
                        "ccHost: " + ccHost + "\n"
        );



        Start start = new Start();
        dt = new DataTransfer();
        while (true) {
            start.readAsteriskAmiMessages();
            System.out.println("reconnect to ami after timeout");
        }
    }

    private Boolean validateProgramArguments(String[] args) {
        return args.length == 3;
    }

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
        managerConnection.registerUserEventClass(DialStateEvent.class);
        managerConnection.deregisterEventClass(org.asteriskjava.manager.event.HangupEvent.class);
        managerConnection.registerUserEventClass(HangupEvent.class);
        managerConnection.deregisterEventClass(org.asteriskjava.manager.event.NewExtenEvent.class);
        managerConnection.registerUserEventClass(ami.connector.events.NewExtenEvent.class);
        managerConnection.addEventListener(this);
        managerConnection.login();
        managerConnection.sendAction(new StatusAction());

        Thread.sleep(0x7fffffffffffffffL);
        managerConnection.logoff();
    }


    private String objectToJson(Object o) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = om.writeValueAsString(o);
        System.out.println("JSON: " + json);
        return json;
    }

    /**
     * Event handler
     * @param event
     */
    public void onManagerEvent(ManagerEvent event) {

        // Входящие "ЗВОНОК"
        if(event.getClass().equals(AgentCalledEvent.class)){
            try {
                dt.sendHttpPost(ccHost, "in-call-ringing", objectToJson(event));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // Входящие "ОТВЕТ"
        if(event.getClass().equals(AgentConnectEvent.class)){
            try {
                dt.sendHttpPost(ccHost, "in-call-answer", objectToJson(event));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // Входящие "ПОЛОЖИЛИ ТРУБКУ"
        if(event.getClass().equals(AgentCompleteEvent.class)){
            try {
                dt.sendHttpPost(ccHost, "in-call-hangup", objectToJson(event));
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
                    dt.sendHttpPost(ccHost, "out-call-ringing", objectToJson(ev));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Исходящие "ОТВЕТ"
        if (event.getClass().equals(BridgeEnterEvent.class)) {
            try {
                dt.sendHttpPost(ccHost, "out-call-answer", objectToJson(event));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        // Исходящие "ПОЛОЖИЛИ ТРУБКУ"
        if (event.getClass().equals(HangupEvent.class)) {
            try {
                dt.sendHttpPost(ccHost, "out-call-hangup", objectToJson(event));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }


//    private void testCam() {
//        while (true) {
//            MediaPlayerFactory mFactory = new MediaPlayerFactory();
//            MediaPlayer player= mFactory.newHeadlessMediaPlayer();
//            long currentTime = System.currentTimeMillis();
//            Timestamp tstart = new Timestamp(currentTime);
//            Timestamp tstop = new Timestamp(currentTime+10000);
//            String options = ":sout=#transcode{vcodec=webm,venc=x264{cfr=16},scale=1,acodec=mp4a,ab=160,channels=2,samplerate=44100}"
//                    + ":file{dst=" + tstart.getTime() + "-" + tstop.getTime() + ".mp4}";
//            player.playMedia("rtsp://admin:123@192.168.0.226:554/onvif1", options
//            );
//
////        player.saveSnapshot(new File("home\\aleksey\\testst.mp4"));
//
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            player.stop();
//        }
//    }

}
