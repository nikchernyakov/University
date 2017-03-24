import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main Thread, which have any count of resource and work time
 * Server will be print some notes to Terminal and to protocol
 * INFO: Inform about some good actions
 * ERROR: If some action have error
 */

public class Server extends Thread{

    private int resource; // Server resource
    private int startResource; // Server start count of resource
    private long time;  // Time that server will live
    private long startTime; // Time that server was started
    private int count; // Count of Requests
    private int completeCount; // Count of Complete requests
    private FileWriter log; // Protocol file
    private String logFileName; // Protocol file name

    Server(String configsFileName){
        System.out.println("INFO: Starting server...\n");
        parseConfigsFile(configsFileName); // Take and set settings from configs file
    }

    @Override
    public void run(){
        RandomizeRequest randomizeRequest = new RandomizeRequest(this);
        randomizeRequest.start();
        startTime = System.currentTimeMillis(); // Save start time;
        resource = startResource; // Set start count of resource
        addToLog("INFO: Server was started at time:" + startTime + "\n");
        // Wait work time
        try {
            sleep(getEndTime() - getCurrentTime());
        } catch (InterruptedException e) {
            System.out.println("ERROR: Error in Server work time \n");
        }
        addToLog("INFO: Server was ended!\n" + "All requests: " + count + "  Complete requests: " + completeCount + "\n");
        System.out.println("INFO: Server off\n");
    }

    /** This method get Resource for some Request, which we generate
     * Request have options: resource and time
     * If server has lot of resource, he get there for request
     * If server hasn't time for request, he won't perform this request
     * @param request
     * @return boolean that has positive value if server get resource for request
     */
    synchronized boolean getResource(Request request) {

        // Check for time
        if (getEndTime() - getCurrentTime() < request.getTime()) {
            addToLog("Didn't have time for " + request.getName() + "\n");
            return false;
        }

        // Check for resource
        if (resource < request.getResource()) {
            addToLog("Didn't have resource for " + request.getName() + "\n");
            return false;
        }

        resource -= request.getResource();
        return true;
    }

    /**
     * Parse config file
     * This method open config file and take 3 options.
     * Lines must sort in order: resource, time, log file name
     * If parser not founded someone option, he set a default value to this option
     * @param configsFileName
     */
    private void parseConfigsFile(String configsFileName) {
        // Create a options
        Scanner logScan;
        int resource = 0;
        long time = 0;
        String logFileName = null;

        System.out.println("INFO: Server try to set a configs...\n");

        // Open file
        try {
            logScan = new Scanner(new File(configsFileName));
        } catch (IOException ex) {
            System.out.println("ERROR: Server can't find file " + configsFileName + "\n" +
                    "Server will be use default configs\n");
            setDefaultConfigs(); // Set default value for all settings
            return;
        }

        // Check resource line
        if(logScan.hasNextLine()){
            resource = Integer.valueOf(logScan.nextLine());
        } else{
            System.out.println("ERROR: Server can't find setting `resource` \n " +
                    "Server will be use default setting\n");
        }

        // Check time line
        if(logScan.hasNextLong()){
            time = Long.valueOf(logScan.nextLine());
        } else{
            System.out.println("ERROR: Server can't find setting `time` \n " +
                    "Server will be use default setting\n");
        }

        // Check logName line
        if(logScan.hasNextLine()){
            logFileName = logScan.nextLine();
        } else{
            System.out.println("ERROR: Server can't find setting `logFileName` \n " +
                    "Server will be use default setting\n");
        }

        // Set default value to the options, that not founded
        setDefaultConfigs(resource, time, logFileName);
        System.out.println("INFO: Server set a configs successful\n");
    }

    private void setDefaultConfigs() {
        setDefaultConfigs(0, 0, null);
    }

    private void setDefaultConfigs(int resource, long time, String logFileName) {
        // Default value
        final int DEFAULT_RESOURCE = 1000;
        final long DEFAULT_TIME = 1000;
        final String DEFAULT_LOG_FILE_NAME = "log.txt";

        // Use default value, if options not founded (value == 0 or null)
        this.startResource = resource == 0 ? DEFAULT_RESOURCE : resource;
        this.time = time == 0 ? DEFAULT_TIME : time;
        this.logFileName = logFileName == null ? DEFAULT_LOG_FILE_NAME : logFileName;

        System.out.println("INFO: Server set settings [res = " + this.startResource + " time = " + this.time + " logName = "
            + this.logFileName + "]\n");

        // Create protocol file
        try {
            log = new FileWriter(this.logFileName);
        } catch (IOException e) {
            System.out.println("ERROR: Error create log file\n");
        }
    }

    // Return resource for server, when request complete his work
    synchronized void returnResource(int res){
        resource += res;
    }

    // Return the resource value at start time server
    int getStartResource() {
        return startResource;
    }

    // Return the time, that server must work
    long getEndTime() {
        return time;
    }

    // Return current time
    long getCurrentTime(){
        return System.currentTimeMillis() - startTime;
    }

    // Increase count of requests
    int addRequest(){
        return ++count;
    }
    // Increase count of completed requests
    int addCompleteRequest(){
        return ++completeCount;
    }

    // Write note to protocol
    synchronized void addToLog(String data) {
        try {
            log.write(data);
            log.flush();
        } catch (IOException e) {
            System.out.println("ERROR: Error write to log file\n");
        }
    }

}
