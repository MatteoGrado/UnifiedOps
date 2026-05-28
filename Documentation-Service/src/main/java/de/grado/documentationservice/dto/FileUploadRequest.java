package de.grado.documentationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class FileUploadRequest
{
    private BigInteger userId;
    private String fileName;
    private File file;
}
