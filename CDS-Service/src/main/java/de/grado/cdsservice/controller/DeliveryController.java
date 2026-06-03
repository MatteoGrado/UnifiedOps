package de.grado.cdsservice.controller;

import de.grado.cdsservice.dto.ReleaseDTO;
import de.grado.cdsservice.dto.ReleaseType;
import de.grado.cdsservice.dto.Supporter;
import de.grado.cdsservice.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cds")
@AllArgsConstructor
@Slf4j
public class DeliveryController
{
    private final DeliveryService deliveryService;

    @GetMapping("/release/{filename}")
    public void release(@PathVariable String filename, @RequestBody ReleaseDTO releaseDto, ReleaseType releaseType) throws Exception
    {
        deliveryService.releaseVideo(filename, releaseDto, releaseType);
    }
    /*
     * TODO:
     *  - Two paths for youtube and tiktok
     *  - Check if for supporter Only
     */
}
