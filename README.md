# Cardio B — Health & Fitness App (CSI 3471)

A Java Swing–based health & fitness desktop app built for **CSI 3471**. The project uses OO design, unit testing with JUnit, and developer documentation via JavaDoc. Work is planned and tracked in GitHub Issues.

## Tech Stack
- **Language:** Java (Swing GUI)
- **Testing:** JUnit 5
- **Docs:** JavaDoc
- **IDE:** IntelliJ IDEA recommended (project contains `.idea/` metadata)
- **Issue tracking:** GitHub Issues

## Project Structure
```
cardioB_3471/
├─ demodemoDemo/            # Main application source (Swing UI, controllers, models)
├─ docs/                    # JavaDoc and project documentation
├─ README.md                # You are here
└─ .idea/                   # IntelliJ project settings
```

## Features
- User onboarding has register, log in / log out, and password reset flows.
- Activity & class management has search classes, view details, and join/leave.
- Trainer has class live hosting where users can join live and participate in workout flows in real time.
- Robust error handling, controller refactors, and test coverage.

## Running Tests
- In IntelliJ: *Right‑click → Run Tests* on the `test` source set or individual classes.
- Command line:
  - **Maven:** `mvn -q -Dtest=*Test test`
  - **Gradle:** `./gradlew test`

## Documentation (JavaDoc)
- Ensured source comments use proper JavaDoc tags.
- Generated docs and output to `/docs` directory.



