package fr.gantoin.raspbeflix.backend.service.dhcp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DhcpMapperIT {

    private final DhcpMapper dhcpMapper = new DhcpMapper();

    @Test
    void map() {
        List<String> input = new ArrayList<>();
        input.add("default via 192.168.1.254 dev eth0 proto dhcp src 192.168.1.48 metric 202 \n");
        input.add("192.168.1.0/24 dev eth0 proto dhcp scope link src 192.168.1.48 metric 202 ");
        assertThat(dhcpMapper.map(input).getIpAddress()).isEqualTo("192.168.1.48/24");
        assertThat(dhcpMapper.map(input).getRouters()).isEqualTo("192.168.1.254");
    }
}
