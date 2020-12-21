package fr.gantoin.raspbeflix.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class DhcpConf {

    private String ipAddress;
    private String routers;

}
