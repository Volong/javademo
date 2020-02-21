package github.io.longo.jcip.examples;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * ThreadDeadlock
 * <p/>
 * Task that deadlocks in a single-threaded Executor
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ThreadDeadlock {
//    private static final ExecutorService exec = Executors.newSingleThreadExecutor();

    private static final ExecutorService exec = Executors.newCachedThreadPool();
    
    public class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        public String call() throws Exception {
            // Here's where we would actually read the file
            return "";
        }
    }

    public class RenderPageTask implements Callable<String> {
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit(new LoadFileTask("header.html"));
            footer = exec.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // Will deadlock -- task waiting for result of subtask
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            // Here's where we would actually render the page
            return "";
        }
    }
    
	public static void main(String[] args) throws Exception {
		ThreadDeadlock t = new ThreadDeadlock();
		RenderPageTask r = t.new RenderPageTask();
		
		Future<String> submit = exec.submit(r);
		System.out.println(submit.get());
		
	}
}
