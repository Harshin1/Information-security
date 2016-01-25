package Package1;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
 
public class FileTracker {
 
	static DAO dao= new DAO();
    public static void main(String[] args) {
    	String type = null;
        try {
            WatchService watcherService = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get("C:/Users/Sri Divya/Desktop/PeerDownload");
            dir.register(watcherService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
             
            while (true) {
                WatchKey watchKey;
                try {
                    watchKey = watcherService.take();
                } catch (InterruptedException ex) {
                    return;
                }
                 
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                     
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();  
                    switch(kind.name())
                    {
                    case "ENTRY_CREATE": type= "Dowloaded File";
                           break;
                    case "ENTRY_DELETE": type= "Deleted File";
                    break;
                    case "ENTRY_MODIFY": type= "Modified File";
                    break;
                 
                    }
                    if(type=="Modified File")
                    {
                    	dao.reduceScore("Harshini");
                    }
                     dao.storeAccessDetails("Harshini", fileName.toString(), type);
                }
                 
                boolean valid = watchKey.reset();
                if (!valid) {
                    break;
                }
            }
                  
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        finally
        {
        	
        	System.out.println("exit");
        }
    }
    
}

