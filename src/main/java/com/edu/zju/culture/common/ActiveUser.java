package com.edu.zju.culture.common;

import com.edu.zju.culture.mbg.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author y4oung
 * @date 2020/3/13 7:08 PM
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveUser {
    private User user;

    private List<String> roles;

    private List<String> permissions;
}
