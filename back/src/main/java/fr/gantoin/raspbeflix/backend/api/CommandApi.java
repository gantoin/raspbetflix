package fr.gantoin.raspbeflix.backend.api;

import static fr.gantoin.raspbeflix.backend.domain.CommandEnum.DISK_FREE;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import fr.gantoin.raspbeflix.backend.domain.CommandEnum;
import fr.gantoin.raspbeflix.backend.domain.CommandResponse;
import fr.gantoin.raspbeflix.backend.domain.DiskFreeLine;
import fr.gantoin.raspbeflix.backend.service.CommandService;
import fr.gantoin.raspbeflix.backend.service.DiskFreeLineMapper;
import fr.gantoin.raspbeflix.backend.service.VpnConfigurer;
import fr.gantoin.raspbeflix.backend.service.ip.IpRouteService;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CommandApi {

    @Autowired
    private CommandService commandService;

    @Autowired
    private DiskFreeLineMapper diskFreeLineMapper;

    @Autowired
    private IpRouteService ipRouteService;

    @Autowired
    private VpnConfigurer vpnConfigurer;

    @PostMapping(value = "/command/exec", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CommandResponse exec(@RequestBody String command) throws IOException {
        return new CommandResponse(commandService.exec(command));
    }

    @GetMapping("/ping-google")
    public CommandResponse pingGoogle() throws IOException {
        return new CommandResponse(commandService.exec(CommandEnum.PING_GOOGLE.getCommand()));
    }

    @GetMapping("/disk-free")
    public List<DiskFreeLine> diskFree() throws IOException {
        return commandService.exec(DISK_FREE.getCommand()).stream().map(diskFreeLineMapper::map).filter(Optional::isPresent)
                .map(Optional::get).collect(toList());
    }

    @GetMapping("/reboot")
    public CommandResponse reboot() throws IOException {
        return new CommandResponse(commandService.exec(CommandEnum.REBOOT.getCommand()));
    }

    @GetMapping("/poweroff")
    public CommandResponse poweroff() throws IOException {
        return new CommandResponse(commandService.exec(CommandEnum.POWER_OFF.getCommand()));
    }

    @GetMapping("/rasp-ip")
    public String getRaspIp() throws IOException {
        return ipRouteService.getRaspIp(commandService.exec(CommandEnum.IP_ROUTE.getCommand()));
    }

    @GetMapping("/reset")
    public void reset() throws IOException {
        ipRouteService.getRaspIp(commandService.exec(CommandEnum.RESET.getCommand()));
        ipRouteService.getRaspIp(commandService.exec(CommandEnum.REBOOT.getCommand()));
    }

    @PostMapping(value = "/vpn")
    public CommandResponse uploadFile(@RequestPart MultipartFile file, @RequestParam String user, @RequestParam String password) throws IOException {
        vpnConfigurer.storeVpnFile(file);
        vpnConfigurer.storeVpnCredentials(user, password);
        return new CommandResponse(List.of("You successfully uploaded " + file.getOriginalFilename() + "!"));
    }

}
