package com.alberto.users.controller.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails {

    private long id;
    private String username;
    private String password;
    private List<String> authorityList;
}
