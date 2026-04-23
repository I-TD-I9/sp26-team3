package com.example.spartanguide.mvc;

import com.example.spartanguide.entity.Booking;
import com.example.spartanguide.entity.Guide;
import com.example.spartanguide.entity.GuideReview;
import com.example.spartanguide.entity.Student;
import com.example.spartanguide.entity.Subscription;
import com.example.spartanguide.entity.Tour;
import com.example.spartanguide.service.BookingService;
import com.example.spartanguide.service.GuideReviewService;
import com.example.spartanguide.service.GuideService;
import com.example.spartanguide.service.StudentService;
import com.example.spartanguide.service.SubscriptionService;
import com.example.spartanguide.service.TourService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentUiController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TourService tourService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private GuideReviewService guideReviewService;

    @Autowired
    private GuideService guideService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        Student student = studentService.getStudentById(studentId).orElse(null);
        if (student == null) return "redirect:/signin";
        model.addAttribute("currentStudent", student);
        model.addAttribute("subscriptions", subscriptionService.getSubscriptionsByStudentId(studentId));
        return "student/dashboard";
    }

    @GetMapping("/tours")
    public String browseTours(HttpSession session, Model model) {
        if (session.getAttribute("studentId") == null) return "redirect:/signin";
        model.addAttribute("tours", tourService.getAllTours());
        return "student/browse-tours";
    }

    @GetMapping("/tours/{id}")
    public String tourDetail(@PathVariable Long id, HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        return tourService.getTourById(id).map(tour -> {
            model.addAttribute("tour", tour);
            return "student/tour-detail";
        }).orElseGet(() -> {
            model.addAttribute("errorMessage", "Tour not found");
            return "error";
        });
    }

    @PostMapping("/tours/{id}/book")
    public String bookTour(@PathVariable Long id, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        Student student = studentService.getStudentById(studentId).orElse(null);
        if (student == null) return "redirect:/signin";
        Booking booking = new Booking();
        booking.setStudent(student);
        Tour tour = new Tour();
        tour.setTourId(id);
        booking.setTour(tour);
        bookingService.createBooking(booking);
        return "redirect:/student/tours/" + id + "?booked";
    }

    @GetMapping("/subscriptions")
    public String subscriptions(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        model.addAttribute("subscriptions", subscriptionService.getSubscriptionsByStudentId(studentId));
        return "student/subscriptions";
    }

    @PostMapping("/subscriptions/new")
    public String createSubscription(HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        Subscription subscription = new Subscription();
        Student student = new Student();
        student.setStudentId(studentId);
        subscription.setStudents(List.of(student));
        subscription.setStatus(Subscription.SubscriptionStatus.ACTIVE);
        subscription.setStartDate(LocalDate.now());
        subscription.setAutoRenew(true);
        subscriptionService.createSubscription(subscription);
        return "redirect:/student/subscriptions?success";
    }

    @GetMapping("/subscriptions/delete/{id}")
    public String deleteSubscription(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("studentId") == null) return "redirect:/signin";
        subscriptionService.deleteSubscription(id);
        return "redirect:/student/subscriptions";
    }

    @GetMapping("/reviews")
    public String reviews(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        model.addAttribute("reviews", guideReviewService.getReviewsByReviewerId(studentId));
        model.addAttribute("guides", guideService.getAllGuides());
        return "student/guide-reviews";
    }

    @PostMapping("/reviews/new")
    public String submitReview(HttpSession session,
                               @RequestParam Long guideId,
                               @RequestParam Integer rating,
                               @RequestParam(required = false) String comment) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        Student reviewer = studentService.getStudentById(studentId).orElse(null);
        if (reviewer == null) return "redirect:/signin";
        GuideReview review = new GuideReview();
        Guide guide = new Guide();
        guide.setGuideId(guideId);
        review.setGuide(guide);
        review.setReviewer(reviewer);
        review.setRating(rating);
        review.setComment(comment);
        guideReviewService.createGuideReview(review);
        return "redirect:/student/reviews?success";
    }

    @PostMapping("/reviews/{id}/edit")
    public String editReview(@PathVariable Long id, HttpSession session,
                             @RequestParam Integer rating,
                             @RequestParam(required = false) String comment) {
        if (session.getAttribute("studentId") == null) return "redirect:/signin";
        GuideReview updates = new GuideReview();
        updates.setRating(rating);
        updates.setComment(comment);
        guideReviewService.updateGuideReview(id, updates);
        return "redirect:/student/reviews?success";
    }

    @GetMapping("/reviews/delete/{id}")
    public String deleteReview(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("studentId") == null) return "redirect:/signin";
        guideReviewService.deleteGuideReview(id);
        return "redirect:/student/reviews";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        Student student = studentService.getStudentById(studentId).orElse(null);
        if (student == null) return "redirect:/signin";
        model.addAttribute("student", student);
        return "student/profile";
    }

    @PostMapping("/profile")
    public String updateProfile(HttpSession session,
                                @RequestParam String name,
                                @RequestParam String email,
                                @RequestParam(required = false) String major,
                                MultipartFile picture) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (studentId == null) return "redirect:/signin";
        Student updates = new Student();
        updates.setName(name);
        updates.setEmail(email);
        updates.setMajor(major);
        Student updated = studentService.updateStudent(studentId, updates);
        studentService.saveProfilePicture(updated, picture);
        return "redirect:/student/profile?success";
    }

    @PostMapping("/signup")
    public String signup(Student student) {
        student.setRole(com.example.spartanguide.entity.User.UserRole.STUDENT);
        student.setStatus(com.example.spartanguide.entity.User.UserStatus.ACTIVE);
        studentService.createStudent(student);
        return "redirect:/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, HttpSession session) {
        try {
            Student student = studentService.getStudentByEmail(email);
            if (student != null) {
                session.setAttribute("studentId", student.getStudentId());
                return "redirect:/student/dashboard";
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
