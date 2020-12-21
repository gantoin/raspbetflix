package fr.gantoin.raspbeflix.backend.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public List<String> exec(String command) throws IOException {
        ProcessBuilder builder = new ProcessBuilder();
        return getOutput(builder.command("sh", "-c", command).start());
    }

    private List<String> getOutput(Process process) throws IOException {
        List<String> strings = new ArrayList<>();

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(process.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(process.getErrorStream()));

        String s = null;
        while ((s = stdInput.readLine()) != null) {
            strings.add(s);
        }

        while ((s = stdError.readLine()) != null) {
            strings.add(s);
        }

        return strings;
    }
}
