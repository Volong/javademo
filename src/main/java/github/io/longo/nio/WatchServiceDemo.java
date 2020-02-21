package github.io.longo.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取当前 OS 的文件监控系统
        WatchService newWatchService = FileSystems.getDefault().newWatchService();
        Paths.get("D:\\watch").register(newWatchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
        
        while (true) {
            WatchKey take = newWatchService.take();
            for (WatchEvent<?> event : take.pollEvents()) {
                System.out.println(event.context() + " comes to " + event.kind());
            }
            boolean reset = take.reset();
            System.out.println("reset:" + reset);
            if (!reset) {
                break;
            }
        }
    }
}
