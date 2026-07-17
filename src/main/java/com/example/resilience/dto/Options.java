package com.example.resilience.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Schema(name = "Options")
public class Options {

  List<Option> options;
}
