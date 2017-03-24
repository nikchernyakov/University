import java.util.Random;

/**
 * This class implement generator of Requests for Server
 * While server work, generator create new requests and connect him with server
 * Period of wait between generate requests order in 0...50 mls
 */

public class RandomizeRequest extends Thread {
    private Server server;

    RandomizeRequest(Server server) {
        this.server = server;
    }

    @Override
    public void run(){
        Random r = new Random();
        // Generate new requests, while server have time for this
        while(server.getEndTime() > server.getCurrentTime()){
            // Generate settings to new request
            int res = r.nextInt(server.getStartResource() + 1) + 1;
            int t = r.nextInt((int)server.getEndTime()/2 + 1) + 1;

            // Create new request
            Request request = new Request(server, "Request " + server.addRequest(), res, t);
            server.addToLog("Perform Request: " + request.getName() + "\n" +
                    "res: " + request.getResource() + " time: " + request.getTime() + "\n");
            request.start(); // Start request

            // Wait random time for next request
            try {
                sleep(r.nextInt(50));
            } catch (InterruptedException e) {
                System.out.println("ERROR: Generator can't wait\n");
            }
        }
        System.out.println("INFO: Finished generate new Requests\n");
    }
}
