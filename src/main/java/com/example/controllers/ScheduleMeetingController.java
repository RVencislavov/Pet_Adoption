package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleMeetingController {
    @GetMapping("/about-schedule-a-meeting")
    public String aboutScheduleMeeting(Model model) {
        return "about-schedule-a-meeting";
    }

    @GetMapping("/schedule-a-meeting")
    public String scheduleMeeting(Model model) {
        return "schedule-a-meeting";
    }
}
