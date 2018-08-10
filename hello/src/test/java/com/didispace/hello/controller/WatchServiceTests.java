package com.didispace.hello.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;
import java.util.Properties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WatchServiceTests {

    private WatchService watchService;
    private String filename;
    private ClassPathResource resource;
    private Properties properties;

    @Test
	public void test() {
        try {
            filename = "watchService.properties";
            resource = new ClassPathResource(filename);
            // 监听filename所在目录下的文件修改、删除事件
            watchService = FileSystems.getDefault().newWatchService();
            System.out.println(resource.getFile().getParent());
            Paths.get(resource.getFile().getParent())
                    .register(watchService,
                            StandardWatchEventKinds.ENTRY_MODIFY,
                            StandardWatchEventKinds.ENTRY_DELETE);
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        // 启动一个线程监听内容变化，并重新载入配置
        Thread watchThread = new Thread(() -> {
            while (true) {
                try {
                    WatchKey watchKey = watchService.take();
                    for(WatchEvent<?> event : watchKey.pollEvents()) {
                        if(Objects.equals(event.context().toString(), filename)) {
                            properties = PropertiesLoaderUtils.loadProperties(resource);
                            break;
                        }
                    }

                    watchKey.reset();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        });
        watchThread.setDaemon(true);
        watchThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }

        System.out.println(properties.get("name"));
    }


}
