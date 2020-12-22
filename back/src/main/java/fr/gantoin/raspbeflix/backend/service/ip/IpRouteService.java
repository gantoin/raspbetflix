package fr.gantoin.raspbeflix.backend.service.ip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import fr.gantoin.raspbeflix.backend.domain.CommandEnum;
import fr.gantoin.raspbeflix.backend.service.CommandService;
import fr.gantoin.raspbeflix.backend.service.dhcp.DhcpMapper;
import fr.gantoin.raspbeflix.backend.service.dhcp.DhcpService;

@Component
@Slf4j
public class IpRouteService {

    public static final String RASPBETFLIX_CONF = "/home/pi/raspbetflix.text";
    public static final String DHCP_CONF = "/etc/dhcp.conf";

    @Autowired
    private CommandService commandService;

    @Autowired
    private IpRouteMapper ipRouteMapper;

    @Autowired
    private DhcpService dhcpService;

    @Autowired
    private DhcpMapper dhcpMapper;

    @PostConstruct
    public void init() throws IOException {
        sendIp();
    }

    public void sendIp() throws IOException {
        if (!new File(RASPBETFLIX_CONF).exists()) {
            List<String> exec = commandService.exec(CommandEnum.IP_ROUTE.getCommand());
            String myRaspIp = getRaspIp(exec);
            final String uri = "https://app-38ba6f2f-f09b-4b49-982e-947344e0205c.cleverapps.io/my-rasp/set-up/" + myRaspIp;
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            createConfFile(myRaspIp);
            configureDhcp(exec);
        } else {
            log.trace("raspbetflix.conf already exists");
        }
    }

    public String getRaspIp(List<String> exec) {
        return ipRouteMapper.map(exec).orElse("Error");
    }

    private void createConfFile(String raspIp) throws IOException {
        File f = new File(RASPBETFLIX_CONF);
        f.getParentFile().mkdirs();
        f.createNewFile();
        FileWriter myWriter = new FileWriter(RASPBETFLIX_CONF);
        myWriter.write(raspIp);
        myWriter.close();
    }

    private void configureDhcp(List<String> outputCommand) throws IOException {
        FileWriter myWriter = new FileWriter(DHCP_CONF);
        myWriter.write(dhcpService.getConf(dhcpMapper.map(outputCommand)));
        myWriter.close();
    }
}
