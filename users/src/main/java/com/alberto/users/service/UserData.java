package com.alberto.users.service;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserData {

    private long id;
    private String username;
    private String password;
    private List<String> authorityList;
}
