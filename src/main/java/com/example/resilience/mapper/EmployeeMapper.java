package com.example.resilience.mapper;

import com.example.resilience.dto.EmployeeDTO;
import com.example.resilience.entities.Employee;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class EmployeeMapper {

  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  //  @Mapping(source = "id",target = "employeeId")
  @Mapping(source = "email", target = "email")
  public abstract Employee toEntity(EmployeeDTO employeeDTO);

  public abstract List<Employee> toEntities(List<EmployeeDTO> employeeDTOList);

  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "employeeId", target = "id")
  @Mapping(source = "email", target = "email")
  public abstract EmployeeDTO toDto(Employee e);

  public abstract List<EmployeeDTO> toDtos(List<Employee> e);
}
