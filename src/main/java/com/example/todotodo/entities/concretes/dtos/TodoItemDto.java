package com.example.todotodo.entities.concretes.dtos;

import java.util.Date;

import com.example.todotodo.entities.concretes.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoItemDto {
    private Long id;
    private String description;
    private Status status;
    private Date fdate;
    private Date createdDate;
    private Long user_id;
    private String fullName;
}
