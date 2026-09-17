package com.sece.student.controller;

import com.sece.student.entity.Studententity;
import com.sece.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StudentWebController {

    private final StudentService studentService;

    public StudentWebController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!model.containsAttribute("student")) {
            model.addAttribute("student", new Studententity());
        }
        model.addAttribute("students", studentService.getAllStudents());
        return "index";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Studententity student, RedirectAttributes redirectAttributes) {
        boolean isUpdate = student.getId() > 0;
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("message",
                isUpdate ? "Student updated successfully!" : "Student registered successfully!");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable int id, Model model) {
        Studententity student = studentService.getStudentById(id)
                .orElse(new Studententity());
        model.addAttribute("student", student);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("isEditing", true);
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id, RedirectAttributes redirectAttributes) {
        boolean deleted = studentService.deleteStudent(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("message", "Student deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Student with ID " + id + " not found.");
        }
        return "redirect:/";
    }
}
