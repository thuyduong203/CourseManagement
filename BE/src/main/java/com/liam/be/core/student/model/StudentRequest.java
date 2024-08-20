package com.liam.be.core.student.model;

import com.liam.be.entity.User;
import com.liam.be.infrastructure.constant.GenderUser;
import com.liam.be.infrastructure.constant.StatusEntity;
import com.liam.be.infrastructure.util.DateUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequest {
    private Long id;

    private String code;

    private String name;

    private String birthDate;

    private String email;

    private String phone;

    private String address;

    private String gender;

    private String roleUser;

    private String status;

    private String password;

    private Long courseId;

    public User tranferUser(StudentRequest req, User student) {
        student.setName(req.getName());
        if (req.getBirthDate() != null && !req.getBirthDate().isBlank()) {
            student.setBirthDate(DateUtil.stringToDate(req.getBirthDate()));
        }
        student.setPhone(req.getPhone());
        if (req.getAddress() != null && !req.getAddress().isBlank()) {
            student.setAddress(req.getAddress());
        }
        if (req.getGender() != null && !req.getGender().isBlank()) {
            student.setGender(GenderUser.valueOf(req.getGender()));
        }
        if (req.getStatus() != null && !req.getStatus().isBlank()) {
            student.setStatus(StatusEntity.valueOf(req.getStatus()));
        }
        return student;
    }

    public StudentRequest userTranfer(User student) {
        return StudentRequest.builder()
                .id(student.getId())
                .code(student.getCode())
                .password(student.getAccount().getPassword())
                .email(student.getEmail())
                .name(student.getName())
                .birthDate(DateUtil.dateToString(student.getBirthDate()))
                .phone(student.getPhone())
                .address(student.getAddress())
                .gender(student.getGender().toString())
                .status(student.getStatus().toString())
                .build();
    }
}
