package fr.gantoin.raspbeflix.backend.service;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import fr.gantoin.raspbeflix.backend.domain.DiskFreeLine;

class DiskFreeLineMapperIT {

    private final DiskFreeLineMapper diskFreeLineMapper = new DiskFreeLineMapper();

    @Test
    void map() {

        List<String> result = List.of( //
                "Sys. de fichiers blocs de 1K Utilisé Disponible Uti% Monté sur",
                "/dev/root           14989480 4391004    9936600  31% /",
                "devtmpfs              440240       0     440240   0% /dev",
                "mpfs                 473520       8     473512   1% /dev/shm",
                "tmpfs                 473520    6432     467088   2% /run",
                "tmpfs                   5120       4       5116   1% /run/lock",
                "tmpfs                 473520       0     473520   0% /sys/fs/cgroup",
                "/dev/mmcblk0p1        258095   56047     202048  22% /boot",
                "tmpfs                  94704       0      94704   0% /run/user/1000");
        List<DiskFreeLine> mapped = result.stream().map(diskFreeLineMapper::map).filter(Optional::isPresent)
                .map(Optional::get).collect(toList());
        assertThat(mapped.size()).isEqualTo((8));
        assertThat(mapped.get(0).getDiskName()).isEqualTo("/dev/root");
        assertThat(mapped.get(0).getPath()).isEqualTo("/");
        assertThat(mapped.get(0).getFilling()).isEqualTo(31);
    }
}
