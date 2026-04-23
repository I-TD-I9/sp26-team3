package com.example.spartanguide.mvc;

import com.example.spartanguide.entity.Guide;
import com.example.spartanguide.entity.Student;
import com.example.spartanguide.entity.StudentReview;
import com.example.spartanguide.entity.Tour;
import com.example.spartanguide.service.GuideReviewService;
import com.example.spartanguide.service.GuideService;
import com.example.spartanguide.service.StudentReviewService;
import com.example.spartanguide.service.StudentService;
import com.example.spartanguide.service.TourService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/guide")
public class GuideUiController {

    @Autowired
    private GuideService guideService;

    @Autowired
    private TourService tourService;

    @Autowired
    private GuideReviewService guideReviewService;

    @Autowired
    private StudentReviewService studentReviewService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long guideId = (Long) session.getAttribute("guideId");
        if (guideId == null) return "redirect:/signin";
        Guide guide = guideService.getGuideById(guideId).orElse(null);
        if (guide == null) return "redirect:/signin";
        List<Tour> tours = tourService.getToursByGuideId(guideId);
        model.addAttribute("currentGuide", guide);
        model.addAttribute("recentTours", tours);
        model.addAttribute("totalTours", tours.size());
        return "guide/dashboard";
    }

    @GetMapping("/tours")
    public String tourManagement(HttpSession session, Model model) {
        Long guideId = (Long) session.getAttribute("guideId");
        if (guideId == null) return "redirect:/signin";
        model.addAttribute("tours", tourService.getToursByGuideId(guideId));
        return "guide/tour-management";
    }

    @GetMapping("/tours/new")
    public String showCreateForm(HttpSession session, Model model) {
        if (session.getAttribute("guideId") == null) return "redirect:/signin";
        model.addAttribute("tour", new Tour());
        model.addAttribute("title", "Create a Tour");
        return "guide/tour-form";
    }

    @PostMapping("/tours/new")
    public String createTour(HttpSession session, @ModelAttribute Tour tour, MultipartFile image) {
        Long guideId = (Long) session.getAttribute("guideId");
        if (guideId == null) return "redirect:/signin";
        guideService.getGuideById(guideId).ifPresent(tour::setGuide);
        tour.setPublished(true);
        Tour created = tourService.createTour(tour);
        tourService.saveTourImage(created, image);
        return "redirect:/guide/tours?success";
    }

    @GetMapping("/tours/edit/{id}")
    public String showEditForm(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("guideId") == null) return "redirect:/signin";
        return tourService.getTourById(id).map(tour -> {
            model.addAttribute("tour", tour);
            model.addAttribute("title", "Edit Tour");
            return "guide/tour-form";
        }).orElseGet(() -> {
            model.addAttribute("errorMessage", "Tour not found");
            return "error";
        });
    }

    @PostMapping("/tours/edit/{id}")
    public String updateTour(@PathVariable Long id, HttpSession session, @ModelAttribute Tour tourDetails, MultipartFile image) {
        Long guideId = (Long) session.getAttribute("guideId");
        if (guideId == null) return "redirect:/signin";
        guideService.getGuideById(guideId).ifPresent(tourDetails::setGuide);
        tourDetails.setPublished(true);
        Tour updated = tourService.updateTour(id, tourDetails);
        tourService.saveTourImage(updated, image);
        return "redirect:/guide/tours?success";
    }

    @GetMapping("/tours/delete/{id}")
    public String deleteTour(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("guideId") == null) return "redirect:/signin";
        tourService.deleteTour(id);
        return "redirect:/guide/tours?deleted";
    }

    @GetMapping("/reviews")
    public String reviews(HttpSession session, Model model) {
        Long guideId = (Long) session.getAttribute("guideId");
        if (guideId == null) return "redirect:/signin";
        model.addAttribute("receivedReviews", guideReviewService.getReviewsByGuideId(guideId));
        model.addAttribute("writtenReviews", studentReviewService.getReviewsByReviewerId(guideId));
        model.addAttribute("students", studentService.getAllStudents());
        return "guide/student-reviews";
    }

    @PostMapping("/reviews/new")
    public String submitStudentReview(HttpSession session,
                                      @RequestParam Long studentId,
                                      @RequestParam Integer rating,
                                      @RequestParam(required = false) String comment) {
        Long guideId = (Long) session.getAttribute("guideId");
        if (guideId == null) return "redirect:/signin";
        Guide reviewer = guideService.getGuideById(guideId).orElse(null);
        if (reviewer == null) return "redirect:/signin";
        StudentReview review = new StudentReview();
        review.setReviewer(reviewer);
        Student student = new Student();
        student.setStudentId(studentId);
        review.setStudent(student);
        review.setRating(rating);
        review.setComment(comment);
        studentReviewService.createStudentReview(review);
        return "redirect:/guide/reviews?success";
    }

    @PostMapping("/reviews/{id}/edit")
    public String editStudentReview(@PathVariable Long id, HttpSession session,
                                    @RequestParam Integer rating,
                                    @RequestParam(required = false) String comment) {
        if (session.getAttribute("guideId") == null) return "redirect:/signin";
        StudentReview updates = new StudentReview();
        updates.setRating(rating);
        updates.setComment(comment);
        studentReviewService.updateStudentReview(id, updates);
        return "redirect:/guide/reviews?success";
    }

    @GetMapping("/reviews/delete/{id}")
    public String deleteStudentReview(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("guideId") == null) return "redirect:/signin";
        studentReviewService.deleteStudentReview(id);
        return "redirect:/guide/reviews";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Long guideId = (Long) session.getAttribute("guideId");
        if (guideId == null) return "redirect:/signin";
        Guide guide = guideService.getGuideById(guideId).orElse(null);
        if (guide == null) return "redirect:/signin";
        List<Tour> tours = tourService.getToursByGuideId(guideId);
        model.addAttribute("guide", guide);
        model.addAttribute("tours", tours);
        return "guide/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(HttpSession session,
                                @RequestParam String name,
                                @RequestParam String email,
                                @RequestParam(required = false) String bio,
                                MultipartFile picture) {
        Long guideId = (Long) session.getAttribute("guideId");
        if (guideId == null) return "redirect:/signin";
        Guide updates = new Guide();
        updates.setName(name);
        updates.setEmail(email);
        updates.setBio(bio);
        Guide updated = guideService.updateGuide(guideId, updates);
        guideService.saveProfilePicture(updated, picture);
        return "redirect:/guide/profile?success";
    }

    @PostMapping("/signup")
    public String signup(Guide guide) {
        guide.setRole(com.example.spartanguide.entity.User.UserRole.GUIDE);
        guide.setStatus(com.example.spartanguide.entity.User.UserStatus.ACTIVE);
        guideService.createGuide(guide);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            Guide guide = guideService.getGuideByEmail(email);
            if (guide != null) {
                session.setAttribute("guideId", guide.getGuideId());
                return "redirect:/guide/dashboard";
            }
            return "redirect:/signin?error";
        } catch (Exception e) {
            return "redirect:/signin?error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
