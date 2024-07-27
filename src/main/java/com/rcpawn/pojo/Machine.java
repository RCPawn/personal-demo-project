package com.rcpawn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Machine {
    private Integer id;
    private String machineName;
    private String type;
    private Integer amount;
    private String location;
    private String state;
    private String factory;
    private String link;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
