package com.playstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInstallStatusDTO {
    private Long appId;
    private boolean installed;
}
