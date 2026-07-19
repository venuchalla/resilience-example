# @LogRequest Annotation Processor Guide

## Overview
The `@LogRequest` annotation processor validates controller methods at compile-time to ensure they follow required patterns.

## Current Implementation

### Annotation Definition
Located in: `src/main/java/com/example/resilience/annotations/LogRequest.java`

```java
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.METHOD)
public @interface LogRequest {
  boolean enforceRequestWrapper() default true;
  boolean enforceResponseWrapper() default true;
  LogLevel logParameters() default LogLevel.FULL;
  LogLevel logResponse() default LogLevel.NONE;

  enum LogLevel {
    NONE, FULL, BRIEF
  }
}
```

### Processor
Located in: `src/main/java/com/example/resilience/annotations/LogRequestProcessor.java`

The processor runs a validation chain with multiple validators.

### Validators

1. **RestControllerValidator** - Ensures method is in a @RestController
2. **MappingValidator** - Ensures method has mapping annotation (@GetMapping, @PostMapping, etc.)
3. **RequestBodyValidator** - Ensures method has @RequestBody parameter
4. **RequestWrapperValidator** - Checks @RequestBody extends BaseRequest (when enforceRequestWrapper=true)
5. **ResponseWrapperValidator** - Checks return type is ResponseWrapper (when enforceResponseWrapper=true)
6. **ResponseEntityValidator** - Checks return type is ResponseEntity
7. **ValidationAnnotationValidator** - Additional validation rules

## Usage Example

### ✅ CORRECT Usage
```java
@RestController
@RequestMapping("/api/test")
public class TestController {

  @PostMapping("/process")
  @LogRequest
  public ResponseEntity<ResponseWrapper<String>> processRequest(
      @RequestBody RequestWrapper<InputDTO, Void> request) {
    // Implementation
    return ResponseEntity.ok(new ResponseWrapper<>("id", "api", "v1", 200, "result"));
  }
}
```

### ❌ INCORRECT Usages

These will cause compilation errors:

```java
// Missing @RequestBody parameter
@PostMapping("/bad1")
@LogRequest
public ResponseEntity<ResponseWrapper<String>> badMethod1() { }

// Wrong request type
@PostMapping("/bad2")
@LogRequest
public ResponseEntity<ResponseWrapper<String>> badMethod2(
    @RequestBody String request) { }

// Wrong response type
@PostMapping("/bad3")
@LogRequest
public ResponseEntity<String> badMethod3(
    @RequestBody RequestWrapper<InputDTO, Void> request) { }
```

## Compilation Process

The pom.xml is configured with a two-phase compilation:

1. **Phase 1** (`compile-processors`): Compiles the processor and validators without annotation processing
2. **Phase 2** (`default-compile`): Compiles all source files with annotation processing enabled

This ensures the processor is available during compilation.

## Troubleshooting

### Processor Not Triggering
- Verify META-INF/services file exists: `src/main/resources/META-INF/services/javax.annotation.processing.Processor`
- Verify it contains: `com.example.resilience.annotations.LogRequestProcessor`
- Run `mvn clean compile` (not incremental compile)

### Compile Errors Not Showing
- Check that ValidatorContext properly calls `error()` method
- Verify annotation targets @LogRequest methods correctly
- Check processor phase ordering in pom.xml

## Customization

To modify validation rules, update validators in:
`src/main/java/com/example/resilience/annotations/validators/`

To skip specific validations:
```java
@LogRequest(
    enforceRequestWrapper = false,  // Skip RequestWrapper validation
    enforceResponseWrapper = true   // Check ResponseWrapper
)
public ResponseEntity<ResponseWrapper<String>> myMethod(...) { }
```
