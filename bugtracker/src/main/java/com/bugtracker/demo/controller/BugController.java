package com.bugtracker.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bugtracker.demo.model.Bug;
import com.bugtracker.demo.service.BugService;

@RestController
@RequestMapping("/bugs")
public class BugController {
    @Autowired
    private BugService bugService;

    @PostMapping("")
    public ResponseEntity<Bug> createBug(@RequestBody Bug bug) {
        Bug createdBug = bugService.createBug(bug);
        return ResponseEntity.created(URI.create("/bugs/" + createdBug.getId())).body(createdBug);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bug> getBugById(@PathVariable Long id) {
        Bug bug = bugService.getBugById(id);
        return ResponseEntity.ok(bug);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bug> updateBug(@PathVariable Long id, @RequestBody Bug bug) {
        Bug updatedBug = bugService.updateBug(id, bug);
        return ResponseEntity.ok(updatedBug);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Bug>> getAllBugs() {
        List<Bug> bugs = bugService.getAllBugs();
        return ResponseEntity.ok(bugs);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Bug>> searchBugs(@RequestParam(required = false) String name, @RequestParam(required = false) String project, @RequestParam(required = false) String status) {
        List<Bug> bugs = bugService.searchBugs(name, project, status);
        return ResponseEntity.ok(bugs);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Bug> updateBugStatus(@PathVariable Long id, @RequestBody String status) {
        Bug updatedBug = bugService.updateBugStatus(id, status);
        return ResponseEntity.ok(updatedBug);
    }
}
