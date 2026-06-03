package de.grado.cdsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ReleaseDTO
{
    private String title;
    private String description;
    private Supporter supporterOnly;
    private Map<String, String> linkList;
    private ReleaseType releaseType;
    private LocalDateTime releaseAt;
}
