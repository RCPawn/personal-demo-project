package com.rcpawn.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowReturn {
    private Integer id;
    private String machineName;
    private String username;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
