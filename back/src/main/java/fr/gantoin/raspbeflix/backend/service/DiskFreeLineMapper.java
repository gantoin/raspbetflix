package fr.gantoin.raspbeflix.backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.gantoin.raspbeflix.backend.domain.DiskFreeLine;

@Service
public class DiskFreeLineMapper {

    public Optional<DiskFreeLine> map(String string) {
        if (string.startsWith("Sys")) {
            return Optional.empty();
        } else {
            DiskFreeLine diskFreeLine = new DiskFreeLine();
            diskFreeLine.setDiskName(string.substring(0, string.indexOf(" ")));
            diskFreeLine.setPath(string.substring(string.lastIndexOf(" ")).replaceAll("\\s+", ""));
            diskFreeLine.setFilling(Long.parseLong(string.substring(string.indexOf("%") - 3, string.indexOf("%")).replaceAll("\\s+", "")));
            return Optional.of(diskFreeLine);
        }
    }

}
