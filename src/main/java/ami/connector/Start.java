package ami.connector;

import ami.connector.events.DialStateEvent;
import ami.connector.events.HangupEvent;
import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.*;

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
        managerConnection.addEventListener(this);
        managerConnection.login();
        managerConnection.sendAction(new StatusAction());

        Thread.sleep(0x7fffffffffffffffL);
        managerConnection.logoff();
    }

    /**
     * Event handler
     * @param event
     */
    public void onManagerEvent(ManagerEvent event) {
        // Входящие "ЗВОНОК"
        if(event.getClass().equals(AgentCalledEvent.class)){
            System.err.println("IN RINGING: " + event);
        }

        // Входящие "ОТВЕТ"
        if(event.getClass().equals(AgentConnectEvent.class)){
            System.err.println("IN ANSWER: " + event);
        }

        // Входящие "ПОЛОЖИЛИ ТРУБКУ"
        if(event.getClass().equals(AgentCompleteEvent.class)){
            System.err.println("IN HANGUP (complete): " + event);
        }

        // Исходящие "ЗВОНОК"
        if (event.getClass().equals(DialStateEvent.class)) {
            System.err.println("OUT RINGING: " + event);
        }

        // Исходящие "ОТВЕТ"
        if (event.getClass().equals(BridgeEnterEvent.class)) {
            System.err.println("OUT ANSWER: " + event);
        }

        // Исходящие "ПОЛОЖИЛИ ТРУБКУ"
        if (event.getClass().equals(HangupEvent.class)) {
            System.err.println("OUT HANGUP: " + event);
        }
    }
}
