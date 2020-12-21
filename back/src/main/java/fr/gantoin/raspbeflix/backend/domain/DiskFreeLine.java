package fr.gantoin.raspbeflix.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class DiskFreeLine {

    private String diskName;
    private String path;
    private Long filling;
    private Long freeSpace;
    private Long totalSpace;

}
