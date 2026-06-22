# Errors & Lessons Learned

This project was not just about building a Hall Ticket Generator. It was also a valuable debugging journey. Below are the major issues encountered and the lessons learned while developing the application.

---

## 1. AppDatabase_Impl Does Not Exist

### Error

```text
Cannot find implementation for AppDatabase
AppDatabase_Impl does not exist
```

### Cause

Room generates `AppDatabase_Impl` during compilation. The Room compiler was not running because the KSP and Room configuration was incorrect.

### Fix

* Added KSP plugin
* Added Room Compiler dependency
* Fixed Room configuration

### Lesson

Room does not generate database implementation classes automatically unless the Room compiler runs successfully.

---

## 2. Mixing Room Versions

### Error

Unexpected Room-related compilation errors.

### Cause

The project was using both:

```kotlin
androidx.room3.*
```

and

```kotlin
androidx.room.*
```

at the same time.

### Fix

Used only:

```kotlin
androidx.room.*
```

### Lesson

Never mix different library generations or versions within the same project.

---

## 3. KSP JVM Signature Error

### Error

```text
unexpected jvm signature V
```

### Cause

Version incompatibility between Kotlin, Room, and KSP.

### Fix

* Updated dependencies
* Removed conflicting configurations

### Lesson

Many build failures are caused by tooling incompatibilities rather than application code.

---

## 4. MainActivity Not Found

### Error

```text
ClassNotFoundException: MainActivity
```

### Cause

The Manifest and generated APK became out of sync after multiple project changes.

### Fix

* Clean Project
* Rebuild Project
* Reinstall Application

### Lesson

Sometimes the APK is stale. Cleaning and rebuilding the project can resolve unexpected runtime issues.

---

## 5. TODO() Crash

### Error

```kotlin
examId = TODO()
```

### Cause

`TODO()` intentionally throws a `NotImplementedError`.

### Fix

Replaced it with a valid value:

```kotlin
val examId = 0
```

### Lesson

Never leave `TODO()` statements inside code paths that execute at runtime.

---

## 6. No Such Table Error

### Error

```text
no such table: HallTicket
```

### Cause

The `HallTicket` entity was accidentally removed from the Room database configuration.

### Fix

```kotlin
@Database(
    entities = [
        Student::class,
        Exam::class,
        HallTicket::class
    ]
)
```

### Lesson

Every entity must be registered inside the Room database definition.

---

## 7. Missing Comma

### Error

Compilation failure due to syntax issue.

### Cause

Forgot a comma between entity declarations.

### Fix

```kotlin
Student::class,
Exam::class,
```

### Lesson

Sometimes the bug is a single character.

---

## 8. Empty UI Screen

### Error

Application launched successfully but displayed nothing.

### Cause

`setContent {}` was empty.

### Fix

Created and displayed:

```kotlin
HallTicketScreen()
```

### Lesson

A working backend is useless if nothing is rendered on the screen.

---

## 9. Repository Returning Wrong Model

### Error

Repository returned:

```kotlin
HallTicket
```

but UI required:

```kotlin
HallTicketDetails
```

### Cause

Database models did not contain all information required by the UI.

### Fix

Created:

```kotlin
data class HallTicketDetails(...)
```

### Lesson

Database models and UI models often serve different purposes and should not always be the same object.

---

## 10. ClassCastException

### Error

```text
HallTicket cannot be cast to HallTicketDetails
```

### Cause

Attempted to cast one object type into another:

```kotlin
return existing as HallTicketDetails
```

### Fix

Created a new object:

```kotlin
return HallTicketDetails(...)
```

### Lesson

Casting does not convert objects. It only tells the compiler how to treat an object that is already of that type.

---

## 11. Duplicate Repository Call

### Error

```kotlin
val ticket = repository.generateTicket()

hallTicket = repository.generateTicket()
```

### Cause

The same operation was executed twice.

### Fix

```kotlin
hallTicket = repository.generateTicket()
```

### Lesson

Avoid unnecessary duplicate operations, especially when they involve database or network calls.

---

# Biggest Takeaways

## Room Architecture

```text
Entity → DAO → Database → Repository
```

---

## MVVM Architecture

```text
UI
 ↓
ViewModel
 ↓
Repository
 ↓
Room Database
```

---

## Debugging Mindset

```text
Read the first real error.

Do not fix random things.

Find the root cause.
```

---

## Architecture Principle

```text
Entity ≠ UI Model
```

A database object and a UI object often have different responsibilities and should be designed separately.

---

# Final Reflection

This project taught me more than just Android development. It taught me how to investigate errors, understand library configurations, work with Room Database, follow MVVM architecture, and think like a software developer when debugging real-world problems.

Every error solved became a lesson that improved my understanding of Android development.
