package fr.gantoin.raspbeflix.backend.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VpnConfigurer {

    public void storeVpnFile(MultipartFile file) throws IOException {
        File f = new File("~/vpn/credentials.txt");
        f.getParentFile().mkdirs();
        f.createNewFile();
        try (OutputStream os = Files.newOutputStream(f.toPath())) {
            os.write(file.getBytes());
        }
    }

    public void storeVpnCredentials(String user, String password) throws IOException {
        File f = new File("~/vpn/credentials.txt");
        f.getParentFile().mkdirs();
        f.createNewFile();
        FileWriter myWriter = new FileWriter("~/vpn/credentials.txt");
        myWriter.write(user + "\n" + password);
        myWriter.close();
    }

}
