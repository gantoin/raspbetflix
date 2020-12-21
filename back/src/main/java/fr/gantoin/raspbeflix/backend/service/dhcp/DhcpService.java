package fr.gantoin.raspbeflix.backend.service.dhcp;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import fr.gantoin.raspbeflix.backend.domain.DhcpConf;

@Slf4j
@Service
public class DhcpService {

    public String getConf(DhcpConf dhcpConf) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("interface eth0\n");
        stringBuilder.append("static ip_address=").append(dhcpConf.getIpAddress()).append("\n");
        stringBuilder.append("static routers=").append(dhcpConf.getRouters()).append("\n");
        return stringBuilder.toString();
    }

}
