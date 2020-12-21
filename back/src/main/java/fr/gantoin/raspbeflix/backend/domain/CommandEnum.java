package fr.gantoin.raspbeflix.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandEnum {

    POWER_OFF("sudo poweroff"),
    PING_GOOGLE("ping www.google.com -c 2"),
    REBOOT("sudo reboot"),
    DISK_FREE("df"),
    MY_PUBLIC_IP("wget -qO- http://ipecho.net/plain | xargs echo"),
    IP_ROUTE("ip route | grep eth0");

    private final String command;
    
}
