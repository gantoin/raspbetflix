package fr.gantoin.raspbeflix.backend.service.ip;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class IpRouteMapper {

    public Optional<String> map(List<String> list) {
        StringBuilder string = new StringBuilder();
        for (String line : list) {
            string.append(line);
        }
        if (!string.toString().isEmpty()) {
            String substring = string.substring(string.lastIndexOf("src"), string.lastIndexOf("metric")) //
                    .replace("src", "") //
                    .replaceAll("\\s+", "");
            return Optional.of(substring);
        }
        return Optional.empty();
    }

}
