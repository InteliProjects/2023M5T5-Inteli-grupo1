package com.pegasus.pegasus.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExportExcelDTO {
    private List<String> AllPaths;
    private String baseFilename;
}
