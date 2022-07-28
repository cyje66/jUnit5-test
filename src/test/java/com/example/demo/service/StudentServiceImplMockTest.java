package com.example.demo.service;

import com.example.demo.dao.StudentDao;
import com.example.demo.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceImplMockTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentDao studentDao;

    @Test
    public void getById() {
        Student mockStudent = new Student();
        mockStudent.setId(100);
        mockStudent.setName("I'm mock");

        Mockito.when(studentDao.getById(Mockito.any())).thenReturn(mockStudent);

        Student student = studentService.getById(2);
        assertNotNull(student);
        assertEquals(100, student.getId());
        assertEquals("I'm mock", student.getName());
    }
}