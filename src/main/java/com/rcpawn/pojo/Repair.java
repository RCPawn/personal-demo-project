package com.rcpawn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Repair {
    private Integer id;
    private String deviceName;
    private String reporterName;
    private String repairerName;
    private String status;
}
