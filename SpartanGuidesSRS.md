# Requirements – Starter Template

**Project Name:** Spartan Guide \
**Team:** Dylan York - Customer; Taif Shaker - Provider  \
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-02-13

---

## 1. Overview
**Vision.**
SpartanGuide is the ultimate adventure companion for UNCG students ready to explore beyond campus. Built by Spartans, for Spartans, the app connects students with experienced guides who lead safe and exciting adventures, especially the kind that are best experienced with someone who knows the way. Whether you’re navigating hidden caves, hiking scenic trails, snorkeling crystal-clear waters, carving fresh powder on skis, or tackling challenging mountain climbs, SpartanGuide makes it easy to find the right guide for the journey SpartanGuide isn’t just about travel, it’s about community, discovery, and stepping outside your comfort zone with people you can trust.

**Glossary** Terms used in the project
- Premium Pass: The subscription tier that unlocks "extra peaks," such as ad-free content, offline downloads, ect.
- Two-Way Review: A feedback system where customers rate the tour experience and guides can provide feedback on customer.

**Primary Users / Roles.**
- **Customer (Students/Users of the app)** Plans vistes with tour guides in the local area.  
- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — 1 line goal statement.


**Scope (this semester).**
- Finds local tour guides for customers.
- Premium Pass for the app to get extra peaks; manage subscription.
- A review service for both the customer and producer. 

**Out of scope (deferred).**
- Having a refund system for users.
- AI-based medical or health diagnostics for travelers


### 2.1 Customer Stories
- **US‑Cust‑001 — Public Restroom Locator**  
  Story: As a traveler in a new city, I want to find the nearest clean public restroom so that I can use the restroom quickly.
  Acceptance:

  Scenario: Searching for restrooms
    Given: I am on the main map
    When: I tap the "Restroom" icon
    Then: the app displays a list of the 5 closest toilets with current user ratings.
  

- **US‑Cust‑002 — Offline Map Access Story**  
  Story: As a tourist, I want to download city guides and maps so that I can navigate and find information even without an internet connection.
  Acceptance:

  Scenario: Accessing downloaded content
    Given: I have previously downloaded the app wanted a map
    When:  I lose cellular data or enter Airplane Mode
    Then:  I can still view the map and read text-based guides.
  

### 2.2 Provider Stories
- **US‑PROV‑001 — Create & Publish Tour**  
  _Story:_ As a provider, I want to create and publish a tour with details so that Spartans can view and register for my adventure.
  _Acceptance:_
  ```gherkin
  Scenario: Provider creates and publishes a new tour.
    Given the provider is logged into SpartanGuide.
    When the provider enters valid tour details.
    Then the tour appears in the student tour listings.
  ```

- **US‑PROV‑002 — Manage Student Registrations**  
  _Story:_ As a provider, I want to view and manage student registrations for my tour so that I can track attendance.
  _Acceptance:_
  ```gherkin
  Scenario: Provider views and manages registrations.
    Given the provider has at least one published tour.
    When the provider navigates to the "My Tours" page and selects a specific tour.
    Then the provider can see a list of registered students.
  ```

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** The map and nearby spots must load in under 2 seconds.
- **Availability/Reliability:** Having a 95% ≥ monthly up time.
- **Security/Privacy:** GPS data needs to only be availablily to the current user.
- **Usability:** Users should be able to start any tour in 3 taps or less.

---

## 4. Assumptions, Constraints, and Policies
- Users have a smartphone with a working camera and GPS.
- Works on modern iPhones and Androids (last 3–4 years).
- Users must agree to watch where they are walking while using the app.
- No bullying or "bad" language allowed in tour reviews.

---

## 5. Milestones (course‑aligned)
- **M2 Requirements** — this file + stories opened as issues. 
- **M3 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M4 Design** — architecture, schema, API outline. 
- **M5 Backend API** — key endpoints + tests. 
- **M6 Increment** — ≥2 use cases end‑to‑end. 
- **M7 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.