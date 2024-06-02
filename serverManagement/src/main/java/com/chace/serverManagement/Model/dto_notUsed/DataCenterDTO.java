package com.chace.serverManagement.Model.dto_notUsed;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // adds @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor annots in the class
@NoArgsConstructor // helps autoInsert NoArgsConstructor
@AllArgsConstructor // helps autoInsert AllArgsConstructor
public class DataCenterDTO {

  /* JAKARTA BEAN VALIDATION :: see all the validation annotations for controllers here
  * https://jakartaee.github.io/jakartaee-documentation/jakartaee-tutorial/current/beanvalidation/bean-validation/bean-validation.html */

  @Column(unique = true)  // creates unique Constraint on this ipAddress Field
  @NotEmpty(message = "datacenter name can't be empty or null")   // a request MUST have an IP Address otherwise an exception will be thrown w/ the message
  private String name;

  @AssertTrue(message = "datacenter must be in use to be created")
  private boolean isInUse;

  private String description;
  private ServerDTO server;
  private Long serverId;

}


/*
*
* Constraint	Description	Example
@AssertFalse

The value of the field or property must be false.

@AssertFalse
boolean isUnsupported;
@AssertTrue

The value of the field or property must be true.

@AssertTrue
boolean isActive;
@DecimalMax

The value of the field or property must be a decimal value lower than or equal to the number in the value element.

@DecimalMax("30.00")
BigDecimal discount;
@DecimalMin

The value of the field or property must be a decimal value greater than or equal to the number in the value element.

@DecimalMin("5.00")
BigDecimal discount;
@Digits

The value of the field or property must be a number within a specified range. The integer element specifies the maximum integral digits for the number, and the fraction element specifies the maximum fractional digits for the number.

@Digits(integer=6, fraction=2)
BigDecimal price;
@Email

The value of the field or property must be a valid email address.

@Email
String emailaddress;
@Future

The value of the field or property must be a date in the future.

@Future
Date eventDate;
@FutureOrPresent

TThe value of the field or property must be a date or time in present or future.

@FutureOrPresent
Time travelTime;
@Max

The value of the field or property must be an integer value lower than or equal to the number in the value element.

@Max(10)
int quantity;
@Min

The value of the field or property must be an integer value greater than or equal to the number in the value element.

@Min(5)
int quantity;
@Negative

The value of the field or property must be a negative number.

@Negative
int basementFloor;
@NegativeOrZero

The value of the field or property must be negative or zero.

@NegativeOrZero
int debtValue;
@NotBlank

The value of the field or property must contain atleast one non-white space character.

@NotBlank
String message;
@NotEmpty

The value of the field or property must not be empty. The length of the characters or array, and the size of a collection or map are evaluated.

@NotEmpty
String message;;
@NotNull

The value of the field or property must not be null.

@NotNull
String username;
@Null

The value of the field or property must be null.

@Null
String unusedString;
@Past

The value of the field or property must be a date in the past.

@Past
Date birthday;
@PastOrPresent

The value of the field or property must be a date or time in the past or present.

@PastOrPresent
Date travelDate;
@Pattern

The value of the field or property must match the regular expression defined in the regexp element.

@Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
String phoneNumber;
@Positive

The value of the field or property must be a positive number.

@Positive
BigDecimal area;
@PositiveOrZero

The value of the field or property must be a positive number or zero.

@PositiveOrZero
int totalGoals;
@Size

The size of the field or property is evaluated and must match the specified boundaries. If the field or property is a String, the size of the string is evaluated. If the field or property is a Collection, the size of the Collection is evaluated. If the field or property is a Map, the size of the Map is evaluated. If the field or property is an array, the size of the array is evaluated. Use one of the optional max or min elements to specify the boundaries.

@Size(min=2, max=240)
String briefMessag
*
*
* */
