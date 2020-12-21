package fr.gantoin.raspbeflix.backend.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import fr.gantoin.raspbeflix.backend.service.ip.IpRouteMapper;

class IpRouteMapperIT {

    private final IpRouteMapper ipRouteMapper = new IpRouteMapper();

    @Test
    void map() {
        List<String> input = new ArrayList<>();
        input.add("default via 192.168.1.254 dev eth0 proto dhcp src 192.168.1.48 metric 202 \n");
        input.add("192.168.1.0/24 dev eth0 proto dhcp scope link src 192.168.1.48 metric 202 ");
        String output = "192.168.1.48";
        assertThat(ipRouteMapper.map(input)).isEqualTo(Optional.of(output));
    }

    @Test
    void map_null() {
        List<String> input = new ArrayList<>();
        assertThat(ipRouteMapper.map(input)).isEqualTo(Optional.empty());
    }
}
