package fr.gantoin.raspbeflix.backend.service.dhcp;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.gantoin.raspbeflix.backend.domain.DhcpConf;
import fr.gantoin.raspbeflix.backend.service.ip.IpRouteMapper;

@Service
public class DhcpMapper {

    private final IpRouteMapper ipRouteMapper = new IpRouteMapper();

    public DhcpConf map(List<String> list) {
        StringBuilder string = new StringBuilder();
        for (String line : list) {
            string.append(line);
        }
        DhcpConf dhcpConf = new DhcpConf();
        String localIp = ipRouteMapper.map(list).orElseThrow();
        String shore = string.substring(string.indexOf("/"), string.indexOf("/") + 3);
        dhcpConf.setIpAddress(localIp + shore);
        String routers = string.substring(string.indexOf("via "), string.indexOf("dev")) //
                .replace("via", "") //
                .replaceAll("\\s+", "");
        dhcpConf.setRouters(routers);
        return dhcpConf;
    }

}
